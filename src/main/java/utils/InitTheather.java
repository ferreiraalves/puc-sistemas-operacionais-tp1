package utils;

import models.Seat;

import java.util.HashMap;

public class InitTheather {
    public static HashMap<String, HashMap<String, HashMap<Integer, Seat>>> getTheather() {
        ReadConfig rc = new ReadConfig();

        // Initialize theather modeling
        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = new HashMap<String, HashMap<String, HashMap<Integer, Seat>>>();
        for (String showtime : rc.getShowtimes()){
            theather.put(showtime, new HashMap<String, HashMap<Integer, Seat>>());
            for (int i = 0; i< rc.getRows(); i++){
                String key = String.valueOf((char)(i + 65));
                theather.get(showtime).put(key, new HashMap<Integer, Seat>());
                for (int j = 0; j<rc.getColumns();j++){
                    theather.get(showtime).get(key).put(j, new Seat());
                }
            }
        }
        return theather;
    }

}
