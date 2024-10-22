package Game;

import java.util.Random;

import java.awt.*;

public class Alien {
    protected int hp;
    protected double defense;
    protected Image skin;
    protected Attack attack1;
    protected Attack attack2;
    protected Attack ultimate;
    protected double generationVariabilty;
    protected Random random = new Random();
    protected String alienType;
    protected Alien base;
    public static final Alien RED_ALIEN_BASE = new Alien("red", 200,
            new Attack(20, 0.8, 0.1),
            new Attack(25, 0.6, 0.1),
            new Attack(30, 0.9, 0.1),
            20, 0.1);
    public static final Alien GREEN_ALIEN_BASE = new Alien("green", 300,
            new Attack(15, 0.8, 0.1),
            new Attack(20, 0.6, 0.1),
            new Attack(22, 0.9, 0.1),
            10, 0.1);
    public static final Alien BlUE_ALIEN_BASE = new Alien("blue", 150,
            new Attack(25, 0.8, 0.1),
            new Attack(30, 0.6, 0.1),
            new Attack(35, 0.7, 0.1),
            25, 0.1);

    // base constructor with no stats
    public Alien() {
        this.hp = 100;
        this.defense = 0.1;
        this.attack1 = new Attack(10, 1, 0);
        this.attack2 = new Attack(10, 1, 0);
        this.ultimate = new Attack(10, 1, 0);
        this.generationVariabilty = 0;

    }

    // basic constructor with all stats as parameters
    public Alien(String AlienType, int hp, Attack attack1, Attack attack2, Attack ultimate, double defense,
            double generationVariabilty) {
        this.alienType = AlienType;
        this.hp = hp;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.ultimate = ultimate;
        this.defense = defense;
        this.generationVariabilty = generationVariabilty;

    }

    public Alien findBase(Alien alien) {
        if (alien.alienType.equals("red")) {
            return (RED_ALIEN_BASE);
        } else if (alien.alienType.equals("blue")) {
            return (BlUE_ALIEN_BASE);
        } else if (alien.alienType.equals("green")) {
            return (GREEN_ALIEN_BASE);
        } else {
            return new Alien();
        }
    }

    public Alien(Alien prototype, Alien prototypeBase, Alien newBase) {
        this.attack1.damage = newBase.attack1.damage * prototype.attack1.damage;
    }

    public double CalculateNewStat(double newbase, double protoype, double prototypeBase) {
        return newbase;

    }

    public void generateAlien(String AlienType, Alien prototype) {
        if (AlienType.equals("red")) {
            Alien(prototype, BlUE_ALIEN_BASE, RED_ALIEN_BASE);
        } else if (AlienType.equals("blue")) {
            generateBlueAlien(prototype);
        } else if (AlienType.equals("green")) {
            generateGreenAlien(prototype);
        }
    }

    public Alien generateRedAlien(Alien prototype) {
        Alien basePrototype = RED_ALIEN_BASE;
        Alien newAlien = new Alien(

        );
        return newAlien;
    }

    public Alien generateBlueAlien(Alien prototype) {
        Alien newAlien = prototype;
        return newAlien;
    }

    public Alien generateGreenAlien(Alien prototype) {
        Alien newAlien = prototype;
        return newAlien;
    }

}
