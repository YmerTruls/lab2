package src.java.com.lab.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DrawPanel extends JPanel {

    private final HashMap<String, BufferedImage> objectImages = new HashMap<>();
    private List<RenderObject> objects;

    public DrawPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
        setBackground(Color.green);
        loadObjectImages();
    }

    public void setRenderObjects(List<RenderObject> objects) {
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

        for (RenderObject object : objects) { // ✅ Now only using RenderObject
            BufferedImage image = objectImages.get(object.getModelName());
            if (image != null) {
                g.drawImage(image, object.getX(), object.getY(), null);
            }
        }
    }
}
