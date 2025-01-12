
package agh.ics.oop.model;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void testIfMapWorks(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal = new Animal();
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("unexpected exception: " + e.getMessage());
        }
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo1(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void testCanMoveTo2(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            map.place(animal1);
        }
        catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }

        try{
            map.place(animal2);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }

        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlace(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
    }

    @Test
    public void testMove(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 2));

        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }

        try {
            map.place(animal2);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }

        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(3, 1), animal2.getPosition());
    }

    @Test
    public void testIsOccupied(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
    }

    @Test
    public void testObjectAt(){
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        try {
            map.place(animal2);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertEquals(Optional.of(animal1), map.objectAt(new Vector2d(2, 2)));
        assertEquals(Optional.of(animal2), map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void testGetOrderedAnimals(){
        WorldMap map = new RectangularMap(10, 10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(5, 2));
        Animal animal3 = new Animal(new Vector2d(3, 3));
        Animal animal4 = new Animal(new Vector2d(3, 4));
        Animal animal5 = new Animal(new Vector2d(2, 5));

        try{
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
            map.place(animal4);
            map.place(animal5);
        } catch (IncorrectPositionException e) {
            throw new RuntimeException(e);
        }
        List<Animal> sortedAnimals = map.getOrderedAnimals();
        List<Animal> expectedAnimals = List.of(animal1,animal5, animal3, animal4, animal2);
//        System.out.println(Arrays.toString(sortedAnimals.toArray()));
        for (int i = 0; i < sortedAnimals.size(); i++) {
            assertEquals(expectedAnimals.get(i), sortedAnimals.get(i));
        }
    }
}
