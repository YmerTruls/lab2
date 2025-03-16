package src.java.com.lab.State;
import src.java.com.lab.lab1.Scania;

public interface RampState {
    void lowerRamp(Scania scania);
    void raiseRamp(Scania scania);
    boolean isRampLowered();
}
