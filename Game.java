package game;

import game.Enums.MessageType;
import game.Objects.Apple;
import game.Objects.IGameObject;
import game.SpawnGenerators.ObjectGenerator;
import game.Objects.Snake;
import game.Timers.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.SwingWorker;
import java.awt.RenderingHints;
import java.util.List;

/**
 *
 * @author aleš tichava
 */
public class Game {
            
    private GameBoard gameBoard = null;
    
    private BufferedImage gameScreen = null;
    private Graphics2D g = null;
    
    private Snake snake = null;
        
    private Timer lifeTimer = null;
    private Timer destructionTimer = null;
    
    private ObjectRegister or = null;
    private ObjectGenerator og = null;
    
    private CollisionDetection collision = null;
        
    private boolean running = true;
            
    private int score = 0;
    
    private final Font fontStat = new Font("Verdana", Font.PLAIN, 19);
    private final Font fontGame = new Font("Times New Roman", Font.PLAIN, 15);
        
    public Game(GameBoard gameBoard)
    {        
        this.snake = new Snake();
        
        this.gameBoard = gameBoard;
        
        initGameScreen();
                
        this.lifeTimer = new Timer(100, 15000, 45, 6);
        this.destructionTimer = new Timer(100, 7000, 45, 19);
        
        this.collision = new CollisionDetection();
        
        this.or = new ObjectRegister(gameBoard, snake);
        this.og = new ObjectGenerator(or);
                
        or.addGameObject(new Apple());
    }
    
    public void run()
    {
        Thread t = new Thread(new GameWorker());
        t.start();
        
        /*GameWorker gw = new GameWorker();
        gw.execute();*/
    }
    
    private void initGameScreen()
    {
        this.gameScreen = new BufferedImage(this.gameBoard.getBoardWidth(), this.gameBoard.getBoardHeight(), BufferedImage.TYPE_INT_RGB);
        this.g = (Graphics2D) gameScreen.getGraphics();
        this.g.setRenderingHint(
                                 RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
        this.g.setRenderingHint(
                                 RenderingHints.KEY_TEXT_ANTIALIASING,
                                 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    
    private void updateGameLogic()
    {
        this.snake.update(this);
                        
        switch (this.collision.checkCollision(this)) {
            
            case OBJECT:
                
                this.or.getGameObjects().get(this.or.createCoordinateKey(
                                             this.snake.getCoordinations())).update(this);
                break;
                
            case BORDER:
                
                this.stop();
                
                break;
                
            case BODY:
                                
                GameText.setText("GAME OVER", 200, MessageType.BAD);
                this.stop();
            
        }

        if (this.lifeTimer.getRestOfTime() <= 0) {
            this.stop();
        }
           
        this.og.spawnObjects();
        
        if (this.destructionTimer.getRestOfTime() <= 0) {
            this.snake.setDestructionMode(false);
        }

        // Test na vykreslení
        //or.addGameObject(new Poison());
        //System.out.println(or.getGameObjects().size());
                                    
    }
        
    private void renderGame()
    {
        // Pozadí herní plochy
        this.g.setColor(new Color(230, 230, 230));
        this.g.fillRect(0, this.gameBoard.getBoardStatsHeight(), this.gameBoard.getBoardWidth(), this.gameBoard.getBoardWidth());
                
        // box pro Timery a skóre
        this.g.setColor(Color.WHITE);
        this.g.fillRect(0, 0, this.gameBoard.getBoardWidth(), this.gameBoard.getBoardStatsHeight());
        
        // vykreslení hada
        this.snake.render(this.g);
        
        // vykreslení herních objektů
        for (IGameObject go : this.or.getGameObjects().values()) {
            go.render(this.g);
        }
        
        this.g.setFont(fontStat);
        
        this.g.setColor(Color.BLACK);
        // délka hada
        this.g.drawString("Had: " + this.snake.getBody().size(), (this.gameBoard.getBoardWidth() - (this.gameBoard.getBoardWidth()/2)), 25);
        this.g.drawString("Skóre: " + this.getScore(), (this.gameBoard.getBoardWidth() - (this.gameBoard.getBoardWidth()/4)), 25);
                
        this.g.setFont(fontGame);
        
        // vykreslení časovače života hada
        this.g.drawString("Život", 10, 18);
        this.lifeTimer.showTimer(this.g, Color.GREEN.darker());
        
        // vykreslení Timeru při aktivním destruction módu
        if (snake.isInDestructionMode()) {
            this.g.setColor(Color.BLACK);
            this.g.drawString("Dest.", 10, 30);
            this.destructionTimer.showTimer(g, Color.BLUE);
        }
        
        // vypisování textů při kolizi s objektem
        if (GameText.getDuration() > System.currentTimeMillis()) {

            GameText.getText(this.snake.getX()+10, this.snake.getY(), this.g);
        }
                        
    }
    
    public void addScore(int score)
    {
        if (score > 0) {
            GameText.setText("+ " + score, 500, MessageType.BONUS);
        } else {
            GameText.setText("- " + score, 500, MessageType.WARRNING);
        }
        
        this.score += score;
        
    }
        
    
    public ObjectGenerator getObjectGenerator()
    {
        return this.og;
    }
    
    public Timer getDestructionTimer()
    {
        return this.destructionTimer;
    }
    
    public int getScore()
    {
        return this.score;
    }
                
    public Timer getLifeTimer()
    {
        return this.lifeTimer;
    }
    
    public Snake getSnake() {
        return this.snake;
    }
    
    public boolean getGameState()
    {
        return running;
    }
    
    public GameBoard getGameBoard()
    {
        return this.gameBoard;
    }
    
    public void stop()
    {
        GameText.setText("GAME OVER", 200, MessageType.BAD);
        
        this.running = false;
    }

    public ObjectRegister getObjectRegister() {
        return this.or;
    }
        
    public CollisionDetection getCollisionDetection() {
        return collision;
    }
    
    
    private class GameWorker extends SwingWorker<BufferedImage, BufferedImage>
    {

        private final int FPS = 10;
        private final int SKIP_TICKS = 1000 / FPS;
        
        @Override
        protected BufferedImage doInBackground() throws Exception {
                        
             long nextGameTick = System.currentTimeMillis();
             long sleepTime;

             while ( getGameState() ) {
           
                 nextGameTick += SKIP_TICKS;
            
                 updateGameLogic();
                 renderGame();
                 publish(gameScreen);
                 
                 sleepTime = nextGameTick - System.currentTimeMillis();
                 
                 if (sleepTime > 0) {
            
                     try {
                         Thread.sleep(sleepTime);
                     } catch (InterruptedException e) {
                         System.err.println(e.getMessage());
                         System.exit(0);
                     }
            
                 }
                                    
             }
             
             return gameScreen;
            
        }

        @Override
        protected void process(List<BufferedImage> list) {
            
            gameBoard.setGameScreen(list.get(list.size() - 1));
            gameBoard.repaint();
            
        }

        @Override
        protected void done() {
            super.done();
            
            GameOverDialog god = new GameOverDialog(gameBoard, score);
            
        }  
    }
    
}