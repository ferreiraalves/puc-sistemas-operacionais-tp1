package utils;

import models.InputLine;
import models.Sale;
import models.Seat;

import java.util.HashMap;

public class ProcessLine {

    private static void out(InputLine line, String status){                 // formats log output
        System.out.println("Client " + line.getIndex()  + "\t" + line.getClient() + "\t" + line.getSeatKey()
                + line.getSeatNumber() + "\t" + line.getShowtime() + "\t" + status);
    }

    public static void processLine(InputLine line, HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, HashMap<String, Sale> report){
        if(Validations.isSeatFree(theather, line)){         // checks if seat is free
            if(Validations.finishPurchase(line)){           // checks clients actions
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
        report.get(line.getShowtime()).addTime(line.getTime());
    }

}
