package src.java.com.lab.lab1;

import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(double xPos, double yPos) {
        super(4, 100, Color.black, "Volvo240", xPos, yPos);
    }
    public double speedFactor(){
    return getEnginePower() * 0.01 * trimFactor;
    }
}
