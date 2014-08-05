package game.Timers;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aleš tichava
 */
public class Timer extends GameTimer{
     
    protected int duration; // life duration
    
    
    public Timer(int width, int duration, int x, int y)// 23, 18
    {
        super(width);
        
        this.time = System.currentTimeMillis() + duration;
        this.duration = duration;
        
        this.x = x;
        this.y = y;
    }
    
    public void prolongTime()
    {
        time = System.currentTimeMillis() + duration;
    }
    
    public void prolongTime(int duration)
    {
        if ((time + duration - System.currentTimeMillis()) > this.duration) {
            prolongTime();    
        } else {
            time += duration;
        }
    }
               
    @Override
    public void showTimer(Graphics g, Color color)
    {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width+6, 13);
            
        // Max. delka timeru je 100px. Cas je 10s(10000ms)
        // Pro odebrani 1 px je potreba 100ms(10000/100)
        
        int px = (int) (time - System.currentTimeMillis()) / (duration / width);
        g.setColor(color);
        g.fillRect(x+3, y+3, px, 8);
    }
}