package src.java.com.lab.Interfaces;

import src.java.com.lab.draw.RenderObject;
import java.util.List;

public interface SimulationListener {
    void onSimulationUpdated(List<RenderObject> renderObjects);
}