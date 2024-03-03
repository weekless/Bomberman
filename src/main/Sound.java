package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    Clip currentClip;
    URL soundURL[] = new URL[30];

    public Sound(){
        //OST
        soundURL[0] = getClass().getClassLoader().getResource("audio/Title.wav");
        soundURL[1] = getClass().getClassLoader().getResource("audio/Selection.wav");
        soundURL[2] = getClass().getClassLoader().getResource("audio/Level1.wav");
        soundURL[9] = getClass().getClassLoader().getResource("audio/Level2.wav");
        soundURL[10] = getClass().getClassLoader().getResource("audio/GameOver.wav");
        soundURL[11] = getClass().getClassLoader().getResource("audio/Credits.wav");

        //SOUND EFFECT
        soundURL[3] = getClass().getClassLoader().getResource("audio/TitleNameClick.wav");
        soundURL[4] = getClass().getClassLoader().getResource("audio/TitleStartClick.wav");
        soundURL[5] = getClass().getClassLoader().getResource("audio/Pause.wav");
        soundURL[6] = getClass().getClassLoader().getResource("audio/BombPlace.wav");
        soundURL[7] = getClass().getClassLoader().getResource("audio/BombExplosion.wav");
        soundURL[8] = getClass().getClassLoader().getResource("audio/Cursor.wav");
        soundURL[12] = getClass().getClassLoader().getResource("audio/Victory.wav");
        soundURL[13] = getClass().getClassLoader().getResource("audio/EnemyDies.wav");
        soundURL[14] = getClass().getClassLoader().getResource("audio/ItemGet.wav");


    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            switch (i){
                case 0, 2 -> gainControl.setValue(-20.0f);
                case 1, 5, 9, 10, 11, 12, 14-> gainControl.setValue(-15.0f);
                case 3, 4, 6, 7, 8, 13-> gainControl.setValue(-10.0f);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void play(){
        clip.start();
    }
    public void loop(){
        currentClip = clip;
        currentClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        currentClip.stop();
        currentClip.flush();
        currentClip.close();
    }

}
