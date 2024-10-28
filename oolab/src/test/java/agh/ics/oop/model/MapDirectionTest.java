package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Niepisze testów dla wszystkich enumów bo redundantne
class MapDirectionTest {
    @Test
    void toStringWorks(){
        //when
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST; // I don't know if redundancy
        MapDirection S = MapDirection.SOUTH;// I don't know if redundancy
        MapDirection W = MapDirection.WEST;// I don't know if redundancy
        //then
        assertEquals("Północ", N.toString());
        assertEquals("Wschód", E.toString());// I don't know if redundancy
        assertEquals("Południe", S.toString());// I don't know if redundancy
        assertEquals("Zachód", W.toString());// I don't know if redundancy
    }

    @Test
    void mapDirectionNextWorks(){
        //when
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST; // I don't know if redundancy
        MapDirection S = MapDirection.SOUTH;// I don't know if redundancy
        MapDirection W = MapDirection.WEST;// I don't know if redundancy
        //then
        assertEquals(MapDirection.EAST, N.next());
        assertEquals(MapDirection.SOUTH, E.next());// I don't know if redundancy
        assertEquals(MapDirection.WEST, S.next());// I don't know if redundancy
        assertEquals(MapDirection.NORTH, W.next());// I don't know if redundancy
    }

    @Test
    void mapDirectionPreviousWorks(){
//        when
        MapDirection N = MapDirection.NORTH;
        MapDirection E = MapDirection.EAST; // I don't know if redundancy
        MapDirection S = MapDirection.SOUTH;// I don't know if redundancy
        MapDirection W = MapDirection.WEST;// I don't know if redundancy
//        then
        assertEquals(MapDirection.WEST,N.prev());
        assertEquals(MapDirection.NORTH,E.prev());
        assertEquals(MapDirection.EAST,S.prev());
        assertEquals(MapDirection.SOUTH,W.prev());
    }

    @Test
    void toUnitVectorWorks(){
//        given
        MapDirection N = MapDirection.NORTH;
        Vector2d expect_N = new Vector2d(0,1);
        MapDirection S = MapDirection.SOUTH;// I don't know if redundancy
        Vector2d expect_S = new Vector2d(0,-1);
        MapDirection E = MapDirection.EAST;// I don't know if redundancy
        Vector2d expect_E = new Vector2d(1,0);
        MapDirection W = MapDirection.WEST;// I don't know if redundancy
        Vector2d expect_W = new Vector2d(-1,0);
//        then
        assertEquals(expect_N ,N.toUnitVector());
        assertEquals(expect_S ,S.toUnitVector());// I don't know if redundancy
        assertEquals(expect_E ,E.toUnitVector());// I don't know if redundancy
        assertEquals(expect_W ,W.toUnitVector());// I don't know if redundancy
    }
}