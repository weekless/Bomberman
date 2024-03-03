package main;

import powerup.PowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class UI{

    public enum SelectState{
        USERNAME_SELECTION, COLOR_SELECTION, FUNCTION_SELECTION
    }

    GamePanel gp;
    Font mistral48, roboto48, mistral112;
    BufferedImage titleScreen, selectWhite, selectBlue, selectBlack,selectWhiteDimmed, selectBlueDimmed, selectBlackDimmed, image, startButton, closeButton, startButtonDimmed, closeButtonDimmed;
    BufferedImage heartImage;
    BufferedImage gameOverScreenWhite, gameOverScreenBlue, gameOverScreenBlack;
    BufferedImage victoryScreenWhite1, victoryScreenBlue1, victoryScreenBlack1, victoryScreenWhite2, victoryScreenBlue2, victoryScreenBlack2;
    int arrowX, arrowY, selectedX;
    int animationCounter = 0;
    int spriteNum = 0;

    SelectState selectState = SelectState.USERNAME_SELECTION;
    int startOrNot = 1;

    public UI(GamePanel gp){

        this.gp = gp;
        mistral48 = new Font("Mistral", Font.PLAIN, 48);
        roboto48 = new Font("Arial", Font.PLAIN, 48);
        mistral112 = new Font("Mistral", Font.BOLD, 112);
        heartImage = gp.powerUpM.lifeUpImage;

        try {
            titleScreen = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/donPepe.png"));
            selectWhite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectWhite.png"));
            selectBlack = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectBlack.png"));
            selectBlue = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectBlue.png"));
            selectWhiteDimmed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectWhiteDimmed.png"));
            selectBlackDimmed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectBlackDimmed.png"));
            selectBlueDimmed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectBlueDimmed.png"));
            startButton = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectStart.png"));
            startButtonDimmed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectStartDimmed.png"));
            closeButton = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectClose.png"));
            closeButtonDimmed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/selectCloseDimmed.png"));
            gameOverScreenWhite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/gameOverWhite.png"));
            gameOverScreenBlue = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/gameOverBlue.png"));
            gameOverScreenBlack = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/gameOverBlack.png"));
            victoryScreenWhite1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenWhite1.png"));
            victoryScreenWhite2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenWhite2.png"));
            victoryScreenBlue1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenBlue1.png"));
            victoryScreenBlue2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenBlue2.png"));
            victoryScreenBlack1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenBlack1.png"));
            victoryScreenBlack2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/victoryScreenBlack2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g2){
        g2.drawImage(titleScreen, 0,0 ,1088, 768, null);
        g2.setFont(mistral112);
        g2.setColor(Color.black);
        g2.drawString("PRESS ENTER", 544-g2.getFontMetrics().stringWidth("PRESS ENTER")/2+5, 480+g2.getFontMetrics().getHeight()+5);
        g2.drawString("TO START", 544-g2.getFontMetrics().stringWidth("TO START")/2+5,600+g2.getFontMetrics().getHeight()+5);
        g2.setColor(Color.orange);
        g2.drawString("PRESS ENTER", 544-g2.getFontMetrics().stringWidth("PRESS ENTER")/2, 480+g2.getFontMetrics().getHeight());
        g2.drawString("TO START", 544-g2.getFontMetrics().stringWidth("TO START")/2,600+g2.getFontMetrics().getHeight());
    }

    public void drawSelectionScreen(Graphics2D g2){
        g2.setFont(mistral48);
        g2.setColor(Color.orange);
        g2.drawString("ENTER USERNAME:", 544-g2.getFontMetrics().stringWidth("ENTER USERNAME")/2, 113);
        g2.setColor(Color.getHSBColor(60,86,100));
        g2.fillRect(246, 177, 596, 48);
        g2.setColor(Color.black);
        int usernameX = g2.getFontMetrics().stringWidth(gp.username)/2;
        g2.drawString(gp.username,544-usernameX,177+40);
        g2.setColor(Color.orange);
        g2.drawImage(selectWhiteDimmed, 88, 288, 240, 240, null);
        g2.drawImage(selectBlackDimmed, 450, 288, 240, 240, null);
        g2.drawImage(selectBlueDimmed, 812, 288, 240, 240, null);
        g2.drawImage(startButtonDimmed, 248, 598, 200, 100, null);
        g2.drawImage(closeButtonDimmed, 695, 598, 200, 100, null);
        switch(selectState){
            case USERNAME_SELECTION -> {
                arrowX = 246-55;
                arrowY = 177+40;
            }
            case COLOR_SELECTION -> {
                arrowY = 328;
                switch(gp.colorSelection){
                    case WHITE -> {
                        arrowX = 33;
                        image = selectWhite;
                        selectedX = 88;
                    }
                    case BLACK -> {
                        arrowX = 395;
                        image = selectBlack;
                        selectedX = 450;
                    }
                    case BLUE -> {
                        arrowX = 757;
                        image = selectBlue;
                        selectedX = 812;
                    }
                }
            }
            case FUNCTION_SELECTION -> {
                arrowY = 657;
                switch (startOrNot){
                    case 0 -> {
                        arrowX = 640;
                        g2.drawImage(closeButton, 695, 598, 200, 100, null);
                    }
                    case 1 -> {
                        arrowX = 193;
                        g2.drawImage(startButton, 248, 598, 200, 100, null);
                    }
                }
            }
        }

        g2.drawImage(image, selectedX, 288, 240, 240, null);
        g2.drawString(">", arrowX, arrowY);
    }

    public void drawPlayingUI(Graphics2D g2){
        g2.setFont(mistral48);
        g2.setColor(Color.orange);
        g2.drawImage(heartImage, 32, 8, 48, 48, null);
        g2.drawString("= "+gp.player.lives, 96, 44);
        g2.drawString(gp.username,544-g2.getFontMetrics().stringWidth(gp.username)/2, 48 );
    }

    public void drawPausedUI(Graphics2D g2){
        g2.setFont(mistral48);
        g2.setColor(Color.orange);
        g2.drawString("PAUSED", 481, 48);
    }

    public void drawVictoryUI(Graphics2D g2){
        animationCounter++;
        if(animationCounter > 30){
            spriteNum = spriteNum%2 + 1;
            animationCounter = 0;
        }
        switch (gp.colorSelection){
            case WHITE -> {
                if(spriteNum == 1){
                    image = victoryScreenWhite1;
                }else{
                    image = victoryScreenWhite2;
                }
            }
            case BLUE -> {
                if(spriteNum == 1){
                    image = victoryScreenBlue1;
                }else{
                    image = victoryScreenBlue2;
                }
            }
            case BLACK -> {
                if(spriteNum == 1){
                    image = victoryScreenBlack1;
                }else{
                    image = victoryScreenBlack2;
                }
            }
        }
        g2.drawImage(image, 0, 0, 1088, 768, null);

    }

    public void drawGameOverUI(Graphics2D g2){
        switch (gp.colorSelection){
            case WHITE -> g2.drawImage(gameOverScreenWhite, 0, 0, 1088, 768, null);
            case BLUE -> g2.drawImage(gameOverScreenBlue, 0, 0, 1088, 768, null);
            case BLACK -> g2.drawImage(gameOverScreenBlack, 0, 0, 1088, 768, null);
        }

    }

}
