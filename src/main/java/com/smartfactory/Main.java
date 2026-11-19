
package com.smartfactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("SMART FACTORY");
        Label subtitle = new Label("Industrial IoT Dashboard");

        VBox root = new VBox(20);
        root.getChildren().addAll(title, subtitle);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Smart Factory");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
