package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import agh.ics.oop.OptionsParser;


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
            switch (direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skreca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skreca w lewo");
                    break;
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("Start");
        List<MoveDirection> directions = OptionsParser.parse(args);
//        run(directions);
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        Animal animal = new Animal();
        System.out.println(animal.toString());

    }
}
