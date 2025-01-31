package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;
import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int count;
    private final List<Vector2d> shuffledPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int count) {
        validateParameters(maxWidth, maxHeight, count);

        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.count = count;
        this.shuffledPositions = createShuffledPositions();
    }

    private void validateParameters(int maxWidth, int maxHeight, int count) {
        if (maxWidth <= 0 || maxHeight <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        final int totalPositions = maxWidth * maxHeight;
        if (count < 0 || count > totalPositions) {
            throw new IllegalArgumentException("Count must be between 0 and " + totalPositions);
        }
    }

    private List<Vector2d> createShuffledPositions() {
        List<Vector2d> positions = new ArrayList<>(maxWidth * maxHeight);

        for (int i = 0; i < maxWidth * maxHeight; i++) {
            positions.add(new Vector2d(i % maxWidth, i / maxWidth));
        }

        Collections.shuffle(positions);
        return positions.subList(0, count);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new PositionIterator();
    }

    private class PositionIterator implements Iterator<Vector2d> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public Vector2d next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more positions available");
            }
            return shuffledPositions.get(currentIndex++);
        }
    }
}