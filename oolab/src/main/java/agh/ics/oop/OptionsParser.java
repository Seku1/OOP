package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] OptionsPacer(String[] directions){
        MoveDirection[] result = new MoveDirection[directions.length];
        int cnt = 0;
        for (int i = 0; i < directions.length; i++){
            result[i] = switch (directions[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
        }
        return result;
    }
}
