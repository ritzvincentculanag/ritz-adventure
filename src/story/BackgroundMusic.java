package story;

import utils.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic implements Sound {
    private Clip clip;
    private AudioInputStream audioInputStream;
    private String filePath;

    @Override
    public void play(String filePath, boolean loop) throws
            UnsupportedAudioFileException,
            IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        clip.start();
    }

    @Override
    public void stop() {
        clip.stop();
    }
}
