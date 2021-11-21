package models;

public class Totem {
    private InputLine currentLine;
    private int turnsLeft;

    public Totem() {

    }

    public InputLine getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(InputLine currentLine) {
        this.currentLine = currentLine;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public boolean isEmpty(){
        return currentLine == null;
    }

    public void tick(){
        this.turnsLeft--;
    }

}

