package ch.netzwerg.palara;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public final class PalaraGamePane extends Pane {

    private final Crosshair crosshair;

    public PalaraGamePane(PalaraModel model) {
        ObservableList<Sprite> sprites = model.getSprites();
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

        this.crosshair = new Crosshair(model);
        getChildren().add(this.crosshair);
    }

    private void onSpriteAdded(Sprite sprite) {
        this.getChildren().add(sprite);
        this.crosshair.toFront();
    }

    private void onSpriteRemoved(Sprite sprite) {
        this.getChildren().remove(sprite);
    }

}