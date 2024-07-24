package entity;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Enemy extends Entity{

    GamePanel gp;
    public BufferedImage animation1, animation2, animation3, animation4, animation5, animation6, deathAnimation1, deathAnimation2, deathAnimation3, deathAnimation4, hitAnimation1, hitAnimation2, hitAnimation3, hitAnimation4, hitAnimation5, hitAnimation6;
    Direction[] directions = Direction.values();
    public ArrayList<Enemy> enemyList;
    public int enemyType;
    public int tick;
    public boolean shouldDisappear = false;
    public int deathAnimationCounter = 0;
    public int hitCounter = 0;
    public boolean deathAnimation = false;

    public Enemy(GamePanel gp){
        this.gp = gp;
    }

    private void getEnemyImage(int insertMapNum){
        try {
            deathAnimation1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/deathAnimation1.png"));
            deathAnimation2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/deathAnimation2.png"));
            deathAnimation3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/deathAnimation3.png"));
            deathAnimation4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/deathAnimation4.png"));

            switch (insertMapNum) {
                case 1 -> {
                    up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/up1.png"));
                    up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/up2.png"));
                    up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/up3.png"));
                    up4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/up4.png"));
                    down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/down1.png"));
                    down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/down2.png"));
                    down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/down3.png"));
                    down4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/down4.png"));
                    left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/left1.png"));
                    left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/left2.png"));
                    left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/left3.png"));
                    left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/left4.png"));
                    right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/right1.png"));
                    right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/right2.png"));
                    right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/right3.png"));
                    right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy1/right4.png"));
                }
                case 2 -> {
                    animation1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/1.png"));
                    animation2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/2.png"));
                    animation3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/3.png"));
                    animation4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/4.png"));
                    animation5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/5.png"));
                    animation6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/6.png"));

                    hitAnimation1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation1.png"));
                    hitAnimation2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation2.png"));
                    hitAnimation3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation3.png"));
                    hitAnimation4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation4.png"));
                    hitAnimation5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation5.png"));
                    hitAnimation6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/enemy2/hitAnimation6.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpEnemyList(int insertMapNum){
        getEnemyImage(insertMapNum);
        enemyList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            enemyList.add(new Enemy(gp));
            enemyList.get(i).solidArea = new Rectangle(0, 32, 62,62);
            enemyList.get(i).aliveOrDead = AliveOrDead.ALIVE;
            enemyList.get(i).direction = Direction.DOWN;
            enemyList.get(i).enemyType = insertMapNum;
            enemyList.get(i).speed = 1;
            switch (enemyList.get(i).enemyType){
                case 1 -> {
                    enemyList.get(i).deathAnimation = true;
                    enemyList.get(i).tick = 4;
                    enemyList.get(i).lives = 0;
                }
                case 2 -> {
                    enemyList.get(i).tick = 12;
                    enemyList.get(i).lives = 1;
                }
            }
            switch (i){
                case 0 -> {
                    enemyList.get(i).x = 64 * 14;
                    enemyList.get(i).y = 64 * 2 - 32;
                }
                case 1 -> {
                    enemyList.get(i).x = 64 * 2;
                    enemyList.get(i).y = 64 * 10 - 32;
                }
                case 2 -> {
                    enemyList.get(i).x = 64 * 14;
                    enemyList.get(i).y = 64 * 10 - 32;
                }
            }
        }
    }

    private void moveEnemy(Move move) {
        if(!collisionOn){
            move(move);
        }else{
            switch (direction){
                case UP -> {
                    direction = Direction.DOWN;
                    y += speed;
                }
                case DOWN -> {
                    direction = Direction.UP;
                    y -= speed;
                }
                case LEFT -> {
                    direction = Direction.RIGHT;
                    x += speed;
                }
                case RIGHT -> {
                    direction = Direction.LEFT;
                    x -= speed;
                }
            }
        }
        //RANDOM CHANGE OF DIRECTION AT INTERSECTIONS
        float colFloat = (float)x/gp.tileSize;
        float rowFloat = ((float)y+32)/gp.tileSize;
        if(x%gp.tileSize == 0 && (y+32)%gp.tileSize == 0 && colFloat%2 == 0.0 && rowFloat%2 == 0){
            direction = directions[new Random().nextInt(4)];
        }
    }

    public  void update(){

        switch (aliveOrDead){
            case ALIVE -> {
                if(invincibility){
                    invincibilityCounter++;
                    if(invincibilityCounter > 300){
                        invincibility = false;
                        invincibilityCounter = 0;
                    }
                }

                collisionOn = false;
                entityCollision = false;

                gp.cChecker.checkTile(this);
                gp.cChecker.checkEnemies(this, gp.enemyM.enemyList);
                gp.cChecker.checkBomb(this, gp.bombs);

                switch (direction){
                    case UP -> moveEnemy(Move.MOVE_UP);
                    case DOWN -> moveEnemy(Move.MOVE_DOWN);
                    case LEFT -> moveEnemy(Move.MOVE_LEFT);
                    case RIGHT -> moveEnemy(Move.MOVE_RIGHT);
                }
                spriteCounter++;
                if(spriteCounter > 10){
                    spriteNum = (spriteNum)%tick + 1;
                    spriteCounter = 0;
                }
            }
            case DEAD -> {
                //ENEMIES DISAPPEARS
                if(deathAnimationCounter == 4){
                    if(lives == 0){
                        gp.playSoundEffect(13);
                        shouldDisappear = true;
                    }else{
                        lives -= 1;
                        aliveOrDead = AliveOrDead.ALIVE;
                        deathAnimation = true;
                        invincibility = true;
                        deathAnimationCounter = 0;
                    }
                }else if(deathAnimationCounter == 0 && deathAnimation){
                    spriteNum = 1;
                }
                if(!deathAnimation){
                    hitCounter++;
                    if(hitCounter > 30){
                        hitCounter = 0;
                        deathAnimationCounter += 1;
                    }
                    spriteCounter++;
                    if(spriteCounter > 10){
                        spriteNum = spriteNum%tick+1;
                        spriteCounter = 0;
                    }
                }else{
                    spriteCounter++;
                    if(spriteCounter > 15){
                        spriteNum = spriteNum%5 +1;
                        spriteCounter = 0;
                        deathAnimationCounter += 1;
                    }
                }
            }
        }

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (aliveOrDead){
            case ALIVE -> {
                switch (enemyType){
                    case 1 -> {
                        switch (direction){
                            case UP -> image = switch (spriteNum) {
                                case 1 -> gp.enemyM.up1;
                                case 2 -> gp.enemyM.up2;
                                case 3 -> gp.enemyM.up3;
                                case 4 -> gp.enemyM.up4;
                                default -> null;
                            };
                            case DOWN -> image = switch (spriteNum) {
                                case 1 -> gp.enemyM.down1;
                                case 2 -> gp.enemyM.down2;
                                case 3 -> gp.enemyM.down3;
                                case 4 -> gp.enemyM.down4;
                                default -> null;
                            };
                            case LEFT -> image = switch (spriteNum) {
                                case 1 -> gp.enemyM.left1;
                                case 2 -> gp.enemyM.left2;
                                case 3 -> gp.enemyM.left3;
                                case 4 -> gp.enemyM.left4;
                                default -> null;
                            };
                            case RIGHT -> image = switch (spriteNum) {
                                case 1 -> gp.enemyM.right1;
                                case 2 -> gp.enemyM.right2;
                                case 3 -> gp.enemyM.right3;
                                case 4 -> gp.enemyM.right4;
                                default -> null;
                            };
                        }
                    }
                    case 2 -> {
                        image = switch (spriteNum){
                            case 1, 12 -> gp.enemyM.animation1;
                            case 2, 11 -> gp.enemyM.animation2;
                            case 3, 10 -> gp.enemyM.animation3;
                            case 4, 9 -> gp.enemyM.animation4;
                            case 5, 8 -> gp.enemyM.animation5;
                            case 6, 7 -> gp.enemyM.animation6;
                            default -> null;
                        };
                    }
                }
            }
            case DEAD -> {
                if(lives == 0){
                    image = switch (spriteNum){
                        case 1 -> gp.enemyM.deathAnimation1;
                        case 2 -> gp.enemyM.deathAnimation2;
                        case 3 -> gp.enemyM.deathAnimation3;
                        case 4, 5 -> gp.enemyM.deathAnimation4;
                        default -> null;
                    };
                }else{
                    image = switch (spriteNum){
                        case 1, 12 -> gp.enemyM.hitAnimation1;
                        case 2, 11 -> gp.enemyM.hitAnimation2;
                        case 3, 10 -> gp.enemyM.hitAnimation3;
                        case 4, 9 -> gp.enemyM.hitAnimation4;
                        case 5, 8 -> gp.enemyM.hitAnimation5;
                        case 6, 7 -> gp.enemyM.hitAnimation6;
                        default -> null;
                    };
                }
            }
        }

        g2.drawImage(image, x, y, 64, 96, null);

    }

}
