package com.company;

public class Room {

    //Attributes of Booking class
    private String name;
    private String type;
    private int id;
    private int floor;
    private int capacity;


    //Constructor Room
    public Room(int id,String name, String type, int floor, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.floor = floor;
    }


    //Getters and Setters
    public int getId () {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    @Override
    public String toString(){
        return  "|" + " Room:" + name + ", Type:" + type + ", Floor:" + floor + ", Capacity:" + capacity;
    }

}