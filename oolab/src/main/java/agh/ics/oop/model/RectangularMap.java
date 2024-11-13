package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private final Integer width;
    private final Integer height;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals;
    private final MapVisualizer visualizer;



    public RectangularMap(final Integer width, final Integer height) {
        this.width = width;
        this.height = height;
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(this.width-1, this.height-1);
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }


    @Override
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) && position.precedes(upperRight) && position.follows(lowerLeft);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        animals.remove(oldPosition);
        animals.put(animal.getPosition(), animal);
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
