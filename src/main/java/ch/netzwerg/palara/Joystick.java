package ch.netzwerg.palara;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Joystick extends Pane {

    private static final Label LEFT_ARROW = new JoystickLabel("\u2190");
    private static final Label UP_ARROW = new JoystickLabel("\u2191");
    private static final Label RIGHT_ARROW = new JoystickLabel("\u2192");
    private static final Label DOWN_ARROW = new JoystickLabel("\u2193");

    public Joystick() {
        GridPane gridPane = new GridPane();

        gridPane.add(UP_ARROW, 1, 0);
        gridPane.add(LEFT_ARROW, 0, 1);
        gridPane.add(RIGHT_ARROW, 2, 1);
        gridPane.add(DOWN_ARROW, 1, 2);

        getChildren().add(gridPane);
    }

    private static final class JoystickLabel extends Label {
        public JoystickLabel(String text) {
            super(text);
            setStyle("-fx-font-size: 300%; -fx-border-color: grey; -fx-border-radius: 5px; -fx-padding: 5 15 5 15");
        }
    }

}
