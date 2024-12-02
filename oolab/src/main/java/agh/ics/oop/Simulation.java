package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.animals = new ArrayList<>();
        for (Vector2d position : positions) {
            try {
                Animal animal = new Animal(position);
                if (map.place(animal)) {
                    this.animals.add(animal);
                }
            }catch (IncorrectPositionException e){
                System.out.println("!!!!!! Warning: " + e.getMessage());
            }
        }
        this.directions = directions;
        this.map = map;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public void run() {
        for(int i = 0; i < directions.size(); i++){
            map.move(animals.get(i % animals.size()), directions.get(i));
        }
    }
}