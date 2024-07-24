package bomb;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BombImages {

    public BufferedImage tick1, tick2, tick3, tick4, tick5, tick6, tick7, tick8;

    public BombImages(){
        getBombImage();
    }

    public void getBombImage(){
        try {
            tick1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb1.png"));
            tick2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb2.png"));
            tick3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb3.png"));
            tick4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb4.png"));
            tick5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb5.png"));
            tick6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb6.png"));
            tick7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb7.png"));
            tick8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bomb/bomb8.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
