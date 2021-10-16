package utils;

import java.util.Random;

public class Generator {

    private static ReadConfig rc = new ReadConfig();
    private static Random random = new Random();

    public static String randomSeat(){
        Integer rand = random.nextInt(rc.getRows() - 1 );
        String row = String.valueOf((char)(rand + 65));
        Integer column = random.nextInt(rc.getColumns() - 1);
        return row + String.format("%02d", column);
    }

    public static String randomShowtime(){
        String[] showtimes = rc.getShowtimes();
        return showtimes[random.nextInt(showtimes.length)];
    }

    public static String randomActions(){
        int roll = random.nextInt(100);
        if (roll < 15){
            return "CXX";
        } else if (roll < 30){
            return "CSX";
        } else {
            return "CSP";
        }
    }

    public static String randomBehavior(){
        int roll = random.nextInt(100);
        if (roll < 30){
            return "D";
        } else {
            return "T";
        }
    }

    public static String randomClientType(){
        int roll = random.nextInt(100);
        if (roll < 20){
            return "C";
        } else if (roll < 30){
            return "M";
        } else {
            return "R";
        }
    }

    public static int randomTime() {
        return 1 + random.nextInt(9);
    }
}
