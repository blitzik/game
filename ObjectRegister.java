package game;

import game.Objects.Coordination;
import game.Objects.Object;
import game.Objects.Brick;
import game.Objects.IGameObject;
import game.Objects.Snake;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author aleš tichava
 */
public final class ObjectRegister {
    
    private final HashMap<String, IGameObject> gameObjects = new HashMap<>();
    
    private final Random r = new Random();
    
    private GameBoard gb = null;
    private Snake snake = null;
    
    public ObjectRegister(GameBoard gb, Snake snake)
    {
        this.gb = gb;
        this.snake = snake;
        
        createWalls();
    }
    
    public void addGameObject(int x, int y, IGameObject go)
    {
        if (isLocationEmpty(x, y)) {
            Coordination c = new Coordination(x, y);
            gameObjects.put(createCoordinateKey(c), go);
            go.setCoordinations(c);
        }
    }
    
    public void addGameObject(IGameObject go)
    {
        Coordination c = locateObject();           
        gameObjects.put(createCoordinateKey(c), go);
        go.setCoordinations(c);
        //System.out.println(go + " " + "x: " + c.getX() + " | " + "y: " + c.getY());
    }
    
    public void changeObjectLocation(IGameObject go)
    {
        this.removeObject(go);
        addGameObject(go);
    }
    
    public void removeObject(IGameObject go)
    {
        gameObjects.remove(createCoordinateKey(go.getCoordinations()));
    }
    
    public IGameObject getGameObject(Coordination c)
    {
        return gameObjects.get(createCoordinateKey(c));
    }
          
    /**
     * Metoda vracející volné souřadnice na herní ploše
     * 
     * @return Coordination objekt se souřadnicemi
     */
    private Coordination locateObject()
    {
        // pomocné proměnné
        int dx = randomXPosition();
        int dy = randomYPosition();
        
        boolean isReadyToPlace = false;
        do{
            
            if (isLocationEmpty(dx, dy)) {
                
                isReadyToPlace = true;
                
            } else {
                dx = randomXPosition();
                dy = randomYPosition();
            }
                
        } while (isReadyToPlace == false);
                       
        return new Coordination(dx, dy);
        
    }
      
    /**
     * Metoda zjištující, zda-li jsou zadané souřadnice
     * volné pro umístění objektu na plochu.
     * 
     * @param x Souřadnice X
     * @param y Souřadnice Y
     * @return boolean 
     */
    public boolean isLocationEmpty(int x, int y)
    {        
        // kontrola objektů v registru
        if (gameObjects.containsKey(createCoordinateKey(new Coordination(x, y)))) {
            return false;
        }
        
        // Objekty se nemohou objevovat v těsném okolí hlavy hada
        if (x >= (snake.getX() - 3*Object.size) && 
            x <= (snake.getX() + 3*Object.size) && 
            y >= (snake.getY() - 3*Object.size) && 
            y <= (snake.getY() + 3*Object.size)) {
            return false;
        }
                
        return true;
    }
    
    /**
     * Metoda, která vrátí jednu náhodnou souřadnici
     * 
     * @return int Jedna souřadnice
     */
    private int randomXPosition()
    {
        return r.nextInt(gb.getBoardWidth() / Object.size) * Object.size;
    }
    
    private int randomYPosition()
    {
        return (r.nextInt((gb.getBoardWidth() - Object.size) / Object.size) + 2) * Object.size;
    }
        
    /**
     *  Privátní metoda, kde se vytvářejí zdi
     */
    private void createWalls()
    {
        for (int i = 140; i <= 200; i += Object.size) {
            for (int j = 100; j <= 160; j += Object.size) {                
                gameObjects.put(createCoordinateKey(new Coordination(j, i)), new Brick(j, i));
            }
        }
        
        for (int i = 140; i <= 200; i += Object.size) {
            for (int j = 320; j <= 380; j += Object.size) {                
                gameObjects.put(createCoordinateKey(new Coordination(j, i)), new Brick(j, i));
            }
        }
        
        for (int i = 360; i <= 420; i += Object.size) {
            for (int j = 100; j <= 160; j += Object.size) {                
                gameObjects.put(createCoordinateKey(new Coordination(j, i)), new Brick(j, i));
            }
        }
        
        for (int i = 360; i <= 420; i += Object.size) {
            for (int j = 320; j <= 380; j += Object.size) {                
                gameObjects.put(createCoordinateKey(new Coordination(j, i)), new Brick(j, i));
            }
        }
        
        //gameObjects.put(createCoordinateKey(new Coordination(100, 520)), new Brick(100, 520));
        
    }
    
    public HashMap<String, IGameObject> getGameObjects()
    {
        return gameObjects;
    }

    
    /**
     * Metoda vracející textový řetězec se souřadnicemi,
     * který je použit jako klíč v HashMapě.
     * 
     * @param c Coorination objekt se souřadnicemi
     * @return String Textový řetězec, který je použit jako klíč
     */
    public String createCoordinateKey(Coordination c)
    {
        return (c.getX() + "x" + c.getY());
    }
    
}