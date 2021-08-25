package com.gt.services;

import com.gt.helpers.Constants;
import static com.gt.helpers.Validator.isValidTime;
import com.gt.utils.CCave;
import com.gt.utils.DTower;
import com.gt.utils.GMansion;
import com.gt.utils.Room;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl implements RoomService {
    private static RoomServiceImpl instance = null;

    /**
     * Private Constructor for singleton
     */
    private RoomServiceImpl(){
        createRooms();
        bookDefaultSlots();
    }

    /**
     * Map storing all three types of rooms
     */
    private Map<String, Room> rooms= new HashMap<>();

    /**
     * Getter for rooms
     * @return rooms
     */
    public Map<String, Room> getRooms() {
        return rooms;
    }

    /**
     * Setter for rooms
     * @param rooms
     */
    public void setRooms(Map<String, Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Initialize rooms on instance creation
     */
    @Override
    public void createRooms() {
        CCave cCave=new CCave();
        DTower dTower=new DTower();
        GMansion gMansion= new GMansion();

        rooms.put(cCave.getName(),cCave);
        rooms.put(dTower.getName(),dTower);
        rooms.put(gMansion.getName(),gMansion);
    }

    /**
     * Getter for slot details for given time interval
     * @param startTime
     * @param endTime
     * @return Map with initial slot("slot") and number of slot("nos")
     */
    @Override
    public Map<String, Integer> getSlotDetails(LocalTime startTime, LocalTime endTime) {
        Map <String,Integer> slotData=new HashMap<>();
        slotData.put("nos",Integer.valueOf((int) (Duration.between(startTime,endTime).toMinutes()/15)));
        slotData.put("slot",Integer.valueOf((int) Duration.between(LocalTime.MIDNIGHT,startTime).toMinutes() / 15));
        return slotData;
    }

    /**
     * Creation of Local Time parsed from string
     * @param timeStr
     * @return
     * @throws Exception Un parsable string
     */
    @Override
    public LocalTime createLocalTime(String timeStr) throws Exception {
        LocalTime lt = null;

        try {
            if(timeStr.length()==5){
                lt = LocalTime.parse(timeStr);
            }
            else if(timeStr.length()==4){
                lt = LocalTime.parse(timeStr);
            }
            return lt;
        } catch (Exception e) {
            throw new Exception("INVALID_INPUT");
        }
    }

    /**
     * Reset rooms for the instance to default
     */
    @Override
    public void resetRooms() {
        Map<String, Room> rooms=instance.rooms;
        rooms.forEach((key,room)->{
            room.getTs().resetSlots();
        });
        bookDefaultSlots();
    }

    /**
     * Get instance for singleton class RoomService
     * @return RoomService
     */
    public static RoomServiceImpl getInstance() {
        if(instance==null){
            instance=new RoomServiceImpl();
        }
        return instance;
    }

    /**
     * Book slots in relevant room for a given command
     * @param args Command split to string array
     */
    @Override
    public void bookSlotFromCommand(String[] args) {
        if(args.length<4){
            System.out.println("Arguments Provided not in Sync. Please provide in the format \nBOOK <start_time(inclusive)> <end_time(exclusive)> <person_capacity>");
            return;
        }
        String startTimeStr=args[1];
        String endTimeStr=args[2];
        String noOfPersons= args[3];
        LocalTime startTime= null,endTime= null;
        Map<String,Integer> slotData=null;
        try {
            startTime = createLocalTime(startTimeStr);
            endTime = createLocalTime(endTimeStr);
            slotData= getSlotDetails(startTime,endTime);
        } catch (Exception e) {
            System.out.println("Exception: "+e.toString());
            return;
        }
        if(!isValidTime(startTime,endTime)){
            System.out.println("INCORRECT_INPUT");
            return;
        }
        boolean roomBooked=false;
        if (Integer.valueOf(noOfPersons) < rooms.get(Constants.C_CAVE_NAME).getCapacity()){
            boolean booked=rooms.get(Constants.C_CAVE_NAME).bookRoom(slotData.get("slot"),slotData.get("nos"));
            roomBooked=booked;
            if(!booked){
//                System.out.println("NO_VACANT_ROOM");
            }else {
                System.out.println(rooms.get(Constants.C_CAVE_NAME).getName());
            }
//            rooms.get(Constants.C_CAVE_NAME).printSlotsInRoom();
//            rooms.get(Constants.D_TOWER_NAME).printSlotsInRoom();
//            rooms.get(Constants.G_MANSION_NAME).printSlotsInRoom();
        }
        if (!roomBooked && Integer.valueOf(noOfPersons) < rooms.get(Constants.D_TOWER_NAME).getCapacity()){
            boolean booked=rooms.get(Constants.D_TOWER_NAME).bookRoom(slotData.get("slot"),slotData.get("nos"));
            roomBooked=booked;
            if(!booked){
//                System.out.println("NO_VACANT_ROOM");
            }else {
                System.out.println(rooms.get(Constants.D_TOWER_NAME).getName());
            }
        } if (!roomBooked && Integer.valueOf(noOfPersons) < rooms.get(Constants.G_MANSION_NAME).getCapacity()){
            boolean booked=rooms.get(Constants.G_MANSION_NAME).bookRoom(slotData.get("slot"),slotData.get("nos"));
            roomBooked=booked;
            if(!booked){
//                System.out.println("NO_VACANT_ROOM");
            }else {
                System.out.println(rooms.get(Constants.G_MANSION_NAME).getName());
            }
        }
//        rooms.get(Constants.C_CAVE_NAME).printSlotsInRoom();
//        rooms.get(Constants.D_TOWER_NAME).printSlotsInRoom();
//        rooms.get(Constants.G_MANSION_NAME).printSlotsInRoom();
        if(!roomBooked)
        {
            System.out.println("NO_VACANT_ROOM");
        }
    }

    /**
     * Show vacant rooms for a given VACANCY command
     * @param args Command split to string array
     * @return True if vacant rooms are available false otherwise
     */
    @Override
    public boolean showVacantRooms(String []args){
        if(args.length<3){
            System.out.println("Arguments Provided not in Sync. Please provide in the format \nBOOK <start_time(inclusive)> <end_time(exclusive)> <person_capacity>");
            return false;
        }
        String startTimeStr=args[1];
        String endTimeStr=args[2];
        Map<String,Integer> slotData=null;
        LocalTime startTime= null,endTime= null;
        try {
            startTime = createLocalTime(startTimeStr);
            endTime = createLocalTime(endTimeStr);
            slotData= getSlotDetails(startTime,endTime);
        } catch (Exception e) {
            System.out.println("Exception: "+e.toString());
            return false;
        }
        if(!isValidTime(startTime,endTime)){
            return false;
        }
        List<Room> freeRooms=checkIfFree(rooms,slotData);
        if(freeRooms.size()==0){
            System.out.print("NO_VACANT_ROOM");
        }
        freeRooms.forEach(room -> {
            System.out.print(room.getName());
            System.out.print(" ");
        });
        System.out.println();
        return true;
    }

    /**
     * This function checks whether slots are free for the given time slots
     * @param rooms
     * @param slotData
     * @return Returns List of Rooms which are free for the given time slot
     */
    private List<Room> checkIfFree(Map<String, Room> rooms, Map<String, Integer> slotData) {
        List<Room> roomsFree=new ArrayList<>();
        rooms.forEach((key,room)->{
//            System.out.println("Checking "+key);
            int i;
            for(i=slotData.get("slot");i<slotData.get("slot")+slotData.get("nos");i++){
                if(room.getTimeSlots().get(i) == true){

                    break;
                };
            }
            if(i==slotData.get("slot")+slotData.get("nos")){
                roomsFree.add(room);
            }
        });
        return  roomsFree;
    }

    /**
     * This function books the buffered slots for all the rooms.
     */
    public void bookDefaultSlots() {
        List<Map<String,Integer>> bookedSlots=new ArrayList<>();
        bookedSlots.add(getSlotDetails(LocalTime.parse("09:00"),LocalTime.parse("09:15")));
        bookedSlots.add(getSlotDetails(LocalTime.parse("13:15"),LocalTime.parse("13:45")));
        bookedSlots.add(getSlotDetails(LocalTime.parse("18:45"),LocalTime.parse("19:00")));
        for(int i=0;i<bookedSlots.size();i++){
            Map<String,Integer> it=bookedSlots.get(i);
            rooms.get(Constants.C_CAVE_NAME).bookRoom(it.get("slot"),it.get("nos"));
            rooms.get(Constants.D_TOWER_NAME).bookRoom(it.get("slot"),it.get("nos"));
            rooms.get(Constants.G_MANSION_NAME).bookRoom(it.get("slot"),it.get("nos"));
        };
//        rooms.get(Constants.C_CAVE_NAME).printSlotsInRoom();
//        rooms.get(Constants.D_TOWER_NAME).printSlotsInRoom();
//        rooms.get(Constants.G_MANSION_NAME).printSlotsInRoom();
    }
}
