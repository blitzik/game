package game.Objects;

import game.Enums.MessageType;
import java.awt.Color;
import java.awt.Graphics;
import game.Game;
import game.GameText;

/**
 *
 * @author aleš tichava
 */
public class Poison extends Object implements IGameObject{
    

    @Override
    public void update(Game game)
    {
        if (game.getSnake().isInDestructionMode()) {
            
            game.getObjectRegister().removeObject(this);
            game.addScore(3);
            
        } else {
            
            game.stop();
        }  
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.ORANGE);
        g.fillRect(this.c.getX(), this.c.getY(), size, size);
        g.setColor(Color.BLACK);
        g.drawRect(this.c.getX(), this.c.getY(), size, size);
        //g.drawString("P", x+7, y+15);
    }
       
}