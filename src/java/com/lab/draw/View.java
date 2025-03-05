package src.java.com.lab.draw;

import src.java.com.lab.Controllers.ViewController;
import javax.swing.*;
import java.awt.*;

import java.util.List;

public class View extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private ViewController viewController;
    private final DrawPanel drawPanel;

    private int gasAmount;

    public View(String title, ViewController controller) {
        this.viewController = controller;
        this.drawPanel = new DrawPanel(WIDTH, HEIGHT - 240);

        initUI(title);
    }

    private void initUI(String title) {
        setTitle(title);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        add(drawPanel);

        JPanel gasPanel = new JPanel(new BorderLayout());
        gasPanel.add(new JLabel("Amount of gas"), BorderLayout.NORTH);
        JSpinner gasSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());
        gasPanel.add(gasSpinner, BorderLayout.SOUTH);
        add(gasPanel);

        JPanel controlPanel = new JPanel(new GridLayout(2, 4));
        addButtonsToControlPanel(controlPanel);
        controlPanel.setPreferredSize(new Dimension(WIDTH / 2 + 4, 200));
        controlPanel.setBackground(Color.CYAN);
        add(controlPanel);

        JButton startButton = createStyledButton("Start all cars", Color.BLUE, Color.GREEN);
        JButton stopButton = createStyledButton("Stop all cars", Color.RED, Color.BLACK);
        startButton.addActionListener(_ -> viewController.onStartPressed());
        stopButton.addActionListener(_ -> viewController.onStopPressed());
        add(startButton);
        add(stopButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addButtonsToControlPanel(JPanel controlPanel) {
        JButton gasButton = new JButton("Gas");
        JButton brakeButton = new JButton("Brake");
        JButton turboOnButton = new JButton("Saab Turbo on");
        JButton turboOffButton = new JButton("Saab Turbo off");
        JButton raiseBedButton = new JButton("Raise Lift Bed");
        JButton lowerBedButton = new JButton("Lower Lift Bed");

        gasButton.addActionListener(_-> viewController.onGasPressed(gasAmount));
        brakeButton.addActionListener(_ -> viewController.onBrakePressed(gasAmount));
        turboOnButton.addActionListener(_-> viewController.onTurboPressed(true));
        turboOffButton.addActionListener(_-> viewController.onTurboPressed(false));
        raiseBedButton.addActionListener(_-> viewController.onRampControlPressed(false));
        lowerBedButton.addActionListener(_ -> viewController.onRampControlPressed(true));

        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(raiseBedButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setPreferredSize(new Dimension(WIDTH / 5 - 15, 200));
        return button;
    }

    public void updateView(List<RenderObject> renderObjects) {
        drawPanel.setRenderObjects(renderObjects);
    }
    public void setViewController(ViewController controller) {
        this.viewController = controller;
    }
}
