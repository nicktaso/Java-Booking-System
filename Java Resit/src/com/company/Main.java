package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private static ArrayList<Room> availableR;

    public static void main(String[] args) throws ParseException {
        DateTimeFormatter formatter;
        ArrayList<Room> rooms = createSampleRooms();
        HashMap<Integer, Booking> mapBooking = new HashMap<>();

        viewAllRooms();

        String menuChoice;
        Scanner scanner;

        do {
            // Generate random integers in range 0 to 999
            Random rand = new Random();
            int id = rand.nextInt(1000);

            //Asking user for username
            scanner = new Scanner(System.in);
            System.out.println("\n" + "Please enter your Username.");
            String username = scanner.next();

            //Scan for date
            System.out.println("Enter the Date that you would like to book your room. (in DD/MM/YYYY format)");
            String dateOfBooking = scanner.next();

            //Validation of DD/MM/YYYY format calling isDateValidFormat
            while (!isDateValidFormat(dateOfBooking)) {
                System.out.println("Wrong format of Date." + "\n" + "Try again in DD/MM/YYYY format.");
                dateOfBooking = scanner.next();
            }

            //Scan for time
            System.out.println("Enter Time. (in HH:mm format)");
            String timeOfBooking = scanner.next();

            //Validation of HH:mm format calling isTimeValidFormat
            while (!isTimeValidFormat(timeOfBooking)) {
                System.out.println("Wrong format of Time." + "\n" + "Try again in HH:mm format.");
                timeOfBooking = scanner.next();
            }
            //Scan for length
            System.out.println("Please enter the length of time that you will use the room.");

            // Validate for an integer number
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter an integer.");
            }
            int length = scanner.nextInt();

            // Scan for capacity
            System.out.println("Number of people who will be attending.");

            // Validate for an integer number
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter an integer.");
            }
            int numberOfPeople = scanner.nextInt();

            //Validate that user will input less tha 70 capacity
            while (numberOfPeople > 70) {
                System.out.println("There is no room with capacity more than 70 people." + "\n" + "Please try again." + "\n \n" + "Number of people who will attending.");
                numberOfPeople = scanner.nextInt();
            }

            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dateTime = dateOfBooking + " " + timeOfBooking;

            LocalDateTime startDateTime = LocalDateTime.parse(dateTime, formatter);
            LocalDateTime endDateTime = startDateTime.plusHours(length);

            viewAvailableRooms(mapBooking,numberOfPeople,startDateTime,endDateTime);
            //Parse all rooms in an array list

            System.out.println("\nPlease enter the number of the room that you would like to book.");
            // Validate for an integer number
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter the number of the room.");
            }
            int roomNum = scanner.nextInt();

            // Validate of room number
            while (roomNum < 1 || roomNum > 9) {
                System.out.println("Give a valid number");
                roomNum = scanner.nextInt();
            }

            String roomName = "";
            for (Room room : rooms) {
                if (roomNum == room.getId()) {
                    roomName = room.getName();
                }
            }

            //Adding all booking details in the bookings hashmap
            mapBooking.put(id, new Booking(id, username, roomName, dateOfBooking, startDateTime, length, numberOfPeople, endDateTime));
            System.out.println("Your booking has been confirmed!!");
            System.out.println("\n" + "If you want to book again press Y or N to exit");
            menuChoice = scanner.next();
            while (!menuChoice.equalsIgnoreCase("y") && !menuChoice.equalsIgnoreCase("n")) {
                System.out.println("Give Y or N");
                menuChoice = scanner.next();
            }
        }
        while (menuChoice.equalsIgnoreCase("y"));
        //Ask username and check if that exist
        do {
            System.out.println("\nEnter your Username to access Menu.");
            String username = scanner.next();

            // Give User a Menu to Choose Options
            System.out.println("\n" + "If you want to See your bookings press 1.\nIf you want to Cancel your bookings press 2.\nIf you want to Modify your booking press 3.\nIf you want to Terminate the program press 4.");
            menuChoice = scanner.next();

            //Validate user choice
            while (!menuChoice.equalsIgnoreCase("1") && !menuChoice.equalsIgnoreCase("2") && !menuChoice.equalsIgnoreCase("3") && !menuChoice.equalsIgnoreCase("4")) {
                System.out.println("Choose 1, 2, 3, or 4" + "\n" + "If you want to See your bookings press 1.\nIf you want to Cancel your bookings press 2.\nIf you want to Modify your booking press 3.\nIf you want to Terminate the program press 4.");
                menuChoice = scanner.next();
            }

            switch (menuChoice) {

                // View Bookings
                case "1":

                    System.out.println("\nYour bookings are:");

                    for (Map.Entry<Integer, Booking> b : mapBooking.entrySet()) {
                        if (username.equalsIgnoreCase(b.getValue().getUsername())) {
                            System.out.println(b.getValue().toString());
                        }
                    }
                    break;

                // Cancel Booking
                case "2":

                    System.out.println("Your bookings are:");

                    for (Map.Entry<Integer, Booking> b : mapBooking.entrySet()) {
                        if (username.equalsIgnoreCase(b.getValue().getUsername())) {
                            System.out.println("ID|" + b.getKey() + ", " + b.getValue().toString());
                        }
                    }

                    System.out.println("\nChoose the ID of your booking that you want to Delete.");

                    // Validate for an integer number
                    while (!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Please enter the ID.");
                    }
                    int indexRemove = scanner.nextInt();

                    mapBooking.remove(indexRemove);

                    break;

                //Modify Booking
                case "3":

                    // Show Bookings to the user
                    System.out.println("\nYour bookings are:");

                    for (Map.Entry<Integer, Booking> b : mapBooking.entrySet()) {
                        if (username.equalsIgnoreCase(b.getValue().getUsername())) {
                            System.out.println("ID| " + b.getKey() + ", " + b.getValue().toString());
                        }
                    }


                    System.out.println("\nChoose the ID of your booking that you want to Modify.");

                    //Validate for an integer number
                    while (!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Please enter the ID.");
                    }
                    int indexModify = scanner.nextInt();

                    //Get the booking that the user wants to change in a new object
                    Booking bookingToModify = null;
                    for (Map.Entry<Integer, Booking> b : mapBooking.entrySet()) {
                        if (b.getValue().getId() == indexModify) {
                            bookingToModify = b.getValue();
                        }
                    }

                    System.out.println("\nBooking to modify:  " + bookingToModify.toString());

                    //Ask the user what they want to modify
                    System.out.println("\nWhat would you like to modify?");
                    System.out.println("1. Room name");
                    System.out.println("2. Date");
                    System.out.println("3. Time");
                    System.out.println("4. Length");
                    System.out.println("5. Number of people");

                    //Validate for an integer number
                    while (!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Please enter the ID.");
                    }
                    int whatToModify = scanner.nextInt();

                    //Validate for menu number
                    while (whatToModify < 1 || whatToModify > 5) {
                        System.out.println("Give a number from one to five:");
                        whatToModify = scanner.nextInt();
                    }

                    // Switch case for each option of what to modify
                    switch (whatToModify) {

                        //1.Change room name
                        case 1:
                            viewAvailableRooms(mapBooking, bookingToModify.getNumOfPeople(), bookingToModify.getStartDateTime(), bookingToModify.getEndDateTime());

                            System.out.println("=====================================================================");
                            System.out.println("\nGive the number of the room you want to change:");

                            //Validate for an integer number
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                                System.out.println("Please enter the number of the room.");
                            }

                            int newRoom = scanner.nextInt();

                            //Check if room is correct name
                            while (newRoom < 1 || newRoom > 9) {
                                System.out.println("Give a valid number");
                                newRoom = scanner.nextInt();
                            }

                            String roomName = "";
                            for (Room room : rooms) {
                                if (newRoom == room.getId()) {
                                    roomName = room.getName();
                                }
                            }

                            bookingToModify.setRoomName(roomName);
                            System.out.println("Your booking has been changed!!");
                            System.out.println("\nNew booking details: " + bookingToModify.toString());
                            break;


                        //2.Change date
                        case 2:

                            LocalTime timeOfBooking = bookingToModify.getStartDateTime().toLocalTime();
                            String time = timeOfBooking.toString();

                            System.out.println("Enter the new Date. (in DD/MM/YYYY format)");
                            String dateOfBooking = scanner.next();

                            //Validation of DD/MM/YYYY format calling isDateValidFormat
                            while (!isDateValidFormat(dateOfBooking)) {
                                System.out.println("Wrong format of Date." + "\n" + "Try again in DD/MM/YYYY format.");
                                dateOfBooking = scanner.next();
                            }
                            String dateTime = dateOfBooking + " " + time;
                            System.out.println(dateTime);


                            LocalDateTime startDateTime = LocalDateTime.parse(dateTime, formatter);
                            LocalDateTime endDateTime = startDateTime.plusHours(bookingToModify.getLength());


                            bookingToModify.setStartDateTime(startDateTime);
                            bookingToModify.setEndDateTime(endDateTime);
                            bookingToModify.setDate(dateOfBooking);
                            System.out.println("Your booking Date has changed!!");
                            break;

                        //3.Change Time of booking
                        case 3:
                            LocalDate dateOfBooking2 = bookingToModify.getStartDateTime().toLocalDate();
                            String date = dateOfBooking2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                            System.out.println("Enter the new Time. (in HH:mm format)");
                            String timeOfBooking2 = scanner.next();

                            //Validation of HH:mm format calling isTimeValidFormat
                            while (!isTimeValidFormat(timeOfBooking2)) {
                                System.out.println("Wrong format of Time." + "\n" + "Try again in HH:mm format.");
                                timeOfBooking2 = scanner.next();
                            }

                            String dateTime2 = date + " " + timeOfBooking2;

                            LocalDateTime startDateTime2 = LocalDateTime.parse(dateTime2, formatter);
                            LocalDateTime endDateTime2 = startDateTime2.plusHours(bookingToModify.getLength());

                            bookingToModify.setStartDateTime(startDateTime2);
                            bookingToModify.setEndDateTime(endDateTime2);
                            System.out.println("Your booking Time has been changed!!");
                            break;

                        //4.Change length of booking
                        case 4:
                            LocalDateTime startDateTime3 = bookingToModify.getStartDateTime();
                            System.out.println("Give the new Length:");

                            // Validate for an integer number
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                                System.out.println("Please enter an integer.");
                            }
                            int length = scanner.nextInt();

                            LocalDateTime endDateTime3 = startDateTime3.plusHours(length);

                            bookingToModify.setLength(length);
                            bookingToModify.setEndDateTime(endDateTime3);
                            System.out.println("Your booking has been changed!!");
                            break;


                        //5.Change the Number of people
                        case 5:

                            //Get the booked room
                            Room bookedRoom= null;
                            for (Room room : rooms) {
                                if (room.getName().equals(bookingToModify.getRoomName())){
                                    bookedRoom = room;
                                }
                            }
                            System.out.println("Enter the new Number of people who will be attending.");

                            // Validate for an integer number
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                                System.out.println("Please enter an integer.");
                            }
                            int numOfPeople = scanner.nextInt();

                            //Checking the capacity of the room
                            while (numOfPeople < bookedRoom.getCapacity() || numOfPeople > bookedRoom.getCapacity()){
                                System.out.println("Please enter a number equal or less of "+ bookedRoom.getCapacity());
                                numOfPeople= scanner.nextInt();
                            }

                            //Validate that user will input less tha 70 capacity
                            while (numOfPeople > 70) {
                                System.out.println("There is no room with capacity more than 70 people." + "\n" + "Please try again." + "\n \n" + "Number of people who will attending.");
                                numOfPeople = scanner.nextInt();
                            }

                            bookingToModify.setNumOfPeople(numOfPeople);
                            System.out.println("Your booking has been changed!!");
                            System.out.println("\nNew booking details: " + bookingToModify.toString());
                            break;

                    }

                    mapBooking.replace(indexModify, bookingToModify);


                    break;
            }

        //Terminate Program
        } while (!menuChoice.equalsIgnoreCase("4"));

        System.out.println("\nExiting program.....\nThank you!");
    }


    //Create method with all the available rooms
    public static ArrayList<Room> createSampleRooms() {

        //Create Room Objects
        Room room1 = new Room(1, "Taff", "Small Meeting Room", 2, 8);
        Room room2 = new Room(2, "Llangorse", "Large Meeting Room", 2, 24);
        Room room3 = new Room(3, "Pen y Fan", "Teaching Space", 2, 70);
        Room room4 = new Room(4, "Usk", "Small Meeting Room", 3, 8);
        Room room5 = new Room(5, "Bala", "Large Meeting Room", 3, 24);
        Room room6 = new Room(6, "Cadair Idris", "Teaching Space", 3, 70);
        Room room7 = new Room(7, "Wye", "Small Meeting Room", 4, 8);
        Room room8 = new Room(8, "Gower", "Open meeting/break-Out space", 4, 24);
        Room room9 = new Room(9, "Snowdon", "Teaching space", 4, 70);
        Library.getRooms().add(room1);
        Library.getRooms().add(room2);
        Library.getRooms().add(room3);
        Library.getRooms().add(room4);
        Library.getRooms().add(room5);
        Library.getRooms().add(room6);
        Library.getRooms().add(room7);
        Library.getRooms().add(room8);
        Library.getRooms().add(room9);
        return Library.getRooms();
    }

    // Create method of date validation
    public static boolean isDateValidFormat(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            df.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    // Create method of time validation
    public static boolean isTimeValidFormat(String time) {
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        try {
            tf.parse(time);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //Create method to view all rooms
    public static void viewAllRooms() {
        System.out.println("\n\n\n" + "Hello Dear User!" + "\n" + "Here are all the Library Rooms:");
        System.out.println("\n===================================================================");
        for (Room room : Library.getRooms()) {
            System.out.println(room.getId() + " " + room.toString());
        }
        System.out.println("===================================================================");
    }

    //Create method to view all the available rooms
    public static void viewAvailableRooms(Map<Integer, Booking> mapBooking,int numberOfPeople, LocalDateTime startTime, LocalDateTime endTime){
        availableR = new ArrayList<>();
        for (Room r : Library.getRooms()) {
            availableR.add(r);
        }

        //Removing unavailable rooms from list
        for (Room r : Library.getRooms()) {
            if (!mapBooking.isEmpty()) {
                for (Map.Entry<Integer, Booking> b : mapBooking.entrySet()) {
                    if (r.getCapacity() >= numberOfPeople) {
                        if (r.getName().equalsIgnoreCase(b.getValue().getRoomName())) {
                            if ((startTime.isBefore(b.getValue().getStartDateTime())
                                    && endTime.isBefore(b.getValue().getStartDateTime()))
                                    || (startTime.isAfter(b.getValue().getEndDateTime()))
                                    || startTime.isEqual(b.getValue().getEndDateTime())) {

                            } else {
                                availableR.remove(r);
                            }
                        }
                    } else {
                        availableR.remove(r);
                    }
                }
            } else {
                if (r.getCapacity() < numberOfPeople) {
                    availableR.remove(r);
                }
            }
        }

        System.out.println("Available rooms:\n=====================================================================");
        for (Room r : availableR) {
            System.out.println(r.getId() + " " + r.toString());
        }
    }

}