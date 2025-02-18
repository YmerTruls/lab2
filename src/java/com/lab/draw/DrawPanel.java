package src.java.com.lab.draw;
import src.java.com.lab.lab1.Vehicle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final HashMap<String, BufferedImage> carImages = new HashMap<>();
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private final HashMap<Vehicle, Point> carPositions = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        loadCarImages();

    }

    // TODO: Make this general for all cars
    public void moveit(Vehicle car, int x, int y){
        carPositions.put(car, new Point(x,y));
        repaint();
    }
    public void setCars(ArrayList<Vehicle> cars) {
        this.cars = cars;
        // Initialize positions if they don't exist
        for (Vehicle car : cars) {
            carPositions.putIfAbsent(car, new Point((int) car.getXPos(), (int) car.getYPos()));
        }
        repaint();
    }
    private void loadCarImages() {
        try {
            carImages.put("Volvo240", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put("Saab95", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put("Scania", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            carImages.put("DAFFXH", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/DAFFXH.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle car : cars) {
            BufferedImage carImage = carImages.get(car.getModelName());
            Point position = carPositions.getOrDefault(car, new Point(0, 0));
            if (carImage != null) {
                g.drawImage(carImage, position.x, position.y, null);
            }
        }
    }
}
