package utils;

import enums.SeatStatus;
import models.InputLine;
import models.Seat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Purchase {
    public static void processPurchase(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, InputLine line){
        Seat seat = Validations.findSeat(theather, line);
        seat.setClient(line.getClient());
        seat.setStatus(SeatStatus.SOLD);
    }
}
