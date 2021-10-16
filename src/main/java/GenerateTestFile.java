import utils.Generator;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateTestFile {

    public static void main(String[] args) {

        int lines = 1000;

        try {
            FileWriter out = new FileWriter("input.txt");
            for (int i = 0; i<= lines; i++){
                out.write(Generator.randomSeat() + ";" +
                        Generator.randomShowtime() + ";" +
                        Generator.randomActions() + ";" +
                        Generator.randomBehavior() + ";" +
                        Generator.randomClientType() + ";" +
                        Generator.randomTime() + ";" +"\n");
            }
            out.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
