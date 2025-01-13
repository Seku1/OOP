package agh.ics.oop.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileMapDisplay implements MapChangeListener {

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String fileName = "map_" + worldMap.getID() + ".log";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Change detected: " + message + "\n");
            writer.write("Current map state:\n");
            writer.write(worldMap.toString());
            writer.write("\n\n");
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
