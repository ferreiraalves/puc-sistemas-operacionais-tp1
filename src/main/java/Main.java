import models.InputLine;
import models.Sale;
import models.Seat;
import utils.GenerateReport;
import utils.InitTheather;
import utils.ProcessLine;
import utils.ReadInput;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ArrayList<InputLine> inputLines = ReadInput.readInput();
        HashMap<String, Sale> report = new HashMap<String, Sale>();

        for (String showtime : theather.keySet()){          // initialize report modeling
            report.put(showtime, new Sale());
        }

        for (InputLine line : inputLines){                  // processes input lines as fifo
            ProcessLine.processLine(line, theather, report);
        }

        GenerateReport.generateReport(report);

    }
}
