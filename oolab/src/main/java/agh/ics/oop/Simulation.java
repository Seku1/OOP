package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animalList;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> coordinates , List<MoveDirection> moves, WorldMap map){
        this.animalList = new ArrayList<>();
        for (Vector2d cord : coordinates) {
            Animal animal = new Animal(cord);
            if (map.place(animal)) {
                this.animalList.add(animal);
            }
        }
        this.moves = moves;
        this.map = map;
    }

    public List<Animal> getAnimalList() {
        return this.animalList;
    }

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public void run() {
        System.out.println(map);
        for(int i = 0; i < moves.size(); i++){
            map.move(animalList.get(i % animalList.size()), moves.get(i));
            System.out.println(map);
        }
    }
}
