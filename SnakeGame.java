package game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author aleš tichava
 */
public class SnakeGame {

    public static void main(String[] args) {
    
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                try {
                     GameBoard gameBoard = new GameBoard(500, 500);
                     
                     JFrame window = new JFrame("Hra had - v2.0");
        
                     window.add(gameBoard);
        
                     window.setResizable(false);
                     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     window.pack();
                     window.setVisible(true);
                     
                     gameBoard.run();
                
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e, "Error", JOptionPane.CLOSED_OPTION);
                }
            }
        });
    }
}