package models;

public class Protagonist extends Character {
    private int money;
    private int hunger;
    private boolean isTired;

    public Protagonist(String name, int age, String description) {
        super(name, age, description);
    }

    public int getMoney() {
        return money;
    }

    public int getHunger() {
        return hunger;
    }

    public boolean isTired() {
        return money < 30 && hunger < 30;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setTired(boolean tired) {
        isTired = tired;
    }
}
