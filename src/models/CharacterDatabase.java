package models;

import utils.Database;

import java.util.ArrayList;
import java.util.List;

public class CharacterDatabase implements Database<Character> {
    private final List<Character> characters;

    public CharacterDatabase() {
        characters = new ArrayList<>(List.of(
                new Character(
                        "Habeb",
                        20,
                        "The mafia boss whose skills specialize in videography,\n" +
                                "photography, as well as video editing.\n"
                ),
                new Character(
                        "Zoid",
                        19,
                        "The mayor's son, prefers to say lowkey but always rides\n" +
                                "a limousine on the way home. Says that he's not the son of\n" +
                                "Mayor Agduma when in fact, he is the son of Mayor Agduma.\n"
                ),
                new Character(
                        "Kim (Cezar)",
                        19,
                        "The chick magnet whose skills and knowledge exceed that\n" +
                                "of anyone, far beyond your imagination.\n"
                ),
                new Character(
                        "Harth",
                        19,
                        "The introvert (or so you think), in the group. The one whose\n" +
                                "always quiet but full of surprises.\n"
                )
        ));
    }

    @Override
    public List<Character> getAll() {
        return characters;
    }
}
