package Game;

import java.util.Random;

public class Attack {
    int damage;
    double hitChance;
    double variabilty;
    Random random = new Random();

    public Attack(int damage, double hitChance, double variabilty) {
        this.damage = damage;
        this.hitChance = hitChance;
        this.variabilty = variabilty;
    }

    public int getDamage() {
        return damage;
    }

    public void addDamage(int change) {
        this.damage += change;
    }

    public double getDamagePower() {
        if (hitChance > Math.random()) {
            return random.nextDouble(damage * variabilty, damage * (1 + variabilty));
        }
        return 0;
    }
}
