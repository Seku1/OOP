package agh.ics.oop.presenter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WorldElementBox extends VBox {
    private ImageView imageView;
    private Label positionLabel;

    public WorldElementBox(Image image, String position) {
        this.imageView = new ImageView(image);
        this.positionLabel = new Label(position);

        // Set image size
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        // Center the elements inside the VBox
        this.getChildren().addAll(imageView, positionLabel);
        this.setAlignment(javafx.geometry.Pos.CENTER);
    }

    public void updatePosition(String position) {
        positionLabel.setText(position);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
