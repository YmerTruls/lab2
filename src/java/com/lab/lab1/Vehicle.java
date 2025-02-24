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
    }

    public void startEngine() {
        engineOn = true;
    }

    public void stopEngine() {
        engineOn = false;
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
    }

    public Color getColor() { return color;
    }

    public void setColor(Color color) { this.color = color;
    }

}
