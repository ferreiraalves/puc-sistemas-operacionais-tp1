package utils;

import models.Sale;

import java.util.HashMap;

public class GenerateReport {
    public static void generateReport(HashMap<String, Sale> report){

        System.out.println();
        System.out.println("#########REPORT POR SESSÃO#########");
        System.out.println();

        int globalSales = 0;
        int globalSeats = 0;
        int globalTime = 0;

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
            globalTime += report.get(showtime).getTime();
        }

        System.out.println("#########REPORT GLOBAL#########");
        System.out.println();
        System.out.println("Tempo de simulação: " + globalTime);
        System.out.println("Vendas: " + globalSales);
        System.out.println("Assentos: " + globalSeats);
        System.out.println("Percentual vendido: " + String.format("%.02f", (float) globalSales / (float) globalSeats *100) + "%");

    }

    public static void generateReport(HashMap<String, Sale> report, int ticks) {
        System.out.println();
        System.out.println("#########REPORT POR SESSÃO#########");
        System.out.println();

        int globalSales = 0;
        int globalSeats = 0;
        int globalTime = 0;

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
            globalTime += report.get(showtime).getTime();
        }

        System.out.println("#########REPORT GLOBAL#########");
        System.out.println();
        System.out.println("Tempo de simulação: " + globalTime);
        System.out.println("Vendas: " + globalSales);
        System.out.println("Assentos: " + globalSeats);
        System.out.println("Percentual vendido: " + String.format("%.02f", (float) globalSales / (float) globalSeats *100) + "%");
        System.out.println();

        System.out.println("#########REPORT SECOND PHASE#########");
        System.out.println();
        System.out.println("Iterações: " + ticks);
        System.out.println("Tempo simulado: " + ticks/60 + ":" + ticks%60);


    }
}
