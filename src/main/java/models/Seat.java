package models;

import enums.Client;
import enums.SeatStatus;

public class Seat {
    SeatStatus status;
    Client client;
    String clientId;

    public Seat() {
        this.status = SeatStatus.FREE;
        client = null;
        clientId = null;
    }
}
