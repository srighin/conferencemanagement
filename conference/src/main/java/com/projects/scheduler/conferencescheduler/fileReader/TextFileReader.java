package com.projects.scheduler.conferencescheduler.fileReader;


import com.projects.scheduler.conferencescheduler.event.Event;
import com.projects.scheduler.conferencescheduler.exception.ConferenceManagementBusinessException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.projects.scheduler.conferencescheduler.config.Constant.*;

/**
 * Created by SG0952876 on 12/3/2016. All test
 */
public class TextFileReader implements FileReader{
    private String path;

    public TextFileReader(String path) {
        this.path = path;
    }

    public List<Event> read(){
        try {
            Stream<String> lines = Files.lines(Paths.get(path));
            return createEvents(lines);
        } catch (FileNotFoundException e) {
            throw new ConferenceManagementBusinessException(e);
        }catch (IOException e) {
            throw new ConferenceManagementBusinessException(e);
        }
    }

    /**
     * createEvents creates event by reading data file
     *
     * @param input
     * @return
     * @throws IOException
     */
    private List<Event> createEvents(Stream<String> input) throws IOException {
         return input
                 .map(String :: trim)
                 .map(line -> parseInputLine(line))
                 .filter(o -> o != null)
                 .collect(Collectors.toList());
    }

    /**
     * parseInputLine parse line and generate arguments to create Events object.
     *
     * @param line
     * @return
     */
    private static Event parseInputLine(String line) {
        if (line.length() == 0) {
            return null;
        }

        Matcher match = INPUT_LINE_PATTERN.matcher(line);
        if (match.find() == false) {
            return null;
        }

        String name = match.group(EVENT_NAME_INDEX);
        String durationInString = match.group(EVENT_DURATION_INDEX);
        if (durationInString == null) {
            return null;
        }
        int duration = Integer.parseInt(durationInString);

        Event event = new Event.Builder().eventName(name).eventDuration(Duration.ofMinutes(duration)).build();
        if (event.getEventDuration().toMinutes() > MAX_EVENT_DURATION) {
            return null;
        }
        return event;
    }
}
