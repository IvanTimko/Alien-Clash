package Game;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(int hp, int attackPower, int defense, String skinName) {
        super(hp, attackPower, defense, skinName);

    }

    @Override
    public String getAction() {
        // Randomly choose between "attack" and "shield" actions for computer
        Random random = new Random();
        return random.nextBoolean() ? "attack" : "shield";
    }
}
