package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlienBattleGame extends JPanel {
    private Player player;
    private ComputerPlayer computer;
    private boolean playerTurn = true; // Player 1 starts the game

    private JLabel playerHPLabel;
    private JLabel opponenHPLabel;
    private JLabel messageLabelPlayer;
    private JLabel messageLabelComputer;

    private ImageIcon attackIcon;
    private ImageIcon defenceIcon;
    private JFrame frame;

    public AlienBattleGame() {
        
        // Initialize the frame
        frame = new JFrame("Alien Clash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        repaint();

        // Initialize player 1 (human) and player 2 (computer)
        player = new Player(100, 20, 5);
        computer = new ComputerPlayer(100, 15, 10);

        // Main game panel
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        

        // player and computer attack / defence ability icon
        attackIcon = new ImageIcon(getClass().getResource("attackLabel.png"));
        defenceIcon = new ImageIcon(getClass().getResource("shieldLabel.png"));

        // Player HP labels
        playerHPLabel = new JLabel(" HP: " + player.getHP());
        playerHPLabel.setForeground(Color.WHITE); // Set label color to make it visible on black background
        playerHPLabel.setBounds(100, 200, 150, 30); // Adjusted bounds
        opponenHPLabel = new JLabel(" HP: " + computer.getHP());
        opponenHPLabel.setForeground(Color.WHITE);
        opponenHPLabel.setBounds(600, 20, 150, 30); // Adjusted bounds

        this.add(playerHPLabel);
        this.add(opponenHPLabel);

        // Message label
        messageLabelPlayer = new JLabel("‎ ");
        messageLabelPlayer.setForeground(Color.WHITE);
        messageLabelPlayer.setBounds(50, 70, 150, 30); // Adjusted bounds
        this.add(messageLabelPlayer);

        messageLabelComputer = new JLabel(" ‎ ");
        messageLabelComputer.setForeground(Color.WHITE);
        messageLabelComputer.setBounds(600, 70, 150, 30); // Adjusted bounds
        this.add(messageLabelComputer);
        

        // Button panel for actions
        JButton shieldButton = new JButton("Shield");
        shieldButton.setBounds(100, 450, 600, 50); // Set bounds to center the button

        JButton attack1Button = new JButton("Attack");
        attack1Button.setBounds(100, 510, 180, 50); // Adjusted bounds for Attack1
        JButton attack2Button = new JButton("Attack2");
        attack2Button.setBounds(310, 510, 180, 50); // Adjusted bounds for Attack2
        JButton ultimateButton = new JButton("Ultimate");
        ultimateButton.setBounds(520, 510, 180, 50); // Adjusted bounds for Ultimate

        this.add(attack1Button);
        this.add(shieldButton);
        this.add(attack2Button);
        this.add(ultimateButton);

        // Add the JPanel to the frame
        frame.add(this);  // JPanel added to the JFrame
        frame.pack();     // Ensures proper layout
        frame.setVisible(true);  // Show the JFrame

        // Action listeners for buttons
        attack1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    playerAction("attack", player, computer);
                }
            }
        });

        shieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerTurn) {
                    playerAction("shield", player, computer);
                }
            }
        });
    }

    // Method to handle player actions (attack/shield)
    private void playerAction(String action, Player player, Player opponent) {
        int damage;
        if (action.equals("attack")) {
            damage = Math.max(player.getAttackPower() - opponent.getDefense(), 0);
            opponent.takeDamage(damage);
            updateHPLabel();
            checkVictory();
            messageLabelPlayer.setIcon(attackIcon);
            this.setBackground(Color.RED); // Visual feedback
        } else if (action.equals("shield")) {
            messageLabelPlayer.setIcon(defenceIcon);
            this.setBackground(Color.BLUE); // Visual feedback
        }

        // Switch to Player 2 (Computer's turn)
        playerTurn = false;
        computerTurn();
    }

    // Computer's turn logic
    private void computerTurn() {
        String action = computer.getAction();
        if (action.equals("attack")) {
            int damage = Math.max(computer.getAttackPower() - player.getDefense(), 0);
            player.takeDamage(damage);
            updateHPLabel();
            checkVictory();
            messageLabelComputer.setIcon(attackIcon);
            this.setBackground(Color.RED);
        } else {
            messageLabelComputer.setIcon(defenceIcon);
            this.setBackground(Color.BLUE);
        }

        // Switch back to Player 1
        playerTurn = true;
    }

    // Update HP labels
    private void updateHPLabel() {
        playerHPLabel.setText(" HP: " + player.getHP());
        opponenHPLabel.setText(" HP: " + computer.getHP());
    }

    // Check if there's a winner
    private void checkVictory() {
        if (player.getHP() <= 0) {
            messageLabelComputer.setText("Player 2 Wins!");
            disableButtons();
        } else if (computer.getHP() <= 0) {
            messageLabelPlayer.setText("Player 1 Wins!");
            disableButtons();
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
    //create background
    Image backgroundImage = new ImageIcon(getClass().getResource("background.jpeg")).getImage();
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 800, 600, this);
            g.drawImage(player.getSkin(), 100,250,130,150, this);
            g.drawImage(computer.getSkin(), 550,100,130, 150, this);
        }
        drawHealthBar(g, player.getHP(), 100, 50, 20, 200, 20, Color.GREEN);
    drawHealthBar(g, computer.getHP(), 100, 550, 20, 200, 20, Color.RED);
        
        
    }

    private void drawHealthBar(Graphics g, int currentHP, int maxHP, int x, int y, int width, int height, Color color) {
        // Calculate health percentage
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
}
