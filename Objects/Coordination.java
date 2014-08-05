package game.Objects;

/**
 *
 * @author aleš tichava
 */
public class Coordination {
    
    private int x;
    private int y;
    
    public Coordination() {}
    
    public Coordination(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
