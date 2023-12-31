package learningmyfirst2dgame;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30]; // USADA PARA ARMAZENAR A ROTA DOS ARQUIVOS DE SOM
    
    public Sound() { // CONSTRUCTOR
        
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/fanfare.wav");
        soundURL[3] = getClass().getResource("/sound/powerup.wav");
        soundURL[4] = getClass().getResource("/sound/unlock.wav");
    }
    
    public void setFile(int i) { // UMA DAS FORMAS DE ABRIR UM ARQUIVO DE SOM NO JAVA
        
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        } catch(Exception e) {            
        }
    }
    
    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
}
