package game.Objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aleš tichava
 */
public class BodyPart extends Object{
    

    public void render(Graphics g)
    {        
        g.setColor(Color.BLACK);
        g.fillOval(getX(), getY(), size, size);
    }
    
    
    
}
