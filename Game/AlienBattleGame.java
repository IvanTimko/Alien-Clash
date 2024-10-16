package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlienBattleGame extends JFrame {
    private Player player;
    private ComputerPlayer computer;
    private boolean playerTurn = true; // Player 1 starts the game
    
    private JLabel playerHPLabel;
    private JLabel opponenHPLabel;
    private JLabel messageLabelPlayer;
    private JLabel messageLabelComputer;
    private JPanel gamePanel;
    
    private ImageIcon attackIcon;
    private ImageIcon defenceIcon;
    
    public AlienBattleGame() {
        this.setTitle("Alien Clash");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Initialize player 1 (human) and player 2 (computer)
        player = new Player(100, 20, 5);
        computer = new ComputerPlayer(100, 15, 10);

        // Main game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(800, 600));
        this.add(gamePanel);

        
        

        

        //player and computer attack / defence ability icon
        attackIcon = new ImageIcon(this.getClass().getResource("attackLabel.png"));
        defenceIcon = new ImageIcon(this.getClass().getResource("shieldLabel.png"));
        
        

        // Player HP labels
        playerHPLabel = new JLabel("Player 1 HP: " + player.getHP());
        playerHPLabel.setBounds(0,0,150,100);
        opponenHPLabel = new JLabel("Player 2 HP: " + computer.getHP());
        opponenHPLabel.setBounds(650,0,150,100);
        
        gamePanel.add(playerHPLabel);
        gamePanel.add(opponenHPLabel);

        // Message label
        messageLabelPlayer = new JLabel("‎ ");
        messageLabelPlayer.setBounds(0, 70, 150, 100);
        gamePanel.add(messageLabelPlayer);

        messageLabelComputer = new JLabel(" ‎ ");
        messageLabelComputer.setBounds(700, 70, 150, 100);
        gamePanel.add(messageLabelComputer);
        
        

        // Button panel for actions
        JButton shieldButton = new JButton("Shield");
        shieldButton.setBounds(100, 450, 600, 50);
        
        JButton attack1Button = new JButton("Attack");
        attack1Button.setBounds(100, 500, 180, 50);
        JButton attack2Button = new JButton("Attack2");
        attack2Button.setBounds(310, 500, 180, 50);
        JButton ultimateButton = new JButton("Ultimate");
        ultimateButton.setBounds(520, 500, 180, 50);
        
        gamePanel.add(attack1Button);
        gamePanel.add(shieldButton);
        gamePanel.add(attack2Button);
        gamePanel.add(ultimateButton);
        


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

        setVisible(true);
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
            gamePanel.setBackground(Color.RED); // Visual feedback
        } else if (action.equals("shield")) {
            messageLabelPlayer.setIcon(defenceIcon);
            
            gamePanel.setBackground(Color.BLUE); // Visual feedback
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
            gamePanel.setBackground(Color.RED);
        } else {
            messageLabelComputer.setIcon(defenceIcon);
            gamePanel.setBackground(Color.BLUE);
        }

        // Switch back to Player 1
        playerTurn = true;
        //messageLabel.setText("Player 1's Turn");
    }

    // Update HP labels
    private void updateHPLabel() {
        playerHPLabel.setText("Player 1 HP: " + player.getHP());
        opponenHPLabel.setText("Player 2 HP: " + computer.getHP());
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
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JButton) {
                        comp.setEnabled(false);
                    }
                }
            }
        }
    }
}
