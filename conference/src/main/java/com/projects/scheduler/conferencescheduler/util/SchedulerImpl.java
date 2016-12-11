package com.projects.scheduler.conferencescheduler.util;

import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.config.Constant;
import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.slot.Slot;
import com.projects.scheduler.conferencescheduler.track.Track;
import org.apache.log4j.Logger;
import static com.projects.scheduler.conferencescheduler.config.Constant.LUNCH_SLOT_DURATION;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;


/**
 * The Scheduler is responsible for evaluating the events received from the user in the form of data file.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class SchedulerImpl {
    /* Holder for logger */
    private static final Logger logger = ConferenceLogger.getLogger(SchedulerImpl.class);
    private List<Event> events = null;
    private Slot slot;

    private static void addLightning(Slot slot, Duration duration) {
        slot.addEvent(new Event.Builder().eventName(Constant.TECH_TALK).eventDuration(duration).build());
    }

    public static SchedulerImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    private SchedulerImpl() {
    }

    private static class LazyHolder {
        private static final SchedulerImpl INSTANCE = new SchedulerImpl();
    }

    /**
     * The schedule will optimise the events into slots and Track and finally return conference
     *
     * @param localEvents
     * @return
     */

    public synchronized Conference schedule(List<Event> localEvents, LocalDateTime localDateTime) {
        Conference conference = new Conference();
        events = localEvents;
        int trackNumber = 0;
        Slot slot;
        while (events.size() != 0) {
            logger.debug("******************************Track Number****************************" + ++trackNumber);
            Track track = new Track();
            /* localDateTime will hold the date to schedule conference*/
            slot = scheduleEvent(localDateTime, events, track, true);

            /* Adding Lunch event to conference*/
            Slot lunchSlot = new Slot(slot.getStartTime(), Duration.ofMinutes(LUNCH_SLOT_DURATION));
           /* slot.setStartTime(slot.getStartTime());
            slot.setDuration(Duration.ofMinutes(LUNCH_SLOT_DURATION));*/
            lunchSlot.addEvent(new Event.Builder().eventName("Lunch").eventDuration(Duration.ofMinutes(LUNCH_SLOT_DURATION)).build());
            track.addSlot(lunchSlot);

            /* Adding Afternoon event to conference*/
            slot = scheduleEvent(lunchSlot.getStartTime(), events, track, false);

            /* Adding Network event to conference*/
            Slot networkingSlot = new Slot(slot.getStartTime(), Duration.ofMinutes(Constant.NETWORKING_EVENT_DURATION));
            networkingSlot.addEvent(new Event.Builder().eventName(Constant.NETWORKING_EVENT_NAME).eventDuration(Duration.ofMinutes(Constant.NETWORKING_EVENT_DURATION)).build());
            track.addSlot(networkingSlot);

            conference.addTrack(track);
            logger.debug("****************************** End of Track ****************************");
        }
        return conference;
    }

    private Slot scheduleEvent(LocalDateTime localDateTime, List<Event> events, Track track, boolean isMorningEvent) {
        LocalDateTime lunchStartDateTime;
        if (isMorningEvent) {

            lunchStartDateTime = localDateTime.toLocalDate().atTime(LocalTime.NOON);
        } else {
            lunchStartDateTime = localDateTime.toLocalDate().atTime(LocalTime.MIDNIGHT).plusHours(18);
        }

        Duration morningSlotDuration = Duration.between(localDateTime, lunchStartDateTime);
        slot = new Slot(localDateTime, morningSlotDuration);
        this.events = addEventsToSlot(slot, events);
        track.addSlot(slot);

        /* Adding Buffer before lunch if required to conference*/
        LocalDateTime morningSlotEndDateTime = slot.getStartTime();
        Slot morningBufferSlot = null;
        if (morningSlotEndDateTime.isBefore(lunchStartDateTime)) {
            Duration diff = Duration.between(morningSlotEndDateTime, lunchStartDateTime);
            morningBufferSlot = new Slot(morningSlotEndDateTime, diff);
            addLightning(morningBufferSlot, diff);
            track.addSlot(morningBufferSlot);
            return morningBufferSlot;
        } else {
            return slot;
        }
    }

    /**
     * addEventsToSlot adding events to slot
     *
     * @param slot
     * @param events
     */
    private List<Event> addEventsToSlot(Slot slot, List<Event> events) {

        return events.stream()
                .filter(
                        event -> {
                            if (event != null && slot.getDuration().toMinutes() >= event.getEventDuration().toMinutes()) {
                                slot.addEvent(event);
                                return false;
                            } else {//Check if any other event can fit into slot
                                Event eventFitted = addSmallerEventToSlot(slot, events);
                                if (null != eventFitted) {
                                    slot.addEvent(eventFitted);
                                    return false;
                                }
                            }
                            return true;
                        }
                ).collect(Collectors.toList());

    }


    /**
     * addSmallerEventToSlot will check if the some event can be added to slots
     *
     * @param slot
     * @param events
     * @return
     */
    private Event addSmallerEventToSlot(Slot slot, List<Event> events) {
        Optional<Event> eventOptional = events.stream()
                .filter(event -> slot.getDuration().toMinutes() > event.getEventDuration().toMinutes())
                .findFirst();
        try {
            return eventOptional.get();
        } catch (Throwable exception) {
            return createLightningEvents(slot);
        }
    }

    /**
     * createLightningEvents will creates events to be added to remaining portion of slot
     *
     * @param slot
     * @return
     */
    private  Event createLightningEvents(Slot slot) {
        LongPredicate durationOfZeroSize = lengthIOfTime -> lengthIOfTime != 0 ? true : false;
        if (durationOfZeroSize.test(slot.getDuration().toMinutes())) {
            return new Event.Builder().eventName(Constant.TECH_TALK).eventDuration(slot.getDuration()).build();
        }
        return null;
    }

}
