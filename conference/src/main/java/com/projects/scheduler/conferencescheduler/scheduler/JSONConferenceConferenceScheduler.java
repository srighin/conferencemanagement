package com.projects.scheduler.conferencescheduler.scheduler;

import com.projects.scheduler.conferencescheduler.ConferenceSchedulerMain;
import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import com.projects.scheduler.conferencescheduler.util.SchedulerImpl;
import org.apache.log4j.Logger;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * class JSONConferenceConferenceScheduler SCHEDULE conference by reading data from JSON file.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public final class JSONConferenceConferenceScheduler implements ConferenceScheduler {

    private static final Logger logger = ConferenceLogger.getLogger(ConferenceSchedulerMain.class);

    /**
     * schedule conference by reading JSON file
     * @param fullyQualifiedName
     * @return
     */
    public Conference schedule(String fullyQualifiedName, LocalDateTime localDateTime) {

        List<Event> events = this.createEvents(fullyQualifiedName);
        return SchedulerImpl.getInstance().schedule(events, localDateTime);
    }

    /**
     * createEvents creates event by reading data file
     *
     * @param fullyQualifiedName
     * @return
     */
    private List<Event> createEvents(String fullyQualifiedName) {
        return new ArrayList<Event>();
    }
}
