package Game;




/**
 * Class whose parent class is Player is used for creation of ComputerPlayers, so enemies.
 * Each computerPlayer is always on a similar level as you, but never the same. 
 */
public class ComputerPlayer extends Player {
    boolean startSecond;
    int lastPUltiCountdown;
    int lastPDefCountdown;

    /**
     * Constructor for ComputerPlayer that creates opponent with unique stats.
     * @param playerType type of computer, player will be facing
     * @param hp how much hp will the computer have
     * @param attack1 attack object with its stats
     * @param attack2 attack object with its stats
     * @param ultimate attack object with its stats
     * @param defence how much defence will computer have
     * @param generationVariabilty variability on stats of computer
     * @param skinName the skin which will computer have
     */
    public ComputerPlayer(String playerType, int hp, Attack attack1, Attack attack2,
            Attack ultimate, double defence, double generationVariabilty, String skinName) {
        super(playerType, hp, attack1, attack2, ultimate, defence,
                generationVariabilty, skinName);

        startSecond = true;
    }


    /**
     * Call the parent Player class constructor that accepts a Player and String.
     * @param basePlayer base stats of the computer 
     * @param skinName skin of computer
     */
    public ComputerPlayer(Player basePlayer, String skinName) {
        // 
        super(basePlayer, skinName);
        this.startSecond = true; // Other specific logic for ComputerPlayer

    }

    /**
     *  Computers logic to determine which move will help him win.
     * @param playerUltiCountDown how many round till Player will have ultimate available
     * @param playerDefenceCountDown how many round till Player will have defence available
     * @param computerUltiCountDown  how many round till Computer will have ultimate available
     * @param computerDefenceCountDown  how many round till Computer will have defence available
     * @return the attack / defence computer will use
     */
    public String getAction(int playerUltiCountDown, int playerDefenceCountDown, 
            int computerUltiCountDown, int computerDefenceCountDown) {

        if (playerDefenceCountDown > 0 && computerUltiCountDown <= 0
                && lastPDefCountdown - playerDefenceCountDown == 1) {

            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;

            return "ultimate";
        } else if (computerUltiCountDown <= 0) {
            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;
            return (Math.random() < 0.4 ? "ultimate" : random.nextBoolean() 
                ? "attack1" : "attack2");
        } else if (playerUltiCountDown < 1 && computerDefenceCountDown <= 0
                && lastPUltiCountdown - playerUltiCountDown == 1) {

            lastPDefCountdown = playerUltiCountDown;
            lastPUltiCountdown = computerUltiCountDown;

            return (Math.random() < 0.7 ? "shield" : random.nextBoolean() 
                ? "attack1" : "attack2"); // 70%
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
