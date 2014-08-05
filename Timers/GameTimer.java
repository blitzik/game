package game.Timers;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aleš tichava
 */
abstract class GameTimer {
    
    protected int width;
    protected long time;
    
    protected int x;
    protected int y;
    
    public GameTimer(int width)
    {
        this.width = width;
    }
        
    abstract void showTimer(Graphics g, Color color);
    
    public long getRestOfTime()
    {
        return (time - System.currentTimeMillis());
    }
            
}