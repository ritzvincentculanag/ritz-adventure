import com.sun.security.jgss.GSSUtil;
import models.Character;
import models.Protagonist;
import story.Audio;
import story.Dialogs;
import story.StoryMode;
import utils.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Story implements Playable {
    private final Printer printer;
    private final Database<Character> database;
    private final Sound sound;
    private StoryMode storyMode;
    private Protagonist protagonist;

    public Story(Printer printer, Database<Character> database, Sound sound) {
        this.printer = printer;
        this.database = database;
        this.sound = sound;
        protagonist = new Protagonist(
                "Ritz",
                19,
                "A boy who likes to code and make applications\n" +
                        "for both mobile and web. He also likes to listen to\n" +
                        "music and play the piano.\n"
        );
        protagonist.setMoney(100);
        protagonist.setHunger(50);
    }

    @Override
    public void startGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        showLoadingScreen();
        try {
            sound.play(Audio.TITLE, true);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            showMenu();
            switch (storyMode) {
                case ADVENTURE_MODE -> renderAdventureMode();
                case ZEN_MODE -> renderZenMode();
                case CHARACTER_MODE -> renderCharacterMode();
                default -> System.exit(0);
            }
        }
    }

    private void showLoadingScreen() {
        printer.printFromFile(Dialogs.BANNER);
        System.out.println();
    }

    private void showMenu() {
        printer.printFromFile(Dialogs.MENU);

        int mode = Console.getIntegerInput("Enter mode: ");
        switch (mode) {
            case 1 -> storyMode = StoryMode.ADVENTURE_MODE;
            case 2 -> storyMode = StoryMode.ZEN_MODE;
            case 3 -> storyMode = StoryMode.CHARACTER_MODE;
            case 4 -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input!");
                System.exit(0);
            }
        }

        Console.clearScreen();
        try {
            sound.stop();
            sound.play(Audio.SELECT, false);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderAdventureMode() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        printer.printCountdown(3, "Starting Adventure");

        sound.stop();
        sound.play(Audio.ADVENTURE, true);

        Console.clearScreen();
        printStatus();
        System.out.println("Press E to read through the story");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("./src/res/adventure.txt"));
            String line = fileReader.readLine();
            String response = "";
            while (line != null) {
                System.out.println(line);
                printer.printSeparator();

                if (line.contains("7/11")) {
                    sound.stop();
                    sound.play(Audio.JOPAY, true);
                }

                if (line.contains("Lakad")) {
                    response = Console.getStringInput("Pindutin ang Y kung papayag ka na lumakad: ");
                    if (response.equalsIgnoreCase("n")) {
                        System.out.println("Nauna ka ng umuwi");
                        System.exit(0);
                    } else {
                        System.out.println("Game!");
                    }

                    line = fileReader.readLine();
                    continue;
                } else if (line.contains("hotdog")) {
                    response = Console.getStringInput("Pindutin ang Y kung papayag ka na bumili ng hotdog: ");
                    if (response.equalsIgnoreCase("y")) {
                        System.out.println("Tara, nagugutom ako eh");
                        this.protagonist.setMoney(protagonist.getMoney() - 39);
                        this.protagonist.setHunger(protagonist.getHunger() + 10);
                    } else {
                        System.out.println("Sa sunod nalang, nagtitipid ako eh. Pero sasama lang ako");
                    }

                    line = fileReader.readLine();
                    continue;
                } else if (line.contains("nakabili")) {
                    printStatus();
                    System.out.println(line);
                    printer.printSeparator();
                }

                if (Console.getStringInput("").equalsIgnoreCase("E")) {
                    line = fileReader.readLine();
                    Console.clearScreen();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printStatus();
    }

    private void printStatus() {
        Console.clearScreen();
        printer.printSeparator();
        System.out.println("Money left: " + protagonist.getMoney());
        System.out.println("Hunger: " + protagonist.getHunger());
    }

    private void renderZenMode() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        printer.printCountdown(3, "Starting Zen Mode");

        sound.stop();
        sound.play(Audio.ADVENTURE, true);

        Console.clearScreen();
        System.out.println("Press E to read through the story");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("./src/res/story.txt"));
            String line = fileReader.readLine();
            while (line != null) {
                System.out.println(line);
                printer.printSeparator();
                if (Console.getStringInput("").equalsIgnoreCase("E")) {
                    if (line.contains("7/11")) {
                        sound.stop();
                        sound.play(Audio.JOPAY, true);
                    }
                    line = fileReader.readLine();
                    Console.clearScreen();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderCharacterMode() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Console.clearScreen();
        while (true) {
            printCharacters();
            System.out.println("Enter a number greater than 4 to exit");
            int characterCode = Console.getIntegerInput("Choose a character to view information: ");
            if (characterCode > database.getAll().size()) {
                Console.clearScreen();
                break;
            }

            printCharacterInformation(characterCode);
        }
    }

    private void printCharacters() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Character> characters = database.getAll();

        sound.play(Audio.SELECT, false);
        System.out.println("Characters");
        printer.printSeparator();
        for (Character character : characters) {
            int characterIndex = characters.indexOf(character) + 1;
            String characterName = character.getName();
            System.out.printf("%d. %s\n", characterIndex, characterName);
        }
        printer.printSeparator();
    }

    private void printCharacterInformation(int characterCode) {
        List<Character> characters = database.getAll();

        printer.printSeparator();
        for (int i = 0; i < characters.size(); i++) {
            if (i == (characterCode - 1)) {
                Console.clearScreen();

                Character character = characters.get(i);
                System.out.printf(
                        "Name: %s\nAge: %s\nDescription: %s\n",
                        character.getName(),
                        character.getAge(),
                        character.getDescription()
                );
            }
        }
    }
}
