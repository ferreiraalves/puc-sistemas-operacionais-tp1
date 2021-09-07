package utils;

import models.Showtime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ReadConfig {
    Integer rows;
    Integer columns;
    ArrayList <Showtime> showtimes;

    public ReadConfig() {
        try {
            File myObj = new File("config.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            this.rows = parseInt(data.split("x")[0]);
            this.columns = parseInt(data.split("x")[1]);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
