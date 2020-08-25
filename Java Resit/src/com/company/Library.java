package com.company;

import java.util.ArrayList;

public class Library {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(ArrayList<Room> rooms) {
        Library.rooms = rooms;

    }
}
