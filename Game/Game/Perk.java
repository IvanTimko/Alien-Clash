package Game;

import java.util.Random;

/** Class for creation of perks that boost aliens every 3 round or when Player kill Computer.
 * 
 */
public class Perk {

    double upperBound;
    double lowerBound;
    String name;
    int value;
    String text;
    private Random random = new Random();

    /**
     * Contructor for specific perks.
     * @param name type of perk to be created
     */
    public Perk(String name) {
        this.name = name;
        if (name.equals("heal")) {
            this.value = random.nextInt(8, 20);
            this.text = "Heal " + this.value + " Hp";
        } else if (name.equals("regenerate")) {
            this.value = random.nextInt(15, 35);
            this.text = "<html>Heal " + this.value + "% of<br>  missing Hp</html>";
        } else if (name.equals("increaseDef")) {
            this.value = random.nextInt(8, 15);
            this.text = "Defence +" + this.value;
        } else if (name.equals("incpercentageDef")) {
            this.value = random.nextInt(10, 30);
            this.text = "Defence +" + this.value + "%";
        } else if (name.equals("decreaseDef")) {
            this.value = random.nextInt(5, 12);
            this.text = "<html>Opponent's<br>  defence -" + +this.value + "</html>";
        } else if (name.equals("increaseDam")) {
            this.value = random.nextInt(3, 6);
            this.text = "Damage +" + this.value;
        } else if (name.equals("incpercentageDam")) {
            this.value = random.nextInt(10, 20);
            this.text = "Damage +" + this.value + "%";
        }
    }

    public String getText() {
        return this.text;
    }
}
