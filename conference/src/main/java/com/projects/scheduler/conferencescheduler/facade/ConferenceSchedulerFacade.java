package com.projects.scheduler.conferencescheduler.facade;

import com.projects.scheduler.conferencescheduler.ConferenceSchedulerMain;
import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.scheduler.ConferenceScheduler;
import com.projects.scheduler.conferencescheduler.scheduler.FileConferenceConferenceScheduler;
import com.projects.scheduler.conferencescheduler.scheduler.JSONConferenceConferenceScheduler;
import com.projects.scheduler.conferencescheduler.scheduler.XMLConferenceConferenceScheduler;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import org.apache.log4j.Logger;


import java.io.File;
import java.time.LocalDateTime;

/**
 * ConferenceSchedulerFacade is design as facade.
 * System complexity is hidden for client application
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class ConferenceSchedulerFacade {

    private ConferenceScheduler fileConferenceScheduler;
    private ConferenceScheduler xmlConferenceScheduler;
    private ConferenceScheduler jsonConferenceScheduler;
    Conference conference = null;
    private static final Logger logger = ConferenceLogger.getLogger(ConferenceSchedulerMain.class);

    /**
     * creating schedulers for separate file extensions
     */
    public ConferenceSchedulerFacade() {
        fileConferenceScheduler = new FileConferenceConferenceScheduler();
        xmlConferenceScheduler = new XMLConferenceConferenceScheduler();

    }

    /**
     * scheduleConferenceWithFileScheduler file as input
     * schedule conference by reading text file
     * @param fullyQualifiedFileName
     * @return
     */
    public Conference scheduleConferenceWithFileScheduler(String fullyQualifiedFileName, LocalDateTime localDateTime) {
//        fileConferenceScheduler = new FileConferenceConferenceScheduler();
        conference = fileConferenceScheduler.schedule(fullyQualifiedFileName, localDateTime);
        return conference;
    }

    /**
     * scheduleConferenceWithXmlScheduler file as input
     * schedule conference by reading xml file
     * @param fullyQualifiedFileName
     * @return
     */
    public Conference scheduleConferenceWithXmlScheduler(String fullyQualifiedFileName, LocalDateTime localDateTime)  {
        xmlConferenceScheduler = new XMLConferenceConferenceScheduler();
        conference = xmlConferenceScheduler.schedule(fullyQualifiedFileName, localDateTime);
        return conference;
    }

    /**
     * scheduleConferenceWithXmlScheduler file as input
     * schedule conference by reading JSON file
     * Code is not completed
     * Demo for application future extension
     *
     * @param input
     * @return
     */
    public Conference scheduleConferenceWithJsonScheduler(File input, LocalDateTime localDateTime) {
        jsonConferenceScheduler = new JSONConferenceConferenceScheduler();
        conference = jsonConferenceScheduler.schedule(null, localDateTime);
        return conference;
    }
}
