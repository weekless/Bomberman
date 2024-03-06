package powerup;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class PowerUpManager {

    GamePanel gp;
    public BufferedImage image, bombUpImage, fireUpImage, lifeUpImage, speedUpImage;

    public PowerUpManager(GamePanel gp){
        this.gp = gp;
        getPowerUpImage();
    }

    public void changeTile(int tileCol, int tileRow){
        int oneOutOfTen = new Random().nextInt(10);
        int whichPowerUp;
        if(gp.player.speed != 8){
            whichPowerUp = new Random().nextInt(4);
        }else{
            whichPowerUp = new Random().nextInt(3);
        }

        if(oneOutOfTen == 0 && !gp.tileM.exitFound){
            gp.tileM.mapTileNum[tileCol][tileRow] = gp.tileM.exitTile;
            gp.tileM.exitFound = true;
        }else if(oneOutOfTen == 1){
            PowerUp powerUp = new PowerUp(gp);
            powerUp.x = tileCol*64;
            powerUp.y = tileRow*64;
            powerUp.type = switch (whichPowerUp){
                case 0 -> PowerUp.Type.BOMB_UP;
                case 1 -> PowerUp.Type.FIRE_UP;
                case 2 -> PowerUp.Type.LIFE_UP;
                case 3 -> PowerUp.Type.SPEED_UP;
                default -> null;
            };
            gp.powerUps.add(powerUp);
        }
        if(gp.tileM.numberOfObstacle == 1 && !gp.tileM.exitFound){
            gp.tileM.mapTileNum[tileCol][tileRow] = gp.tileM.exitTile;
            gp.tileM.exitFound = true;
        }
    }

    private void getPowerUpImage(){
        try{
            bombUpImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("powerUp/bombUp.png"));
            fireUpImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("powerUp/fireUp.png"));
            lifeUpImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("powerUp/lifeUp.png"));
            speedUpImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("powerUp/speedUp.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
