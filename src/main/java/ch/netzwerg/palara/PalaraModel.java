package ch.netzwerg.palara;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class PalaraModel {

    private final ObservableList<Sprite> sprites = FXCollections.observableArrayList();
    private final DoubleProperty crosshairXProperty = new SimpleDoubleProperty();
    private final DoubleProperty crosshairYProperty = new SimpleDoubleProperty();

    public ObservableList<Sprite> getSprites() {
        return sprites;
    }

    public DoubleProperty crosshairXProperty() {
        return crosshairXProperty;
    }

    public DoubleProperty crosshairYProperty() {
        return crosshairYProperty;
    }

}
