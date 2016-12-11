package com.projects.scheduler.conferencescheduler.scheduler;

import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.fileReader.FileReader;
import com.projects.scheduler.conferencescheduler.fileReader.XmlFileReader;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import com.projects.scheduler.conferencescheduler.util.SchedulerImpl;
import org.apache.log4j.Logger;


import java.time.LocalDateTime;
import java.util.List;

/**
 * class XMLConferenceConferenceScheduler SCHEDULE conference by reading data from xml file.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public final class XMLConferenceConferenceScheduler implements ConferenceScheduler {

    private static final Logger logger = ConferenceLogger.getLogger(XMLConferenceConferenceScheduler.class);

    /**
     * schedule conference by reading xml file
     * @param fullyQualifiedName
     * @return
     */
    public Conference schedule(String fullyQualifiedName, LocalDateTime localDateTime) {
        logger.debug( "beginning of schedule of "+ Conference.class);
        FileReader fileReader;
        List<Event> events = null;
        fileReader = new XmlFileReader(fullyQualifiedName);
        events = fileReader.read();
        logger.debug( "End of schedule of "+ Conference.class);
        return SchedulerImpl.getInstance().schedule(events, localDateTime);
    }


}
