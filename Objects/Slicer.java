package game.Objects;

import java.awt.Color;
import java.awt.Graphics;
import game.Game;
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
        
        if (game.getSnake().isInDestructionMode()) {
            game.getDestructionTimer().prolongTime(2500);
        } else {
            game.getObjectRegister().addGameObject(new Poison());
            game.getObjectRegister().addGameObject(new Poison());
        }

        game.addScore(1);

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.MAGENTA);
        g.fillOval(this.c.getX(), this.c.getY(), size, size);
    }
          
}