package ch.netzwerg.palara;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Palara extends Application {

    public static void main(String[] args) {
        Platform.setImplicitExit(true);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        PalaraModel model = new PalaraModel();
        PalaraWorld gameWorld = new PalaraWorld(model);
        ControlPane controlPane = new ControlPane(model);
        root.getChildren().addAll(gameWorld, controlPane);

        Scene scene = new Scene(root, 800, 600);

        scene.setOnKeyPressed(e -> {
            if (KeyCode.SPACE == e.getCode()) {
                gameWorld.onShot();
            }
        });

        stage.setTitle("Palara");
        stage.setScene(scene);
        stage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameWorld.update();
            }
        };
        gameLoop.start();
    }

}
