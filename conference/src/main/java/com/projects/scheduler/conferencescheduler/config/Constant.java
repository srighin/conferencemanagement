package com.projects.scheduler.conferencescheduler.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * The Constant holds the constant.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class Constant {
    private Constant() {
    }
    /* Holds value for INPUT_LINE_PATTERN */
    public static final Pattern INPUT_LINE_PATTERN = Pattern.compile(
            "^(.+)\\s(\\d+)?\\s?((min)|(lightning))$");
    /* Holds value for EVENT_NAME_INDEX */
    public static final int EVENT_NAME_INDEX = 1;
    /* Holds value for EVENT_DURATION_INDEX */
    public static final int EVENT_DURATION_INDEX = 2;
    /* Holds value for MORNING_SLOT_DURATION */
    public static final int MORNING_SLOT_DURATION = 180;
    /* Holds value for LUNCH_SLOT_DURATION */
    public static final int LUNCH_SLOT_DURATION = 60;
    /* Holds value for AFTERNOON_SLOT_DURATION */
    public static final int AFTERNOON_SLOT_DURATION = 240;
    /* Holds value for MAX_EVENT_DURATION */
    public static final int MAX_EVENT_DURATION = Collections.max(Arrays.asList(
            MORNING_SLOT_DURATION, LUNCH_SLOT_DURATION, AFTERNOON_SLOT_DURATION));
    /* Holds value for NETWORKING_EVENT_NAME */
    public static final String NETWORKING_EVENT_NAME = "Networking Event";
    /* Holds value for NETWORKING_EVENT_DURATION */
    public static final int NETWORKING_EVENT_DURATION = 60;
    private static final String FILE_PATH = "C:\\ConferenceManagement\\src\\resource\\ConferenceData.txt";
    public static final String FILE_PATH_WITH_NO_DATA = "C:\\ConferenceManagement\\src\\resource\\ConferenceDataNoData.txt";
    public static final String TECH_TALK = "Tech Talk";


}
