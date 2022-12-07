package game.src.main;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){

        BufferedImage img = image.getSubimage((col*15) - 15, (row*15) - 15, width, height);
        return img;
    }

}
