import models.InputLine;
import models.Sale;
import models.Seat;
import models.Totem;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

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
}
