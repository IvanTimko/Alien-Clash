package Game;

import java.awt.*;
import javax.swing.*;
import java.util.Random;
public class Player {
    protected int hp;
    protected int defense;
    protected Image skin;
    protected Attack attack;
    protected Random random = new Random();
    public Player(int hp, int defense, String skinName, int damage, double hitChance, double variabilty) {
        this.hp = hp;
        this.defense = defense;
        skin = new ImageIcon(getClass().getResource(skinName)).getImage();
        attack=new Attack(damage,hitChance,variabilty);
    }

    
    
    public void addHP(int extraHp) {
        this.hp += extraHp;
    }
    
    public void addDefence(int extraDef) {

        this.defense += extraDef;
    }
    
    
    public void addDamage(int dmg) {
        this.attack.addDamage(dmg);
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

        return this.attack.getDamage();
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
