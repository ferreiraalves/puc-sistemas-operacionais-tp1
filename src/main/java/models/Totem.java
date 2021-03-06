package models;

import enums.SeatStatus;
import utils.ProcessLine;

import java.util.HashMap;

public class Totem {
    private InputLine currentLine;
    private int ticksLeft;
    private int totemId;

    public Totem() {

    }

    public Totem(int i) {
        this.totemId = i;
    }

    public InputLine getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(InputLine currentLine) {
        this.currentLine = currentLine;
    }

    public int getTicksLeft() {
        return ticksLeft;
    }

    public void setTicksLeft(int ticksLeft) {
        this.ticksLeft = ticksLeft;
    }

    public boolean isEmpty(){
        return currentLine == null;
    }

    public int getTotemId() {
        return totemId;
    }

    public void setTotemId(int totemId) {
        this.totemId = totemId;
    }

    public void start(InputLine line, Seat newSeat) {
        this.currentLine = line;
        this.ticksLeft = line.getTime();
        if (newSeat.getStatus() == SeatStatus.FREE){
            newSeat.setStatus(SeatStatus.RESERVED);
            newSeat.setClientId(line.getIndex());
        }
    }

    public void finish(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, HashMap<String, Sale> report){
        ProcessLine.processLine(this.currentLine, theather, report, totemId);    //processes line
        this.currentLine = null;                                        // "resets" totem
    }

    public void tick(HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather, HashMap<String, Sale> report) {
        if (this.currentLine != null){
            this.ticksLeft--;
            if (this.ticksLeft == 0){               //if theres no ticks left, completes action defined in input line
                finish(theather, report);
            }
        }
    }
}

