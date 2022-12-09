package game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Present {

    private double x; // x-coordinate of PowerUps
    private double y; // y-coordinate of PowerUps
    private boolean PresentIsVisable = true;

    private BufferedImage present;

    public Present(double x, double y, MazeGame game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        present = ss.grabImage(8, 1, 15, 15);
    }

    public void render(Graphics g) {
        if(PresentIsVisable)
            g.drawImage(present, (int) x, (int) y, null);
        g.setColor(Color.white);
    }


    public boolean isTouching(int x, int y) {

        if(this.x == x && this.y == y){
            this.x = 0;
            this.y = 0;
            return true;
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

    public void setPresentIsVisable(boolean b){
        PresentIsVisable = b;
    }
}
