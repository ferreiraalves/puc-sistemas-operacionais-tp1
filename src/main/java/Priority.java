import enums.Client;
import models.InputLine;
import models.Sale;
import models.Seat;
import utils.GenerateReport;
import utils.InitTheather;
import utils.ProcessLine;
import utils.ReadInput;

import java.util.ArrayList;
import java.util.HashMap;

public class Priority {

    public static void main(String[] args) {

        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ArrayList<InputLine> inputLines = ReadInput.readInput();
        HashMap<String, Sale> report = new HashMap<String, Sale>();

        ArrayList<InputLine> club = new ArrayList<InputLine>();
        ArrayList<InputLine> half = new ArrayList<InputLine>();
        ArrayList<InputLine> regular = new ArrayList<InputLine>();

        for (InputLine line : inputLines){                  //  separates input into client tipes
            if (line.getClient().equals(Client.CLUB)){
                club.add(line);
            }else if (line.getClient().equals(Client.HALF)){
                half.add(line);
            }else {
                regular.add(line);
            }
        }

        for (String showtime : theather.keySet()){          // initialize report modeling
            report.put(showtime, new Sale());
        }

        for (InputLine line : club){                    // processes all club entries. This is top priority.
            ProcessLine.processLine(line, theather, report);
        }

        for (InputLine line : half){                    // processes all half entries. If > 40% occupancy mixes with regular clients;
            float aux = report.get(line.getShowtime()).getPercentage();
            if ( aux < 0.001){      //checks if under occupancy
                ProcessLine.processLine(line, theather, report);
            }else{
                regular.add(line);
            }
        }

        regular.sort((o1, o2) ->  o1.getIndex().compareTo(o2.getIndex()));     // sorts regular clients and unlucky halfs according to index
        for (InputLine line : regular){                    // processes all regular and unlucky half entries.
            ProcessLine.processLine(line, theather, report);
        }

        GenerateReport.generateReport(report);

    }
}
