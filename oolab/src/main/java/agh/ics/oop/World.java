    package agh.ics.oop;
    import agh.ics.oop.model.*;

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.List;
    import static agh.ics.oop.OptionsParser.parse;


    public class World {
        public static void main(String[] args){
            System.out.println("system wystartowal");

            try {
    //            List<MoveDirection> directions = parse(args);
    //            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(1, 3));
    //            AbstractWorldMap map1 = new GrassField(10);
    //            AbstractWorldMap map2 = new RectangularMap(5,5);
    //            map1.addObserver(new ConsoleMapDisplay());
    //            map2.addObserver(new ConsoleMapDisplay());
    //            Simulation simulation1 = new Simulation(directions, positions, map1);
    //            Simulation simulation2 = new Simulation(directions, positions, map2);
    //            SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));

                List<MoveDirection> directions = parse(args);
                List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(1, 3));
                List<Simulation> simulations = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                for (int i = 0; i < 10000; i++){
                    AbstractWorldMap map = i % 2 == 0 ? new GrassField(10) : new RectangularMap(5, 5);
//                    map.addObserver(new ConsoleMapDisplay());
                    map.addObserver(((worldMap, message) -> {
                        String now = LocalDateTime.now().format(formatter);
                        System.out.println(now + " " + message);
                    }));
                    Simulation simulation = new Simulation(directions, positions, map);
                    simulations.add(simulation);
                }

                SimulationEngine engine = new SimulationEngine(simulations);

//                System.out.println("run sync");
//                engine.runSync();
//                System.out.println("run async");
//                engine.runAsync();
                engine.runAsyncInThreadPool();
//                Thread.sleep(1);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }


            System.out.println("system zakonczyl dzialanie");
        }
    }