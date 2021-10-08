package utils;

import models.InputLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInput {


    public static ArrayList<InputLine> readImput(){

        //This reads the input files, parses into InputLine class and returns as array
        ArrayList<InputLine> lines = new ArrayList<>();
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split(";");
                lines.add(new InputLine(tokens)) ;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return lines;

    }
}
