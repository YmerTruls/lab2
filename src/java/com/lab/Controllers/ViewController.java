package src.java.com.lab.Controllers;

import src.java.com.lab.Interfaces.Command;
import src.java.com.lab.Interfaces.SimulationListener;
import src.java.com.lab.draw.RenderObject;
import src.java.com.lab.draw.View;
import java.util.List;
import src.java.com.lab.Factory.*;

/**
 * ViewController now listens for simulation updates and forwards them to CarView.
 */
public class ViewController implements SimulationListener {
    private final ModelFacade modelFacade;
    private final View view;
    private final Command addCar;
    private final Command removeCar;

    public ViewController(ModelFacade simController, View view) {
        this.modelFacade = simController;
        this.view = view;
        this.addCar = new AddCar(modelFacade);
        this.removeCar = new RemoveCar(modelFacade);
        simController.addListener(this); // Register this as a listener
    }

    @Override
    public void onSimulationUpdated(List<RenderObject> renderObjects) {
        view.updateView(renderObjects);
    }


    public void onGasPressed(int gasAmount) {
        modelFacade.gasAll(gasAmount / 100.0);
    }

    public void onBrakePressed(int brakeAmount) {
        modelFacade.brakeAll(brakeAmount / 100.0);
    }

    public void onTurboPressed(boolean enabled) {
        modelFacade.setTurbo(enabled);
    }

    public void onRampControlPressed(boolean lowered) {
        modelFacade.setScaniaRamp(lowered);
    }

    public void onStartPressed() {
        modelFacade.startAllEngines();
    }

    public void onStopPressed() {
        modelFacade.stopAllEngines();
    }

    public void onAddCarPressed() {
        addCar.execute();

    }
    public void onRemoveCarPressed() {
        removeCar.execute();
    }
}
