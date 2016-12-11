package com.projects.scheduler.conferencescheduler.fileReader;


import com.projects.scheduler.conferencescheduler.event.Event;

import java.util.List;

/**
 * Created by SG0952876 on 12/3/2016. All test
 */
public interface FileReader {
    public List<Event> read();
}
