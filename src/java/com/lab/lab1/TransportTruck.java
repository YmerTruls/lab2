package src.java.com.lab.lab1;

import java.awt.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public abstract class TransportTruck extends Truck {

    private final int capacity;
    private final List<Car> loadedCars;
    private final OnOffLift transportLift;

    protected TransportTruck(int nrDoors, double enginePower, Color color,
                             String modelName, int capacity, double xPos, double yPos) {
        super(nrDoors, enginePower, color, modelName, xPos, yPos);
        this.capacity = capacity;
        this.loadedCars = new ArrayList<>();
        this.transportLift = new OnOffLift();
        transportLift.raiseRamp();
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            transportLift.lowerRamp();
        }
    }

    public void raiseRamp() {
        transportLift.raiseRamp();
    }

    public boolean isRampLowered() {
        return transportLift.isRampLowered();
    }

    @Override
    public void move() {
        super.move();
        loadedCars.forEach(car -> car.getPosition().set(getPosition().getX(), getPosition().getY()));
    }

    @Override
    public void gas(double amount) {
        if (!isRampLowered()) {
            super.gas(amount);
        }
    }

    public boolean canLoad(Car car) {
        return isRampLowered()
                && loadedCars.size() < capacity
                && car.getCurrentSpeed() == 0
                && Math.abs(car.getPosition().getX() - getPosition().getX()) < 10
                && Math.abs(car.getPosition().getY() - getPosition().getY()) < 10
                && !loadedCars.contains(car);
    }

    public boolean load(Car car) {
        if (canLoad(car)) {
            loadedCars.add(car);
            car.getPosition().set(getPosition().getX(), getPosition().getY());
            car.stopEngine();
            return true;
        }
        return false;
    }

    public boolean canUnload() {
        return isRampLowered() && !loadedCars.isEmpty();
    }

    public Car unload() {
        if (canUnload()) {
            Car car = loadedCars.removeLast();
            car.getPosition().move(-5, -5);
            return car;
        }
        return null;
    }

    public List<Car> getLoadedCars() {
        return Collections.unmodifiableList(loadedCars);
    }
}
