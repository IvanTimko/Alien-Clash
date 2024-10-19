package Game;

import java.util.Random;

public class ComputerPlayer extends Player {
    boolean startSecond;


    public ComputerPlayer(int hp, int defense, String skinName, int damage, double hitChance, double variabilty) {
        super(hp, defense, skinName, damage, hitChance, variabilty);
        startSecond = true;

   
        
        

    }

    @Override
    public String getAction() {
        // Randomly choose between "attack" and "shield" actions for computer
        Random random = new Random();
        return random.nextBoolean() ? "attack" : "shield";
    }

    public boolean getStart() {
        return startSecond;
    }
    //fixes the bug when new computer is created,otherwise it will not attack
    public void setStart() {
        this.startSecond = false;
    }
}
