package com.projects.scheduler.conferencescheduler.track;


import com.projects.scheduler.conferencescheduler.slot.Slot;

import java.util.ArrayList;
import java.util.List;

/**
 * The Track holds the slots.
 * slots holds events
 *
 * @author Sriman Singh
 * @version 1.0
 */
public class Track {

    private List<Slot> slots;

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public Track() {
        slots = new ArrayList<Slot>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Slot slot : slots) {
            str.append(slot);
        }
        return str.toString();
    }

    public void addSlot(Slot slot) {
        this.getSlots().add(slot);
    }
}
