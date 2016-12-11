package com.projects.scheduler.conferencescheduler.event;

import java.time.Duration;

/**
 * The Event is POJO which is define with its attributes name and duration
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class Event {
    private String eventName;
    private Duration eventDuration;

    private Event(Builder builder) {
        this.eventName = builder.eventName;
        this.eventDuration = builder.eventDuration;
    }

    public static class Builder {
        private String eventName;
        private Duration eventDuration;

        public Builder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public Builder eventDuration(Duration eventDuration) {
            this.eventDuration = eventDuration;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
    /**
     * Holds Event Name
     * @return
     */
    public String getEventName() {
        return eventName;
    }
    /**
     * Holds Event duration
     * @return
     */
    public Duration getEventDuration() {
        return eventDuration;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDuration=" + eventDuration +
                '}';
    }
}
