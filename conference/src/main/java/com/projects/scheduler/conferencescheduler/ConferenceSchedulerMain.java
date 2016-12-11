package com.projects.scheduler.conferencescheduler;

import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.config.ConfigConstant;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;
import com.projects.scheduler.conferencescheduler.facade.ConferenceSchedulerFacade;
import com.projects.scheduler.conferencescheduler.util.ConferenceLogger;
import org.apache.log4j.Logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The ConferenceSchedulerMain acts like client to text the conference scheduler
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class ConferenceSchedulerMain {
    private static int SIZE_OF_ARGUMENT = 1;
    private static String DATE_FORMATE_PATTERN = "yyyy-MM-dd-HH:mm";
    private static final Logger logger = ConferenceLogger.getLogger(ConferenceSchedulerMain.class);
    private static String inputFile;
    private static String inputDate;
    private static ConferenceSchedulerMain conferenceSchedulerMain = new ConferenceSchedulerMain();
    private static ConferenceSchedulerFacade facade = new ConferenceSchedulerFacade();
    public static void main(String[] argument) {

        ConferenceSchedulerMain conferenceSchedulerMain = new ConferenceSchedulerMain();

        if (argument.length < SIZE_OF_ARGUMENT) {
            throw new ConferenceManagementBusinessException("Please enter the Fully qualified path and Date of conference in yyyy-MM-dd-HH:mm format");
        }
        conferenceSchedulerMain.inputFile = argument[0];
        conferenceSchedulerMain.inputDate = argument[1];

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(ConferenceSchedulerMain :: execute);
        executorService.execute(ConferenceSchedulerMain ::execute);

        executorService.shutdown();


    }

    private static void execute(){
        //ConferenceSchedulerMain conferenceSchedulerMain = new ConferenceSchedulerMain();
        LocalDateTime dateTime;

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATE_PATTERN);
            dateTime = LocalDateTime.parse(inputDate, formatter);
        }catch (Exception e){
            throw new ConferenceManagementBusinessException("Please enter Date of conference in yyyy-MM-dd-HH:mm format only");
        }
        conferenceSchedulerMain.invokeFacade(inputFile, dateTime);
    }

    public void invokeFacade(String filePath, LocalDateTime dateTime) {
        String fullyQualifiedFileName = filePath;
        Conference conference = null;
        try {
//            ConferenceSchedulerFacade facade = new ConferenceSchedulerFacade();
            if (filePath.contains(ConfigConstant.TXT_FILE.getName())) {
                conference = facade.scheduleConferenceWithFileScheduler(fullyQualifiedFileName, dateTime);
            } else if (filePath.contains(ConfigConstant.XML_FILE.getName())) {
                conference = facade.scheduleConferenceWithXmlScheduler(fullyQualifiedFileName, dateTime);
            } else if (filePath.contains(ConfigConstant.JSON_FILE.getName())) {
                conference = facade.scheduleConferenceWithJsonScheduler(null, dateTime);
            } else {
                logger.debug("File Not Supported Error. Application do not support file type other than text, xml and Json.",
                        new ConferenceManagementBusinessException("File Not Supported Error. Application do not support file type other than text, xml and Json."));
            }
            System.out.println(conference);
            logger.debug(conference);
        } catch (ConferenceManagementBusinessException businessException) {
            logger.debug(businessException);
            System.exit(1);
        }
    }
}

