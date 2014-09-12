package game.Objects;

import java.awt.Color;
import java.awt.Graphics;
import game.Enums.MessageType;
import game.Game;
import game.GameText;

/**
 *
 * @author aleš tichava
 */
public class Apple extends Object implements IGameObject{
            
        
    @Override
    public void update(Game game)
    {
        game.getLifeTimer().prolongTime(4000);
        
        game.getSnake().addPartOfBody(new BodyPart());
        
        game.getObjectRegister().changeObjectLocation(this);
        
        game.addScore(1);
        
        if (game.getSnake().getBody().size() % 3 == 0) {
            game.getObjectRegister().addGameObject(new Poison());
            game.addScore(2);
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(new Color(0, 150, 0));
        g.fillOval(this.c.getX(), this.c.getY(), size, size);
    }
          
}