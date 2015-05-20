package ch.netzwerg.palara;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
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
        Scene scene = new Scene(root, 800, 600);

        PalaraModel model = new PalaraModel();
        PalaraController controller = new PalaraController(scene, model);

        PalaraGamePane gamePane = new PalaraGamePane(model);
        ControlPane controlPane = new ControlPane(model);
        root.getChildren().addAll(gamePane, controlPane);

        stage.setTitle("Palara");
        stage.setScene(scene);
        stage.show();

        controller.startAnimation();
    }

}
