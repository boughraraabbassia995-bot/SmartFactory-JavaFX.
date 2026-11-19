
package com.smartfactory.controller;

import com.smartfactory.model.Machine;

public class MachineController {

    private Machine machine;

    public MachineController(Machine machine) {
        this.machine = machine;
    }

    public void startMachine() {
        machine.setRunning(true);
    }

    public void stopMachine() {
        machine.setRunning(false);
    }

    public Machine getMachine() {
        return machine;
    }
}
