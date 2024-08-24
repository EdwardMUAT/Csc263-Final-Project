import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class BackgroundMusic extends Thread {
    private final String filePath;
    private boolean running = true;

    public BackgroundMusic(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            try (Clip audioClip = AudioSystem.getClip()) {
                audioClip.open(audioStream);
                audioClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
                while (running) {
                    // Just keep the thread alive while music is playing
                    Thread.sleep(1000);
                }
                audioClip.stop();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.out.println("Error playing background music.");
        }
    }

    public void stopMusic() {
        running = false;
    }
}
