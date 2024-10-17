package Game;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Player {
    protected int hp;
    protected int attackPower;
    protected int defense;
    protected Image skin;
    protected String[] Skins = new String[] {
            "blue-player.png", "red-player.png", "green-player.png" };
    protected Random random = new Random();

    public Player(int hp, int attackPower, int defense) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        skin = new ImageIcon(getClass().getResource(Skins[random.nextInt(3)])).getImage();

    }
    public void setDefence(int extraDef) {
        this.defense += extraDef;
    }

    public void setRandomSkin() {
        this.skin = new ImageIcon(getClass().getResource(Skins[random.nextInt(3)])).getImage();
    }

    public Image getSkin() {
        return skin;
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
