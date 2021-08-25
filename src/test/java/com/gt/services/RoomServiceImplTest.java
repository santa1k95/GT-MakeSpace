package com.gt.services;

import com.gt.helpers.Constants;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceImplTest {
    RoomServiceImpl fixture=RoomServiceImpl.getInstance();
    @Test
    void getRooms() {
        Map result=fixture.getRooms();
        assertNotNull(result);
    }

    @Test
    void setRooms() {
        Map result=fixture.getRooms();
        assertNotNull(result);
    }

    @Test
    void createRooms() {
        fixture.createRooms();
        fixture.getRooms();
        assertNotNull(fixture.getRooms());
    }


    @Test
    void getSlotDetails() {
        Map slotDetails=fixture.getSlotDetails(LocalTime.parse("10:00"),LocalTime.parse("11:00"));
        assertNotNull(slotDetails);
        assertEquals(slotDetails.get("slot"),40);
        assertEquals(slotDetails.get("nos"),4);
    }

    @Test
    void createLocalTime() {
        LocalTime result = null;
        try {
            result=fixture.createLocalTime("10:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result instanceof LocalTime);

    }

    @Test
    void resetRooms() {
        fixture.resetRooms();
        assertEquals(fixture.getRooms().get(Constants.C_CAVE_NAME).getTs().getSlots().get(0),false);
    }

    @Test
    void getInstance() {
        RoomServiceImpl rs=RoomServiceImpl.getInstance();
        assertTrue(rs instanceof RoomServiceImpl);
    }

    @Test
    void bookSlotFromCommand() {
        fixture.bookSlotFromCommand("BOOK 11:00 11:45 2".split(" "));
        assertEquals(fixture.getRooms().get(Constants.C_CAVE_NAME).getTs().getSlots().get(44),true);
    }

    @Test
    void showVacantRooms() {
        boolean result=fixture.showVacantRooms("VACANCY 15:45 16:00".split(" "));
        assertEquals(result,true);
    }

    @Test
    void bookDefaultSlots() {
        fixture.bookDefaultSlots();
        assertEquals(fixture.getRooms().get(Constants.C_CAVE_NAME).getTs().getSlots().get(36),true);
    }
}