package ch.netzwerg.palara;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ControlPane extends BorderPane {

    public ControlPane() {
        setStyle("-fx-background-color: transparent");

        HBox contents = new HBox();
        contents.setAlignment(Pos.BOTTOM_CENTER);
        contents.getChildren().add(new Joystick());
        setBottom(contents);
    }

}
