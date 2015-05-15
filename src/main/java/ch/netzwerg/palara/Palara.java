package ch.netzwerg.palara;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Palara extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        PalaraWorld gameWorld = new PalaraWorld();
        Scene scene = new Scene(gameWorld, 800, 600);
        stage.setTitle("Palara");
        stage.setScene(scene);
        stage.show();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameWorld.update(scene.getWidth(), scene.getHeight());
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        Platform.setImplicitExit(true);
        launch(args);
    }

}
