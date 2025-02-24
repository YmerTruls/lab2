package src.java.com.lab.draw;

import src.java.com.lab.Interfaces.Positionable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DrawPanel extends JPanel {

    private final HashMap<String, BufferedImage> objectImages = new HashMap<>();
    private List<Positionable> objects;

    public DrawPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
        setBackground(Color.green);
        loadObjectImages();
    }

    public void setPosObjects(List<Positionable> objects) {
        this.objects = objects;
        repaint();
    }

    private void loadObjectImages() {
        String[] modelNames = {"Volvo240", "Saab95", "Scania", "DAFFXH", "VolvoWorkshop"};

        for (String model : modelNames) {
            try {
                BufferedImage img = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + model + ".jpg"));
                objectImages.put(model, img);
            } catch (IOException | IllegalArgumentException ex) {
                System.err.println("Image loading failed for: " + model);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (objects == null) return;

        for (Positionable object : objects) {
            BufferedImage image = objectImages.get(object.getModelName());
            if (image != null) {
                int x = (int) Math.round(object.getPosition().getX());
                int y = (int) Math.round(object.getPosition().getY());
                g.drawImage(image, x, y, null);
            }
        }
    }
}
