package bomb;

import entity.Enemy;
import core.GamePanel;
import powerup.PowerUp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {

    GamePanel gp;

    public BufferedImage image = null;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public Type type;
    public int x, y;
    public int animationCounter = 0;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean shouldDisappear = false;


    public enum Type{
        START, END_BOTTOM, END_TOP, END_LEFT, END_RIGHT, MIDDLE_BOTTOM, MIDDLE_TOP, MIDDLE_LEFT, MIDDLE_RIGHT
    }

    public Explosion(GamePanel gp){
        this.gp = gp;
    }

    public void update() {

        for(Bomb bomb: gp.bombs){
            gp.cChecker.checkChained(this, bomb);
        }
        for(PowerUp pu: gp.powerUps){
            gp.cChecker.powerUpDestroyed(this, pu);
        }
        for(Enemy e: gp.enemyM.enemyList){
            gp.cChecker.checkExplosion(this, e);
        }
        gp.cChecker.checkExplosion(this, gp.player);

        if(animationCounter == 8){
            shouldDisappear = true;
        }
        spriteCounter++;
        if(spriteCounter > 5){
            spriteNum = (spriteNum)%8 + 1;
            animationCounter += 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        image = switch (type){
            case START -> gp.explosionImages.starts.get(spriteNum-1);
            case END_TOP -> gp.explosionImages.endTops.get(spriteNum-1);
            case END_BOTTOM -> gp.explosionImages.endBottoms.get(spriteNum-1);
            case END_LEFT -> gp.explosionImages.endLefts.get(spriteNum-1);
            case END_RIGHT -> gp.explosionImages.endRights.get(spriteNum-1);
            case MIDDLE_TOP -> gp.explosionImages.middleTops.get(spriteNum-1);
            case MIDDLE_BOTTOM -> gp.explosionImages.middleBottoms.get(spriteNum-1);
            case MIDDLE_LEFT -> gp.explosionImages.middleLefts.get(spriteNum-1);
            case MIDDLE_RIGHT -> gp.explosionImages.middleRights.get(spriteNum-1);
        };

        g2.drawImage(image, x, y, 64, 64, null);

    }

}
