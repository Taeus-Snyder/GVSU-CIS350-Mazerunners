package game.src.main;

import java.awt.*;

public class InGameMenu {

    //public Rectangle optionsBox = new Rectangle(MazeGame.WIDTH / 2 + 110, 150, 120, 50);
    public Rectangle optionsButton = new Rectangle(MazeGame.WIDTH + 175, 10, 80, 25);
    public Rectangle exitButton = new Rectangle(MazeGame.WIDTH + 260 , 10, 50, 25);



    public void render (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);

        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);


        g.drawString("Exit", exitButton.x + 5, exitButton.y + 20);
        g2d.draw(exitButton);
        g.drawString("Options", optionsButton.x + 3, optionsButton.y + 20);
        g2d.draw(optionsButton);


    }

}
