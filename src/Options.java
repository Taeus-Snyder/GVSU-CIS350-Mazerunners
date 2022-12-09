package game.src.main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Options {
    public Rectangle lettersButton = new Rectangle(MazeGame.WIDTH / 2, 250, 120, 50);
    public Rectangle arrowsButton = new Rectangle(MazeGame.WIDTH, 250, 120, 50);
    public Rectangle mmButton = new Rectangle(MazeGame.WIDTH , 450, 320, 50);

    public int up = KeyEvent.VK_UP;
    public int down = KeyEvent.VK_DOWN;
    public int right = KeyEvent.VK_RIGHT;
    public int left = KeyEvent.VK_LEFT;

    public Boolean arrowsPressed = false;

    public void render (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.red);
        g.drawString("Options", MazeGame.WIDTH / 6, 100);

        Font fnt1 = new Font("times new roman", Font.BOLD, 30);
        g.setFont(fnt1);

        g.drawString("Configure keyboard input: ", MazeGame.WIDTH / 3 + 30, 200);

        g.drawString("AWSD", lettersButton.x + 17, lettersButton.y + 33);
        g2d.draw(lettersButton);
        g.drawString("<^v>", arrowsButton.x + 27, arrowsButton.y + 33);
        g2d.draw(arrowsButton);

        g.drawString("Return to Main Menu", mmButton.x + 15, mmButton.y + 33);
        g2d.draw(mmButton);

        if (arrowsPressed) {
            g.setColor(Color.black);
            g.drawString("<^v>", arrowsButton.x + 27, arrowsButton.y + 33);
            g2d.draw(arrowsButton);
            g.setColor(Color.red);
            g.drawString("AWSD", lettersButton.x + 17, lettersButton.y + 33);
            g2d.draw(lettersButton);

        }
        else {
            g.setColor(Color.black);
            g.drawString("AWSD", lettersButton.x + 17, lettersButton.y + 33);
            g2d.draw(lettersButton);
            g.setColor(Color.red);
            g.drawString("<^v>", arrowsButton.x + 27, arrowsButton.y + 33);
            g2d.draw(arrowsButton);

        }

    }

}
