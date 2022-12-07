package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {
    private BufferedImage santa;
    public Rectangle startButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 250, 120, 50);
    public Rectangle optionsButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 350, 120, 50);
    public Rectangle exitButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 450, 120, 50);


    public void render (Graphics g){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            santa = loader.loadImage("santa1.png");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(santa, 10, 250, null);
        Font font0 = new Font("pacifico", Font.BOLD, 60);
        g.setFont(font0);
        g.setColor(Color.red);
        g.drawString("HOLIDAY MAZE DASH", MazeGame.WIDTH / 8, 100);



        Font fnt1 = new Font("times new roman", Font.BOLD, 30);
        g.setFont(fnt1);

        g.drawString("Collect presents to save Christmas!", MazeGame.WIDTH / 8, 150);

        Font fnt2 = new Font("times new roman", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Start", startButton.x + 24, startButton.y + 33);
        g2d.draw(startButton);
        g.drawString("Exit", exitButton.x + 29, exitButton.y + 33);
        g2d.draw(exitButton);
        g.drawString("Options", optionsButton.x + 7, optionsButton.y + 33);
        g2d.draw(optionsButton);


    }
}
