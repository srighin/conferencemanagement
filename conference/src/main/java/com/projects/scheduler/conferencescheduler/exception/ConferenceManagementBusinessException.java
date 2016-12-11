package com.projects.scheduler.conferencescheduler.exception;

/**
 * ConferenceManagementBusinessException is a wrapper exception class.
 * Defined to catch any user exception
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class ConferenceManagementBusinessException extends RuntimeException{

    public ConferenceManagementBusinessException() {
    }

    public ConferenceManagementBusinessException(String message) {
        super(message);
    }

    public ConferenceManagementBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConferenceManagementBusinessException(Throwable cause) {
        super(cause);
    }

    public ConferenceManagementBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
