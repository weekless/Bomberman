package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int x, y, speed;
    public int lives;

    public BufferedImage up1,up2, up3, up4, down1, down2, down3, down4,  left1, left2, left3, left4, right1, right2, right3, right4, ko1, ko2, ko3, ko4;
    public Direction direction;
    public AliveOrDead aliveOrDead;

    public int spriteCounter = 0;
    public int spriteNum = 1;


    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean entityCollision = false;

    public boolean invincibility;
    public int invincibilityCounter;


    public enum AliveOrDead{
        ALIVE, DEAD
    }

    public enum Direction{
        DOWN, UP, LEFT, RIGHT
    }

    public enum Move
    {
        MOVE_DOWN(0, 1),
        MOVE_UP(0, -1),
        MOVE_LEFT(-1, 0),
        MOVE_RIGHT(1, 0);
        private final int deltaX;
        private final int deltaY;
        Move(final int deltaX,final int deltaY){
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    public void move(Move move){
        y += move.deltaY * speed;
        x += move.deltaX * speed;
    }

}
