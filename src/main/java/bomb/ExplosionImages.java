package bomb;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ExplosionImages {

    GamePanel gp;
    public BufferedImage start1, start2, start3, start4, start5, start6, start7, start8;
    public BufferedImage endTop1, endTop2, endTop3, endTop4, endTop5, endTop6, endTop7, endTop8;
    public BufferedImage endBottom1, endBottom2, endBottom3, endBottom4, endBottom5, endBottom6, endBottom7, endBottom8;
    public BufferedImage endLeft1, endLeft2, endLeft3, endLeft4, endLeft5, endLeft6, endLeft7, endLeft8;
    public BufferedImage endRight1, endRight2, endRight3, endRight4, endRight5, endRight6, endRight7, endRight8;
    public BufferedImage middleTop1, middleTop2, middleTop3, middleTop4, middleTop5, middleTop6, middleTop7, middleTop8;
    public BufferedImage middleBottom1, middleBottom2, middleBottom3, middleBottom4, middleBottom5, middleBottom6, middleBottom7, middleBottom8;
    public BufferedImage middleLeft1, middleLeft2, middleLeft3, middleLeft4, middleLeft5, middleLeft6, middleLeft7, middleLeft8;
    public BufferedImage middleRight1, middleRight2, middleRight3, middleRight4, middleRight5, middleRight6, middleRight7, middleRight8;

    public ArrayList<BufferedImage> starts = new ArrayList<>();
    public ArrayList<BufferedImage> endTops = new ArrayList<>();
    public ArrayList<BufferedImage> endBottoms = new ArrayList<>();
    public ArrayList<BufferedImage> endLefts = new ArrayList<>();
    public ArrayList<BufferedImage> endRights = new ArrayList<>();
    public ArrayList<BufferedImage> middleTops = new ArrayList<>();
    public ArrayList<BufferedImage> middleBottoms = new ArrayList<>();
    public ArrayList<BufferedImage> middleLefts = new ArrayList<>();
    public ArrayList<BufferedImage> middleRights = new ArrayList<>();

    public ExplosionImages(){
        getExplosionImage();
    }

    public void getExplosionImage(){

        try {

            start1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start1.png"));
            start2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start2.png"));
            start3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start3.png"));
            start4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start4.png"));
            start5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start5.png"));
            start6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start6.png"));
            start7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start7.png"));
            start8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/start/start8.png"));
            starts.addAll(Arrays.asList(start1, start2, start3, start4, start5, start6, start7, start8));

            endBottom1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom1.png"));
            endBottom2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom2.png"));
            endBottom3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom3.png"));
            endBottom4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom4.png"));
            endBottom5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom5.png"));
            endBottom6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom6.png"));
            endBottom7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom7.png"));
            endBottom8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/bottom/endBottom8.png"));
            endBottoms.addAll(Arrays.asList(endBottom1, endBottom2, endBottom3, endBottom4, endBottom5, endBottom6, endBottom7, endBottom8));

            endTop1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop1.png"));
            endTop2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop2.png"));
            endTop3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop3.png"));
            endTop4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop4.png"));
            endTop5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop5.png"));
            endTop6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop6.png"));
            endTop7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop7.png"));
            endTop8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/top/endTop8.png"));
            endTops.addAll(Arrays.asList(endTop1, endTop2, endTop3, endTop4, endTop5, endTop6, endTop7, endTop8));

            endLeft1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft1.png"));
            endLeft2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft2.png"));
            endLeft3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft3.png"));
            endLeft4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft4.png"));
            endLeft5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft5.png"));
            endLeft6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft6.png"));
            endLeft7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft7.png"));
            endLeft8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/left/endLeft8.png"));
            endLefts.addAll(Arrays.asList(endLeft1, endLeft2, endLeft3, endLeft4, endLeft5, endLeft6, endLeft7, endLeft8));

            endRight1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight1.png"));
            endRight2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight2.png"));
            endRight3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight3.png"));
            endRight4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight4.png"));
            endRight5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight5.png"));
            endRight6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight6.png"));
            endRight7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight7.png"));
            endRight8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/end/right/endRight8.png"));
            endRights.addAll(Arrays.asList(endRight1, endRight2, endRight3, endRight4, endRight5, endRight6, endRight7, endRight8));

            middleBottom1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom1.png"));
            middleBottom2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom2.png"));
            middleBottom3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom3.png"));
            middleBottom4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom4.png"));
            middleBottom5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom5.png"));
            middleBottom6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom6.png"));
            middleBottom7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom7.png"));
            middleBottom8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/bottom/middleBottom8.png"));
            middleBottoms.addAll(Arrays.asList(middleBottom1, middleBottom2, middleBottom3, middleBottom4, middleBottom5, middleBottom6, middleBottom7, middleBottom8));

            middleTop1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop1.png"));
            middleTop2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop2.png"));
            middleTop3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop3.png"));
            middleTop4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop4.png"));
            middleTop5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop5.png"));
            middleTop6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop6.png"));
            middleTop7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop7.png"));
            middleTop8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/top/middleTop8.png"));
            middleTops.addAll(Arrays.asList(middleTop1, middleTop2, middleTop3, middleTop4, middleTop5, middleTop6, middleTop7, middleTop8));

            middleLeft1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft1.png"));
            middleLeft2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft2.png"));
            middleLeft3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft3.png"));
            middleLeft4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft4.png"));
            middleLeft5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft5.png"));
            middleLeft6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft6.png"));
            middleLeft7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft7.png"));
            middleLeft8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/left/middleLeft8.png"));
            middleLefts.addAll(Arrays.asList(middleLeft1, middleLeft2, middleLeft3, middleLeft4, middleLeft5, middleLeft6, middleLeft7, middleLeft8));

            middleRight1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight1.png"));
            middleRight2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight2.png"));
            middleRight3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight3.png"));
            middleRight4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight4.png"));
            middleRight5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight5.png"));
            middleRight6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight6.png"));
            middleRight7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight7.png"));
            middleRight8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("explosion/middle/right/middleRight8.png"));
            middleRights.addAll(Arrays.asList(middleRight1, middleRight2, middleRight3, middleRight4, middleRight5, middleRight6, middleRight7, middleRight8));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
