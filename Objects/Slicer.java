package game.Objects;

import game.Enums.MessageType;
import java.awt.Color;
import java.awt.Graphics;
import game.Game;
import game.GameText;
import static game.Objects.Object.size;

/**
 *
 * @author aleš tichava
 */
public class Slicer extends Object implements IGameObject{
            
        
    @Override
    public void update(Game game)
    {
        game.getObjectRegister().removeObject(this);
        game.getLifeTimer().prolongTime(2000);
        
        GameText.setText("+1", 500, MessageType.BONUS);
        game.addScore(1);

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.MAGENTA);
        g.fillOval(this.c.getX(), this.c.getY(), size, size);
    }
          
}