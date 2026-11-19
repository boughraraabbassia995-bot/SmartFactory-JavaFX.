
package com.smartfactory;

import com.smartfactory.model.Machine;
import com.smartfactory.controller.MachineController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Machine machine = new Machine("Motor 1");
        MachineController controller =
                new MachineController(machine);

        Label title = new Label("SMART FACTORY");
        Label status = new Label("Machine Stopped");
        Label speedLabel = new Label("Speed: 0 RPM");
        Label temperatureLabel = new Label("Temperature: 25 °C");
Label alarmLabel = new Label("Status: STOPPED");

        ToggleButton power = new ToggleButton("OFF");

        Slider speed = new Slider(0, 3000, 0);
        speed.setShowTickLabels(true);
        speed.setShowTickMarks(true);

        ProgressIndicator load = new ProgressIndicator(0);
        Timeline temperatureTimer = new Timeline(
    new KeyFrame(Duration.seconds(2), e -> {

        if (machine.isRunning()) {

            double newTemperature =
                machine.getTemperature() + 2;

            machine.setTemperature(newTemperature);

            temperatureLabel.setText(
                "Temperature: " +
                machine.getTemperature() + " °C"
            );

            alarmLabel.setText(
                "Status: " + machine.getStatus()
            );

            if (machine.getStatus().equals("FAULT")) {

                power.setSelected(false);
                power.setText("OFF");
                speed.setValue(0);

                status.setText("Machine Stopped - FAULT");

                temperatureTimer.stop();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Temperature Alert");
                alert.setHeaderText("Machine Overheated!");
                alert.setContentText(
                    "Temperature exceeded 100 °C. Machine stopped automatically."
                );
                alert.showAndWait();
            }
        }
    })
);

temperatureTimer.setCycleCount(Timeline.INDEFINITE);
        

         
power.setOnAction(e -> {

    if (power.isSelected()) {

        controller.startMachine();
        power.setText("ON");
        status.setText("Machine Running");

    } else {

        Alert alert = new Alert(
            Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Confirmation");
        alert.setHeaderText("Stop the machine?");
        alert.setContentText(
            "Are you sure you want to stop Motor 1?"
        );

        if (alert.showAndWait().orElse(
            ButtonType.CANCEL
        ) == ButtonType.OK) {

            controller.stopMachine();
            power.setText("OFF");
            status.setText("Machine Stopped");
            speed.setValue(0);

        } else {
            power.setSelected(true);
        }
    }
});
 {
            if (power.isSelected()) {
                controller.startMachine();
                power.setText("ON");
                status.setText("Machine Running");
            } else {
                controller.stopMachine();
                power.setText("OFF");
                status.setText("Machine Stopped");
                speed.setDisable(true);
                speed.setValue(0);
            }
        });

        speed.setDisable(true);

        power.selectedProperty().addListener(
            (obs, oldValue, newValue) -> {
                speed.setDisable(!newValue);
            }
        );

        speed.valueProperty().addListener(
            (obs, oldValue, newValue) -> {
                machine.setSpeed(newValue.doubleValue());
                speedLabel.setText(
                    "Speed: " + newValue.intValue() + " RPM"
                );
            }
        );

        VBox root = new VBox(15);
        root.setPadding(new Insets(25));

        root.getChildren().addAll(
            title,
            new Label("Machine: Motor 1"),
            status,
            power,
            new Label("Adjust Speed"),
            speed,
            speedLabel,
            temperatureLabel,
alarmLabel,
            new Label("Machine Load"),
            load
        );

        Scene scene = new Scene(root, 600, 450);

        stage.setTitle("Smart Factory Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

