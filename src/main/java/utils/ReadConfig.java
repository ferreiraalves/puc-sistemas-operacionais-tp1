package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ReadConfig {
    Integer rows;
    Integer columns;
    String[] showtimes;
    Integer tokens;

    public ReadConfig() {
        try {
            //Reads configuration file
            File myObj = new File("config.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            this.rows = parseInt(data.split("x")[0]);
            this.columns = parseInt(data.split("x")[1]);

            data = myReader.nextLine();
            showtimes = data.split(",");

            data = myReader.nextLine();
            tokens = parseInt(data);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public String[] getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(String[] showtimes) {
        this.showtimes = showtimes;
    }
}
