package utils;

import models.Sale;
import models.Seat;
import models.Totem;

import java.util.ArrayList;
import java.util.HashMap;

public class TotemUtils {

    public static ArrayList<Totem> totems = InitTotems.getTotems(); //initializes totem utils

    public static Totem findEmptyTotem(){
        for (Totem t : totems){
            if (t.isEmpty()){
                return t;
            }
        }
        return null;
    }

    public static void tick(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, HashMap<String, Sale> report) {
        for (Totem t : totems){
            t.tick(theather, report);               //moves time forward for every totem
        }
    }

    public static void updateTotemNumber(int n){
        totems = InitTotems.getTotems(n);
    }

}
