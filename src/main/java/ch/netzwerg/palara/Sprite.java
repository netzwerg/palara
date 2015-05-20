package ch.netzwerg.palara;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Sprite extends Group {

    private static final Random R = new Random();

    // Solarized colors

    private static final Color YELLOW = Color.web("#b58900");
    private static final Color ORANGE = Color.web("#cb4b16");
    private static final Color RED = Color.web("#dc322f");
    private static final Color MAGENTA = Color.web("#d33682");
    private static final Color VIOLET = Color.web("#6c71c4");
    private static final Color BLUE = Color.web("#268bd2");
    private static final Color CYAN = Color.web("#2aa198");
    private static final Color GREEN = Color.web("#859900");
    private static final List<Color> COLORS = Arrays.asList(YELLOW, ORANGE, RED, MAGENTA, VIOLET, BLUE, CYAN, GREEN);

    private static final double Y_DIRECTION_FLIP_PROBABILITY = 0.01;
    private static final int SPEED_X = 3;
    private static final int SPEED_Y = 5;

    private final PalaraModel model;
    private boolean initialized = false;
    private int yDirection = 1;

    public Sprite(PalaraModel model) {
        this.model = model;
        Circle circle = new Circle(R.nextInt(100), COLORS.get(R.nextInt(COLORS.size())));
        getChildren().add(circle);
    }

    public void update(double width, double height) {
        if (!initialized) {
            initialized = true;
            setTranslateX(0);
            setTranslateY(R.nextDouble() * height);
        } else {
            yDirection = getPossiblySwitchedYDirection(yDirection);
            double dy = calcDeltaY(yDirection);
            getTransforms().add(new Translate(SPEED_X, dy));
            removeIfOutOfSceneBounds(width, height);
        }
    }

    private static int getPossiblySwitchedYDirection(int yDirection) {
        return yDirection * (R.nextDouble() < Y_DIRECTION_FLIP_PROBABILITY ? -1 : 1);
    }

    private static double calcDeltaY(int yDirection) {
        return (double) (int) (yDirection * Math.sin(R.nextDouble()) * SPEED_Y);
    }

    private void removeIfOutOfSceneBounds(double width, double height) {
        Point2D location = localToParent(0, 0);
        double spriteWidth = getBoundsInParent().getWidth();
        double spriteHeight = getBoundsInParent().getHeight();
        if (location.getX() - (spriteWidth / 2) > width || location.getY() - (spriteHeight / 2) > height) {
            Platform.runLater(() -> model.getSprites().remove(this));
        }
    }

}
