import models.CharacterDatabase;
import story.BackgroundMusic;
import utils.Dialog;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Story story = new Story(
                new Dialog(),
                new CharacterDatabase(),
                new BackgroundMusic()
        );
        try {
            story.startGame();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
