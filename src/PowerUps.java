package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUps {

    private double x; // x-coordinate of PowerUps
    private double y; // y-coordinate of PowerUps
    private boolean PuIsVisable = true;

    private BufferedImage powerUps, display;

    public PowerUps(double x, double y, MazeGame game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        powerUps = ss.grabImage(3, 1, 32, 32);
        display = ss.grabImage(2,3,128,32);
    }

    public void render(Graphics g) {
        if(PuIsVisable)
            g.drawImage(powerUps, (int) x, (int) y, null);
        else
            g.drawImage(display, 192, 208, null);
    }

    public double[] getBounds() {
        double[] bounds = new double[4];
        bounds[0] = getX();
        bounds[1] = getY();
        bounds[2] = getX() + powerUps.getWidth();
        bounds[3] = getY() + powerUps.getHeight();

        return bounds;
    }

    public boolean isTouching(double[] playerBounds) {
        if (PuIsVisable) {
            if (playerBounds[0] >= getX() && playerBounds[0] <= getX() + 32) {      //check if x is within bounds
                if (playerBounds[1] >= getY() && playerBounds[1] <= getY() + 32)
                    return true;
                return playerBounds[3] >= getY() && playerBounds[3] <= getY() + 32;
            }
            if (playerBounds[2] >= getX() && playerBounds[2] <= getX() + 32) {
                if (playerBounds[1] >= getY() && playerBounds[1] <= getY() + 32)
                    return true;
                return playerBounds[3] >= getY() && playerBounds[3] <= getY() + 32;
            }
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPuIsVisable(boolean b){
        PuIsVisable = b;
    }
}
