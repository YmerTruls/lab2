package src.java.com.lab.Controllers;


public class ViewController {
    private final SimulationController simulationController;

    public ViewController(SimulationController simController) {
        this.simulationController = simController;
    }

    public void onGasPressed(int gasAmount) {
        simulationController.gasAll(gasAmount / 100.0);
    }

    public void onBrakePressed(int brakeAmount) {
        simulationController.brakeAll(brakeAmount / 100.0);
    }

    public void onTurboPressed(boolean enabled) {
        simulationController.setTurbo(enabled);
    }

    public void onRampControlPressed(boolean lowered) {
        simulationController.setScaniaRamp(lowered);
    }

    public void onStartPressed() {
        simulationController.startAllEngines();
    }

    public void onStopPressed() {
        simulationController.stopAllEngines();
    }
}
