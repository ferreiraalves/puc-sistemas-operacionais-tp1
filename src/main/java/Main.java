import models.InputLine;
import models.Sale;
import models.Seat;
import models.Totem;
import utils.*;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static int ticks;

    public static HashMap<String, Sale> simulate(){
        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ArrayList<InputLine> inputLines = ReadInput.readInput(ConfigUtils.getInputFile());
        HashMap<String, Sale> report = new HashMap<String, Sale>();
        TotemUtils.updateTotemNumber(ConfigUtils.getTotems());

        ticks = 0;
        int nextClient = 0;

        for (String showtime : theather.keySet()){          // initialize report modeling
            report.put(showtime, new Sale());
        }

        while (!inputLines.isEmpty()){
            Totem freeTotem = TotemUtils.findEmptyTotem();
            if (freeTotem != null && nextClient <= 0){              // checks if there's a free totem and if new client has arrived
                InputLine currentLine = inputLines.remove(0);
                freeTotem.start(currentLine, Validations.findSeat(theather,currentLine));   //assigns client to totem, reserves seat
                nextClient = currentLine.getNextClient();                                   //updates when the next client will arrive
            }
            nextClient --;                              //
            TotemUtils.tick(theather, report);         //   These move simulation time forward
            ticks++;
        }

//        GenerateReport.generateReport(report, ticks);
        return report;
    }

    public static void setOutput(){
        try {
            if(ConfigUtils.getLog().equals("tela")){
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            }else if (ConfigUtils.getLog().equals("arquivo")){
                System.setOut(new PrintStream(ConfigUtils.getOutputFile()));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        HashMap<String, Sale> report = null;
        String command = null;

        while (!Objects.equals(command, "finalizar")){
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.out.println("Enter command: ");
            command = keyboard.nextLine();
            ConfigUtils.updateConfig(command);
            setOutput();
            switch (ConfigUtils.getMainCommand()) {
                case "simular":
                    report = simulate();
                    System.out.flush();
                    break;
                case "alterar":
                    for (String key : ConfigUtils.getParameters().keySet()) {
                        System.out.println(key + "\t->\t" + ConfigUtils.getParameters().get(key));
                    }
                    System.out.println("Configurações alteradas");
                    System.out.flush();
                    break;
                case "totalizar":
                    if (report != null) {
                        GenerateReport.generateReport(report, ticks);
                    } else {
                        System.out.println("No simulations to report yet!");
                    }
                    System.out.flush();
                    break;
            }

        }

    }
}
