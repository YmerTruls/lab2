package src.java.com.lab.Factory;
import src.java.com.lab.Controllers.ModelFacade;
import src.java.com.lab.Interfaces.Command;
import src.java.com.lab.lab1.Vehicle;

public class AddCar implements Command {
    private final ModelFacade modelFacade;

    public AddCar(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    @Override
    public void execute() {
        if (modelFacade.getVehicleCount() < 10) {
            Vehicle newVehicle = VehicleFactory.createRandomVehicle();
            modelFacade.addVehicle(newVehicle);
        } else {
            System.out.println("Car limit reached!");
        }
    }
}
