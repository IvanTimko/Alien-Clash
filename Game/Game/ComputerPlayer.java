package Game;

import java.util.Random;

import javax.swing.ImageIcon;

public class ComputerPlayer extends Player {
    boolean startSecond;
    int lastPUltiCountdown;
    int lastPDefCountdown;

    public ComputerPlayer(String PlayerType, int hp, Attack attack1, Attack attack2, Attack ultimate, double defense,
            double generationVariabilty, String skinName) {
        super(PlayerType, hp, attack1, attack2, ultimate, defense,
                generationVariabilty, skinName);

        startSecond = true;
    }

    public ComputerPlayer(Player basePlayer, String skinName) {
        // Call the parent Player class constructor that accepts a Player and String
        super(basePlayer, skinName);
        this.startSecond = true; // Other specific logic for ComputerPlayer

    }

    public String getAction(int playerUltiCountDown, int playerDefenseCountDown, int computerUltiCountDown,
            int computerDefenseCountDown) {

        if (playerDefenseCountDown > 0 && computerUltiCountDown <= 0
                && lastPDefCountdown - playerDefenseCountDown == 1) {

            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;

            return "ultimate";
        } else if (computerUltiCountDown <= 0) {
            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;
            return (Math.random() < 0.4 ? "ultimate" : random.nextBoolean() ? "attack1" : "attack2"); // 40%
        } else if (playerUltiCountDown < 1 && computerDefenseCountDown <= 0
                && lastPUltiCountdown - playerUltiCountDown == 1) {

            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;

            return (Math.random() < 0.7 ? "shield" : random.nextBoolean() ? "attack1" : "attack2"); // 70%
        } else {
            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;
            return random.nextBoolean() ? "attack1" : "attack2";
        }

        // Randomly choose between "attack" and "shield" actions for computer
    }

    public boolean getStart() {
        return startSecond;
    }

    // fixes the bug when new computer is created,otherwise it will not attack
    public void setStart() {
        this.startSecond = false;
    }
}
