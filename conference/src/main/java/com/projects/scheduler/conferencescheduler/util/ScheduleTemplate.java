package com.projects.scheduler.conferencescheduler.util;


import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.slot.Slot;
import com.projects.scheduler.conferencescheduler.track.Track;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by SG0952876 on 12/9/2016. All test
 */
public abstract class ScheduleTemplate {
    public List<Event> events;
    public LocalDateTime localDateTime;
    public Conference conference;
    public Slot slot;
    public Track track;

    abstract void scheduleMorningSlot();

    abstract void scheduleLunchSlot();

    abstract void scheduleAfternoonSlot();

    abstract void scheduleNetworkingSlot();



    public final Conference schedule(List<Event> events, LocalDateTime localDateTime) {
        scheduleMorningSlot();
        scheduleLunchSlot();
        scheduleAfternoonSlot();
        scheduleNetworkingSlot();
        return conference;
    }
}
