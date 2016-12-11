package com.projects.scheduler.conferencescheduler.scheduler;

import com.projects.scheduler.conferencescheduler.conference.Conference;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementSystemException;

import java.time.LocalDateTime;

/**
 * ConferenceScheduler interface
 *
 * @author Sriman Singh
 * @version 1.0
 */
public interface ConferenceScheduler {
    Conference schedule(String fullyQualifiedName, LocalDateTime localDateTime) throws ConferenceManagementBusinessException, ConferenceManagementSystemException;
}
