package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

//ex 1-10
//public class main {
//    public static void main(String[] args) {
//        System.out.println("System wystartował.");
//        run();
//        System.out.println("System przestał działać.");
//    }
//    public static void run(){
//        System.out.println("Zwieżak idzie do przodu.");
//    }
//}
//11-15
//public class main {
//    public static void main(String[] args) {
//        System.out.println("System wystartował.");
//        run(args);
//        System.out.println("System przestał działać.");
//    }
//    public static void run(String[] args){
//        for (String arg : args) {
//            switch (arg) {
//                case "f" -> System.out.println("zwierzak idzie do przodu");
//                case "s" -> System.out.println("zwierzak idzie do tyłu");
//                case "r" -> System.out.println("zwierzak skręca w prawo");
//                case "l" -> System.out.println("zwierzak skręca w lewo");
//                default -> System.out.println("bratku, coś ci się chyba pomyliło");
//            }
//        }
//    }
//}
//ex 16-20
public class World {

    public static void run(MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
            System.out.println(direction);
        }
    }
    public static MoveDirection[] OptionsPacer(String[] directions){
        MoveDirection[] result = new MoveDirection[directions.length];
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
    public static void main(String[] args) {
        System.out.println("System wystartował.");
        run(OptionsPacer(args));
        System.out.println("System przestał działać.");
    }
}