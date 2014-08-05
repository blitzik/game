package game;

import java.awt.Color;
import java.awt.Graphics;
import game.Enums.MessageType;

/**
 *
 * @author aleš tichava
 */
public final class GameText {
    
    private static String messageText;
    private static long messageDuration;
    private static MessageType messageType;
    
    public static void setText(String text, int duration, MessageType type)
    {
        messageText = text;
        messageDuration = System.currentTimeMillis() + duration;
        messageType = type;
    }
    
    public static void getText(int x, int y, Graphics g)
    {
        switch (messageType) {
            
            case BONUS:
                
                g.setColor(Color.GREEN.darker());
                break;
                
            case WARRNING:
                
                g.setColor(Color.ORANGE.darker());
                break;
                
            case BAD:
                
                g.setColor(Color.RED);
                break;
            
        }
        
        g.drawString(messageText, x, y);
    }
    
    public static long getDuration()
    {
        return messageDuration;
    }
    
}
