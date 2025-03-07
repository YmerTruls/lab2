package src.java.com.lab.Controllers;

import src.java.com.lab.Factory.VehicleFactory;
import src.java.com.lab.Interfaces.HasTurbo;
import src.java.com.lab.draw.*;
import src.java.com.lab.Interfaces.SimulationListener;
import src.java.com.lab.lab1.*;

import java.util.ArrayList;
import java.util.List;

public class ModelFacade {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Workshop<?>> workshops = new ArrayList<>();
    private final List<SimulationListener> listeners = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        notifyListeners();
    }
    public void removeVehicle() {
        vehicles.removeLast();
        notifyListeners();
    }

    public void addWorkshop(Workshop<?> workshop) {
        workshops.add(workshop);
    }

    public void addListener(SimulationListener listener) {
        listeners.add(listener);
    }

    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            handleWorkshopInteraction(vehicle);
            handleWallCollision(vehicle);
        }
        notifyListeners();
    }

    private void handleWallCollision(Vehicle vehicle) {
        Position pos = vehicle.getPosition();
        if (pos.getX() < 0 || pos.getX() > 680) {
            vehicle.turnLeft();
            vehicle.turnLeft();
        }
        if (pos.getY() < 0 || pos.getY() > 560) {
            vehicle.turnLeft();
            vehicle.turnLeft();
        }
    }

    private void handleWorkshopInteraction(Vehicle vehicle) {
        for (Workshop workshop : workshops) {
            if (workshop.canLoad(vehicle)) {
                workshop.load(vehicle);
            }

        }
    }

    private void notifyListeners() {
        List<RenderObject> renderObjects = new ArrayList<>();

        // Add vehicles
        for (Vehicle vehicle : vehicles) {
            Position pos = vehicle.getPosition();
            renderObjects.add(new RenderObject(vehicle.getModelName(), (int) pos.getX(), (int) pos.getY()));
        }

        // Add workshops
        for (Workshop<?> workshop : workshops) {
            Position pos = workshop.getPosition();
            renderObjects.add(new RenderObject("VolvoWorkshop", (int) pos.getX(), (int) pos.getY()));
        }

        for (SimulationListener listener : listeners) {
            listener.onSimulationUpdated(renderObjects);
        }
    }


    public void gasAll(double amount) {
        vehicles.forEach(v -> v.gas(amount));
    }

    public void brakeAll(double amount) {
        vehicles.forEach(v -> v.brake(amount));
    }

    public void setTurbo(boolean on) {
        for (Vehicle object : vehicles) {
            if (object instanceof HasTurbo) {
                if (on){
                    ((HasTurbo) object).setTurboOn();
                }
                else {
                    ((HasTurbo) object).setTurboOff();
                }
            }
        }
    }

    public void setScaniaRamp(boolean lower) {
        for (Vehicle object : vehicles) {
            if (object instanceof Scania) {
                if (lower) {
                    ((Scania) object).setRampdown();
                }
                else {
                    ((Scania) object).setRampup();
                }
            }
        }
    }

    public void startAllEngines() {
        vehicles.forEach(Vehicle::EngineOn);
    }

    public void stopAllEngines() {
        vehicles.forEach(Vehicle::EngineOff);
    }
    public void addCar() {
        if (vehicles.size() < 10) {
            Vehicle newVehicle = VehicleFactory.createRandomVehicle();
            addVehicle(newVehicle);
        } else {
            System.out.println("Car limit reached!");
        }
    }
    public void removeCar() {
        if (!vehicles.isEmpty()) {
            removeVehicle();
        } else {
            System.out.println("No cars to remove!");
        }
    }
}

