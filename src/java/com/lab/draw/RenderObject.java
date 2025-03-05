package src.java.com.lab.draw;

public class RenderObject {
    private final String modelName;
    private final int x, y;

    public RenderObject(String modelName, int x, int y) {
        this.modelName = modelName;
        this.x = x;
        this.y = y;
    }

    public String getModelName() {
        return modelName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

