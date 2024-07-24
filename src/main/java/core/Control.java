package core;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;

public class Control implements KeyListener {


    GamePanel gp;

    public String lastArrowPressed;
    public boolean spacePressed, upPressed, downPressed, leftPressed, rightPressed;


    public Control(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (gp.state){
            case TITLE ->{
                if(code == KeyEvent.VK_ENTER){
                    gp.stopMusic();
                    gp.playSoundEffect(3);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    gp.playMusic(1);
                    gp.state = GamePanel.GameState.SELECTION;

                }
            }
            case SELECTION ->{
                switch (code){
                    //FOR SELECTION
                    case KeyEvent.VK_LEFT -> {
                        switch(gp.ui.selectState){
                            case COLOR_SELECTION -> {
                                switch (gp.colorSelection){
                                    case BLUE -> {
                                        gp.playSoundEffect(8);
                                        gp.colorSelection = GamePanel.PlayerSelection.BLACK;
                                    }
                                    case BLACK -> {
                                        gp.playSoundEffect(8);
                                        gp.colorSelection = GamePanel.PlayerSelection.WHITE;
                                    }
                                }
                            }
                            case FUNCTION_SELECTION -> {
                                if(gp.ui.startOrNot == 0){
                                    gp.playSoundEffect(8);
                                    gp.ui.startOrNot = 1;
                                }
                            }
                        }

                    }
                    case KeyEvent.VK_RIGHT -> {
                        switch(gp.ui.selectState){
                            case COLOR_SELECTION -> {
                                switch (gp.colorSelection){
                                    case WHITE -> {
                                        gp.playSoundEffect(8);
                                        gp.colorSelection = GamePanel.PlayerSelection.BLACK;
                                    }
                                    case BLACK -> {
                                        gp.playSoundEffect(8);
                                        gp.colorSelection = GamePanel.PlayerSelection.BLUE;
                                    }
                                }
                            }
                            case FUNCTION_SELECTION -> {
                                if(gp.ui.startOrNot == 1){
                                    gp.playSoundEffect(8);
                                    gp.ui.startOrNot = 0;
                                }
                            }
                        }
                    }
                    case KeyEvent.VK_ESCAPE -> {
                        switch (gp.ui.selectState){
                            case COLOR_SELECTION -> {
                                gp.playSoundEffect(8);
                                gp.ui.selectState = UI.SelectState.USERNAME_SELECTION;
                            }
                            case FUNCTION_SELECTION -> {
                                gp.playSoundEffect(8);
                                gp.ui.selectState = UI.SelectState.COLOR_SELECTION;
                            }
                        }
                    }
                    case KeyEvent.VK_ENTER -> {
                        switch (gp.ui.selectState){
                            case USERNAME_SELECTION -> {
                                if(!gp.username.equals("")){
                                    gp.playSoundEffect(8);
                                    gp.ui.selectState = UI.SelectState.COLOR_SELECTION;
                                }
                            }
                            case COLOR_SELECTION -> {
                                gp.playSoundEffect(8);
                                gp.ui.selectState = UI.SelectState.FUNCTION_SELECTION;
                            }
                            case FUNCTION_SELECTION -> {
                                switch (gp.ui.startOrNot){
                                    case 0 -> {
                                        gp.stopMusic();
                                        gp.playSoundEffect(4);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        System.exit(0);
                                    }
                                    case 1 -> {
                                        gp.stopMusic();
                                        gp.playSoundEffect(4);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        gp.createPlayer();
                                        gp.startLevel(1);
                                        gp.state = GamePanel.GameState.PLAYING;
                                    }
                                }
                            }
                        }
                    }
                    //FOR ENTERING USERNAME
                    case KeyEvent.VK_BACK_SPACE -> {
                        if(!gp.username.equals("") && gp.ui.selectState == UI.SelectState.USERNAME_SELECTION){
                            gp.username = gp.username.substring(0, gp.username.length() - 1);
                        }
                    }
                    default -> {
                        if(gp.ui.selectState == UI.SelectState.USERNAME_SELECTION){
                            if (Character.isAlphabetic(e.getKeyChar())) {
                                if (gp.username.length() < 20){
                                    gp.username += Character.toUpperCase(e.getKeyChar());
                                }
                            }
                        }
                    }
                }
            }
            case PLAYING -> {
                switch (code){
                    case KeyEvent.VK_ENTER -> {
                        gp.playSoundEffect(5);
                        gp.state = GamePanel.GameState.PAUSE;
                    }
                    case KeyEvent.VK_UP -> {
                        upPressed = true;
                        lastArrowPressed = "upPressed";
                    }
                    case KeyEvent.VK_DOWN -> {
                        downPressed = true;
                        lastArrowPressed = "downPressed";
                    }
                    case KeyEvent.VK_LEFT -> {
                        leftPressed = true;
                        lastArrowPressed = "leftPressed";
                    }
                    case KeyEvent.VK_RIGHT -> {
                        rightPressed = true;
                        lastArrowPressed = "rightPressed";
                    }
                    case KeyEvent.VK_SPACE -> spacePressed = true;
                }
            }
            case PAUSE -> {
                if (code == KeyEvent.VK_ENTER) {
                    gp.state = GamePanel.GameState.PLAYING;
                }
            }
            case GAMEOVER, VICTORY -> {
                gp.playSoundEffect(4);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.state == GamePanel.GameState.PLAYING || gp.state == GamePanel.GameState.PAUSE){
            switch (code){
                case KeyEvent.VK_UP -> upPressed = false;
                case KeyEvent.VK_DOWN -> downPressed = false;
                case KeyEvent.VK_LEFT -> leftPressed = false;
                case KeyEvent.VK_RIGHT -> rightPressed = false;
                case KeyEvent.VK_SPACE -> spacePressed = false;
            }
        }
    }
}

