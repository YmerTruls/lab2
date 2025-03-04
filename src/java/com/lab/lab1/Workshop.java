package src.java.com.lab.lab1;

import src.java.com.lab.Interfaces.Positionable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Workshop<T extends Vehicle> implements Positionable {

    private final int capacity;
    private final Position position;
    private final List<T> loadedVehicles;
    private final String name;

    protected Workshop(int capacity, double xPos, double yPos, String name) {
        this.capacity = capacity;
        this.position = new Position(xPos, yPos);
        this.name = name;
        this.loadedVehicles = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public String getModelName() {
        return name;
    }

    public boolean canLoad(T vehicle) {
        return loadedVehicles.size() < capacity
                && Math.abs(vehicle.getPosition().getX() - getPosition().getX()) < 50
                && Math.abs(vehicle.getPosition().getY() - getPosition().getY()) < 50
                && vehicle.getCurrentSpeed() == 0
                && !loadedVehicles.contains(vehicle);
    }

    public boolean load(T vehicle) {
        if (canLoad(vehicle)) {
            loadedVehicles.add(vehicle);
            vehicle.getPosition().set(getPosition().getX(), getPosition().getY());
            vehicle.stopEngine();
            vehicle.setEngineState(false);
            vehicle.setLoaded(true);  
            return true;
        }
        return false;
    }

    public boolean unload(T vehicle) {
        if (loadedVehicles.remove(vehicle)) {
            vehicle.getPosition().move(-5, -5); // Move vehicle slightly away from workshop
            vehicleInWorkshop.setLoaded(false);
            return true;

        }
        return false;
    }

    public List<T> getLoadedVehicles() {
        return Collections.unmodifiableList(loadedVehicles);
    }
}
