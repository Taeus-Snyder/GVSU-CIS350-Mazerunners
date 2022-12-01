package game.src.main;

import java.awt.*;

public class Menu {

    public Rectangle startButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 150, 120, 50);
    public Rectangle optionsButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 250, 120, 50);
    public Rectangle exitButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 350, 120, 50);


    public void render (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.red);
        g.drawString("HOLIDAY MAZE DASH", MazeGame.WIDTH / 6, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        g.drawString("Start", startButton.x + 24, startButton.y + 33);
        g2d.draw(startButton);
        g.drawString("Exit", exitButton.x + 29, exitButton.y + 33);
        g2d.draw(exitButton);
        g.drawString("Options", optionsButton.x + 3, optionsButton.y + 33);
        g2d.draw(optionsButton);


    }
}
