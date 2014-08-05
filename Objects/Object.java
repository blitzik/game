package game.Objects;

/**
 * Základní objekt, od kterého budou
 * dědit další objekty, jako jsou had,
 * bonus apod.
 * 
 * @author aleš tichava
 */

public abstract class Object{
    
    protected Coordination c = new Coordination();
        
    // Výška a šířka objektu
    public static final int size = 20;
    
        
    public void setX(int x)
    {        
        this.c.setX(x);
    }
    
    public void setY(int y)
    {
        this.c.setY(y);
    }
    
    public int getX()
    {
        return this.c.getX();
    }
    
    public int getY()
    {
        return this.c.getY();
    }
    
    public void setCoordinations(Coordination co)
    {
        this.c = co;
    }
    
    public Coordination getCoordinations()
    {
        return this.c;
    }
    
    
    
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
        
}