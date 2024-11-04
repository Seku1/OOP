package agh.ics.oop.model;


public class Animal {
    private MapDirection direction;
    private Vector2d position;
    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;;
        if (! position.precedes(new Vector2d(4,4)) || ! position.follows(new Vector2d(0,0))){
            throw new IllegalArgumentException("Animal outside map");
        }
        else{
            this.position = position;
        }
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction){
        Vector2d prevPosition = this.getPosition();
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.prev();
            case FORWARD -> this.position = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> this.position = this.position.subtract(this.direction.toUnitVector());
        }
        if (! this.position.precedes(new Vector2d(4,4)) || ! this.position.follows(new Vector2d(0,0))){
            this.position = prevPosition;
        }
    }

    @Override
    public String toString() {
        return "Animal [direction=" + this.direction + ", position=" + this.position + "]";
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}
