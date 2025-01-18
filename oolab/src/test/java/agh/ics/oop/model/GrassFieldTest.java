
package agh.ics.oop.model;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void testIfMapWorks(){
        WorldMap map = new GrassField(10);
        Animal animal = new Animal();
        try {
            map.place(animal);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo1(){
        WorldMap map = new GrassField(10);
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
        WorldMap map = new GrassField(10);
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
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlace(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }


    }

    @Test
    public void testMove(){
        WorldMap map = new GrassField(10);
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
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
        System.out.println(map);
    }

    @Test
    public void testObjectAt(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        }catch (IncorrectPositionException e){
            fail("unexpected exception: " + e.getMessage());
        }
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
    }

    @Test
    public void testGetElements(){
        WorldMap map = new GrassField(10);
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
        assertEquals(12, ((GrassField) map).getElements().size());
    }
}
