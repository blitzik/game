package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import game.Enums.Direction;
import game.Objects.Snake;


/**
 *
 * @author aleš tichava
 */
public class GameBoard extends JPanel implements KeyListener{
    
    private static final int boardStatsHeight = 40;
    
    private int boardWidth = 0;
    private int boardHeight = 0;
        
    private BufferedImage gameScreen = null;
    
    private Snake snake = null;
                
    public GameBoard(int width, int height) throws Exception
    {
        super();
                
        if (width < 400 || height < 400) {
            throw new Exception("Too small board size.");
        } else {
            this.boardWidth = width;
            this.boardHeight = height + boardStatsHeight;
        }
        
        addKeyListener(this);
                
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setVisible(true);
        setFocusable(true);
        requestFocus();        
    }
    
    public void run()
    {
        Game game = new Game(this);
            
        this.snake = game.getSnake();
            
        game.run();
    }
          
    @Override
    protected void paintComponent(Graphics g)
    {
        g.drawImage(this.gameScreen, 0, 0, null);
        g.dispose();
    } 

    
    // KEY LISTENER
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != Direction.DOWN) {
            this.snake.setDirection(Direction.UP);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.LEFT) {
            this.snake.setDirection(Direction.RIGHT);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != Direction.UP) {
            this.snake.setDirection(Direction.DOWN);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != Direction.RIGHT) {
            this.snake.setDirection(Direction.LEFT);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    public void setGameScreen(BufferedImage i)
    {
        this.gameScreen = i;
    }
    
    public BufferedImage getGameScreen()
    {
        return this.gameScreen;
    }

    public int getBoardStatsHeight() {
        return boardStatsHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
    
    
        
}