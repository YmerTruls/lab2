package src.java.com.lab.Factory;
import src.java.com.lab.lab1.*;

import java.util.Random;

public class VehicleFactory {
    private static final Random random = new Random();
    private static final int MIN_X = 50;
    private static final int MAX_X = 750;
    private static final int MIN_Y = 50;
    private static final int MAX_Y = 500;

    public static Vehicle createVehicle(String type, double x, double y) {
        return switch (type) {
            case "Volvo240" -> new Volvo240(x, y);
            case "Saab95" -> new Saab95(x, y);
            case "Scania" -> new Scania(x, y);
            case "DAFFXH" -> new DAFFXH(x, y);
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }
    public static Vehicle createRandomVehicle() {
        double x = MIN_X + random.nextInt(MAX_X - MIN_X);
        double y = MIN_Y + random.nextInt(MAX_Y - MIN_Y);

        String[] vehicleTypes = {"Volvo240", "Saab95", "Scania", "DAFFXH"};
        return createVehicle(vehicleTypes[random.nextInt(vehicleTypes.length)], x, y);
    }
}
