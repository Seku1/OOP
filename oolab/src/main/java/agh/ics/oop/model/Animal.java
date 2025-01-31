package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        validatePosition(position);
        this.direction = MapDirection.NORTH;
        this.position = position;
    }

    private void validatePosition(Vector2d position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
    }


    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction, WorldMap map) {
        validateMoveParameters(direction, map);

        switch (direction) {
            case RIGHT -> turnRight();
            case LEFT -> turnLeft();
            case FORWARD -> moveInDirection(this.direction.toUnitVector(), map);
            case BACKWARD -> moveInDirection(this.direction.toUnitVector().opposite(), map);
        }
    }

    private void validateMoveParameters(MoveDirection direction, WorldMap map) {
        if (direction == null) {
            throw new IllegalArgumentException("Move direction cannot be null");
        }
        if (map == null) {
            throw new IllegalArgumentException("WorldMap cannot be null");
        }
    }

    private void turnRight() {
        this.direction = this.direction.next();
    }

    private void turnLeft() {
        this.direction = this.direction.prev();
    }

    private void moveInDirection(Vector2d movementVector, WorldMap map) {
        Vector2d newPosition = this.position.add(movementVector);
        if (map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    @Override
    public String toString() {
        return direction.toString();
    }


    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}