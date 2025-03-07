package src.java.com.lab.Controllers;


import src.java.com.lab.Interfaces.SimulationListener;
import src.java.com.lab.draw.RenderObject;
import src.java.com.lab.draw.View;
import java.util.List;

public class ViewController implements SimulationListener {
    private final ModelFacade modelFacade;
    private final View view;


    public ViewController(ModelFacade simController, View view) {
        this.modelFacade = simController;
        this.view = view;
        simController.addListener(this);
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
        modelFacade.addCar();

    }
    public void onRemoveCarPressed() {
        modelFacade.removeCar();
    }
}
