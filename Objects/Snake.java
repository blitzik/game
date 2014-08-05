package game.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import game.Enums.Direction;
import game.Game;
import java.util.LinkedList;

/**
 *
 * @author aleš tichava
 */
public class Snake extends Object{
    
    private Direction direction = Direction.RIGHT;
    
    private LinkedList<BodyPart> body = null;
    
    private boolean canDestructObject = false;
        
    
    public Snake()
    {
        this.c.setX(20);
        this.c.setY(240);
        this.body = new LinkedList();
    }
    
    public void setDestructionMode(boolean b)
    {
        canDestructObject = b;
    }
    
    public boolean isInDestructionMode()
    {
        return canDestructObject;
    }
        
    public void update(Game game) {

        // posun souřadnic těla hada
        // musí být první, vychází ze souřadnic hlavy
        int prevX = this.c.getX();
        int prevY = this.c.getY();
        int temp;
        
        for (BodyPart o : body) {
            
            temp = o .getX();
            o.setX(prevX);
            prevX = temp;
            
            temp = o.getY();
            o.setY(prevY);
            prevY = temp;
            
        }
        
        switch (direction) {
            
            case UP: 
                        this.c.setY(this.c.getY() - size); 
                        break;
            case DOWN: 
                        this.c.setY(this.c.getY() + size);  
                        break;
            case LEFT: 
                        this.c.setX(this.c.getX() - size);  
                        break;
            case RIGHT: 
                        this.c.setX(this.c.getX() + size);  
                        break;       
        }
                                  
    }

    public void render(Graphics g) {
          
        Graphics2D g2d = (Graphics2D) g;
        
        // Vykreslení těla hada
        for (BodyPart bp : body) {
            bp.render(g);
        }
        
        // Vykreslení hlavy hada
                      
        if (canDestructObject) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.fillRect(this.c.getX(), this.c.getY(), size, size);
    
    }
    
    public void removeLastPartOfBody()
    {            
        this.getBody().remove(this.getBody().size()-1);      
    }
    
    public void addPartOfBody(BodyPart go)
    {                
        // nastavit souradnice na hlavu hada,
        // tim se zobrazi dalsi clanek az po 
        // celkovem prejeti bonusu
        go.setX(this.getX());
        go.setY(this.getY());
        this.body.add(0, go);       
    }
    
    public void setDirection(Direction d)
    {
        this.direction = d;
    }
    
    public Direction getDirection()
    {
        return this.direction;
    }
    
    public LinkedList<BodyPart> getBody()
    {
        return this.body;
    }
        
}
