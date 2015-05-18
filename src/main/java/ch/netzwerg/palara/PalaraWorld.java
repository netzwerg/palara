package ch.netzwerg.palara;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public final class PalaraWorld extends Pane {

    private static final int BIRTH_INTERVAL_MS = 300;

    private final PalaraModel model;
    private final Pane spritePane;
    private final Crosshair crosshair;

    public PalaraWorld(PalaraModel model) {
        this.model = model;
        this.spritePane = new Pane();

        ObservableList<Sprite> sprites = this.model.getSprites();
        sprites.addListener((ListChangeListener<Sprite>) change -> {
            while (change.next()) {
                for (Sprite removedSprite : change.getRemoved()) {
                    onSpriteRemoved(removedSprite);
                }
                for (Sprite addedSprite : change.getAddedSubList()) {
                    onSpriteAdded(addedSprite);
                }
            }
        });


        spawnSprites(sprites);

        this.crosshair = new Crosshair(model);
        getChildren().add(new StackPane(spritePane, crosshair));
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

    private void onSpriteAdded(Sprite sprite) {
        this.spritePane.getChildren().add(sprite);
    }

    private void onSpriteRemoved(Sprite sprite) {
        this.spritePane.getChildren().remove(sprite);
    }

    public void update() {
        this.model.getSprites().stream().forEach(s -> s.update(getWidth(), getHeight()));
    }

    public void onShot() {
        this.model.getSprites().forEach(sprite -> {
            if (sprite.getBoundsInParent().intersects(this.crosshair.getBoundsInParent())) {
                Platform.runLater(() -> this.model.getSprites().remove(sprite));
            }
        });
    }

}
