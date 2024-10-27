package Game;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * Class player That creates instances of Aliens, which are controlled by the human.
 */
public class Player {
    protected int hp;
    protected int maxHP;
    protected double defence;
    protected Image skin;
    protected Attack attack1;
    protected Attack attack2;
    protected Attack ultimate;
    protected Random random = new Random();
    protected double generationVariabilty;
    protected String playerType;
    protected Player base;

    public static final Player RED_PLAYER_BASE = new Player("red", 100,
            new Attack(30, 0.8, 0.1),
            new Attack(35, 0.6, 0.1),
            new Attack(45, 0.9, 0.1),
            20, 0.1, "pictures/red-computer.png");
    public static final Player GREEN_PLAYER_BASE = new Player("green", 150,
            new Attack(35, 0.8, 0.1),
            new Attack(30, 0.6, 0.1),
            new Attack(32, 0.9, 0.1),
            10, 0.1, "pictures/green-computer.png");
    public static final Player BLUE_PLAYER_BASE = new Player("blue", 80,
            new Attack(35, 0.8, 0.1),
            new Attack(40, 0.6, 0.1),
            new Attack(45, 0.7, 0.1),
            25, 0.1, "pictures/blue-computer.png");

    /**
     * Constructor that creates player with given attributes.
     * @param playerType type of alien player will use red/green/blue
     * @param hp how much hp will he have.
     * @param attack1 how much dmg will attack1 have
     * @param attack2 how much dmg will attack2 have
     * @param ultimate how much dmg will ultimate have
     * @param defence how much defence will he have
     * @param generationVariabilty variability of stats , so it will be unique
     * @param skinName what skin will he have
     */ 
    public Player(String playerType, int hp, Attack attack1, 
        Attack attack2, Attack ultimate, double defence,
            double generationVariabilty, String skinName) {
        this.skin = new ImageIcon(getClass().getResource(skinName)).getImage();
        this.playerType = playerType;
        this.hp = hp;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.ultimate = ultimate;
        this.defence = defence;
        this.generationVariabilty = generationVariabilty;
        this.maxHP = hp;

    }

    public int getMaxHp() {
        return this.maxHP;
    }

    /**
     * Heals Player.
     * @param extraHp amount of health added
     */
    public void addHP(int extraHp) {
        this.hp += extraHp;
        if (this.hp > this.maxHP) {
            this.hp = maxHP;
        }
    }

    /**
     * Add player extra defence.
     * @param extraDef how much % def will be added
     */
    public void addDefence(int extraDef) {

        this.defence += extraDef / 100;
    }

    /**
     * Add player extra dmg.
     * @param dmg how much dmg will be added
     */
    public void addDamage(int dmg) {
        this.attack1.addDamage(dmg);
        this.attack2.addDamage(dmg);
        this.ultimate.addDamage(dmg);

    }

    /**
     * Add player extra dmg in %.
     * @param percentage how much % of dmg will be added
     */
    public void addPercentageDam(double percentage) {
        this.defence = this.defence * (1 + percentage / 100.0);
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
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    
    /**
     * Getter for attack1 dmg.
     * @return damage of attack1;
     */
    public double getAttack1Power() {

        return this.attack1.getDamagePower();
    }

    /**
     * Getter for attack2 dmg.
     * @return damage of attack2;
     */
    public double getAttack2Power() {

        return this.attack2.getDamagePower();
    }

    /**
     * Getter for ultimate dmg.
     * @return damage of ultimate;
     */
    public double getUltimatePower() {

        return this.ultimate.getDamagePower();
    }

    public double getDefence() {
        return this.defence;
    }

    /**
     * Method to determine dmg dealt.
     * @param damage initial damage
     * @return reduced damage by opponent
     */
    public int takeDamage(double damage) {
        damage = Math.max(Math.round(damage * (1 - this.defence / 100.0)), 0);
        this.hp -= damage;
        return (int) damage;
    }

    public String getAction() {
        return "attack1"; // default action for human, overridden by computer player
    }

    public void healPercentage(double percentage) {
        this.hp += (int) Math.round((this.maxHP - this.hp) * percentage / 100.0);
    }

    /**
     * add % of defence.
     * @param percentage % of defence to be added
     */
    public void incPercentageDef(double percentage) {
        this.defence = this.defence * (1 + percentage / 100.0);
        if (this.defence > 100) {
            this.defence = 100;
        }
    }

    /**
     * Add defence.
     * @param value  how much defence will be added
     */
    public void increaseDef(int value) {
        this.defence += value;
        if (this.defence > 100) {
            this.defence = 100;
        }
    }

    /**
     * Decrease defence.
     * @param value  how much defence will be subtracted
     */
    public void decreaseDefence(int value) {
        this.defence -= value;
        if (this.defence < 0) {
            this.defence = 0;
        }
    }

    /**
     * Initial Player Contructor.
     */
    public Player() {
        this.hp = 100;
        this.defence = 0.1;
        this.attack1 = new Attack(10, 1, 0);
        this.attack2 = new Attack(10, 1, 0);
        this.ultimate = new Attack(10, 1, 0);
        this.generationVariabilty = 0;

    }

    
    /**
     * Returns base Player of the same type as on parameter.
     */
    public Player findBase(Player player) {
        if (player.playerType.equals("red")) {
            return (RED_PLAYER_BASE);
        } else if (player.playerType.equals("blue")) {
            return (BLUE_PLAYER_BASE);
        } else if (player.playerType.equals("green")) {
            return (GREEN_PLAYER_BASE);
        } else {
            return new Player();
        }
    }

    /**
     * Constructor that creates unique Character from given prototype and playerType.
     */
    public Player(Player prototype, String playerType) {
        Player prototypeBase = findBase(prototype);
        Player newBase = new Player();
        if (playerType.equals("red")) {
            newBase = RED_PLAYER_BASE;
        } else if (playerType.equals("blue")) {
            newBase = BLUE_PLAYER_BASE;
        } else if (playerType.equals("green")) {
            newBase = GREEN_PLAYER_BASE;
        }
        this.attack1 = new Attack((int) Math.round(varyStat(
                newBase.attack1.damage * prototype.attack1.damage / prototypeBase.attack1.damage,
                newBase.generationVariabilty)),
                Math.round(varyStat(
                        newBase.attack1.hitChance * prototype.attack1.hitChance 
                        / prototypeBase.attack1.hitChance,
                        newBase.generationVariabilty) * 100) / 100.0,
                newBase.attack1.variabilty);
        this.attack2 = new Attack((int) Math.round(varyStat(
                newBase.attack2.damage * prototype.attack2.damage / prototypeBase.attack2.damage,
                newBase.generationVariabilty)),
                Math.round(varyStat(
                        newBase.attack2.hitChance * prototype.attack2.hitChance 
                        / prototypeBase.attack2.hitChance,
                        newBase.generationVariabilty) * 100.0) / 100.0,
                newBase.attack2.variabilty);
        this.ultimate = new Attack((int) Math
                .round(varyStat(newBase.ultimate.damage * prototype.ultimate.damage 
                / prototypeBase.ultimate.damage,
                        newBase.generationVariabilty)),
                Math.round(
                        varyStat(
                                newBase.ultimate.hitChance * prototype.ultimate.hitChance
                                        / prototypeBase.ultimate.hitChance,
                                newBase.generationVariabilty) * 100.0)
                        / 100.0,
                newBase.ultimate.variabilty);
        this.hp = (int) Math.round(varyStat(newBase.hp * prototype.hp / prototypeBase.hp,
                newBase.generationVariabilty));
        this.maxHP = this.hp;
        this.defence = Math.round(varyStat(newBase.defence * prototype.defence 
        / prototypeBase.defence,
                newBase.generationVariabilty));
        this.skin = newBase.skin;
    
        this.generationVariabilty = newBase.generationVariabilty;
    }

    /**
     * Method that creates variability of a value.
     * @param statValue which value is being varied.
     * @param variabilty how big is the variability (-variability,variability)
     * @return created value in range(-variability,variability)
     */
    public double varyStat(double statValue, double variabilty) {

        statValue = statValue * (1 + random.nextDouble(-variabilty, variabilty));
        return statValue;
    }

}
