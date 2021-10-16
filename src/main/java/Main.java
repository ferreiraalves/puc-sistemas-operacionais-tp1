import models.InputLine;
import models.Sale;
import models.Seat;
import utils.*;


import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    private static void out(InputLine line, String status){
        System.out.println(line.getClient() + "\t" + line.getSeatKey() + line.getSeatNumber() + "\t" + line.getShowtime() + "\t" + status);
    }

    private static void processLine(InputLine line, HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, HashMap<String, Sale> report){
        if(Validations.isSeatFree(theather, line)){
            if(Validations.finishPurchase(line)){
                Purchase.processPurchase(theather, line);
                report.get(line.getShowtime()).registerSale();
                out(line, "confirmou");
            }else {                                   // gave up during process
                out(line, "desistiu");
            }
        }else{                                   // seat taken
            if (line.getBehavior().equals("T")){  // tries to find a new seat
                Seat seat = Validations.findNewSeat(theather, line);
                if (seat != null){
                    if(Validations.finishPurchase(line)){
                        Purchase.processPurchase(theather, line);
                        report.get(line.getShowtime()).registerSale();
                        out(line, "ocupado - mudou para " + seat.getId() + " e confirmou");
                    }else{                                                                          // gave up even with new seat available. Jerk!
                        out(line, "ocupado - mudou para " + seat.getId() + " mas desistiu");
                    }
                }else{                                                              // could not find new seat
                    out(line, "ocupado - não encontrou outro");
                }
            }else{                                                          // did not try to find a new seat
                out(line, "ocupado - desistiu");
            }
        }
    }

    public static void main(String[] args) {

        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ArrayList<InputLine> inputLines = ReadInput.readImput();
        HashMap<String, Sale> report = new HashMap<String, Sale>();

        for (String showtime : theather.keySet()){          // initialize report modeling
            report.put(showtime, new Sale());
        }

        for (InputLine line : inputLines){
            processLine(line, theather, report);

        }
        GenerateReport.generateReport(report);

    }
}
