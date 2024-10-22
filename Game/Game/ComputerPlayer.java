package Game;

import java.util.Random;

public class ComputerPlayer extends Player {
    boolean startSecond;

    public ComputerPlayer(String PlayerType, int hp, Attack attack1, Attack attack2, Attack ultimate, double defense,
            double generationVariabilty,String skinName) {
        super(PlayerType,hp, attack1,attack2,  ultimate,defense,
             generationVariabilty,skinName);
        
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
