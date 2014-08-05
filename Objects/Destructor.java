package game.Objects;

import java.awt.Color;
import java.awt.Graphics;
import game.Game;

/**
 *
 * @author aleš tichava
 */
public class Destructor extends Object implements IGameObject{
       
    @Override
    public void update(Game game)
    {          
        game.getSnake().setDestructionMode(true);
        game.getObjectRegister().removeObject(this);
        game.getDestructionTimer().prolongTime();
        
        game.getObjectGenerator().getGenerator("DestructorGenerator").prolongTime();
    }

    @Override
    public void render(Graphics g)
    {        
        g.setColor(Color.BLUE);
        g.fillRect(this.c.getX(), this.c.getY(), size, size);
    }
        
}