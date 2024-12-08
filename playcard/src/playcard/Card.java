package playcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

final public class Card extends BufferedImage {
    final private static int WIDTH = 45;
    final private static int HEIGHT = 60;

    final private static int ANGLE = 6;
    
    private Card() {
        super(WIDTH, HEIGHT, BufferedImage.TRANSLUCENT);
        
        Graphics g = getGraphics();
        
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ANGLE, ANGLE);
        
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ANGLE, ANGLE);
    }
    
    final private static int INSET = 5;
    
    public static BufferedImage getBack(Color color) {
        Card back = new Card();
        
        Graphics g = back.getGraphics();
        
        g.setColor(color);
        g.fillRect(INSET, INSET, WIDTH - 2 * INSET, HEIGHT - 2 * INSET);
        
        return back;
    }
    
    final private static String SUIT = "\u2660\u2665\u2663\u2666";
    final private static Object[] RANK = {'A', 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K'};

    public static BufferedImage getFace() {
        BufferedImage face = new BufferedImage(RANK.length * WIDTH, SUIT.length() * HEIGHT, BufferedImage.TRANSLUCENT);
        
        int y = 0;
        
        for (char suit : SUIT.toCharArray()) {
            int x = 0;
            
            for (Object rank : RANK) {
                Card card = new Card();
                
                Graphics g = card.getGraphics();
                
                g.setColor(new Color[] {Color.black, Color.red}[SUIT.indexOf(suit) % 2]);
                
                g.setFont(g.getFont().deriveFont(Font.BOLD, card.getHeight() / 3));
                g.drawString(String.valueOf(rank), g.getFont().getSize() / 10, (int) (0.9 * g.getFont().getSize()));
                g.drawString(String.valueOf(suit), card.getWidth() - g.getFont().getSize() / 10 - g.getFontMetrics().charWidth(suit), (int) (0.9 * g.getFont().getSize()));
                
                g.setFont(g.getFont().deriveFont(Font.PLAIN, card.getHeight() - g.getFont().getSize()));
                g.drawString(String.valueOf(suit), 1 + (card.getWidth() - g.getFontMetrics().charWidth(suit)) / 2, (int) (0.9 * card.getHeight()));
                
                g = face.getGraphics();
                
                g.drawImage(card, x, y, null);
                
                x += face.getWidth() / RANK.length;
            }
            
            y += face.getHeight() / SUIT.length();
        }
        
        return face;
    }
}
