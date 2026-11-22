HMI Rationale – Smart Factory

1. Project Overview

The Smart Factory HMI is an Industrial IoT dashboard developed using JavaFX. Its purpose is to provide a simple interface for monitoring and controlling factory machines.

2. Machine Monitoring

The dashboard displays three machines:

- Motor
- Pump
- Conveyor

Each machine has an independent ON/OFF control and a visible operating status.

3. Control Components

- ToggleButton: Used to start and stop machines.
- Slider: Allows the operator to adjust the motor speed from 0 to 3000 RPM.
- ProgressIndicator: Displays the simulated motor load.
- Confirmation Alert: Requests confirmation before stopping the motor.
- Acknowledge Alarm Button: Allows the operator to acknowledge an alarm.

4. Temperature Monitoring and Safety

The motor temperature is simulated using JavaFX Timeline.

The system uses three operating conditions:

- Temperature up to 90°C: Normal.
- Temperature above 90°C: WARNING.
- Temperature above 100°C: FAULT and automatic motor stop.

5. Visual Design

The interface uses a dark background, clear labels, and contrasting button colors to improve readability and make machine controls easy to identify.

6. Conclusion

The Smart Factory HMI provides a simple and organized interface for machine monitoring, control, and basic safety alerts using JavaFX.
