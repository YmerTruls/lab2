package src.java.com.lab.lab1;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

