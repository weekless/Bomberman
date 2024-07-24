package tile;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    Boolean alreadyExecuted;
    public Tile[] tile;
    public int[][] mapTileNum;
    public int mapNum;
    int col, row, tileNum;
    public int exitTile;
    int animationCounter = 0;
    int spriteCounter = 0;
    public int stillAnimating = 0;
    public boolean exitFound;
    public int numberOfObstacle = 0;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[35];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
    }

    //SET UP THE MAP OF CHOICE BASED ON THE NUMBER OF THE MAP
    public void setupMap(int insertMapNum){
        mapNum = insertMapNum;
        exitFound = false;
        getTileImage();
        switch (mapNum){
            case 1 -> {
                loadMap("maps/map01.txt");
                gp.stopMusic();
                gp.playMusic(2);
            }
            case 2 -> {
                loadMap("maps/map02.txt");
                gp.stopMusic();
                gp.playMusic(9);
            }
        }
        setObstacle();
        numberOfObstacle = countObstacle();
    }

    //LOADS THE IMAGE BASED ON THE NUMBER OF THE MAP
    private void getTileImage() {
        try{
            switch (mapNum){
                case 1 ->{
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/black.png"));

                    tile[1] = new Tile();
                    tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/background.png"));
                    tile[1].collision = false;

                    tile[2] = new Tile();
                    tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/hardWall.png"));

                    tile[3] = new Tile();
                    tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/softWall.png"));
                    tile[3].breakable = true;

                    tile[4] = new Tile();
                    tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall1.png"));

                    tile[5] = new Tile();
                    tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall2.png"));

                    tile[6] = new Tile();
                    tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall3.png"));

                    tile[7] = new Tile();
                    tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall4.png"));

                    tile[8] = new Tile();
                    tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall5.png"));

                    tile[9] = new Tile();
                    tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/brokenSoftWall6.png"));

                    tile[10] = new Tile();
                    tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map1/edge.png"));

                    tile[11] = new Tile();
                    tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/exitLocked.png"));
                    tile[11].collision = false;

                    tile[12] = new Tile();
                    tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/exitUnlocked.png"));
                    tile[12].collision = false;

                    exitTile = 11;

                }
                case 2 ->{
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/black.png"));

                    tile[1] = new Tile();
                    tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/background.png"));
                    tile[1].collision = false;

                    tile[2] = new Tile();
                    tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/hardWall.png"));

                    tile[3] = new Tile();
                    tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/softWall.png"));
                    tile[3].breakable = true;

                    tile[4] = new Tile();
                    tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall2.png"));

                    tile[5] = new Tile();
                    tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall2.png"));

                    tile[6] = new Tile();
                    tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall3.png"));

                    tile[7] = new Tile();
                    tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall4.png"));

                    tile[8] = new Tile();
                    tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall5.png"));

                    tile[9] = new Tile();
                    tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/brokenSoftWall6.png"));

                    tile[10] = new Tile();
                    tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge1.png"));

                    tile[11] = new Tile();
                    tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge2.png"));

                    tile[12] = new Tile();
                    tile[12].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge3.png"));

                    tile[13] = new Tile();
                    tile[13].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge4.png"));

                    tile[14] = new Tile();
                    tile[14].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge5.png"));

                    tile[15] = new Tile();
                    tile[15].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge6.png"));

                    tile[16] = new Tile();
                    tile[16].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge7.png"));

                    tile[17] = new Tile();
                    tile[17].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge8.png"));

                    tile[18] = new Tile();
                    tile[18].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge9.png"));

                    tile[19] = new Tile();
                    tile[19].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge10.png"));

                    tile[20] = new Tile();
                    tile[20].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge11.png"));

                    tile[21] = new Tile();
                    tile[21].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge12.png"));

                    tile[22] = new Tile();
                    tile[22].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge13.png"));

                    tile[23] = new Tile();
                    tile[23].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge14.png"));

                    tile[24] = new Tile();
                    tile[24].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge15.png"));

                    tile[25] = new Tile();
                    tile[25].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge16.png"));

                    tile[26] = new Tile();
                    tile[26].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge17.png"));

                    tile[27] = new Tile();
                    tile[27].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge18.png"));

                    tile[28] = new Tile();
                    tile[28].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge19.png"));

                    tile[29] = new Tile();
                    tile[29].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/map2/edge20.png"));

                    tile[30] = new Tile();
                    tile[30].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/exitLocked.png"));
                    tile[30].collision = false;

                    tile[31] = new Tile();
                    tile[31].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/exitUnlocked.png"));
                    tile[31].collision = false;

                    exitTile = 30;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    //LOADS THE MAP AS PER THE .TXT FILE
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            col = 0;
            row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    String[] numbers = (line.split(" "));
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //IT SETS OBSTACLE IN THE MAP
    public void setObstacle(){
        col = 0;
        row = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            if(mapTileNum[col][row] == 1){
                if(new Random().nextInt(10) < 5){
                    mapTileNum[col][row] = 3;
                }
            }
            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
        mapTileNum[2][2] = 1; mapTileNum[3][2] = 1; mapTileNum[2][3] = 1; //CLEARS THE TOP LEFT ANGLE
        mapTileNum[14][2] = 1; mapTileNum[13][2] = 1; mapTileNum[14][3] = 1; //CLEARS THE TOP RIGHT ANGLE
        mapTileNum[2][10] = 1; mapTileNum[3][10] = 1; mapTileNum[2][9] = 1; //CLEARS THE BOTTOM LEFT ANGLE
        mapTileNum[14][10] = 1; mapTileNum[13][10] = 1; mapTileNum[14][9] = 1; //CLEARS THE BOTTOM RIGHT ANGLE
    }

    public int countObstacle(){
        int count = 0;
        for(int i = 0; i < gp.maxScreenCol; i++){
            for(int j = 0; j < gp.maxScreenRow; j++){
                if(mapTileNum[i][j] == 3){
                    count += 1;
                }
            }
        }
        return count;
    }

    //IT ANIMATE THE OBSTACLE WHEN THEY ARE BROKEN
    public void update(){
        int col = 0;
        int row = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            if(mapTileNum[col][row] == exitTile){
                if(gp.enemyM.enemyList.isEmpty()){
                    mapTileNum[col][row] += 1;
                }
            }
            if(mapTileNum[col][row] >= 4 && mapTileNum[col][row] <= 9){
                if(animationCounter == 6){
                    mapTileNum[col][row] = 1;
                    stillAnimating -= 1;
                    gp.powerUpM.changeTile(col, row);
                    numberOfObstacle -= 1;
                    if(stillAnimating == 0){
                        spriteCounter = 0;
                        animationCounter = 0;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 4){
                    if(mapTileNum[col][row] < 9){
                        mapTileNum[col][row] += 1;
                    }
                    animationCounter += 1;
                    spriteCounter = 0;
                }
            }
            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    //DRAWS THE MAP
    public void draw(Graphics2D g2){
        col = 0;
        row = 0;
        int x = 0;
        int y= 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }

}
