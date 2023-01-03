package models;

public class Character {
    private final String name;
    private final int age;
    private final String description;

    public Character(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }
}
