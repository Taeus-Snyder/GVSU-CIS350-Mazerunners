package game.src.main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOver {


    public void render (Graphics g){
        Rectangle restartButton = new Rectangle(MazeGame.WIDTH / 2 + 60, 375, 140, 50);
        Rectangle menuButton = new Rectangle(MazeGame.WIDTH / 2 + 35, 300, 310, 50);
        Rectangle exitButton = new Rectangle(MazeGame.WIDTH / 2 + 220, 375, 100, 50);

        Graphics2D g2d = (Graphics2D) g;

        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.red);


        Font fnt1 = new Font("times new roman", Font.BOLD, 30);


        if (MazeGame.dead) {
            //character died
            g.drawString("GAME OVER", MazeGame.WIDTH/2 + 30, 100);
            g.setFont(fnt1);
            g.drawString("Uh oh! You died...", MazeGame.WIDTH / 2 + 70, 200);

        }
        else {
            //player won
            g.drawString("LEVEL COMPLETE", MazeGame.WIDTH /3 + 20, 100);
            g.setFont(fnt1);
            g.drawString("You did it!", MazeGame.WIDTH - 70, 200);
        }

        g.drawString("Restart", restartButton.x + 24, restartButton.y + 33);
        g2d.draw(restartButton);
        g.drawString("Exit", exitButton.x + 24, exitButton.y + 33);
        g2d.draw(exitButton);
        g.drawString("Return to Main Menu", menuButton.x + 12, menuButton.y + 33);
        g2d.draw(menuButton);



    }

}
