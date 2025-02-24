package src.java.com.lab.Interfaces;
import java.util.List;


public interface SimulationListener {
    void onSimulationUpdated(List<Positionable> posObjects);
}

