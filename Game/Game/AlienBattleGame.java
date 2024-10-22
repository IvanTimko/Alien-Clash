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
    JButton addHPButton;
    JButton addDmgButton;
    JButton addDefenceButton;

    private ImageIcon attackIcon;
    private ImageIcon defenceIcon;
    private JFrame frame;

    private int statsMenuDamage;
    private int statsMenuHp;
    private int statMenuDefence;
    private int ultimateLoaderCT;
    private int roundCounter;
    private int playerAttackX; // X position for the player's attack animation
    private int playerAttackY;
    private int computerAttackX; // X position for the computer's attack animation
    private int computerAttackY;

    private String[] skinsP = new String[] {
            "pictures/blue-player.png", "pictures/red-player.png", "pictures/green-player.png" };
    private String[] skinsC = new String[] {
            "pictures/blue-computer.png", "pictures/red-computer.png", "pictures/green-computer.png" };
    private Random random = new Random();
    private String skin;
    public AlienBattleGame(String skin) {

        // Initialize the frame
        this.skin = skin;
        frame = new JFrame("Alien Clash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        repaint();

        // Initialize player 1 (human) and player 2 (computer)
        String[] alienTypeColors = new String[]{"red","green","blue"};

        if (skin.equals("red")) {
            player=RED_Player_BASE;
        } else if (skin.equals("blue")) {
            player=BlUE_Player_BASE;
        } else if (skin.equals("green")) {
            player=GREEN_Player_BASE;
        }

        computer = new ComputerPlayer(100, 0.1, skinsC[random.nextInt(3)],
                new Attack(15, 0.8, 0.15),
                new Attack(25, 0.65, 0.22),
                new Attack(35, 0.7, 0.1));


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

        addDmgButton = new JButton("extra damage");
        addDmgButton.setBounds(45, 100, 150, 200);
        addDmgButton.setBackground(new Color(75, 44, 111));
        addDmgButton.setForeground(new Color(49, 91, 164));
        addDmgButton.setFont(new Font("Orbitron", Font.BOLD, 27));
        addDmgButton.setOpaque(true);
        addDmgButton.setBorderPainted(false);

        addDefenceButton = new JButton("extra def");
        addDefenceButton.setBounds(45 + 180, 100, 150, 200);
        addDefenceButton.setFont(new Font("Orbitron", Font.BOLD, 27));
        addDefenceButton.setForeground(new Color(49, 91, 164));
        addDefenceButton.setBackground(new Color(75, 44, 111));
        addDefenceButton.setOpaque(true);
        addDefenceButton.setBorderPainted(false);

        addHPButton = new JButton("heal");
        addHPButton.setBounds(45 + 360, 100, 150, 200);
        addHPButton.setFont(new Font("Orbitron", Font.BOLD, 27));
        addHPButton.setForeground(new Color(49, 91, 164));
        addHPButton.setBackground(new Color(75, 44, 111));
        addHPButton.setOpaque(true);
        addHPButton.setBorderPainted(false);

        statsMenuLabel.add(addDmgButton);
        statsMenuLabel.add(addHPButton);
        statsMenuLabel.add(addDefenceButton);

        // Action listeners for buttons
        attack1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startPlayerAttackAnimation(); // Player attack
                // Execute attack logic
                playerAction("attack1", player, computer);

            }
        });

        attack2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                playerAction("ultimate", player, computer);
                startPlayerAttackAnimation(); // Player attack

            }
        });

        addDmgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.addDamage(statsMenuDamage);
                statsMenuLabel.setVisible(false);
                toggleButtons(true);

                computer.setStart();

            }
        });
        addHPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.addHP(statsMenuHp);
                if (player.getHP() > 100) {
                    player.setHP(100);
                }
                updateHPLabel();
                statsMenuLabel.setVisible(false);
                toggleButtons(true);
                computer.setStart();

            }
        });
        addDefenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.addDefence(statMenuDefence);
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

        // player starts each new round
        roundCounter++;

        // enhanced defence is only active for one round than it reverts back to base

        if (playerDefenceActive) {
            playerDefenceActive = false;
            player.addDefence(-10);

        }

        if (action.equals("attack1")) {
            damage = player.getAttack1Power();
            opponent.takeDamage(damage);
            updateHPLabel();
            checkVictory();
        } else if (action.equals("attack2")) {
            damage = player.getAttack2Power();
            opponent.takeDamage(damage);
            updateHPLabel();
            checkVictory();
        } else if (action.equals("shield")) {
            toggleButtons(false);
            player.addDefence(10);
            playerDefenceActive = true;

        } else if (action.equals("ultimate")) {
            damage = player.getUltimatePower();
            opponent.takeDamage(damage);
            ultimateLoaderCT = 0;
            updateHPLabel();
            checkVictory();

        }

        // checks if the Stats boost menu should open
        isStatsBoost(roundCounter);
        computer.setStart();

        computerTurn();

    }

    // Computer's turn logic

    private void computerTurn() {

        messageLabelComputer.setVisible(false);

        // Create a Swing Timer to delay the computer's move by 2.5 seconds (2500
        // milliseconds)
        Timer computerMoveTimer = new Timer(2500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (computer.getHP() != 100 || !computer.getStart()) {
                    // Get the computer's action (either attack or shield)
                    String action = computer.getAction();
                    if (roundCounter % 3 != 0) {
                        toggleButtons(true);
                    }
                    if (computerDefenceActive) {
                        computerDefenceActive = false;
                        computer.addDefence(-10);
                    }

                    // If the computer decides to attack
                    if (action.equals("attack1")) {
                        startComputerAttackAnimation(); // Start the computer's attack animation

                        // Execute attack logic
                        double damage = Math.max(computer.getAttack1Power() - player.getDefense(), 0);
                        player.takeDamage(damage);
                        updateHPLabel();
                        checkVictory();
                        messageLabelComputer.setIcon(attackIcon);

                        // If the computer decides to shield
                    } else {
                        messageLabelComputer.setIcon(defenceIcon);
                        computer.addDefence(10);
                        computerDefenceActive = true;

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
    // Check if there's a winner
    private void checkVictory() {
        if (player.getHP() <= 0) {
            disableButtons();
            messageLabelComputer.setText("Game Over - You lost!");
        } else if (computer.getHP() <= 0) {

            isStatsBoost(0); // Trigger stats boost if needed
            roundCounter = 0;

            // Create a timer to delay for 1 second (1000ms)
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create a new computer opponent after 1 second
                    computer = new ComputerPlayer(100, 0.05, skinsC[random.nextInt(3)],
                            new Attack(15, 0.8, 0.15),
                            new Attack(25, 0.65, 0.22),
                            new Attack(35, 0.7, 0.1));

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
        attack1Button.setVisible(val);
        attack2Button.setVisible(val);
        shieldButton.setVisible(val);

        if (ultimateLoaderCT >= 4 || !val) {
            ultimateButton.setVisible(val);

        }
    }

    private void isStatsBoost(int roundCounter) {
        if (roundCounter % 3 == 0) {
            initStatsBoost();
        }

    }

    // INPUT WILL BE LEVEL
    private void initStatsBoost() {
        toggleButtons(false);
        // Create a Timer to delay the execution by 4.5 second (4500 milliseconds)
        Timer delayTimer = new Timer(4500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your stat boosts logic here
                statMenuDefence = 3;
                statsMenuDamage = 4;
                statsMenuHp = 5;

                addHPButton.setText("+" + statsMenuHp + " HP");
                addDmgButton.setText("+" + statsMenuDamage + " AD");
                addDefenceButton.setText("+" + statMenuDefence + " Def");

                statsMenuLabel.setVisible(true);

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
