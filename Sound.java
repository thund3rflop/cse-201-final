import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A simple sound class for background music.
 * 
 * @author Sam Kujawa
 *
 */
public class Sound {
    Clip clip;
    AudioInputStream sound;

    /**
     * A method to be called to set file name in GameFrame.
     * 
     * @param soundFileName The name of the audio file
     */
    public void setFile(String soundFileName) {
        try {
            // Creates file of audio
            File file = new File("BlueBoyAdventure.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that begins playing music when called in GameFrame.
     */
    public void play() {
        clip.start();
    }

    /**
     * Continuously loops sound.
     */
    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops playing the music when called.
     * 
     * @throws IOException Occurs when file path is wrong.
     */
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}