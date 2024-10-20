package Game;

import java.util.Random;

public class ComputerPlayer extends Player {
    boolean startSecond;

    public ComputerPlayer(int hp, double defense, String skinName, Attack at1, Attack at2, Attack ulti) {
        super(hp, defense, skinName, at1, at2, ulti);
        startSecond = true;

    }

    @Override
    public String getAction() {
        // Randomly choose between "attack" and "shield" actions for computer
        Random random = new Random();
        return random.nextBoolean() ? "attack1" : "shield";
    }

    public boolean getStart() {
        return startSecond;
    }

    // fixes the bug when new computer is created,otherwise it will not attack
    public void setStart() {
        this.startSecond = false;
    }
}
