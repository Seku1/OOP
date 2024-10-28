package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Niepisze testów dla wszystkich enumów bo redundantne
class MapDirectionTest {
    @Test
    void toStringWorks(){
        //when
        MapDirection N = MapDirection.NORTH;

        //then
        assertEquals("Północ", N.toString());
    }

    @Test
    void mapDirectionNextWorks(){
        //when
        MapDirection N = MapDirection.NORTH;
        //then
        assertEquals(MapDirection.EAST, N.next());
    }

    @Test
    void mapDirectionPreviousWorks(){
//        when
        MapDirection N = MapDirection.NORTH;
//        then
        assertEquals(MapDirection.WEST,N.prev());
    }

    @Test
    void toUnitVectorWorks(){
//        given
        MapDirection N = MapDirection.NORTH;
        Vector2d vec = new Vector2d(0,1);
//        then
        assertEquals(vec ,N.toUnitVector());
    }
}