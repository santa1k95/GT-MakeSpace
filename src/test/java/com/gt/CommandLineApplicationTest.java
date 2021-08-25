package com.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineApplicationTest {
    URL resource = getClass().getClassLoader().getResource("commands.txt");
    URL resource2 = getClass().getClassLoader().getResource("commands2.txt");
    File file,file2;
    @BeforeEach
    void setUp() {

        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                file=new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        if (resource2 == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                file2=new File(resource2.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    void mainTest1() {
        System.out.println("----------------Starting Test 1----------------");
        Scanner testScanner = null;
        ArrayList<String> commandList=new ArrayList<>();
        try {
            testScanner=new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(testScanner.hasNextLine()){
            commandList.add(testScanner.nextLine());
//            args[i]=testScanner.nextLine();
        }
        String [] args=new String[commandList.size()];int i=0;
        for(String command: commandList){
            args[i]=command;i++;
        };

        CommandLineApplication.roomService.resetRooms();
        CommandLineApplication.main(args);
        System.out.println("");

    }

    @Test
    void mainTest2() {
        System.out.println("----------------Starting Test 2----------------");
        Scanner testScanner = null;
        ArrayList<String> commandList=new ArrayList<>();
        String [] args=new String[100];int i=0;

        try {
            testScanner=new Scanner(file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(testScanner.hasNextLine()){
            commandList.add(testScanner.nextLine());
        }
        for(String command: commandList){
            args[i]=command;i++;
        };
        CommandLineApplication.roomService.resetRooms();
        CommandLineApplication.main(args);
        System.out.println("");
    }



}