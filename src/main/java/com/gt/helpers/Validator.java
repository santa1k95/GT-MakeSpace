package com.gt.helpers;

import java.time.LocalTime;

public class Validator {
    public static boolean isValidTime(LocalTime startTime, LocalTime endTime){

        if(startTime.getMinute()%15 != 0 || endTime.getMinute()%15 != 0) {
            return false;
        }
        if(startTime.isAfter(endTime)){
            return false;
        }
        if(startTime.isAfter(LocalTime.parse("23:45")) || endTime.isAfter(LocalTime.parse("23:45"))){
            return false;
        }
        return true;
    }

    public static boolean isValidCommand(String [] args) {
        if (args.length < 1)
            return false;
        if (!(args[0].equalsIgnoreCase("exit") || args[0].equalsIgnoreCase("book") || args[0].equalsIgnoreCase("vacancy"))){
            return false;
        }else{
            if(args[0].equalsIgnoreCase("book")){
                if(args.length != 4){
                    return false;
                }
            }else if(args[0].equalsIgnoreCase("vacancy")){
                if(args.length != 3){
                    return false;
                }
            }
        }
        return true;
    }
}
