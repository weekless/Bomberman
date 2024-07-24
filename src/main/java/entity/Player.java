package entity;

import bomb.Bomb;
import core.Control;
import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    Control keyC;

    BufferedImage invincibilityAura;
    public int deathAnimationCounter = 0;

    public int bombNum, firePower;
    public boolean canSet = true;
    public boolean canPlace = true;

    public Player(GamePanel gp, Control keyC){
        this.gp = gp;
        this.keyC = keyC;
        setDefaultValues();
        setStartingValues();
        getPlayerImage();
    }

    public void setStartingValues(){
        speed = 2;
        lives = 3;
        firePower = 1;
        bombNum = 1;
        solidArea = new Rectangle(0, 32, 62,62);
    }

    public void setDefaultValues(){
        setStartingPosition();
        invincibility = true;
        direction = Direction.DOWN;
        aliveOrDead = AliveOrDead.ALIVE;
    }

    public void setStartingPosition(){
        x = 64 * 2;
        y = 64 * 2 - 32;
    }

    private void getPlayerImage(){
        try{
            invincibilityAura = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/saiyanAura.png"));
            switch (gp.colorSelection){
                case WHITE -> {
                    up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberUp1.png"));
                    up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberUp2.png"));
                    up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberUp3.png"));
                    up4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberUp4.png"));
                    down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberDown1.png"));
                    down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberDown2.png"));
                    down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberDown3.png"));
                    down4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberDown4.png"));
                    left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberLeft1.png"));
                    left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberLeft2.png"));
                    left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberLeft3.png"));
                    left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberLeft4.png"));
                    right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberRight1.png"));
                    right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberRight2.png"));
                    right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberRight3.png"));
                    right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberRight4.png"));
                    ko1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberKo1.png"));
                    ko2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberKo2.png"));
                    ko3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberKo3.png"));
                    ko4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/whiteBomber/whiteBomberKo4.png"));
                }
                case BLACK -> {
                    up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberUp1.png"));
                    up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberUp2.png"));
                    up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberUp3.png"));
                    up4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberUp4.png"));
                    down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberDown1.png"));
                    down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberDown2.png"));
                    down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberDown3.png"));
                    down4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberDown4.png"));
                    left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberLeft1.png"));
                    left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberLeft2.png"));
                    left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberLeft3.png"));
                    left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberLeft4.png"));
                    right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberRight1.png"));
                    right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberRight2.png"));
                    right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberRight3.png"));
                    right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberRight4.png"));
                    ko1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberKo1.png"));
                    ko2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberKo2.png"));
                    ko3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberKo3.png"));
                    ko4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blackBomber/blackBomberKo4.png"));
                }
                case BLUE -> {
                    up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberUp1.png"));
                    up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberUp2.png"));
                    up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberUp3.png"));
                    up4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberUp4.png"));
                    down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberDown1.png"));
                    down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberDown2.png"));
                    down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberDown3.png"));
                    down4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberDown4.png"));
                    left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberLeft1.png"));
                    left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberLeft2.png"));
                    left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberLeft3.png"));
                    left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberLeft4.png"));
                    right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberRight1.png"));
                    right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberRight2.png"));
                    right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberRight3.png"));
                    right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberRight4.png"));
                    ko1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberKo1.png"));
                    ko2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberKo2.png"));
                    ko3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberKo3.png"));
                    ko4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/blueBomber/blueBomberKo4.png"));
                }
            }
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void movePlayer(Move move) {
        if(!collisionOn){
            move(move);
        }
    }

    public void pressingSpace(){
        if(gp.tileM.mapTileNum[(x+32)/64][(y+64)/64] == gp.tileM.exitTile + 1){
            gp.playSoundEffect(4);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //LEVEL COMPLETION
            switch (gp.tileM.mapNum){
                case 1 -> gp.startLevel(2);
                case 2 -> {
                    gp.stopMusic();
                    gp.playSoundEffect(12);
                    gp.state = GamePanel.GameState.VICTORY;
                    try {
                        Thread.sleep(5500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    gp.playMusic(11);
                }
            }
        }else{
            if(gp.bombs.size() < bombNum){
                if(canSet){
                    Bomb bomb = new Bomb(gp);
                    for (Bomb existingBomb : gp.bombs){
                        if(bomb.x == existingBomb.x && bomb.y == existingBomb.y){
                            canPlace = false;
                            break;
                        }
                    }
                    if(canPlace){
                        gp.playSoundEffect(6);
                        gp.bombs.add(bomb);
                    }else{
                        canPlace = true;
                    }
                }
                canSet = false;
            }
        }

        /*
        if(gp.tileM.mapTileNum[(x+32)/64][(y+64)/64] != gp.tileM.exitTile){
            if(gp.bombs.size() < bombNum){
                if(canSet){
                    Bomb bomb = new Bomb(gp);
                    for (Bomb existingBomb : gp.bombs){
                        if(bomb.x == existingBomb.x && bomb.y == existingBomb.y){
                            canPlace = false;
                            break;
                        }
                    }
                    if(canPlace){
                        gp.bombs.add(bomb);
                    }else{
                        canPlace = true;
                    }
                }
                canSet = false;
            }
        }else{
            //LEVEL COMPLETION
            switch (gp.tileM.mapNum){
                case 1 -> gp.startLevel(2);
                case 2 -> gp.state = GamePanel.GameState.END;
            }
        }

         */

    }

    public void update(){

        switch (aliveOrDead){
            case ALIVE -> {
                //INVINCIBILITY FRAMES
                if(invincibility){
                    invincibilityCounter++;
                    if(invincibilityCounter > 300){
                        invincibility = false;
                        invincibilityCounter = 0;
                    }
                }
                //PLACING BOMB
                if(keyC.spacePressed){
                    pressingSpace();
                }else{
                    canSet = true;
                }
                //CHECKING COLLISION WITH ENEMIES
                entityCollision = false;
                gp.cChecker.checkEnemies(this, gp.enemyM.enemyList);

                if (entityCollision){
                    if(!invincibility){
                        entityCollision = false;
                        aliveOrDead = AliveOrDead.DEAD;
                    }
                }
                //MOVING WHEN ARROW KEYS ARE PRESSED
                if (keyC.upPressed || keyC.downPressed || keyC.leftPressed || keyC.rightPressed){

                    collisionOn = false;
                    gp.cChecker.checkTile(this);
                    gp.cChecker.checkBomb(this, gp.bombs);

                    switch (keyC.lastArrowPressed){
                        case "upPressed" -> {
                            direction = Direction.UP;
                            movePlayer(Move.MOVE_UP);
                        }
                        case "downPressed" -> {
                            direction = Direction.DOWN;
                            movePlayer(Move.MOVE_DOWN);
                        }
                        case "leftPressed" -> {
                            direction = Direction.LEFT;
                            movePlayer(Move.MOVE_LEFT);
                        }
                        case "rightPressed" -> {
                            direction = Direction.RIGHT;
                            movePlayer(Move.MOVE_RIGHT);
                        }
                    }
                    spriteCounter++;
                    if(spriteCounter > 10){
                        spriteNum = (spriteNum)%4 + 1;
                        spriteCounter = 0;
                    }
                }else{
                    spriteNum = 1;
                }
            }
            case DEAD -> {
                if (deathAnimationCounter == 4){
                    if (lives == 0) {
                        gp.stopMusic();
                        gp.playMusic(10);
                        gp.state = GamePanel.GameState.GAMEOVER;
                    } else {
                        lives -= 1;
                        invincibility = true;
                        aliveOrDead = AliveOrDead.ALIVE;
                        deathAnimationCounter = 0;
                        setStartingPosition();
                    }
                }else if(deathAnimationCounter == 0) {
                    spriteNum = 1;
                }
                spriteCounter++;
                if(spriteCounter > 30){
                    spriteNum = (spriteNum)%4 + 1;
                    spriteCounter = 0;
                    deathAnimationCounter += 1;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (aliveOrDead){
            case ALIVE -> {
                switch (direction){
                    case UP -> image = switch (spriteNum) {
                        case 1 -> up1;
                        case 2 -> up2;
                        case 3 -> up3;
                        case 4 -> up4;
                        default -> null;
                    };
                    case DOWN -> image = switch (spriteNum) {
                        case 1 -> down1;
                        case 2 -> down2;
                        case 3 -> down3;
                        case 4 -> down4;
                        default -> null;
                    };
                    case LEFT -> image = switch (spriteNum) {
                        case 1 -> left1;
                        case 2 -> left2;
                        case 3 -> left3;
                        case 4 -> left4;
                        default -> null;
                    };
                    case RIGHT -> image = switch (spriteNum) {
                        case 1 -> right1;
                        case 2 -> right2;
                        case 3 -> right3;
                        case 4 -> right4;
                        default -> null;
                    };
                }
            }
            case DEAD -> image = switch (spriteNum) {
                case 1 -> ko1;
                case 2 -> ko2;
                case 3 -> ko3;
                case 4 -> ko4;
                default -> null;
            };

        }
        if(invincibility){
            g2.drawImage(invincibilityAura, x-28, y-32, 116, 128, null);
        }
        g2.drawImage(image, x, y, 64, 96, null);


    }

}
