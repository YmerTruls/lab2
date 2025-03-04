package src.java.com.lab.lab1;

import src.java.com.lab.Interfaces.Direction;
import src.java.com.lab.Interfaces.Movable;

import java.awt.*;

public abstract class Vehicle implements Movable {
    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed = 0;
    private Color color;
    private final String modelName;
    private boolean engineOn;

    private final Position position;
    private Direction direction = Direction.EAST;

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed = 0; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    private boolean engineState;
    public boolean isloaded;

    private double xPos; //X-position
    private double yPos; //Y-position
    public abstract double speedFactor();

    protected Vehicle(int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = new Position(xPos, yPos);
    }

    public abstract double speedFactor();

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

        if (!engineOn) {
            currentSpeed *= 0.95;
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();

    public void setLoaded(boolean state) {
        isloaded = state;
    }
    public boolean isLoaded() {
        return isloaded;
    }
    public int getNrDoors(){
        return nrDoors;
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void gas(double amount) {
        if (engineOn && amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }


    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);

    public void setEngineState(boolean state){
       if (!isLoaded()) {
           engineState = state;
           System.out.println("Engine State: " + engineState);
       }
       else {
           engineState = false;
       }

    }

    public boolean isEngineOn() {
        return engineOn;
    }

    public String getModelName() { return modelName;
    }

    public int getNrDoors() { return nrDoors;
    }

    public double getEnginePower() { return enginePower;
    }


    public double getCurrentSpeed() { return currentSpeed;

    //1: x+
    //2: y+
    //3: x-
    //4: y-
    // when 5 set to one, when 0 set to 4

    public void move(){
        switch(getCurrentDirection()){
            case 1 ->
                    xPos += currentSpeed;
            case 2 ->
                    yPos += currentSpeed;
            case 3 ->
                    xPos -= currentSpeed;
            case 4 ->
                    yPos -= currentSpeed;
            default -> throw new
                    IllegalStateException("Unexpected value (getCurrentDirection): " + getCurrentDirection());
        }
        if (!getEngineState()){
            currentSpeed *= 0.95;
            if (getCurrentSpeed() < 1){
                currentSpeed = 0;
            }
        }

    }

    public Color getColor() { return color;
    }

    public void setColor(Color color) { this.color = color;
    }

}
