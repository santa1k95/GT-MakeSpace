package com.gt.utils;

import java.util.List;

abstract public class Room {
    TimeSlots ts = new TimeSlots();

    /**
     * Get Capacity of a Room
     * @return Capacity
     */
    public abstract Integer getCapacity();

    /**
     * Get Name of a Rooms
     * @return Name
     */
    public abstract String getName();

    public abstract void setCapacity(Integer capacity);

    public abstract void setName(String name);

    /**
     * Function to book room from slot data
     * @param slot initial slot
     * @param nos number of slots
     * @return true if slots booked false otherwise
     */
    public boolean bookRoom(Integer slot, Integer nos) {
        int i;
        for (i = slot; i < slot + nos; i++) {
            if(!this.ts.getSlots().get(i))
            {
                continue;
            }
            else break;
        }
        if (i!=slot+nos){
            return false;
        }
        for(int j=slot;j<slot+nos;j++){
            this.ts.bookSlot(j);
        }
        return true;
    }

    /**
     * Getter for Time Slots for a particular room
     * @return List of booleans
     */
    public List<Boolean> getTimeSlots() {
        return this.ts.getSlots();
    }

    /**
     * Prints time slots in a room
     */
    public void printSlotsInRoom() {
        System.out.println(this.ts.getSlots().toString());
        ;
    }

    /**
     * Getter for ts (TimeSlots)
     * @return complete TimeSlots object with List of booleans (slots) in it
     */
    public TimeSlots getTs() {
        return ts;
    }

    /**
    * Getter for ts (TimeSlots)
     * */

    public void setTs(TimeSlots ts) {
        this.ts = ts;
    }

    /**
     * To String Method
     * @return Stringified Room
     */
    @Override
    public String toString() {
        return "Room{" +
                "ts=" + ts +
                '}';
    }
    //    default Integer getSlot()
}
