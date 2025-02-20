package src.java.com.lab.draw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.java.com.lab.lab1.*;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Positionable> posObjects = new ArrayList<>();
    ArrayList<VolvoWorkshop> workshops = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.posObjects.add(new Volvo240(0, 0));
        cc.posObjects.add(new Saab95(0, 60));
        cc.posObjects.add(new Scania(0, 120));
        cc.posObjects.add(new DAFFXH(0, 180));
        VolvoWorkshop volvoWorkshop = new VolvoWorkshop(600, 0);
        cc.posObjects.add(volvoWorkshop);
        cc.workshops.add(volvoWorkshop);


        // Start a new view and send a reference of self
        cc.frame = new CarView("MulleHowToMeck-Sim 6.9", cc);
        cc.frame.updateCars(cc.posObjects);


        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Positionable object : posObjects) {

                if (object instanceof Vehicle) {
                    turnOnWall(object);
                    ((Vehicle) object).move();
                }
                loadWorkshop(object);
                int x = (int) Math.round(object.getXPos());
                int y = (int) Math.round(object.getYPos());
                frame.drawPanel.moveit(object, x, y);

            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Positionable object : posObjects) {
            if (object instanceof Vehicle) {
                ((Vehicle) object).gas(gas);
            }
        }
    }

    void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (Positionable object : posObjects) {
            if (object instanceof Vehicle) {
                ((Vehicle) object).brake(gas);
            }
        }
    }

    void startButton() {
        for (Positionable object : posObjects) {
            if (object instanceof Vehicle) {
                ((Vehicle) object).setEngineState(true);
            }
        }
    }
    void stopButton () {
        for (Positionable object : posObjects) {
            if (object instanceof Vehicle) {
                ((Vehicle) object).setEngineState(false);
                }
            }
        }
        void turboOn () {
            for (Positionable object : posObjects) {
                if (object instanceof HasTurbo) {
                    ((HasTurbo) object).setTurboOn();
                }

            }
        }
        void turboOff () {
            for (Positionable object : posObjects) {
                if (object instanceof HasTurbo) {
                    ((HasTurbo) object).setTurboOff();
                }
            }
        }
        void raiseBed () {
            for (Positionable object : posObjects) {
                if (object instanceof Scania) {
                    ((Scania) object).setRampup();
                }
            }
        }

        void lowerBed () {
            for (Positionable object : posObjects) {
                if (object instanceof Scania) {
                    ((Scania) object).setRampdown();
                }
            }
        }

        void turnOnWall(Positionable object) {
            if (object instanceof Vehicle) {
                if (object.getXPos() > frame.getDrawPanelX() - 120) {
                    ((Vehicle) object).turnLeft();
                    ((Vehicle) object).turnLeft();
                }
                if (object.getXPos() < 0) {
                    ((Vehicle) object).turnRight();
                    ((Vehicle) object).turnRight();
                }
                if (object.getYPos() > frame.getDrawPanelY()) {
                    ((Vehicle) object).turnLeft();
                    ((Vehicle) object).turnLeft();
                }
                if (object.getYPos() < 0) {
                    ((Vehicle) object).turnRight();
                    ((Vehicle) object).turnRight();
                }
            }
        }

        void loadWorkshop(Positionable object){
            if (object instanceof Volvo240 car) {
                for (VolvoWorkshop workshop : workshops) {
                    workshop.load(car);

                }
            }
        }
    }
