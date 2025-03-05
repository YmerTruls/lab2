package src.java.com.lab.lab1;

import java.awt.Color;

public class Scania extends Truck {

    private final AngularLift scaniaLift;

    public Scania(double xPos, double yPos){
        super(2,200,
                Color.YELLOW,
                "Scania",
                xPos, yPos);
        //System.out.println("Scania Initiated");
        scaniaLift = new AngularLift(0, 70);
    }
    public double getAngle() {
        return scaniaLift.getAngle();
    }


    public void setRampdown() {
        if (getCurrentSpeed() == 0) {
            scaniaLift.lowerRamp();
        } else {
            System.out.println("Vehicle moving, cannot lower platform");
        }
    }

    public void setRampup() {
        scaniaLift.raiseRamp();
    }

    @Override
    public void move(){
        if (getAngle() == 0) {
            super.move();
        }
        else {
            System.out.println("Platform lowered, cannot move.");
        }
    }
}