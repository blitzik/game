package game;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author aleš tichava
 */
public class GameOverDialog extends JDialog{
    
    private JLabel text = null;
    private JLabel question = null;
    
    private JButton yesButton = null;
    private JButton noButton = null;
    private JButton saveButton = null;
    
    private GameBoard gb = null;
    private int score = 0;
    
    public GameOverDialog(GameBoard gb , int score)
    {
        super();
        
        this.gb = gb;
        this.score =score;
        
        initComponents();
        
        setTitle("Konec hry");
        
        setModal(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(gb);
        setVisible(true);
    }
    
    private void initComponents()
    {     
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel buttonsPanel = new JPanel();
        
        this.text = new JLabel("Vaše skóre je: " + score);
        this.question = new JLabel("Přejete si další hru?");
        //Dimension d = new Dimension(100, 35);
        
        this.yesButton = new JButton("Ano");
        //this.yesButton.setPreferredSize(d);
        this.yesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                yesButtonActionPerformed();
            }
        });
        
        this.noButton = new JButton("Ne");
        //this.noButton.setPreferredSize(d);
        this.noButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                noButtonActionPerformed();
            }
        });
        
        this.saveButton = new JButton("Uložit obraz");
        //this.saveButton.setPreferredSize(d);
        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                saveButtonActionPerformed();
            }
        });
        
        panel.add(text);
        panel.add(question);
        
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        buttonsPanel.add(saveButton);
        
        panel.add(buttonsPanel);
        
        add(panel);
        
    }
    
    private void yesButtonActionPerformed()
    {
        this.gb.run();
        dispose();
        setVisible(false);
    }
    
    private void noButtonActionPerformed()
    {
        System.exit(0);
    }
    
    private void saveButtonActionPerformed()
    {
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("d-MMMMMM-yyyy-HH-mm");
        String fileName = String.valueOf(format.format(calendar.getTime())) + ".jpg";
        File outputFile = new File(fileName);
        
        try {
            ImageIO.write(gb.getGameScreen(), "jpg", outputFile);
        } catch (IOException ex) {
            Logger.getLogger(GameOverDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}