package game.src.main;

import java.awt.*;

public class InGameMenu {

    public Rectangle optionsBox = new Rectangle(MazeGame.WIDTH +260, 50, 80, 180);
    public Rectangle optionsButton = new Rectangle(MazeGame.WIDTH + 260, 10, 80, 25);
    public Rectangle exitButton = new Rectangle(MazeGame.WIDTH + 280 ,60, 50, 25);
    public Rectangle menuButton = new Rectangle(MazeGame.WIDTH + 275 ,90, 60, 25);
    public Rectangle restartButton = new Rectangle(MazeGame.WIDTH + 275 ,120, 60, 25);



    public void render (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);

        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);



        g.drawString("Options", optionsButton.x + 3, optionsButton.y + 20);
        g2d.draw(optionsButton);
        if (MazeGame.openBox) {
            g2d.draw(optionsBox);
            g.drawString("Exit", exitButton.x + 5, exitButton.y + 20);
            g2d.draw(exitButton);
            g.drawString("Menu", menuButton.x + 5, menuButton.y + 20);
            g2d.draw(menuButton);
            g.drawString("Reset", restartButton.x + 5, restartButton.y + 20);
            g2d.draw(restartButton);
        }

    }

}
