package ch.netzwerg.palara;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class PalaraModel {

    private final ObservableList<Sprite> sprites = FXCollections.observableArrayList();

    public ObservableList<Sprite> getSprites() {
        return sprites;
    }

}
