
package com.smartfactory;

import com.smartfactory.model.Machine;
import com.smartfactory.controller.MachineController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Machine motor = new Machine("Motor 1");
        Machine pump = new Machine("Pump");
        Machine conveyor = new Machine("Conveyor");

        MachineController motorController =
                new MachineController(motor);

        MachineController pumpController =
                new MachineController(pump);

        MachineController conveyorController =
                new MachineController(conveyor);

        Label title = new Label("SMART FACTORY");
        Label overview = new Label("FACTORY OVERVIEW");

        Label motorStatus = new Label("Motor: STOPPED");
        Label pumpStatus = new Label("Pump: STOPPED");
        Label conveyorStatus = new Label("Conveyor: STOPPED");

        ToggleButton motorPower = new ToggleButton("Motor OFF");
        ToggleButton pumpPower = new ToggleButton("Pump OFF");
        ToggleButton conveyorPower =
                new ToggleButton("Conveyor OFF");

        Label speedLabel = new Label("Speed: 0 RPM");

        Slider speed = new Slider(0, 3000, 0);
        speed.setShowTickLabels(true);
        speed.setShowTickMarks(true);
        speed.setDisable(true);

        Label temperatureLabel =
                new Label("Temperature: 25 °C");

        Label alarmLabel =
                new Label("Status: STOPPED");

        Label loadLabel = new Label("Machine Load: 0%");

        Button acknowledgeButton =
                new Button("Acknowledge Alarm");

        ProgressIndicator load =
                new ProgressIndicator(0);

        // Simulation of temperature and load

        Timeline simulation = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {

                if (motor.isRunning()) {

                    double newTemperature =
                            motor.getTemperature() + 2;

                    motor.setTemperature(newTemperature);

                    temperatureLabel.setText(
                        "Temperature: " +
                        motor.getTemperature() + " °C"
                    );

                    double newLoad =
                            Math.min(motor.getLoad() + 0.05, 1.0);

                    motor.setLoad(newLoad);

                    load.setProgress(newLoad);

                    loadLabel.setText(
                        "Machine Load: " +
                        (int)(newLoad * 100) + "%"
                    );

                    alarmLabel.setText(
                        "Status: " + motor.getStatus()
                    );

                    if (motor.getStatus().equals("WARNING")) {
                        motorStatus.setText(
                            "Motor: WARNING - High Temperature"
                        );
                    }

                    if (motor.getStatus().equals("FAULT")) {

                        motorPower.setSelected(false);
                        motorPower.setText("Motor OFF");

                        motorStatus.setText("Motor: FAULT");

                        speed.setValue(0);

                        simulation.stop();

                        Alert alert = new Alert(
                            Alert.AlertType.WARNING
                        );

                        alert.setTitle("Temperature Alert");
                        alert.setHeaderText("Motor Overheated!");
                        alert.setContentText(
                            "Temperature exceeded 100 °C. " +
                            "Motor stopped automatically."
                        );

                        alert.showAndWait();
                    }
                }
            })
        );

        simulation.setCycleCount(Timeline.INDEFINITE);

        // Speed control

        motorPower.selectedProperty().addListener(
            (obs, oldValue, newValue) -> {
                speed.setDisable(!newValue);
            }
        );

        speed.valueProperty().addListener(
            (obs, oldValue, newValue) -> {

                motor.setSpeed(newValue.doubleValue());

                speedLabel.setText(
                    "Speed: " + newValue.intValue() + " RPM"
                );
            }
        );

        // Motor control

        motorPower.setOnAction(e -> {

            if (motorPower.isSelected()) {

                motorController.startMachine();

                motorPower.setText("Motor ON");
                motorStatus.setText("Motor: RUNNING");
                alarmLabel.setText("Status: RUNNING");

                simulation.playFromStart();

            } else {

                Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION
                );

                alert.setTitle("Confirmation");
                alert.setHeaderText("Stop Motor?");
                alert.setContentText(
                    "Are you sure you want to stop the motor?"
                );

                if (alert.showAndWait().orElse(
                    ButtonType.CANCEL
                ) == ButtonType.OK) {

                    motorController.stopMachine();
                    simulation.stop();

                    motorPower.setText("Motor OFF");
                    motorStatus.setText("Motor: STOPPED");
                    alarmLabel.setText("Status: STOPPED");

                    speed.setValue(0);
                    motor.setLoad(0);
                    load.setProgress(0);
                    loadLabel.setText("Machine Load: 0%");

                } else {
                    motorPower.setSelected(true);
                }
            }
        });

        // Pump control

        pumpPower.setOnAction(e -> {

            if (pumpPower.isSelected()) {
                pumpController.startMachine();
                pumpPower.setText("Pump ON");
                pumpStatus.setText("Pump: RUNNING");
            } else {
                pumpController.stopMachine();
                pumpPower.setText("Pump OFF");
                pumpStatus.setText("Pump: STOPPED");
            }
        });

        // Conveyor control

        conveyorPower.setOnAction(e -> {

            if (conveyorPower.isSelected()) {
                conveyorController.startMachine();
                conveyorPower.setText("Conveyor ON");
                conveyorStatus.setText("Conveyor: RUNNING");
            } else {
                conveyorController.stopMachine();
                conveyorPower.setText("Conveyor OFF");
                conveyorStatus.setText("Conveyor: STOPPED");
            }
        });

        // Acknowledge alarm

        acknowledgeButton.setOnAction(e -> {

            alarmLabel.setText(
                "Alarm Acknowledged - " +
                motor.getStatus()
            );
        });

        // Dashboard layout

        VBox root = new VBox(15);
        root.setPadding(new Insets(25));

        root.getChildren().addAll(
            title,
            overview,

            new Label("MACHINE 1"),
            motorStatus,
            motorPower,

            new Label("MACHINE 2"),
            pumpStatus,
            pumpPower,

            new Label("MACHINE 3"),
            conveyorStatus,
            conveyorPower,

            new Label("MOTOR SPEED"),
            speed,
            speedLabel,

            new Label("TEMPERATURE MONITORING"),
            temperatureLabel,
            alarmLabel,
            acknowledgeButton,

            new Label("MACHINE LOAD"),
            loadLabel,
            load
        );

        Scene scene = new Scene(root, 650, 800);

        stage.setTitle("Smart Factory Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
