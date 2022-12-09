package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {

    private double x; // x-coordinate of player
    private double y; // y-coordinate of player
    private double volX = 0;
    private double volY = 0;
    public double volMod = 1;
    private int rPos = 0;
    private int cPos = 0;
//    public int health = 300;

    private BufferedImage player;

    public Enemy(double x, double y, MazeGame game){
        this.x = x;
        this.y = y;

        rPos = (int)y/16;
        cPos = (int)x/16;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        player = ss.grabImage(5, 1, 15, 15);
        //change coordinates depending on future sprite sheet
    }

    public void tick(){

            x += volX * volMod;
            y += volY * volMod;

    }

    public void setrPos(int rPos){
        this.rPos = rPos;
    }
    public void setcPos(int cPos){
        this.cPos = cPos;
    }

    public int getrPos(){
        return rPos;
    }

    public int getcPos(){
        return cPos;
    }

    public void incRPos(){
        if(rPos == 31) return;
        this.rPos++;
    }
    public void decRPos(){
        if(rPos == 0) return;
        this.rPos--;
    }
    public void incCPos(){
        if(cPos == 27) return;
        this.cPos++;
    }
    public void decCPos(){
        if(rPos == 0) return;
        this.cPos--;
    }
    public void render(Graphics g, int mazeX, int mazeY){
        x = mazeX + (cPos*16);
        y = mazeY + (rPos*16);
        g.drawImage(player, mazeX + (cPos*16), mazeY + (rPos*16), null);
    }

    public double[] getBounds() {
        double[] bounds = new double[4];
        bounds[0] = getX();
        bounds[1] = getY();
        bounds[2] = getX() + player.getWidth();
        bounds[3] = getY() + player.getHeight();

        return bounds;
    }

    public boolean isTouching(double[] playerBounds) {
        if (playerBounds[0] >= getX() && playerBounds[0] <= getX() + 32) {      //check if x is within bounds
            if (playerBounds[1] >= getY() && playerBounds[1] <= getY() + 32)
                return true;
            if (playerBounds[3] >= getY() && playerBounds[3] <= getY() + 32)
                return true;
        }
        if (playerBounds[2] >= getX() && playerBounds[2] <= getX() + 32) {
            if (playerBounds[1] >= getY() && playerBounds[1] <= getY() + 32)
                return true;
            return playerBounds[3] >= getY() && playerBounds[3] <= getY() + 32;
        }
        return false;
    }

//    public void setHealth(int change) {
//        this.health += change;
//    }
//
//    public int getHealth(){
//        return health;
//    }

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

    public void setVolMod(double volMod) { this.volMod = volMod; }
}
