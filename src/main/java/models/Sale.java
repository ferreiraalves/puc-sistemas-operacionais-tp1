package models;

import utils.ReadConfig;

public class Sale {
    Integer sales;
    Integer totalSeats;
    Integer time;

    public Sale() {
        ReadConfig rc = new ReadConfig();
        sales = 0;
        time = 0;
        totalSeats = rc.getColumns() * rc.getRows();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void registerSale() {
        this.sales++;
    }

    public float getPercentage(){
        return sales.floatValue()/totalSeats.floatValue();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void addTime(Integer time) {
        this.time += time;
    }
}

