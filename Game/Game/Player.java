package Game;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Player {
    protected int hp;
    protected int maxHP;
    protected double defense;
    protected Image skin;
    protected Attack attack1;
    protected Attack attack2;
    protected Attack ultimate;
    protected Random random = new Random();
    protected double generationVariabilty;
    protected String playerType;
    protected Player base;

    public static final Player RED_Player_BASE = new Player("red", 100,
            new Attack(30, 0.8, 0.1),
            new Attack(35, 0.6, 0.1),
            new Attack(45, 0.9, 0.1),
            20, 0.1, "pictures/red-computer.png");
    public static final Player GREEN_Player_BASE = new Player("green", 150,
            new Attack(35, 0.8, 0.1),
            new Attack(30, 0.6, 0.1),
            new Attack(32, 0.9, 0.1),
            10, 0.1, "pictures/green-computer.png");
    public static final Player BlUE_Player_BASE = new Player("blue", 80,
            new Attack(35, 0.8, 0.1),
            new Attack(40, 0.6, 0.1),
            new Attack(45, 0.7, 0.1),
            25, 0.1, "pictures/blue-computer.png");

    // basic constructor with all stats as parameters
    public Player(String playerType, int hp, Attack attack1, Attack attack2, Attack ultimate, double defense,
            double generationVariabilty, String skinName) {
        skin = new ImageIcon(getClass().getResource(skinName)).getImage();
        this.playerType = playerType;
        this.hp = hp;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.ultimate = ultimate;
        this.defense = defense;
        this.generationVariabilty = generationVariabilty;
        this.maxHP = hp;

    }

    public int getMaxHp() {
        return this.maxHP;
    }

    public void addHP(int extraHp) {
        this.hp += extraHp;
        if (this.hp > this.maxHP) {
            this.hp = maxHP;
        }
    }

    public void addDefence(int extraDef) {

        this.defense += extraDef / 100;
    }

    public void addDamage(int dmg) {
        this.attack1.addDamage(dmg);
        this.attack2.addDamage(dmg);
        this.ultimate.addDamage(dmg);

    }

    public void addPercentageDam(double percentage) {
        this.defense = this.defense * (1 + percentage / 100.0);
        this.attack1.setDamage((int) Math.round(this.attack1.damage * (1 + percentage / 100.0)));
        this.attack2.setDamage((int) Math.round(this.attack1.damage * (1 + percentage / 100.0)));
        this.ultimate.setDamage((int) Math.round(this.attack1.damage * (1 + percentage / 100.0)));
    }

    public Image getSkin() {
        return skin;
    }

    public void setSkin(String skinPath) {
        this.skin = new ImageIcon(getClass().getResource(skinPath)).getImage();
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
        damage = Math.max(Math.round(damage * (1 - this.defense / 100.0)), 0);
        this.hp -= damage;
        System.out.println("damage delth" + damage + ",hp after:" + this.hp);

    }

    public String getAction() {
        return "attack1"; // default action for human, overridden by computer player
    }

    public void healPercentage(double percentage) {
        this.hp += (int) Math.round((this.maxHP - this.hp) * percentage / 100.0);
    }

    public void incPercentageDef(double percentage) {
        this.defense = this.defense * (1 + percentage / 100.0);
        if (this.defense > 100) {
            this.defense = 100;
        }
    }

    public void increaseDef(int value) {
        this.defense += value;
        if (this.defense > 100) {
            this.defense = 100;
        }
    }

    public void decreaseDefense(int value) {
        this.defense -= value;
        if (this.defense < 0) {
            this.defense = 0;
        }
    }

    // base constructor with no stats
    public Player() {
        this.hp = 100;
        this.defense = 0.1;
        this.attack1 = new Attack(10, 1, 0);
        this.attack2 = new Attack(10, 1, 0);
        this.ultimate = new Attack(10, 1, 0);
        this.generationVariabilty = 0;

    }

    // returns base Player of the same type as on parameter
    public Player findBase(Player player) {
        if (player.playerType.equals("red")) {
            return (RED_Player_BASE);
        } else if (player.playerType.equals("blue")) {
            return (BlUE_Player_BASE);
        } else if (player.playerType.equals("green")) {
            return (GREEN_Player_BASE);
        } else {
            return new Player();
        }
    }

    public Player(Player prototype, String playerType) {
        Player prototypeBase = findBase(prototype);
        Player newBase = new Player();
        if (playerType.equals("red")) {
            newBase = RED_Player_BASE;
        } else if (playerType.equals("blue")) {
            newBase = BlUE_Player_BASE;
        } else if (playerType.equals("green")) {
            newBase = GREEN_Player_BASE;
        }
        this.attack1 = new Attack((int) Math.round(VaryStat(
                newBase.attack1.damage * prototype.attack1.damage / prototypeBase.attack1.damage,
                newBase.generationVariabilty)),
                Math.round(VaryStat(
                        newBase.attack1.hitChance * prototype.attack1.hitChance / prototypeBase.attack1.hitChance,
                        newBase.generationVariabilty) * 100) / 100.0,
                newBase.attack1.variabilty);
        this.attack2 = new Attack((int) Math.round(VaryStat(
                newBase.attack2.damage * prototype.attack2.damage / prototypeBase.attack2.damage,
                newBase.generationVariabilty)),
                Math.round(VaryStat(
                        newBase.attack2.hitChance * prototype.attack2.hitChance / prototypeBase.attack2.hitChance,
                        newBase.generationVariabilty) * 100.0) / 100.0,
                newBase.attack2.variabilty);
        this.ultimate = new Attack((int) Math
                .round(VaryStat(newBase.ultimate.damage * prototype.ultimate.damage / prototypeBase.ultimate.damage,
                        newBase.generationVariabilty)),
                Math.round(
                        VaryStat(
                                newBase.ultimate.hitChance * prototype.ultimate.hitChance
                                        / prototypeBase.ultimate.hitChance,
                                newBase.generationVariabilty) * 100.0)
                        / 100.0,
                newBase.ultimate.variabilty);
        this.hp = (int) Math.round(VaryStat(newBase.hp * prototype.hp / prototypeBase.hp,
                newBase.generationVariabilty));
        this.maxHP = this.hp;
        this.defense = Math.round(VaryStat(newBase.defense * prototype.defense / prototypeBase.defense,
                newBase.generationVariabilty));
        this.skin = newBase.skin;
        this.generationVariabilty = newBase.generationVariabilty;
    }

    public double VaryStat(double statValue, double variabilty) {
        statValue = statValue * (1 + random.nextDouble(-variabilty, variabilty));
        return statValue;
    }

}
