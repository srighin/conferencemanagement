package com.projects.scheduler.conferencescheduler.config;

/**
 * The ConfigConstant holds configuration related data.
 *
 * @author Sriman Singh
 * @version 1.0
 */
public enum ConfigConstant {

    NAME("name"),
    TIME("time"),
    UNIT("unit"),
    TOPIC("topic"),
    MINUTES("min"),
    EMPTY_STRING(""),
    TXT_FILE(".txt"),
    XML_FILE(".xml"),
    NEW_LINE(System.getProperty("line.separator")),
    JSON_FILE(".json");

    private String name;

    ConfigConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
