package utils;

import models.Totem;

import java.util.ArrayList;

public class InitTotems {
    public static ArrayList<Totem> getTotems(){
        ReadConfig rc = new ReadConfig();
        ArrayList<Totem> totems = new ArrayList<Totem>();
        for (int i = 0; i<rc.getTotems(); i++){
            totems.add(new Totem(i));
        }
        return totems;
    }
}
