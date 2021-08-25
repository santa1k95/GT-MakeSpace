package com.gt;

import com.gt.helpers.Validator;
import com.gt.services.RoomService;
import com.gt.services.RoomServiceImpl;

import java.util.Scanner;

public class CommandLineApplication {
    public static RoomService roomService= RoomServiceImpl.getInstance();
    static void prompt(){
        System.out.print("Enter a command or type 'Exit' to quit \n>> ");
    }
    public static void main(String[] args) {


        if(args.length==0){
            Scanner sc=new Scanner(System.in);

            for (prompt(); sc.hasNextLine(); prompt()){
                String command = sc.nextLine().replaceAll("\n", "");

                if(command.length() == 0){
                    continue;
                }
                String[] arguments = command.split(" ");
                if(!Validator.isValidCommand(arguments)){
                    System.out.println("INVALID_INPUT");
                    continue;
                }
                if (arguments.length > 0) {
                    if (arguments[0].equalsIgnoreCase("exit"))
                        System.exit(0);
                    else{
                        if (arguments[0].equalsIgnoreCase("book"))
                        {
                            roomService.bookSlotFromCommand(arguments);
                        }
                        else if (arguments[0].equalsIgnoreCase("vacancy"))
                        {
                            boolean result=roomService.showVacantRooms(arguments);
                            if(!result){
                                System.out.println("INCORRECT_INPUT");
                            }
                        }
                    }
                }
            }
        }else{
            for (String command:args){

                if(command.length() == 0){
                    continue;
                }
                String[] arguments = command.split(" ");
                if(!Validator.isValidCommand(arguments)){
                    System.out.println("INVALID_INPUT");
                    continue;
                }
                if (arguments.length > 0) {
                    if (arguments[0].equalsIgnoreCase("exit"))
                        break;
                    else{
                        if (arguments[0].equalsIgnoreCase("book"))
                        {
                            roomService.bookSlotFromCommand(arguments);
                        }
                        else if (arguments[0].equalsIgnoreCase("vacancy"))
                        {
                            boolean result=roomService.showVacantRooms(arguments);
                            if(!result){
                                System.out.println("INVALID_INPUT");
                            }
                        }
                    }
                }
            }
        }
    }
}
