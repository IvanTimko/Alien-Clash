package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AlienBattleGame extends JPanel {

    private Player player;
    private ComputerPlayer computer;
    private boolean playerDefenceActive = false;

    private boolean computerDefenceActive = false;
    private boolean isAnimatingPlayerAttack = false; // To check if the player's attack animation is running
    private boolean isAnimatingComputerAttack = false; // To check if the computer's attack animation is running

    private JLabel playerHPLabel;
    private JLabel opponenHPLabel;
    private JLabel messageLabelComputer;
    private JLabel statsMenuLabel;

    JButton shieldButton;
    JButton attack1Button;
    JButton attack2Button;
    JButton ultimateButton;
    JButton perk1Button;
    JButton perk2Button;
    JButton perk3Button;

    private ImageIcon attackIcon;
    private ImageIcon defenceIcon;
    private JFrame frame;

    
    private int ultimateLoaderCT;
    private int roundCounter;
    private int playerAttackX; // X position for the player's attack animation
    private int playerAttackY;
    private int computerAttackX; // X position for the computer's attack animation
    private int computerAttackY;
    private int defenceCountDown;
    private int computerUltiCountDown;
    private int computerDefenseCountDown;
    private int score;
    

    private String[] skinsP = new String[] {
            "pictures/blue-player.png", "pictures/red-player.png", "pictures/green-player.png" };
    private String[] skinsC = new String[] {
            "pictures/blue-computer.png", "pictures/red-computer.png", "pictures/green-computer.png" };
    private Random random = new Random();
    
    private String[] alienType = new String[] { "red", "green", "blue" };

    public AlienBattleGame(String skin) {

        // Initialize the frame
        
        frame = new JFrame("Alien Clash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        repaint();

        // Initialize player 1 (human) and player 2 (computer)

        if (skin.equals("red")) {
            player = player.RED_Player_BASE;
            player.setSkin(skinsP[1]);
        } else if (skin.equals("green")) {
            player = player.GREEN_Player_BASE;
            player.setSkin(skinsP[2]);
        } else if (skin.equals("blue")) {
            player = player.BlUE_Player_BASE;
            player.setSkin(skinsP[0]);
        }
        String computerType = alienType[random.nextInt(0, 3)];
        if (computerType.equals("red")) {
            computer = new ComputerPlayer(Player.RED_Player_BASE, "red");
            computer.setSkin(skinsC[1]);
        } else if (computerType.equals("blue")) {
            computer = new ComputerPlayer(Player.BlUE_Player_BASE, "blue");

            computer.setSkin(skinsC[0]);
        } else if (computerType.equals("green")) {
            computer = new ComputerPlayer(Player.GREEN_Player_BASE, "green");
            computer.setSkin(skinsC[2]);

            

        }
        System.out.println(
                "player: attack1: " + player.attack1.damage + ", " + player.attack1.hitChance + ", "
                        + player.attack1.variabilty
                        + "attack2: " + player.attack2.damage + ", " + player.attack2.hitChance + ", "
                        + player.attack2.variabilty
                        + "ultiamte: " + player.ultimate.damage + ", " + player.ultimate.hitChance + ", "
                        + player.ultimate.variabilty + ", hp:" + player.hp + " ,defense " + player.defense
                        + "genvar: " + player.generationVariabilty);
        System.out.println("computer: attack1: " + computer.attack1.damage + ", "
                + computer.attack1.hitChance + ", "
                + computer.attack1.variabilty
                + "attack2: " + computer.attack2.damage + ", " + computer.attack2.hitChance + ", "
                + computer.attack2.variabilty
                + "ultiamte: " + computer.ultimate.damage + ", " + computer.ultimate.hitChance + ", "
                + computer.ultimate.variabilty + ", hp:" + computer.hp + " ,defense " + computer.defense
                + "genvar: " + computer.generationVariabilty);
        // Initialize player and computer

        // Main game panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);

        // Computer attack / defense ability icons
        attackIcon = new ImageIcon(getClass().getResource("pictures/attackLabel.png"));
        defenceIcon = new ImageIcon(getClass().getResource("pictures/shieldLabel.png"));

        // Player HP labels
        playerHPLabel = new JLabel(" HP: " + player.getHP());
        playerHPLabel.setForeground(Color.WHITE);
        playerHPLabel.setBounds(70, 170, 150, 30);
        opponenHPLabel = new JLabel(" HP: " + computer.getHP());
        opponenHPLabel.setForeground(Color.WHITE);

        opponenHPLabel.setBounds(530, 30, 150, 30);

        this.add(playerHPLabel);
        this.add(opponenHPLabel);

        // Label for Last action of computer
        messageLabelComputer = new JLabel(" ‎ ");
        messageLabelComputer.setForeground(Color.WHITE);
        messageLabelComputer.setBounds(700, 130, 150, 50);
        this.add(messageLabelComputer);

        // shield button set-up
        shieldButton = new JButton("Shield");
        shieldButton.setBounds(100, 450, 600, 50);
        shieldButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/defence.png")));
        // attack 1 button set-up
        attack1Button = new JButton("Attack1");
        attack1Button.setBounds(100, 510, 180, 50);
        attack1Button.setIcon(new ImageIcon(this.getClass().getResource("pictures/attack1.png")));
        // attack 2 button set-up
        attack2Button = new JButton("Attack2");
        attack2Button.setBounds(310, 510, 180, 50);
        attack2Button.setIcon(new ImageIcon(this.getClass().getResource("pictures/attack2.png")));
        // ultimate button set-up
        ultimateButton = new JButton("Ultimate");
        ultimateButton.setBounds(520, 510, 180, 50);
        ultimateButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/ultimate.png")));
        // ultimate button is not initially available for use
        ultimateButton.setVisible(false);

        this.add(attack1Button);
        this.add(shieldButton);
        this.add(attack2Button);
        this.add(ultimateButton);

        // Add the JPanel to the frame
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Stats boost menu + buttons
        statsMenuLabel = new JLabel();
        statsMenuLabel.setBackground(new Color(0, 0, 0, 255));
        statsMenuLabel.setBounds(100, 100, 600, 400);
        this.add(statsMenuLabel);
        statsMenuLabel.setOpaque(true);
        this.setComponentZOrder(statsMenuLabel, 0);
        statsMenuLabel.setVisible(false);

        perk2Button = new JButton("perk2");
        perk2Button.setBounds(45, 100, 150, 200);
        perk2Button.setBackground(new Color(75, 44, 111));
        perk2Button.setForeground(new Color(49, 91, 164));
        perk2Button.setFont(new Font("Orbitron", Font.BOLD, 14));
        perk2Button.setOpaque(true);
        perk2Button.setBorderPainted(false);

        perk3Button = new JButton("perk3");
        perk3Button.setBounds(45 + 180, 100, 150, 200);
        perk3Button.setFont(new Font("Orbitron", Font.BOLD, 14));
        perk3Button.setForeground(new Color(49, 91, 164));
        perk3Button.setBackground(new Color(75, 44, 111));
        perk3Button.setOpaque(true);
        perk3Button.setBorderPainted(false);

        perk1Button = new JButton("perk1");
        perk1Button.setBounds(45 + 360, 100, 150, 200);
        perk1Button.setFont(new Font("Orbitron", Font.BOLD, 14));
        perk1Button.setForeground(new Color(49, 91, 164));
        perk1Button.setBackground(new Color(75, 44, 111));
        perk1Button.setOpaque(true);
        perk1Button.setBorderPainted(false);

        statsMenuLabel.add(perk2Button);
        statsMenuLabel.add(perk1Button);
        statsMenuLabel.add(perk3Button);

        // Action listeners for buttons
        attack1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundCounter++;
                startPlayerAttackAnimation(); // Player attack
                // Execute attack logic
                playerAction("attack1", player, computer);

            }
        });

        attack2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundCounter++;
                startPlayerAttackAnimation(); // Player attack
                // Execute attack logic
                playerAction("attack2", player, computer);

            }
        });
        shieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                playerAction("shield", player, computer);

            }
        });

        ultimateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundCounter++;
                playerAction("ultimate", player, computer);
                startPlayerAttackAnimation(); // Player attack

            }
        });
        /// new perks
        perk1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (perk1.name.equals("heal")) {
                    player.addHP(perk1.value);
                } else if (perk1.name.equals("regenerate")) {
                    player.healPercentage(perk1.value);
                }
                updateHPLabel();

                statsMenuLabel.setVisible(false);
                toggleButtons(true);
                computer.setStart();

            }

        });
        perk2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (perk2.name.equals("increaseDef")) {
                    player.increaseDef(perk2.value);
                } else if (perk2.name.equals("incpercentageDef")) {
                    player.incPercentageDef(perk2.value);
                } else if (perk2.name.equals("decreaseDef")) {
                    computer.decreaseDefense(perk2.value);
                }

                updateHPLabel();
                statsMenuLabel.setVisible(false);
                toggleButtons(true);

                computer.setStart();

            }
        });
        perk3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (perk3.name.equals("increaseDam")) {
                    player.addDamage(perk3.value);
                } else if (perk3.name.equals("incpercentageDam")) {
                    player.addPercentageDam(perk3.value);
                }
                updateHPLabel();
                statsMenuLabel.setVisible(false);
                toggleButtons(true);

                computer.setStart();

            }
        });
    }

    // Start the player attack animation
    private void startPlayerAttackAnimation() {
        // turn off buttons
        toggleButtons(false);
        isAnimatingPlayerAttack = true;
        playerAttackX = 350; // Start position just after player
        playerAttackY = 0;
        // Timer to animate the player's attack moving towards the target
        Timer timer = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAttackX += 10; // Move attack towards the computer
                playerAttackY += 10;
                // Check for collision with the computer's position
                if (playerAttackX + 50 >= 550) { // Stop when it reaches the opponent
                    ((Timer) e.getSource()).stop();
                    isAnimatingPlayerAttack = false;

                }

                repaint(); // Repaint to show the animation frame

            }
        });
        timer.start();

    }

    // Start the computer attack animation
    private void startComputerAttackAnimation() {
        isAnimatingComputerAttack = true;
        computerAttackX = 350; // Start position just before computer
        computerAttackY = 100;

        // Timer to animate the computer's attack moving towards the player
        Timer timer = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computerAttackX -= 10; // Move attack towards the player
                computerAttackY += 10;
                // Check for collision with the player's position
                if (computerAttackX <= 150) { // Stop when it reaches the player
                    ((Timer) e.getSource()).stop();
                    isAnimatingComputerAttack = false;

                }

                repaint(); // Repaint to show the animation frame
            }
        });
        timer.start();
    }

    // Method to handle player actions (attack/shield)
    private void playerAction(String action, Player player, Player opponent) {
        double damage;
        // increment of ultimate loader
        ultimateLoaderCT++;

        // enhanced defence is only active for one round than it reverts back to base

        if (playerDefenceActive) {
            playerDefenceActive = false;
            player.addDefence(-10);

        }

        if (action.equals("attack1")) {
            damage = player.getAttack1Power();
            score+=damage;
            opponent.takeDamage(damage);
            updateHPLabel();
            checkVictory();
            defenceCountDown--;
        } else if (action.equals("attack2")) {
            damage = player.getAttack2Power();
            score+=damage;
            opponent.takeDamage(damage);
            updateHPLabel();
            checkVictory();
            defenceCountDown--;
        } else if (action.equals("shield")) {
            shieldButton.setVisible(false);
            player.addDefence(20);
            defenceCountDown = 4;
            playerDefenceActive = true;

        } else if (action.equals("ultimate")) {
            damage = player.getUltimatePower();
            score+=damage;
            opponent.takeDamage(damage);
            ultimateLoaderCT = 0;
            updateHPLabel();
            checkVictory();
            defenceCountDown--;
            

        }

        // checks if the Stats boost menu should open

        // if we use shield we can still do our move
        if (!action.equals("shield")) {
            computerTurn();
            isStatsBoost(roundCounter);
            computer.setStart();
        }

    }

    // Computer's turn logic

    private void computerTurn() {

        messageLabelComputer.setVisible(false);

        // Create a Swing Timer to delay the computer's move by 2.5 seconds (2500
        // milliseconds)
        Timer computerMoveTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (computer.getHP() != computer.getMaxHp() || !computer.getStart()) {

                    // Get the computer's action (either attack or shield)
                    String action = computer.getAction(ultimateLoaderCT, defenceCountDown, computerUltiCountDown,
                            computerDefenseCountDown);
                    System.out.println(
                            "player: attack1: " + player.attack1.damage + ", " + player.attack1.hitChance + ", "
                                    + player.attack1.variabilty
                                    + "attack2: " + player.attack2.damage + ", " + player.attack2.hitChance + ", "
                                    + player.attack2.variabilty
                                    + "ultiamte: " + player.ultimate.damage + ", " + player.ultimate.hitChance + ", "
                                    + player.ultimate.variabilty + ", hp:" + player.hp + " ,defense " + player.defense
                                    + "genvar: " + player.generationVariabilty);
                    System.out.println("computer: attack1: " + computer.attack1.damage + ", "
                            + computer.attack1.hitChance + ", "
                            + computer.attack1.variabilty
                            + "attack2: " + computer.attack2.damage + ", " + computer.attack2.hitChance + ", "
                            + computer.attack2.variabilty
                            + "ultiamte: " + computer.ultimate.damage + ", " + computer.ultimate.hitChance + ", "
                            + computer.ultimate.variabilty + ", hp:" + computer.hp + " ,defense " + computer.defense
                            + "genvar: " + computer.generationVariabilty);
                    System.out.println(action);
                    if (roundCounter % 3 != 0) {
                        toggleButtons(true);
                    }
                    if (computerDefenceActive) {
                        computerDefenceActive = false;
                        computer.addDefence(-30);
                    }

                    // If the computer decides to attack
                    if (action.equals("attack1")) {
                        computerUltiCountDown--;
                        defenceCountDown--;
                        startComputerAttackAnimation(); // Start the computer's attack animation

                        // Execute attack logic
                        double damage = Math.round(computer.attack1.getDamagePower());
                        player.takeDamage(damage);
                        updateHPLabel();
                        checkVictory();
                        messageLabelComputer.setIcon(attackIcon);

                        // If the computer decides to shield
                    } else if (action.equals("attack2")) {
                        computerUltiCountDown--;
                        defenceCountDown--;
                        startComputerAttackAnimation(); // Start the computer's attack animation

                        // Execute attack logic
                        double damage = Math.round(computer.attack2.getDamagePower());
                        player.takeDamage(damage);
                        updateHPLabel();
                        checkVictory();
                        messageLabelComputer.setIcon(attackIcon);

                        // If the computer decides to shield
                    } else if (action.equals("ultimate")) {
                        startComputerAttackAnimation(); // Start the computer's attack animation
                        computerUltiCountDown = 4;
                        defenceCountDown--;

                        // Execute attack logic
                        double damage = Math.round(computer.ultimate.getDamagePower());
                        player.takeDamage(damage);
                        updateHPLabel();
                        checkVictory();
                        messageLabelComputer.setIcon(attackIcon);

                        // If the computer decides to shield
                    } else if (action.equals("shield")) {
                        messageLabelComputer.setIcon(defenceIcon);
                        computer.addDefence(30);
                        computerDefenceActive = true;
                        defenceCountDown = 4;

                    }

                    // Stop the timer after the computer has made its move
                    messageLabelComputer.setVisible(true);
                    ((Timer) e.getSource()).stop();
                }

            }

        });
        // Start the timer (after 2 seconds, the computer's move will execute)
        computerMoveTimer.setRepeats(false); // Ensure it only runs once
        computerMoveTimer.start();

    }

    // Update HP labels
    private void updateHPLabel() {
        playerHPLabel.setText(" HP: " + player.getHP());
        opponenHPLabel.setText(" HP: " + computer.getHP());
        repaint(); // Repaint to update health bars
    }

    // Check if there's a winner
    private void checkVictory() {
        if (player.getHP() <= 0) {
            disableButtons();
            statsMenuLabel.setVisible(false);
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    new StartMenu("rematch",score);
                    
                    player.setHP(player.getMaxHp());
                    computer.setHP(computer.getMaxHp());
                    
                    

                    // Stop the timer after it's done
                    ((Timer) e.getSource()).stop();
                }
            });

            // Start the timer
            timer.setRepeats(false); // Only run once
            timer.start();
            


            
        } else if (computer.getHP() <= 0) {

            isStatsBoost(0); // Trigger stats boost if needed
            roundCounter = 0;

            // Create a timer to delay for 1 second (1000ms)
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create a new computer opponent after 1 second
                    computer = new ComputerPlayer(player, alienType[random.nextInt(0, 3)]);
                    

                    // Reset the opponent's HP label
                    opponenHPLabel.setText(" HP: " + computer.getHP());

                    // Update the game display
                    repaint();

                    // Stop the timer after it's done
                    ((Timer) e.getSource()).stop();
                }
            });

            // Start the timer
            timer.setRepeats(false); // Only run once
            timer.start();

        }
    }

    // Disable buttons after the game ends
    private void disableButtons() {
        Component[] components = this.getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                comp.setEnabled(false);
                comp.setVisible(false);
            }
        }
    }

    // Load background
    Image backgroundImage = new ImageIcon(getClass().getResource("pictures/background.jpeg")).getImage();
    // Load attack image
    Image missile = new ImageIcon(getClass().getResource("pictures/missile.png")).getImage();
    Image missileRotated = new ImageIcon(getClass().getResource("pictures/missileRotated.png")).getImage();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 800, 600, this);
            g.drawImage(player.getSkin(), 100, 250, 75, 150, this);
            g.drawImage(computer.getSkin(), 600, 100, 75, 150, this);
        }

        drawHealthBar(g, player.getHP(), player.getMaxHp(), 50, 200, 200, 20, Color.GREEN);

        drawHealthBar(g, computer.getHP(), computer.getMaxHp(), 530, 60, 200, 20, Color.RED);

        // Draw the player's attack animation
        if (isAnimatingPlayerAttack) {
            g.drawImage(missileRotated, playerAttackX, playerAttackY, 50, 50, null);
        }

        // Draw the computer's attack animation
        if (isAnimatingComputerAttack) {
            g.drawImage(missile, computerAttackX, computerAttackY, 50, 50, null);
        }
    }

    private void drawHealthBar(Graphics g, int currentHP, int maxHP, int x, int y, int width, int height, Color color) {
        int healthPercentage = (int) ((double) currentHP / maxHP * width);

        // Draw the background (empty health bar)
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        // Draw the foreground (filled health bar based on current health)
        g.setColor(color);
        g.fillRect(x, y, healthPercentage, height);

        // Draw a border around the health bar
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }

    private void toggleButtons(Boolean val) {
        if (player.getHP() > 0) {
            attack1Button.setVisible(val);
            attack2Button.setVisible(val);

            if (ultimateLoaderCT >= 4 || !val) {
                ultimateButton.setVisible(val);

            }
            if (defenceCountDown <= 0 || !val) {
                shieldButton.setVisible(val);
            }
        }
    }

    private void isStatsBoost(int roundCounter) {
        if (roundCounter % 3 == 0 && player.getHP() > 0) {
            initStatsBoost();
        }

    }

    private String[] perkHeals = new String[] { "heal", "regenerate" };
    private String[] perkDefenses = new String[] { "increaseDef", "incpercentageDef", "decreaseDef" };
    private String[] perkDamage = new String[] { "increaseDam", "incpercentageDam" };
    private Perk perk1;
    private Perk perk2;
    private Perk perk3;

    // INPUT WILL BE LEVEL
    private void initStatsBoost() {
        toggleButtons(false);
        // Create a Timer to delay the execution by 4.5 second (4500 milliseconds)
        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your stat boosts logic here

                perk1 = new Perk(perkHeals[random.nextInt(perkHeals.length)]);
                perk2 = new Perk(perkDefenses[random.nextInt(perkDefenses.length)]);
                perk3 = new Perk(perkDamage[random.nextInt(perkDamage.length)]);

                perk1Button.setText(perk1.getText());
                perk2Button.setText(perk2.getText());
                perk3Button.setText(perk3.getText());

                statsMenuLabel.setVisible(true);

                // logic for computer choice of perks
                if (player.hp > computer.hp) {
                    Perk computerPerk = new Perk(perkHeals[random.nextInt(perkHeals.length)]);
                    if (computerPerk.name.equals("heal")) {
                        computer.addHP(computerPerk.value);
                    } else if (computerPerk.name.equals("regenerate")) {
                        computer.healPercentage(computerPerk.value);
                    }
                } else if (player.attack2.damage > computer.attack2.damage) {
                    Perk computerPerk = new Perk(perkDefenses[random.nextInt(perkDefenses.length)]);
                    if (computerPerk.name.equals("increaseDef")) {
                        computer.increaseDef(computerPerk.value);
                    } else if (computerPerk.name.equals("incpercentageDef")) {
                        computer.incPercentageDef(computerPerk.value);
                    } else if (computerPerk.name.equals("decreaseDef")) {
                        player.decreaseDefense(computerPerk.value);
                    }
                } else if (player.defense < computer.defense || player.hp < computer.hp) {
                    Perk computerPerk = new Perk(perkDamage[random.nextInt(perkDamage.length)]);
                    if (computerPerk.name.equals("increaseDam")) {
                        computer.addDamage(computerPerk.value);
                    } else if (computerPerk.name.equals("incpercentageDam")) {
                        computer.addPercentageDam(computerPerk.value);
                    }
                } else {
                    Perk computerPerk = new Perk(perkHeals[random.nextInt(perkHeals.length)]);
                    if (computerPerk.name.equals("heal")) {
                        computer.addHP(computerPerk.value);
                    } else if (computerPerk.name.equals("regenerate")) {
                        computer.healPercentage(computerPerk.value);
                    }
                }

                System.out.println(
                        "player: attack1: " + player.attack1.damage + ", " + player.attack1.hitChance + ", "
                                + player.attack1.variabilty
                                + "attack2: " + player.attack2.damage + ", " + player.attack2.hitChance + ", "
                                + player.attack2.variabilty
                                + "ultiamte: " + player.ultimate.damage + ", " + player.ultimate.hitChance + ", "
                                + player.ultimate.variabilty + ", hp:" + player.hp + " ,defense " + player.defense
                                + "genvar: " + player.generationVariabilty);
                System.out.println("computer: attack1: " + computer.attack1.damage + ", "
                        + computer.attack1.hitChance + ", "
                        + computer.attack1.variabilty
                        + "attack2: " + computer.attack2.damage + ", " + computer.attack2.hitChance + ", "
                        + computer.attack2.variabilty
                        + "ultiamte: " + computer.ultimate.damage + ", " + computer.ultimate.hitChance + ", "
                        + computer.ultimate.variabilty + ", hp:" + computer.hp + " ,defense " + computer.defense
                        + "genvar: " + computer.generationVariabilty);
                // Stop the timer after the task is executed
                ((Timer) e.getSource()).stop();

            }
        });

        // Ensure the timer only runs once
        delayTimer.setRepeats(false);

        // Start the timer (after 4.5 second, the action will execute)
        delayTimer.start();

    }

}
