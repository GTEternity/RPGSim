//https://www.youtube.com/watch?v=TErboGLHZGA&t=180s

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    private static Clip clip;
    public static void play(String fileLocation) {
        try {
            File musicPath = new File(fileLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
            else {
                System.out.println("Can't find file.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop() {
        clip.stop();
    }
    public static boolean isDone(){
        return clip.getMicrosecondPosition() >= clip.getMicrosecondLength();
    }
    public static boolean isPlaying() {
        return clip != null;
    }
}