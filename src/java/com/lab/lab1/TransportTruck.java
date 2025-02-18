package src.java.com.lab.lab1;

import java.awt.*;
import java.util.ArrayList;

public abstract class TransportTruck extends Truck {

    private final int capacity;
    private final ArrayList<Car> loadedCars;
    private final OnOffLift transportLift;

    public TransportTruck(int nrDoors, double enginePower, Color color, String modelName, int capacity, double xPos, double yPos) {
        super(nrDoors, enginePower, color, modelName, xPos, yPos);
        this.loadedCars = new ArrayList<>();
        this.capacity = capacity;
        this.transportLift = new OnOffLift();
        setRampDown(false);
    }

    public void setRampDown(boolean state){
        if(state) {
            if (getCurrentSpeed() == 0) {
                transportLift.lowerRamp();
            }
        }

        else {
            transportLift.raiseRamp();
        }
    }

    public boolean getRampDown(){
        return transportLift.isRampLowered();
    }
    @Override
    public void move(){
        super.move();
        for (Car car : loadedCars) {
            car.moveWith(this);
        }
    }

    @Override
    public void gas(double amount){
        if (!transportLift.isRampLowered()){
            super.gas(amount);
        }
        else{
            System.out.println("Ramp Down, Unable to gas.");
        }
    }

    public ArrayList<Car> getLoadedCars(){
        return loadedCars;
    }

    public void load(Car car){
        if(transportLift.isRampLowered() &&
                loadedCars.size() < capacity &&
                Math.abs(car.getXPos() - getXPos()) < 10 &&
                Math.abs(car.getYPos() - getYPos()) < 10 &&
                car.getCurrentSpeed() == 0 &&
                !loadedCars.contains(car)){

            loadedCars.add(car);
            car.moveWith(this);
            car.setEngineState(false);
        }
    }

    public Car unload(){
        int size = loadedCars.size();
        if(transportLift.isRampLowered() && size > 0){
            Car car = loadedCars.get(size-1);
            loadedCars.remove(size-1);
            car.moveRelative((byte) -5,(byte) -5);
            return car;
        }
        else {
            return null;
        }
   }

}
