package src.java.com.lab.Factory;

import src.java.com.lab.Controllers.ModelFacade;
import src.java.com.lab.Interfaces.Command;

public class RemoveCar implements Command {
    private final ModelFacade modelFacade;

    public RemoveCar(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    @Override
    public void execute() {
        if (modelFacade.getVehicleCount() > 0) {
            modelFacade.removeVehicle();
        } else {
            System.out.println("No cars to remove!");
        }
    }
}
