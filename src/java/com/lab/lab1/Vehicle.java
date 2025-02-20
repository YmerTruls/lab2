package src.java.com.lab.lab1;

import java.awt.*;

public abstract class Vehicle implements Movable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed = 0; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    private boolean engineState;

    private double xPos; //X-position
    private double yPos; //Y-position
    private int currentDirection = 1; //current facing direction
    public abstract double speedFactor();

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXPos(){
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }

    private void setPosition(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void moveRelative(Byte xAmount, Byte yAmount) {
        setPosition(getXPos() + xAmount, getYPos() + yAmount);
    }

    public void moveWith(Positionable target) {
        setPosition(target.getXPos(), target.getYPos());
    }

    public String getModelName() {
        return modelName;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }

    public void setEngineState(boolean state){
        engineState = state;
        System.out.println("Engine State: " + engineState);
    }
    public boolean getEngineState(){ return engineState; }
    private void setCurrentDirection(int direction){ currentDirection = direction; }
    public int getCurrentDirection(){
        return currentDirection;
    }
    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    public void turnRight(){
        setCurrentDirection(getCurrentDirection()-1);

        if(getCurrentDirection() == 0){
            setCurrentDirection(4);
        }
    }

    public void turnLeft(){
        setCurrentDirection(getCurrentDirection()+1);

        if(getCurrentDirection() == 5){
            setCurrentDirection(1);
        }
    }

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
        }
    }

    public void gas(double amount){
        if(0 <= amount && amount <= 1 && getEngineState()){
            incrementSpeed(amount);
        }
    }

    public void brake(double amount){
        if(0 <= amount && amount <= 1){
            decrementSpeed(amount);
        }
    }
}
