package Game;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Player {
    protected int hp;
    protected int attackPower;
    protected int defense;
    protected Image skin;

    public Player(int hp, int attackPower, int defense, String skinName) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        skin = new ImageIcon(getClass().getResource(skinName)).getImage();

    }


    
    
    public void addHP(int extraHp) {
        this.hp += extraHp;
    }
    
    public void addDefence(int extraDef) {

        this.defense += extraDef;
    }
    
    
    public void addAttackPower(int dmg) {
        this.attackPower += dmg;
        
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
