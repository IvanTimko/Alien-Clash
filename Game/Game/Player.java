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

    public static final Player RED_Player_BASE = new Player("red", 200,
            new Attack(20, 0.8, 0.1),
            new Attack(25, 0.6, 0.1),
            new Attack(30, 0.9, 0.1),
            20, 0.1,"pictures/red-computer.png");
    public static final Player GREEN_Player_BASE = new Player("green", 300,
            new Attack(15, 0.8, 0.1),
            new Attack(20, 0.6, 0.1),
            new Attack(22, 0.9, 0.1),
            10, 0.1,"pictures/green-computer.png");
    public static final Player BlUE_Player_BASE = new Player("blue", 150,
            new Attack(25, 0.8, 0.1),
            new Attack(30, 0.6, 0.1),
            new Attack(35, 0.7, 0.1),
            25, 0.1,"pictures/blue-computer.png");

    // basic constructor with all stats as parameters
    public Player(String playerType, int hp, Attack attack1, Attack attack2, Attack ultimate, double defense,
            double generationVariabilty,String skinName) {
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
            ;
        }
        this.attack1.damage = (int) Math
                .round(VaryStat(newBase.attack1.damage * prototype.attack1.damage / prototypeBase.attack1.damage,
                        newBase.generationVariabilty));
        this.attack1.hitChance = Math
                .round(VaryStat(
                        newBase.attack1.hitChance * prototype.attack1.hitChance / prototypeBase.attack1.hitChance,
                        newBase.generationVariabilty) * 100)
                / 100;
        this.attack2.damage = (int) Math
                .round(VaryStat(newBase.attack2.damage * prototype.attack2.damage / prototypeBase.attack1.damage,
                        newBase.generationVariabilty));
        this.attack2.hitChance = Math.round(
                VaryStat(newBase.attack2.hitChance * prototype.attack2.hitChance / prototypeBase.attack2.hitChance,
                        newBase.generationVariabilty) * 100)
                / 100;
        this.ultimate.damage = (int) Math
                .round(VaryStat(newBase.ultimate.damage * prototype.ultimate.damage / prototypeBase.ultimate.damage,
                        newBase.generationVariabilty));
        this.ultimate.hitChance = Math.round(
                VaryStat(newBase.ultimate.hitChance * prototype.ultimate.hitChance / prototypeBase.ultimate.hitChance,
                        newBase.generationVariabilty) * 100)
                / 100;
        this.hp = (int) Math.round(VaryStat(newBase.hp * prototype.hp / prototypeBase.hp,
                newBase.generationVariabilty));
        this.defense = Math.round(VaryStat(newBase.defense * prototype.defense / prototypeBase.defense,
                newBase.generationVariabilty) * 100)
                / 100;
        this.skin = newBase.skin;
        this.generationVariabilty = newBase.generationVariabilty;
    }

    public double VaryStat(double statValue, double variabilty) {
        statValue = statValue * (1 + random.nextDouble(-variabilty, variabilty));
        return statValue;
    }

}
