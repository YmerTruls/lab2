package src.java.com.lab.Application;

import src.java.com.lab.Controllers.ModelFacade;
import src.java.com.lab.Controllers.ViewController;
import src.java.com.lab.Factory.VehicleFactory;
import src.java.com.lab.draw.*;
import src.java.com.lab.lab1.*;
import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModelFacade modelFacade = new ModelFacade();

            View view = new View("Car Simulator", null);
            ViewController viewController = new ViewController(modelFacade, view);
            view.setViewController(viewController);

            modelFacade.addListener(viewController);

            Timer timer = new Timer(50, _ -> modelFacade.update());
            timer.start();
        });
    }
}

