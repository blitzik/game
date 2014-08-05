package game;

import game.Objects.Coordination;
import game.Enums.Collision;
import game.Objects.BodyPart;

/**
 *
 * @author aleš tichava
 */
public class CollisionDetection {
        
    
    public Collision checkCollision(Game g)
    {
        int snakeX = g.getSnake().getX();
        int snakeY = g.getSnake().getY();
        
        GameBoard gb = g.getGameBoard();
            
        String coordKey = g.getObjectRegister().createCoordinateKey(new Coordination(snakeX, snakeY));
        
        // Kolize s herním objektem
        if (g.getObjectRegister().getGameObjects().containsKey(coordKey)) {

            return Collision.OBJECT;
            
        }
        
        // kolize hada s tělem       
        for (BodyPart b : g.getSnake().getBody()) {
            
            if (snakeX == b.getX() &&
                snakeY == b.getY()) {
                return Collision.BODY;
            }
                
        }
        
        // Kolize hada se stěnou herního pole
        if (snakeX >=  gb.getBoardWidth()||
            snakeY >= gb.getBoardHeight() ||
            snakeX <= -(gb.getBoardStatsHeight() / 2) ||
            snakeY < gb.getBoardStatsHeight()) {
            //System.out.println("Snake " + snakeX + " | " + snakeY);
            return Collision.BORDER;   
        }
                
        return Collision.NO_COLLISION;
    }
    
}