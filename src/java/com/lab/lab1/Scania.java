package src.java.com.lab.lab1;

import java.awt.Color;

public class Scania extends Truck {

    private final AngularLift scaniaLift;

    public Scania(){
        super(2,200, Color.YELLOW, "src.java.com.lab.Scania");
        setEngineState(false);
        scaniaLift = new AngularLift(0, 70);
    }
    public double getAngle() {
        return scaniaLift.getAngle();
    }


    public void setRampUp(double amount) {
        if (getCurrentSpeed() == 0) {
            scaniaLift.raiseRamp();
        } else {
            System.out.println("src.java.com.lab.Vehicle moving, cannot raise platform");
        }
    }

    public void setRampDown() {
        scaniaLift.lowerRamp();
    }

    @Override
    public void move(){
        if (scaniaLift.getAngle() == 0) {
            super.move();
        }
        else {
            System.out.println("Platform raised, cannot move.");
        }
    }
}