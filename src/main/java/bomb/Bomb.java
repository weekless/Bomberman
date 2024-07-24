package bomb;

import core.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bomb {

    GamePanel gp;
    public int x,y;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public BufferedImage image;
    public int tickCounter;
    public int spriteCounter;
    public int spriteNum;
    public boolean firstCollision;
    public boolean bombExplosion;
    public boolean chained;
    public boolean exploded;
    public ArrayList<Explosion> explosions = new ArrayList<>();


    public Bomb(GamePanel gp){

        this.gp = gp;
        x = (gp.player.x + 32) / 64 * 64;
        y = (gp.player.y + 64) / 64 * 64;
        tickCounter = 0;
        spriteCounter = 0;
        spriteNum = 0;
        firstCollision = true;
        bombExplosion = false;
        chained = false;
        exploded = false;

    }

    public void createExplosion(int xOffset, int yOffset, Explosion.Type End, Explosion.Type Middle){
        int colNum = x/gp.tileSize;
        int rowNum = y/gp.tileSize;
        for(int i = 0; i < gp.player.firePower; i++){
            int nextCol = colNum + (xOffset / 64) * (i+1);
            int nextRow = rowNum + (yOffset / 64) * (i+1);
            if(gp.tileM.tile[gp.tileM.mapTileNum[nextCol][nextRow]].collision){
                if(gp.tileM.tile[gp.tileM.mapTileNum[nextCol][nextRow]].breakable){
                    gp.tileM.mapTileNum[nextCol][nextRow] = 4;
                    gp.tileM.stillAnimating += 1;
                }
                break;
            }else{
                Explosion explosion = new Explosion(gp);
                int afterNextCol = colNum + (xOffset / 64) * (i+2);
                int afterNextRow = rowNum + (yOffset / 64) * (i+2);
                explosion.x = x + xOffset * (i+1);
                explosion.y = y + yOffset * (i+1);
                if(i == gp.player.firePower-1 || gp.tileM.tile[gp.tileM.mapTileNum[afterNextCol][afterNextRow]].collision){
                    explosion.type = End;
                }else{
                    explosion.type = Middle;
                }
                explosions.add(explosion);
            }
        }
    }

    public void createStart(){
        Explosion explosion = new Explosion(gp);
        explosion.x = x;
        explosion.y = y;
        explosion.type = Explosion.Type.START;
        explosions.add(explosion);
    }

    public void createTop(){
        createExplosion(0, -64, Explosion.Type.END_TOP, Explosion.Type.MIDDLE_TOP);
    }

    public void createBottom(){
        createExplosion(0, +64, Explosion.Type.END_BOTTOM, Explosion.Type.MIDDLE_BOTTOM);
    }

    public void createLeft(){
        createExplosion(-64, 0, Explosion.Type.END_LEFT, Explosion.Type.MIDDLE_LEFT);
    }

    public void createRight(){
        createExplosion(+64, 0, Explosion.Type.END_RIGHT, Explosion.Type.MIDDLE_RIGHT);
    }

    public void update(){

        if(tickCounter == 8 || chained){
            if(!bombExplosion){
                gp.playSoundEffect(7);
                createStart();
                createTop();
                createBottom();
                createLeft();
                createRight();
                bombExplosion = true;
            }
            exploded = true;
        }
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum) % 8 + 1;
            tickCounter += 1;
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2){

        image = switch (spriteNum){
            case 1 -> gp.bombImages.tick1;
            case 2 -> gp.bombImages.tick2;
            case 3 -> gp.bombImages.tick3;
            case 4 -> gp.bombImages.tick4;
            case 5 -> gp.bombImages.tick5;
            case 6 -> gp.bombImages.tick6;
            case 7 -> gp.bombImages.tick7;
            case 8 -> gp.bombImages.tick8;
            default -> null;
        };

        g2.drawImage(image, x, y, 64,64, null);
    }

}
