package com.projects.scheduler.conferencescheduler.conference;

import com.projects.scheduler.conferencescheduler.config.ConfigConstant;
import com.projects.scheduler.conferencescheduler.track.Track;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * The Conference holds the track.
 * track holds slots like Morning slot, Lunch and Afternoon slot
 * slots contains events
 *
 * @author Sriman Singh
 * @version 1.0
 */
public final class Conference {
    private List<Track> track;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    private LocalDateTime localDateTime;

    public Conference() {
        track = new ArrayList<Track>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("conference Schedule:" + ConfigConstant.NEW_LINE.getName() + ConfigConstant.NEW_LINE.getName());
        return str.toString();
    }

    public void addTrack(Track track) {
        this.getTrack().add(track);
    }
}
