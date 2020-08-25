package com.company;

import java.time.LocalDateTime;

public class Booking {

    //Attributes of Booking class
    private String username;
    private String roomName;
    private String date;
    private int id;
    private int numOfPeople;
    private int length;
    private LocalDateTime startDateTime, endDateTime;

    //Booking constructor
    public Booking(int id, String username, String roomName, String date, LocalDateTime startDateTime, int length, int numOfPeople, LocalDateTime endDateTime) {
        this.id =id;
        this.username = username;
        this.roomName = roomName;
        this.date = date;
        this.startDateTime = startDateTime;
        this.length = length;
        this.numOfPeople = numOfPeople;
        this.endDateTime = endDateTime;
    }


    //Getters and Setters
    public int getId() {return  id;}

    public void setId (int id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }


    @Override
    public String toString(){
        //return  "Id " + id + ", Username " + username;
        return  "Room: " + roomName + ", Date: " + date + ", Time: "+ startDateTime.toLocalTime() + ", Duration: " + length + " Number of People: " +numOfPeople;
    }
}