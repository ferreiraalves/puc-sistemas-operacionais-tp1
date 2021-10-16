package utils;

import models.Sale;

import java.util.HashMap;

public class GenerateReport {
    public static void generateReport(HashMap<String, Sale> report){

        System.out.println();
        System.out.println("#########REPORT POR SESSÃO#########");
        System.out.println();

        Integer globalSales = 0;
        Integer globalSeats = 0;

        for (String showtime : report.keySet()){
            Integer sales =  report.get(showtime).getSales();
            Integer seats =  report.get(showtime).getTotalSeats();
            System.out.println("Sessão : " + showtime);
            System.out.println("Vendas: " + sales);
            System.out.println("Assentos: " + seats);
            System.out.println("Percentual vendido: " + String.format("%.02f", sales.floatValue()/seats.floatValue()*100) + "%");

            System.out.println();

            globalSales += sales;               // Cathes a ride on Hashmap iteration to calculate global stats
            globalSeats += seats;
        }

        System.out.println("#########REPORT GLOBAL#########");
        System.out.println();
        System.out.println("Vendas: " + globalSales);
        System.out.println("Assentos: " + globalSeats);

        System.out.println("Percentual vendido: " + String.format("%.02f", globalSales.floatValue()/globalSeats.floatValue()*100) + "%");

    }
}
