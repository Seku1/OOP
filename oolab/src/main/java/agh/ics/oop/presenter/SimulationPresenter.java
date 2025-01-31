package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;


import static agh.ics.oop.OptionsParser.parse;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField moveListTextField;
    @FXML
    private Label moveDescriptionLabel;

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private int mapWidth;
    private int mapHeight;

    private int width = 100;
    private int height = 100;

    private final int mapMaxHeight = 400;
    private final int mapMaxWidth = 600;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void xyLabel(){
        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    public void updateBounds(){
        xMin = map.getCurrentBounds().lowerLeft().getX();
        yMin = map.getCurrentBounds().lowerLeft().getY();
        xMax = map.getCurrentBounds().upperRight().getX();
        yMax = map.getCurrentBounds().upperRight().getY();
        mapWidth = xMax - xMin + 1;
        mapHeight = yMax - yMin + 1;
        width = Math.round(mapMaxWidth/mapWidth);
        height = Math.round(mapMaxHeight/mapHeight);
        height = Math.min(height, width);
        width = height;
    }

    public void columnsFunction(){
        for(int i=0; i<mapWidth; i++){
            Label label = new Label(Integer.toString(i+xMin));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
            mapGrid.add(label, i+1, 0);
        }
    }

    public void rowsFunction(){
        for(int i=0; i<mapHeight; i++){
            Label label = new Label(Integer.toString(yMax-i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            mapGrid.add(label, 0, i+1);
        }
    }

    public void addElements() {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d pos = new Vector2d(i, j);

                int finalI = i;
                int finalJ = j;

                map.objectAt(pos).ifPresentOrElse(worldElement -> {
                    if (worldElement instanceof Animal animal) {
                        Image animalImage = createImageViewElement(animal);
                        WorldElementBox animalBox = new WorldElementBox(animalImage, "A" + animal.getPosition().toString());
                        mapGrid.add(animalBox, finalI - xMin + 1, yMax - finalJ + 1);
                    } else if (worldElement instanceof Grass grass) {
                        Image grassImage = createImageViewElement(grass);
                        WorldElementBox grassBox = new WorldElementBox(grassImage, "Trawa");
                        mapGrid.add(grassBox, finalI - xMin + 1, yMax - finalJ + 1);
                    } else {
                        Label label = new Label(worldElement.toString());
                        mapGrid.add(label, finalI - xMin + 1, yMax - finalJ + 1);
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }, () -> {
                    Label emptyLabel = new Label(" ");
                    mapGrid.add(emptyLabel, finalI - xMin + 1, yMax - finalJ + 1);
                    GridPane.setHalignment(emptyLabel, HPos.CENTER);
                });
            }
        }
    }


    private void drawMap() {
        updateBounds();
        xyLabel();
        columnsFunction();
        rowsFunction();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }

    private void clearGrid(){
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
            moveDescriptionLabel.setText(message);
        });
    }

    @FXML
    private void onSimulationStartClicked() {
        String moveList = moveListTextField.getText();
        List<MoveDirection> directions = parse(moveList.split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        Simulation simulation = new Simulation(directions, positions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        moveDescriptionLabel.setText("Simulation started with moves: " + moveList);
        new Thread(engine::runSync).start();
    }


    private Image createImageViewElement(Animal animal) {
        String imagePath;

        switch (animal.getDirection()) {
            case NORTH -> imagePath = "galley_up.jpg";
            case SOUTH -> imagePath = "galley_down.jpg";
            case EAST -> imagePath = "galley_left.jpg";
            case WEST -> imagePath = "galley_right.jpg";
            default -> throw new IllegalArgumentException("Invalid direction: " + animal.getDirection());
        }

        return new Image(imagePath); // return the image directly
    }

    private Image createImageViewElement(Grass grass) {
        String imagePath = "grass.jpg";
        return new Image(imagePath); // return the image directly
    }


}