package src.java.com.lab.Application;

import src.java.com.lab.Controllers.ModelFacade;
import src.java.com.lab.Controllers.ViewController;
import src.java.com.lab.Factory.WorkshopFactory;
import src.java.com.lab.draw.*;
import src.java.com.lab.lab1.*;
import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModelFacade modelFacade = new ModelFacade();
            Workshop<Volvo240> new_Workshop = WorkshopFactory.createWorkshop( "VolvoWorkshop", 200, 200);
            modelFacade.addWorkshop(new_Workshop);
            View view = new View("Car Simulator", null);
            ViewController viewController = new ViewController(modelFacade, view);
            view.setViewController(viewController);

            modelFacade.addListener(viewController);
            modelFacade.timer();
        });
    }
}

