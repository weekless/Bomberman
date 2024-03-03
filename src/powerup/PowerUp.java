package powerup;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PowerUp {

    public enum Type{
        FIRE_UP, BOMB_UP, LIFE_UP, SPEED_UP
    }

    GamePanel gp;
    public BufferedImage image;
    public int x,y;
    public Rectangle solidArea;
    public boolean taken;
    public boolean shouldDisappear;
    public Type type;

    public PowerUp(GamePanel gp){
        this.gp = gp;
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        taken = false;
        shouldDisappear = false;
    }

    public void update(){
        gp.cChecker.pickUpPowerUp(this, gp.player);
        if(taken){
            gp.playSoundEffect(14);
            switch (type){
                case LIFE_UP -> gp.player.lives += 1;
                case FIRE_UP -> gp.player.firePower += 1;
                case BOMB_UP -> gp.player.bombNum += 1;
                case SPEED_UP -> gp.player.speed *= 2;
            }
            shouldDisappear = true;
        }
    }

    public void draw(Graphics2D g2){

        switch (type){
            case BOMB_UP -> image = gp.powerUpM.bombUpImage;
            case FIRE_UP -> image = gp.powerUpM.fireUpImage;
            case LIFE_UP -> image = gp.powerUpM.lifeUpImage;
            case SPEED_UP -> image = gp.powerUpM.speedUpImage;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
