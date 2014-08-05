package game.Objects;

import game.Game;
import java.awt.Graphics;

/**
 * Rozhraní pro vykreslitelný herní objekt,
 * který nějakým způsobem může ovlivňovat
 * průběh hry. (Jídlo, Jed apod...)
 * 
 * @author aleš tichava
 */
public interface IGameObject {
    
    public void update(Game game);
    
    public void render(Graphics g);

    public void setX(int x);

    public void setY(int y);

    public Coordination getCoordinations();
    public void setCoordinations(Coordination co);
        
}