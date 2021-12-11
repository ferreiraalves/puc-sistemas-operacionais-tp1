import models.InputLine;
import models.Sale;
import models.Seat;
import models.Totem;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void simulate(){
        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ArrayList<InputLine> inputLines = ReadInput.readInput();
        HashMap<String, Sale> report = new HashMap<String, Sale>();

        int ticks = 0;
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

        GenerateReport.generateReport(report, ticks);

    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        while (true){
            System.out.println("Enter command: ");
//            String command = keyboard.nextLine();
            String command = "simular -log tela -pontos 5";
            System.out.println(command);
            ConfigUtils.updateConfig(command);
        }

    }
}
