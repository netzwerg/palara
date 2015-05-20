package ch.netzwerg.palara;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;

public final class PalaraModel {

    private final ObservableList<Sprite> sprites = FXCollections.observableArrayList();
    private final DoubleProperty crosshairXProperty = new SimpleDoubleProperty();
    private final DoubleProperty crosshairYProperty = new SimpleDoubleProperty();
    private final ObjectProperty<Bounds> crosshairBoundsInParentProperty = new SimpleObjectProperty<>();

    public ObservableList<Sprite> getSprites() {
        return sprites;
    }

    public DoubleProperty crosshairXProperty() {
        return crosshairXProperty;
    }

    public DoubleProperty crosshairYProperty() {
        return crosshairYProperty;
    }

    public ObjectProperty<Bounds> crosshairBoundsInParentProperty() {
        return crosshairBoundsInParentProperty;
    }

}
