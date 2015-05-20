package ch.netzwerg.palara;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
        KeyFrame keyFrame = new KeyFrame(Duration.millis(BIRTH_INTERVAL_MS), e -> {
            Sprite sprite = new Sprite(model);
            sprite.setOnMouseClicked(e1 -> model.getSprites().remove(sprite));
            sprites.add(sprite);
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateSprites() {
        this.model.getSprites().stream().forEach(s -> s.update(scene.getWidth(), scene.getHeight()));
    }

}
