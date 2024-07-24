package core;

import bomb.Bomb;
import bomb.BombImages;
import bomb.Explosion;
import bomb.ExplosionImages;
import entity.Enemy;
import entity.Player;
import powerup.PowerUp;
import powerup.PowerUpManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    //GAME SETTINGS
    public final int tileSize = 64; // 64x64 tiles
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 12;
    int FPS = 60; // FPS



    //GAME STATES
    public enum GameState {
        TITLE, PAUSE, PLAYING, GAMEOVER, VICTORY, SELECTION
    }

    public enum PlayerSelection {
        WHITE, BLACK, BLUE
    }

    //INITIALIZATION
    public Thread gameThread;
    public GameState state;
    public PlayerSelection colorSelection;
    public String username = "";
    Control keyC = new Control(this);
    Sound sound = new Sound();
    public PowerUpManager powerUpM = new PowerUpManager(this);
    public UI ui = new UI(this);
    public CollisionCheck cChecker = new CollisionCheck(this);
    public BombImages bombImages = new BombImages();
    public ExplosionImages explosionImages = new ExplosionImages();
    public Player player;
    public Enemy enemyM = new Enemy(this);
    public TileManager tileM = new TileManager(this);
    public ArrayList<Bomb> bombs = new ArrayList<>();
    public ArrayList<PowerUp> powerUps = new ArrayList<>();



    public GamePanel(){
        this.setPreferredSize(new Dimension(maxScreenCol * tileSize,maxScreenRow * tileSize));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyC);
        this.setFocusable(true);
        this.setupGame();
    }

    public void setupGame(){
        state = GameState.TITLE;
        colorSelection = PlayerSelection.WHITE;
        playMusic(0);
    }

    public void createPlayer(){
        player = new Player(this, keyC);
    }

    public void startLevel(int i){
        player.setDefaultValues();
        bombs.clear();
        powerUps.clear();
        enemyM.setUpEnemyList(i);
        tileM.setupMap(i);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime -  lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(state == GameState.PLAYING){
            //UPDATES TILES
            tileM.update();
            //UPDATES POWERUPS
            if(!powerUps.isEmpty()){
                for(int i = powerUps.size()-1; i>= 0; i--){
                    if(!powerUps.get(i).shouldDisappear){
                        powerUps.get(i).update();
                    }else{
                        powerUps.remove(i);
                    }
                }
            }
            //UPDATES PLAYER
            player.update();
            //UPDATES ENEMIES
            if (!enemyM.enemyList.isEmpty()){
                for(int i = enemyM.enemyList.size()-1; i >= 0; i--){
                    if(!enemyM.enemyList.get(i).shouldDisappear){
                        enemyM.enemyList.get(i).update();
                    }else{
                        enemyM.enemyList.remove(i);
                    }
                }
            }
            //UPDATES BOMBS AND EXPLOSIONS
            if(!bombs.isEmpty()){
                for(int i = bombs.size()-1; i >= 0; i--){
                    if(!bombs.get(i).exploded){
                        bombs.get(i).update();
                    }else{
                        if(bombs.get(i).explosions.isEmpty()){
                            bombs.remove(i);
                        }else{
                            for(int j = bombs.get(i).explosions.size()-1; j >= 0; j--){
                                if(!bombs.get(i).explosions.get(j).shouldDisappear){
                                    bombs.get(i).explosions.get(j).update();
                                }else{
                                    bombs.get(i).explosions.remove(j);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch (state){
            case TITLE -> ui.drawTitleScreen(g2);
            case SELECTION -> ui.drawSelectionScreen(g2);
            case PLAYING -> {
                tileM.draw(g2);
                ui.drawPlayingUI(g2);
                if(!powerUps.isEmpty()){
                    for(PowerUp powerUp : powerUps){
                        powerUp.draw(g2);
                    }
                }
                if (!bombs.isEmpty()){
                    for(Bomb bomb : bombs){
                        if(!bomb.exploded){
                            bomb.draw(g2);
                        }
                        for(Explosion explosion : bomb.explosions){
                            explosion.draw(g2);
                        }
                    }
                }
                player.draw(g2);
                if (!enemyM.enemyList.isEmpty()){
                    for(Enemy e : enemyM.enemyList){
                        e.draw(g2);
                    }
                }
            }
            case PAUSE -> {
                tileM.draw(g2);
                ui.drawPausedUI(g2);
                if(!powerUps.isEmpty()){
                    for(PowerUp powerUp : powerUps){
                        powerUp.draw(g2);
                    }
                }
                if(!bombs.isEmpty()){
                    for(Bomb bomb : bombs){
                        bomb.draw(g2);
                        for(Explosion explosion : bomb.explosions){
                            explosion.draw(g2);
                        }
                    }

                }
                player.draw(g2);
                if(!enemyM.enemyList.isEmpty()){
                    for(Enemy e : enemyM.enemyList){
                        e.draw(g2);
                    }
                }
            }
            case VICTORY -> ui.drawVictoryUI(g2);
            case GAMEOVER -> ui.drawGameOverUI(g2);
        }

    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }


}