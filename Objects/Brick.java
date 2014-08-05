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
public class Brick extends Object implements IGameObject{
        
    public Brick(int x, int y)
    {
        this.c.setX(x);
        this.c.setY(y);
    }

    @Override
    public void update(Game game)
    {
        GameText.setText("GAME OVER", 200, MessageType.BAD);
        game.stop();
    }

    @Override
    public void render(Graphics g)
    {        
        g.setColor(Color.GRAY);
        g.fillRect(this.c.getX(), this.c.getY(), size, size);
        g.setColor(Color.BLACK);
        g.drawRect(this.c.getX(), this.c.getY(), size, size);
    }
  
}