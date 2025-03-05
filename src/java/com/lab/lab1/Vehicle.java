package src.java.com.lab.lab1;

import src.java.com.lab.Interfaces.Direction;
import src.java.com.lab.Interfaces.Movable;

public abstract class Vehicle implements Movable {
    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed = 0;
    private final String modelName;

    private final Position position;
    private Direction direction = Direction.EAST;
    private boolean engineState;
    public boolean isloaded;

    public abstract double speedFactor();

    protected Vehicle(int nrDoors, double enginePower, String modelName, double xPos, double yPos) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = new Position(xPos, yPos);
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case EAST -> position.move(currentSpeed, 0);
            case WEST -> position.move(-currentSpeed, 0);
            case NORTH -> position.move(0, -currentSpeed);
            case SOUTH -> position.move(0, currentSpeed);
        }

        if (!getEngineState()) {
            currentSpeed *= 0.95;
            if (getCurrentSpeed() < 1) {
                currentSpeed = 0;
            }
        }
    }
    public boolean getEngineState(){
        return engineState;
    }
    public void turnLeft() {
        direction = direction.turnLeft();
    }
    public void setLoaded ( boolean state){
        isloaded = state;
    }
    public boolean isLoaded () {
        return isloaded;}
    public void turnRight () {
        direction = direction.turnRight();
    }
    public void gas ( double amount){
        if (getEngineState() && amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }
    }

    public void brake ( double amount){
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
    }
    private void incrementSpeed ( double amount){
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }
    private void decrementSpeed ( double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }
    public void EngineOn() {
        if (!isLoaded()) {
            engineState = true;
            System.out.println("Engine State: " + true);
        } else {
            engineState = false;
        }
    }
    public void EngineOff(){
        engineState = false;
    }
    public String getModelName () {
        return modelName;
    }
    public int getNrDoors () {
        return nrDoors;
    }
    public double getEnginePower () {
        return enginePower;
    }
    public double getCurrentSpeed () {
        return currentSpeed;
    }
}


