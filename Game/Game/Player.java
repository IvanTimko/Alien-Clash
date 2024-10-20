package Game;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Player {
    protected int hp;
    protected double defense;
    protected Image skin;
    protected Attack attack1;
    protected Attack attack2;
    protected Attack ultimate;
    protected Random random = new Random();

    public Player(int hp, double defense, String skinName, Attack at1, Attack at2, Attack ulti) {
        this.hp = hp;
        this.defense = defense;
        skin = new ImageIcon(getClass().getResource(skinName)).getImage();
        this.attack1 = at1;
        this.attack2 = at2;
        this.ultimate = ulti;
    }

    public void addHP(int extraHp) {
        this.hp += extraHp;
    }

    public void addDefence(int extraDef) {

        this.defense += extraDef / 100;
    }

    public void addDamage(int dmg) {
        this.attack1.addDamage(dmg);
        this.attack2.addDamage(dmg);
        this.ultimate.addDamage(dmg);

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

    public double getAttack1Power() {

        return this.attack1.getDamagePower();
    }

    public double getAttack2Power() {

        return this.attack2.getDamagePower();
    }

    public double getUltimatePower() {

        return this.ultimate.getDamagePower();
    }

    public double getDefense() {
        return defense;
    }

    public void takeDamage(double damage) {
        this.hp -= Math.round(damage * (1 - defense));
        System.out.println(damage);
    }

    public String getAction() {
        return "attack1"; // default action for human, overridden by computer player
    }
}
