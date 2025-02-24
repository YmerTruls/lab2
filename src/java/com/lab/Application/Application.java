package src.java.com.lab.Application;

import src.java.com.lab.Controllers.SimulationController;
import src.java.com.lab.Controllers.ViewController;
import src.java.com.lab.draw.*;
import src.java.com.lab.lab1.*;
import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationController simulationController = new SimulationController();

            simulationController.addVehicle(new Volvo240(0, 0));
            simulationController.addVehicle(new Saab95(0, 60));
            simulationController.addVehicle(new Scania(0, 120));
            simulationController.addVehicle(new DAFFXH(0, 180));
            simulationController.addWorkshop(new VolvoWorkshop(600, 0));

            ViewController viewController = new ViewController(simulationController);
            CarView view = new CarView("Car Simulator", viewController);

            simulationController.addListener(view);

            Timer timer = new Timer(50, e -> simulationController.update());
            timer.start();
        });
    }
}

