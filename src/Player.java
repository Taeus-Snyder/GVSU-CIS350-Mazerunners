package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private double x; // x-coordinate of player
    private double y; // y-coordinate of player
    private double volX = 0;
    private double volY = 0;

    private BufferedImage player;

    public Player(double x, double y, MazeGame game){
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        player = ss.grabImage(1, 1, 32, 32);
        //change coordinates depending on future sprite sheet
    }

    public void tick(){
        x += volX;
        y += volY;
    }

    public void render(Graphics g){
        g.drawImage(player, (int)x, (int)y, null);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setVolX(double volX){
        this.volX = volX;
    }

    public void setVolY(double volY){
        this.volY = volY;
    }

}
