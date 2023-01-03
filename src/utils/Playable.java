package utils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface Playable {
    void startGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException;
}
