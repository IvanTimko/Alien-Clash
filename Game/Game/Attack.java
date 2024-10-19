package Game;

public class Attack {
    int damage;
    double hitChance;
    double variabilty;
    public Attack(int damage, double hitChance,double variabilty){
        this.damage=damage;
        this.hitChance=hitChance;
        this.variabilty=variabilty;
    }
    public int getDamage(){
        return damage;
    }
    public void addDamage(int change){
        this.damage+=change;
    }

}
