
package com.smartfactory.model;

public class Machine {

    private String name;
    private boolean running;
    private double speed;
    private double load;
    private double temperature;

    public Machine(String name) {
        this.name = name;
        this.running = false;
        this.speed = 0;
        this.load = 0;
        this.temperature = 25;
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
