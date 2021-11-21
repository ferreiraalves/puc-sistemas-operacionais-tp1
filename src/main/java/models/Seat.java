package models;

import enums.Client;
import enums.SeatStatus;

public class Seat {
    String id;
    SeatStatus status;
    Client client;
    Integer clientId;

    public Seat() {
        this.status = SeatStatus.FREE;
        client = null;
        clientId = null;
    }

    public Seat(String key, int j) {
        this.status = SeatStatus.FREE;
        client = null;
        clientId = null;
        id = key + j;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
