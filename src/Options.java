package game.src.main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Options {
    public Rectangle lettersButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 150, 120, 50);
    public Rectangle arrowsButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 250, 120, 50);


    public int up = KeyEvent.VK_UP;
    public int down = KeyEvent.VK_DOWN;
    public int right = KeyEvent.VK_RIGHT;
    public int left = KeyEvent.VK_LEFT;

    public void render (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.red);
        g.drawString("Options", MazeGame.WIDTH / 6, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.drawString("WSAD", lettersButton.x + 29, lettersButton.y + 33);
        g2d.draw(lettersButton);
        g.drawString("<>^v", arrowsButton.x + 29, arrowsButton.y + 33);
        g2d.draw(arrowsButton);

        // if one button is pressed, the other is unpressed
        //save button to set the variables
        //if unsaved, the highlight will change back to default once you change states

    }

}
