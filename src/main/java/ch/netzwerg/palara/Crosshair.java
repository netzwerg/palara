package ch.netzwerg.palara;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Crosshair extends Canvas {

    private static final int CROSSHAIR_SIZE = 100;
    private static final int OUTER_CIRCLE_WIDTH = 80;
    private static final int INNER_CIRCLE_WIDTH = 50;

    public Crosshair(PalaraModel model) {
        super(CROSSHAIR_SIZE, CROSSHAIR_SIZE);
        GraphicsContext gc = getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        drawCenteredCircle(gc, OUTER_CIRCLE_WIDTH);
        drawCenteredCircle(gc, INNER_CIRCLE_WIDTH);

        gc.strokeLine(CROSSHAIR_SIZE / 2, 0, CROSSHAIR_SIZE / 2, CROSSHAIR_SIZE);
        gc.strokeLine(0, CROSSHAIR_SIZE / 2, CROSSHAIR_SIZE, CROSSHAIR_SIZE / 2);

        translateXProperty().bind(model.crosshairXProperty());
        translateYProperty().bind(model.crosshairYProperty());
    }

    private void drawCenteredCircle(GraphicsContext gc, int width) {
        int inset = (CROSSHAIR_SIZE - width) / 2;
        gc.strokeOval(inset, inset, width, width);
    }

}
