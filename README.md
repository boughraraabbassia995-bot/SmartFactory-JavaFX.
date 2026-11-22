SmartFactory-JavaFX

Smart Factory – Industrial IoT Dashboard

A JavaFX-based Human-Machine Interface (HMI) for monitoring and controlling industrial machines.

Features

- Monitor three machines: Motor, Pump, and Conveyor.
- Start and stop machines using ToggleButtons.
- Control motor speed from 0 to 3000 RPM.
- Monitor machine temperature and load.
- Display WARNING and FAULT states.
- Automatically stop the motor when temperature exceeds 100°C.
- Acknowledge alarms.
- Simulate live machine data using JavaFX Timeline.

Technologies

- Java 17
- JavaFX 21
- Maven
- CSS

Project Structure

- "src/main/java" – Java source code.
- "src/main/resources" – CSS stylesheet.
- "HMI-Rationale.md" – HMI design explanation.
- "pom.xml" – Maven configuration.

Run the Project

mvn clean javafx:run
