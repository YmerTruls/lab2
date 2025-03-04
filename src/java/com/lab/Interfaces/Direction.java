package src.java.com.lab.Interfaces;

public enum Direction {
    EAST, SOUTH, WEST, NORTH;

    public Direction turnLeft() {
        return values()[(this.ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        return values()[(this.ordinal() + 1) % 4];
    }
}
