package com.projects.scheduler.conferencescheduler.exception;

/**
 * ConferenceManagementSystemException is a wrapper exception class.
 * Defined to catch any system exceptions
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class ConferenceManagementSystemException extends RuntimeException {
    public ConferenceManagementSystemException() {
    }

    public ConferenceManagementSystemException(String message) {
        super(message);
    }

    public ConferenceManagementSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConferenceManagementSystemException(Throwable cause) {
        super(cause);
    }

    public ConferenceManagementSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
