package utils;

import enums.SeatStatus;
import models.InputLine;
import models.Seat;

import java.util.HashMap;

public class Purchase {
    public static void processPurchase(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, InputLine line){
        Seat seat = Validations.findSeat(theather, line);
        seat.setClient(line.getClient());
        seat.setStatus(SeatStatus.SOLD);
    }
}
