package com.projects.scheduler.conferencescheduler.slot;

import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import org.apache.log4j.Logger;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * class Slot decide based on the starttime and duration.
 * The slot here is like morning, evening, Lunch and Networking
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class Slot {
    private static final Logger logger = ConferenceLogger.getLogger(Slot.class);
    LocalDateTime startTime;
    Duration duration;
    private List<Event> events = new ArrayList<Event>();

    public Slot(LocalDateTime startTime, Duration duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public List<Event> getEvents() {
        return events;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }


    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adding event to slot
     * update event start time
     * Update duration left
     *
     * @param event
     */
    public void addEvent(Event event) {
       if (duration.toMinutes() < event.getEventDuration().toMinutes()) {
            throw new ConferenceManagementBusinessException("Not enough room in this slot to fit the event: '"
                    + event.getEventName() + "'");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        logger.debug(startTime.format(formatter) +" "+ event.getEventName()+" "+ event.getEventDuration().toMinutes()+"min");
        events.add(event);
        startTime = startTime.plusMinutes(event.getEventDuration().toMinutes());
        duration=duration.minusMinutes(event.getEventDuration().toMinutes());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Slot{" +
                "startTime=" + startTime +
                ", duration=" + duration +
                ", events=" + events +
                '}';
    }
}
