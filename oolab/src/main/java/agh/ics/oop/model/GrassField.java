package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;
import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses;

    public GrassField(int grassNumber) {
        this.grasses = new HashMap<>();
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) Math.sqrt(10 * grassNumber), (int) Math.sqrt(10 * grassNumber), grassNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return super.objectAt(position)
                .or(() -> Optional.ofNullable(grasses.get(position)));
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {
        List<WorldElement> elements = getElements();
        if (elements.isEmpty()) {
            return new Boundary(new Vector2d(0, 0), new Vector2d(0, 0));
        }

        Vector2d bottom = elements.get(0).getPosition();
        Vector2d top = elements.get(0).getPosition();

        for (WorldElement element : elements) {
            bottom = bottom.lowerLeft(element.getPosition());
            top = top.upperRight(element.getPosition());
        }

        return new Boundary(bottom, top);
    }
}