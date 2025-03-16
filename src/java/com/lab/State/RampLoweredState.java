package src.java.com.lab.State;

import src.java.com.lab.lab1.Scania;

public class RampLoweredState implements RampState {

    @Override
    public void lowerRamp(Scania scania) {
        System.out.println("Ramp is already lowered.");
    }

    @Override
    public void raiseRamp(Scania scania) {
        System.out.println("Raising the ramp...");
        scania.setRampState(new RampRaisedState());
    }

    @Override
    public boolean isRampLowered() {
        return true;
    }
}
