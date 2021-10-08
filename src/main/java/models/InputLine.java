package models;

import enums.Client;

public class InputLine {
    String seatKey;
    Integer seatNumber;
    String showtime;
    String actions;
    String behavior;
    Client client;
    Integer time;

    public InputLine(String[] tokens) {
        seatNumber = Integer.valueOf(tokens[0].replaceAll("\\D+", ""));
        seatKey = String.valueOf(tokens[0].charAt(0));
        showtime = tokens[1];
        actions = tokens[2];
        behavior = tokens[3];
        if (tokens[4].equals("C")){
            client = Client.CINEMA_CLUB;
        }else if (tokens[4].equals("M")){
            client = Client.HALF;
        }else{
            client = Client.REGULAR;
        }
        time = Integer.valueOf(tokens[5]);
    }

    public String getSeatKey() {
        return seatKey;
    }

    public void setSeatKey(String seatKey) {
        this.seatKey = seatKey;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}


