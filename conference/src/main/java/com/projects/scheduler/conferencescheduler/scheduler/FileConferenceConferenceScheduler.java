package com.projects.scheduler.conferencescheduler.scheduler;

import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;
import com.projects.scheduler.conferencescheduler.fileReader.FileReader;
import com.projects.scheduler.conferencescheduler.fileReader.TextFileReader;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import com.projects.scheduler.conferencescheduler.util.SchedulerImpl;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class FileConferenceConferenceScheduler SCHEDULE conference by reading data from TXT file.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public final class FileConferenceConferenceScheduler implements ConferenceScheduler {
    private static final Logger logger = ConferenceLogger.getLogger(FileConferenceConferenceScheduler.class);

    /**
     * schedule conference by reading text file
     *
     * @param fullyQualifiedName
     * @return
     */
    public Conference schedule(String fullyQualifiedName, LocalDateTime localDateTime) {
        FileReader fileReader;
        List<Event> events = null;
        fileReader = new TextFileReader(fullyQualifiedName);
        events = fileReader.read();

        if (events.size() == 0) {
            throw new ConferenceManagementBusinessException("File is empty");
        }
        return SchedulerImpl.getInstance().schedule(events, localDateTime);
    }
}
