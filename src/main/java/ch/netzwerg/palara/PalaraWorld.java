package ch.netzwerg.palara;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public final class PalaraWorld extends Pane {

    private static final int BIRTH_INTERVAL_MS = 300;

    private final PalaraModel model;

    public PalaraWorld() {
        this.model = new PalaraModel();
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
    }

    private void spawnSprites(ObservableList<Sprite> sprites) {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(BIRTH_INTERVAL_MS), e -> sprites.add(createSprite()));
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Sprite createSprite() {
        Sprite sprite = new Sprite(model);
        sprite.setOnMouseClicked(e -> model.getSprites().remove(sprite));
        return sprite;
    }

    private void onSpriteAdded(Sprite sprite) {
        getChildren().add(sprite);
    }

    private void onSpriteRemoved(Sprite sprite) {
        getChildren().remove(sprite);
    }

    public void update() {
        this.model.getSprites().stream().forEach(s -> s.update(getWidth(), getHeight()));
    }

}
