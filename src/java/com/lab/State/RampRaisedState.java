package src.java.com.lab.State;

import src.java.com.lab.lab1.Scania;

public class RampRaisedState implements RampState {

    @Override
    public void lowerRamp(Scania scania) {
        if (scania.getCurrentSpeed() == 0) {
            System.out.println("Lowering the ramp...");
            scania.setRampState(new RampLoweredState());
        } else {
            System.out.println("Cannot lower ramp while vehicle is moving.");
        }
    }

    @Override
    public void raiseRamp(Scania scania) {
        System.out.println("Ramp is already raised.");
    }

    @Override
    public boolean isRampLowered() {
        return false;
    }
}
