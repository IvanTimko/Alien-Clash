package Game;

import javax.swing.*;

public class Player {
    protected int hp;
    protected int attackPower;
    protected int defense;
    protected JLabel playerSkin;
    

    public Player(int hp, int attackPower, int defense) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public String getAction() {
        return "attack"; // default action for human, overridden by computer player
    }
}
