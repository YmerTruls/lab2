package src.java.com.lab.lab1;

public class AngularLift implements AngledRamp {

    private double angle;
    private final double maxAngle;
    private final double minAngle;

    public AngularLift(double minAngle, double maxAngle){
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        angle = minAngle;

    }

    public void lowerRamp() {
        if (angle <= maxAngle){
            angle += 10;
        }
    }
    public void raiseRamp(){
        if (angle > minAngle) {
            angle -= 10;
        }
    }

    @Override
    public boolean isRampLowered() {
        return angle > 0;
    }

    @Override
    public double getAngle() {
        return angle;
    }
}
