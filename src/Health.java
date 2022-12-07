package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health {

    private double x; // x-coordinate of PowerUps
    private double y; // y-coordinate of PowerUps
    public int healthCounter = 3;
    private boolean HealthIsVisable = true;

    private BufferedImage health;

    public Health(double x, double y, MazeGame game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        health = ss.grabImage(7, 1, 15, 15);
    }

    public void render(Graphics g) {
        if(HealthIsVisable)
            g.drawImage(health, (int) x, (int) y, null);
        g.setColor(Color.white);
        g.drawString("Health Counter: " + getHealth(), 10, 20);
    }

    public double[] getBounds() {
        double[] bounds = new double[4];
        bounds[0] = getX();
        bounds[1] = getY();
        bounds[2] = getX() + health.getWidth();
        bounds[3] = getY() + health.getHeight();

        return bounds;
    }

    public boolean isTouching(double[] playerBounds) {
        if (HealthIsVisable) {
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
        }
        return false;
    }

    public void setHealth(int change) {
        this.healthCounter += change;
    }

    public String getHealth(){
        return String.valueOf(healthCounter);
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

    public void setHealthIsVisable(boolean b){
        HealthIsVisable = b;
    }
}
