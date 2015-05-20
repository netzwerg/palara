package ch.netzwerg.palara;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public final class PalaraController {

    private static final int BIRTH_INTERVAL_MS = 300;

    private final Scene scene;
    private final PalaraModel model;

    public PalaraController(Scene scene, PalaraModel model) {
        this.scene = scene;
        this.model = model;

        scene.setOnKeyPressed(e -> {
            if (KeyCode.SPACE == e.getCode()) {
                this.model.getSprites().forEach(sprite -> {
                    if (sprite.getBoundsInParent().intersects(model.crosshairBoundsInParentProperty().get())) {
                        Platform.runLater(() -> this.model.getSprites().remove(sprite));
                    }
                });
            }
        });
    }

    public void startAnimation() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSprites();
            }
        };
        gameLoop.start();
        spawnSprites(model.getSprites());
    }

    private void spawnSprites(ObservableList<Sprite> sprites) {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(BIRTH_INTERVAL_MS), e -> sprites.add(new Sprite()));
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateSprites() {
        ObservableList<Sprite> sprites = this.model.getSprites();
        sprites.stream().forEach(sprite -> {
            double width = scene.getWidth();
            double height = scene.getHeight();
            sprite.update(width, height);
            if (isOffScreen(sprite, width, height)) {
                Platform.runLater(() -> sprites.remove(sprite));
            }
        });
    }

    private static boolean isOffScreen(Sprite sprite, double width, double height) {
        Point2D location = sprite.localToParent(0, 0);
        double spriteWidth = sprite.getBoundsInParent().getWidth();
        double spriteHeight = sprite.getBoundsInParent().getHeight();
        boolean isOffTopEdge = location.getX() < 0;
        boolean isOffRightEdge = location.getX() - (spriteWidth / 2) > width;
        boolean isOffBottomEdge = location.getY() - (spriteHeight / 2) > height;
        // no need to check left edge (always moving rightwards)
        return isOffTopEdge || isOffRightEdge || isOffBottomEdge;
    }

}
