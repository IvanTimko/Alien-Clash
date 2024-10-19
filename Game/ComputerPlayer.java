package Game;

import java.util.Random;

public class ComputerPlayer extends Player {
    boolean startSecond;

    public ComputerPlayer(int hp, int attackPower, int defense) {
        super(hp, attackPower, defense);
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
