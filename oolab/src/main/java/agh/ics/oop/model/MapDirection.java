package agh.ics.oop.model;

public enum MapDirection {
    NORTH, EAST, SOUTH, WEST;;

    @Override
    public String toString() {
        return switch (this){
            case NORTH -> "N";
            case EAST -> "E";
            case WEST -> "W";
            case SOUTH -> "S";
        };
    }

    public MapDirection next(){
        switch (this){
            case NORTH -> {
                return EAST;
            }
            case SOUTH -> {
                return WEST;
            }
            case EAST -> {
                return SOUTH;
            }
            case WEST -> {
                return NORTH;
            }
        }
        return null;
    }

    public MapDirection prev(){
        switch (this){
            case NORTH -> {return WEST;}
            case SOUTH -> {return EAST;}
            case EAST -> {return NORTH;}
            case WEST -> {return SOUTH;}
        }
        return null;
    }

    public Vector2d toUnitVector(){
        switch (this){
            case NORTH -> {return new Vector2d(0,1);}
            case SOUTH -> {return new Vector2d(0,-1);}
            case EAST -> {return new Vector2d(1,0);}
            case WEST -> {return new Vector2d(-1,0);}
        }
        return null;
    }
}
