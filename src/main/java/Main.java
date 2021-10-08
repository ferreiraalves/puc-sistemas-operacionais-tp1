import models.Seat;
import utils.InitTheather;
import utils.ReadInput;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, HashMap<String, HashMap<Integer, Seat>>> theather = InitTheather.getTheather();
        ReadInput.readImput();

    }
}
