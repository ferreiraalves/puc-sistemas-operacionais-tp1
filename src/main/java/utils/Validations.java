package utils;

import enums.SeatStatus;
import models.InputLine;
import models.Seat;

import java.util.HashMap;
import java.util.Random;

public class Validations {

    public static Seat findSeat(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, InputLine inputLine){
        HashMap<String, HashMap<Integer, Seat>> showtime = theather.get(inputLine.getShowtime());   //locates seat on hashmap
        HashMap<Integer, Seat> col = showtime.get(inputLine.getSeatKey());
        Seat seat = col.get(inputLine.getSeatNumber());

        return seat;
    }

    public static Seat findSeat(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, String sw, String row, Integer column){
        HashMap<String, HashMap<Integer, Seat>> showtime = theather.get(sw);    //locates seat on hashmap
        HashMap<Integer, Seat> col = showtime.get(row);
        Seat seat = col.get(column);

        return seat;
    }


    public static boolean isSeatFree(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, InputLine inputLine){
        Seat seat = findSeat(theather, inputLine);          //checks if seat is free
        if (seat.getStatus() == SeatStatus.FREE){
            return true;
        }else{
            return false;
        }
    }

    public static boolean finishPurchase(InputLine line) {
        String actions = line.getActions();             //checks if client finished purchase
        if (actions.toLowerCase().contains("x")){
            return false;
        }else{
            return true;
        }
    }

    public static Seat findNewSeat(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, InputLine line) {
        ReadConfig rc = new ReadConfig();
        Random random = new Random();
        for (int i = 0; i< 5; i++){             // tries to find new seat 5 times
            Integer rand = random.nextInt(rc.getRows());
            String row = String.valueOf((char)(rand + 65));         //Generates a random row
            Integer column = random.nextInt(rc.getColumns());       //Generates a random column
            Seat seat = findSeat(theather, line.getShowtime(), row, column);
            if (seat.getStatus() == SeatStatus.FREE){           //found a vacant seat. NICE!
                return seat;
            }
        }
        return null;                        //no seat found
    }
}
