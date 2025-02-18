package src.java.com.lab.lab1;
import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;

    public Saab95(double xPos, double yPos){
        super(2, 125, Color.red,
                "Saab95", xPos, yPos);
        setTurboOff();
        setEngineState(false);
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
