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
        this.position = position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction, RectangularMap map){
        Vector2d potentialNewPosition;

        switch (direction){
            case RIGHT -> {
                this.direction = this.direction.next();
            }
            case LEFT -> {
                this.direction = this.direction.prev();
            }
            case FORWARD -> {
                potentialNewPosition = this.position.add(this.direction.toUnitVector());
                if (map.canMoveTo(potentialNewPosition)) {
                    this.position = potentialNewPosition;
                }
            }
            case BACKWARD -> {
                potentialNewPosition = this.position.subtract(this.direction.toUnitVector());
                if (map.canMoveTo(potentialNewPosition)) {
                    this.position = potentialNewPosition;
                }
            }
            default -> {break;}
        }
    }

    public String toString(){
        return this.direction.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}
