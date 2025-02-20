package src.java.com.lab.draw;
import src.java.com.lab.lab1.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final HashMap<String, BufferedImage> posObjectImages = new HashMap<>();
    private ArrayList<Positionable> posObjects = new ArrayList<>();
    private final HashMap<Positionable, Point> posObjectsPositions = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        loadCarImages();
    }

    // TODO: Make this general for all cars
    public void moveit(Positionable object, int x, int y){
        posObjectsPositions.put(object, new Point(x,y));
        repaint();
    }
    public void setPosObjects(ArrayList<Positionable> objects) {
        this.posObjects = objects;
        // Initialize positions if they don't exist
        for (Positionable object : objects) {
            posObjectsPositions.putIfAbsent(object, new Point((int) object.getXPos(), (int) object.getYPos()));
        }
        repaint();
    }

    private void loadCarImages() {
        try {
            posObjectImages.put("Volvo240", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            posObjectImages.put("Saab95", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            posObjectImages.put("Scania", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            posObjectImages.put("DAFFXH", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/DAFFXH.jpg")));
            posObjectImages.put("VolvoWorkshop", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoWorkshop.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Positionable object : posObjects) {
            BufferedImage carImage = posObjectImages.get(object.getModelName());
            Point position = posObjectsPositions.getOrDefault(object, new Point(0, 0));
            if (carImage != null) {
                g.drawImage(carImage, position.x, position.y, null);
            }
        }
    }
}
