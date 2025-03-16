package src.java.com.lab.lab1;


import src.java.com.lab.Interfaces.Ramp;
import src.java.com.lab.State.RampState;
import src.java.com.lab.State.RampRaisedState;
import java.awt.Color;

public class Scania extends Truck implements Ramp {

    private RampState rampState;

    public Scania(double xPos, double yPos) {
        super(2, 200, Color.YELLOW, "Scania", xPos, yPos);
        rampState = new RampRaisedState();
    }

    public void setRampState(RampState newState) {
        this.rampState = newState;
    }

    @Override
    public void lowerRamp() {
        rampState.lowerRamp(this);
    }

    @Override
    public void raiseRamp() {
        rampState.raiseRamp(this);
    }

    @Override
    public boolean isRampLowered() {
        return rampState.isRampLowered();
    }

    @Override
    public void move() {
        if (!isRampLowered()) {
            super.move();
        } else {
            System.out.println("Cannot move while ramp is lowered.");
        }
    }
}
