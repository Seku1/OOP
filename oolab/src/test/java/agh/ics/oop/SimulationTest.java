
package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class SimulationTest {

    @Test
    void checkAnimalOrientation() {
        Animal animal = new Animal();
        assertEquals(animal.getDirection() , MapDirection.NORTH);
    }
    @Test
    void checkAnimalMoveForward() {
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2,3),animal.getPosition() );
        assertEquals(animal.getDirection() , MapDirection.NORTH);
    }
    @Test
    void checkAnimalMoveBackward() {
        Animal animal = new Animal();

        animal.move(MoveDirection.BACKWARD);

        assertEquals(animal.getPosition(), new Vector2d(2, 1));
        assertEquals(animal.getDirection(), MapDirection.NORTH);
    }
    @Test
    void checkAnimalMoveLeft() {
        Animal animal = new Animal();

        animal.move(MoveDirection.LEFT);

        assertEquals(animal.getPosition() , new Vector2d(2,2) );
        assertEquals(animal.getDirection() , MapDirection.WEST);
    }
    @Test
    void checkAnimalMoveRight() {
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getPosition() , new Vector2d(2,2) );
        assertEquals(animal.getDirection() , MapDirection.EAST);
    }
    @Test
    void animalMovesOutsideMap(){
        Animal animal = new Animal(new Vector2d(4,4));

        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.getPosition() , new Vector2d(4,4));
    }
    @Test
    void cannotCreateAnimalOutsideMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal(new Vector2d(4, 10));
        });
    }
    @Test
    void moveAnimalAccordingToTab(){
        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT);
        List<Vector2d> positions = List.of(new Vector2d(0,0));
        Simulation simulation = new Simulation(positions, directions);

        simulation.run();

        assertEquals(simulation.getAnimalList().getFirst().getPosition(), new Vector2d(0,0));
        assertEquals(simulation.getAnimalList().getFirst().getDirection(), MapDirection.NORTH);
    }
}
