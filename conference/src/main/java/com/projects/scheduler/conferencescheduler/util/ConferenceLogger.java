package com.projects.scheduler.conferencescheduler.util;


import org.apache.log4j.Logger;

/**
 * The ConferenceLogger is custom logger class to log details for debugging.
 * slots holds events
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class ConferenceLogger extends Logger {

    protected ConferenceLogger(String name) {
        super(name);
    }

    @Override
    public void error(Object message) {
        message = "test - " + message;

        super.error(message);
    }

    @Override
    public void debug(Object message) {
        super.debug(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        super.debug(message, t);
    }

}