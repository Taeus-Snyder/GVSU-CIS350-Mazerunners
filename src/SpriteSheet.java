package game.src.main;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){

        BufferedImage img = image.getSubimage((col*32) - 32, (row*32), width, height);
        return img;
    }

}
