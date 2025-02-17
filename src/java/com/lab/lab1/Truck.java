package src.java.com.lab.lab1;

import java.awt.*;

public abstract class Truck extends Vehicle{

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
    public double speedFactor(){
        return getEnginePower() * 0.01;
    }

}
