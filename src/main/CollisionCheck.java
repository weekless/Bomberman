package main;

import bomb.Bomb;
import bomb.Explosion;
import entity.Enemy;
import entity.Entity;
import entity.Player;
import powerup.PowerUp;

import java.util.ArrayList;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeft/gp.tileSize;
        int entityRightCol = entityRight/gp.tileSize;
        int entityTopRow = entityTop/gp.tileSize;
        int entityBottomRow = entityBottom/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case UP -> {
                entityTopRow = (entityTop - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                    if(tileNum1 != tileNum2 && (entity.x+31)/gp.tileSize == entityRightCol) {
                        entity.x += entity.speed;
                    }else if(tileNum1 != tileNum2 && (entity.x+31)/gp.tileSize != entityRightCol){
                        entity.x -= entity.speed;
                    }
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottom + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    if(tileNum1 != tileNum2 && (entity.x+31)/gp.tileSize == entityRightCol) {
                        entity.x += entity.speed;
                    }else if(tileNum1 != tileNum2 && (entity.x+31)/gp.tileSize != entityRightCol){
                        entity.x -= entity.speed;
                    }
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeft - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    if(tileNum1 != tileNum2 && (entity.y+32+31)/gp.tileSize == entityTopRow) {
                        entity.y -= entity.speed;
                    }else if(tileNum1 != tileNum2 && (entity.y+32+31)/gp.tileSize == entityBottomRow){
                        entity.y += entity.speed;
                    }
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRight + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    if(tileNum1 != tileNum2 && (entity.y+32+31)/gp.tileSize == entityTopRow) {
                        entity.y -= entity.speed;
                    }else if(tileNum1 != tileNum2 && (entity.y+32+31)/gp.tileSize == entityBottomRow){
                        entity.y += entity.speed;
                    }
                }
            }
        }

    }

    public void checkEnemies(Entity entity, ArrayList<Enemy> enemies){

        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        for(Enemy e : enemies){
            if(entity != e){
                int enemyLeft = e.x + e.solidArea.x;
                int enemyRight = e.x + e.solidArea.x + e.solidArea.width;
                int enemyTop = e.y + e.solidArea.y;
                int enemyBottom = e.y + e.solidArea.y + e.solidArea.height;
                if(entityTop < enemyBottom && entityBottom > enemyTop && entityLeft < enemyRight && entityRight > enemyLeft){
                    entity.collisionOn = true;
                    entity.entityCollision = true;
                }
            }
        }
    }

    public void checkBomb(Entity entity, ArrayList<Bomb> bombs){

        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        for(Bomb bomb : bombs){

            int bombLeft = bomb.x + bomb.solidArea.x-2;
            int bombRight = bomb.x + bomb.solidArea.x + bomb.solidArea.width;
            int bombTop = bomb.y + bomb.solidArea.y-2;
            int bombBottom = bomb.y + bomb.solidArea.y + bomb.solidArea.height;

            boolean intersection = entityTop < bombBottom && entityBottom > bombTop && entityLeft < bombRight && entityRight > bombLeft;

            if(entity.getClass() == Enemy.class) {
                if(intersection){
                    entity.collisionOn = true;
                }
            }else{
                //TO ENSURE THE PLAYER CAN GET OUT OF THE BOMB BEFORE COLLIDING WITH IT
                if(intersection){
                    if(!bomb.firstCollision){
                        entity.collisionOn = true;
                        if(entity.x == bomb.x){
                            if(entity.y > bomb.y && entity.direction == Entity.Direction.DOWN){
                                entity.y += entity.speed;
                            }else if(entity.y < bomb.y && entity.direction == Entity.Direction.UP){
                                entity.y -= entity.speed;
                            }
                        }else{
                            if(entity.x > bomb.x && entity.direction == Entity.Direction.RIGHT){
                                entity.x += entity.speed;
                            }else if(entity.x < bomb.x && entity.direction == Entity.Direction.LEFT){
                                entity.x -= entity.speed;
                            }
                        }
                    }
                }else{
                    if(bomb.firstCollision){
                        bomb.firstCollision = false;
                    }
                }
            }
        }

    }

    public void checkChained(Explosion explosion, Bomb bomb){

        int explosionLeft = explosion.x + explosion.solidArea.x;
        int explosionRight = explosion.x + explosion.solidArea.x + explosion.solidArea.width;
        int explosionTop = explosion.y + explosion.solidArea.y;
        int explosionBottom = explosion.y + explosion.solidArea.y + explosion.solidArea.height;

        int bombLeft = bomb.x + bomb.solidArea.x;
        int bombRight = bomb.x + bomb.solidArea.x + bomb.solidArea.width;
        int bombTop = bomb.y + bomb.solidArea.y;
        int bombBottom = bomb.y + bomb.solidArea.y + bomb.solidArea.height;
        if(explosionTop < bombBottom && explosionBottom > bombTop && explosionLeft < bombRight && explosionRight > bombLeft){
            bomb.chained = true;
        }
    }

    public void checkExplosion(Explosion explosion, Entity entity){

        int explosionLeft = explosion.x + explosion.solidArea.x;
        int explosionRight = explosion.x + explosion.solidArea.x + explosion.solidArea.width;
        int explosionTop = explosion.y + explosion.solidArea.y;
        int explosionBottom = explosion.y + explosion.solidArea.y + explosion.solidArea.height;

        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        if(explosionTop < entityBottom && explosionBottom > entityTop && explosionLeft < entityRight && explosionRight > entityLeft){
            if(!entity.invincibility){
                entity.aliveOrDead = Entity.AliveOrDead.DEAD;
            }
        }

    }

    public void pickUpPowerUp(PowerUp powerUp, Player player){

        int powerUpLeft = powerUp.x + powerUp.solidArea.x;
        int powerUpRight = powerUp.x + powerUp.solidArea.x + powerUp.solidArea.width;
        int powerUpTop = powerUp.y + powerUp.solidArea.y;
        int powerUpBottom = powerUp.y + powerUp.solidArea.y + powerUp.solidArea.height;

        int playerLeft = player.x + player.solidArea.x;
        int playerRight = player.x + player.solidArea.x + player.solidArea.width;
        int playerTop = player.y + player.solidArea.y;
        int playerBottom = player.y + player.solidArea.y + player.solidArea.height;

        if(powerUpTop < playerBottom && powerUpBottom > playerTop && powerUpLeft < playerRight && powerUpRight > playerLeft){
            powerUp.taken = true;
        }

    }

    public void powerUpDestroyed(Explosion explosion, PowerUp powerUp){

        int explosionLeft = explosion.x + explosion.solidArea.x;
        int explosionRight = explosion.x + explosion.solidArea.x + explosion.solidArea.width;
        int explosionTop = explosion.y + explosion.solidArea.y;
        int explosionBottom = explosion.y + explosion.solidArea.y + explosion.solidArea.height;

        int powerUpLeft = powerUp.x + powerUp.solidArea.x;
        int powerUpRight = powerUp.x + powerUp.solidArea.x + powerUp.solidArea.width;
        int powerUpTop = powerUp.y + powerUp.solidArea.y;
        int powerUpBottom = powerUp.y + powerUp.solidArea.y + powerUp.solidArea.height;

        if(explosionTop < powerUpBottom && explosionBottom > powerUpTop && explosionLeft < powerUpRight && explosionRight > powerUpLeft){
            powerUp.shouldDisappear = true;
        }

    }

}
