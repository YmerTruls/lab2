package src.java.com.lab.lab1;
import java.util.ArrayList;

public abstract class Workshop<T extends Vehicle> implements Positionable {

    private final int capacity;
    private final double XPos;
    private final double YPos;
    private final ArrayList<T> loadedCars;
    private final String name;

    public Workshop(int capacity, double XPos, double YPos, String name) {
        this.loadedCars = new ArrayList<>();
        this.capacity = capacity;
        this.XPos = XPos;
        this.YPos = YPos;
        this.name = name;

    }

    public double getYPos() {
        return YPos;
    }
    public double getXPos() {
        return XPos;
    }


    public void load(T vehicleInWorkshop) {
        if(loadedCars.size() < capacity &&
                Math.abs(vehicleInWorkshop.getXPos() - getXPos()) < 50 &&
                Math.abs(vehicleInWorkshop.getYPos() - getYPos()) < 50 &&
                vehicleInWorkshop.getCurrentSpeed() == 0 &&
                !loadedCars.contains(vehicleInWorkshop)) {
            System.out.println("Car Loaded");

            loadedCars.add(vehicleInWorkshop);
            vehicleInWorkshop.moveWith(this);
            vehicleInWorkshop.setEngineState(false);
            vehicleInWorkshop.setLoaded(true);
        }
    }

    public T unload(T vehicleInWorkshop) {
        if (loadedCars.remove(vehicleInWorkshop)) {
            vehicleInWorkshop.moveRelative((byte) -5, (byte) -5);
            vehicleInWorkshop.setLoaded(false);
            return vehicleInWorkshop;
        }
        else{
            System.out.println("Car not found in workshop");
            return null;
        }
    }

    public ArrayList<T> getLoadedCars() {
        return loadedCars;
    }
    public String getModelName(){
        return name;
    }
}
