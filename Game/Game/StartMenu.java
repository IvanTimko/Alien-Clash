package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {
    JLabel background;
    String startGameMessage;
    int score;
    public StartMenu(String startGameMessage,int score) {
        
        this.setTitle("Alien Clash");
        
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        // Backgroung label
        if (startGameMessage.equals("rematch")) {
            background = new JLabel(new ImageIcon(this.getClass().getResource("pictures/background_rematch.png")));
        }else {
            background = new JLabel(new ImageIcon(this.getClass().getResource("pictures/background.jpeg")));
        }
        background.setSize(800, 600);
        this.add(background);
        // Title label
        JLabel titleLabel = new JLabel("Alien Clash", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        background.add(titleLabel, BorderLayout.CENTER);

        // Play Game button
        JButton playButton = new JButton("Play Game");
        playButton.setBounds(300, 250, 200, 50);
        playButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/playButton.png")));

        playButton.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(playButton, BorderLayout.SOUTH);

        // Background for alien choice
        JLabel alienMenu = new JLabel();
        alienMenu.setBackground(Color.BLACK);
        alienMenu.setBounds(100, 100, 600, 400);
        this.add(alienMenu);
        alienMenu.setVisible(false);
        background.add(alienMenu);
        
        

        // Button for Red Alien
        JButton redAlienButton = new JButton("");
        redAlienButton.setBounds(140, 125, 75, 150);
        redAlienButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/red-computer.png")));
        redAlienButton.setOpaque(false);
        redAlienButton.setContentAreaFilled(false);
        redAlienButton.setBorderPainted(false);
        redAlienButton.setFocusPainted(false);
        // Button for Blue Alien
        JButton blueAlienButton = new JButton("");
        blueAlienButton.setBounds(259, 125, 75, 150);
        blueAlienButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/blue-computer.png")));
        blueAlienButton.setOpaque(false);
        blueAlienButton.setContentAreaFilled(false);
        blueAlienButton.setBorderPainted(false);
        blueAlienButton.setFocusPainted(false);
        // Button for Green Alien
        JButton greenAlienButton = new JButton("");
        greenAlienButton.setBounds(378, 125, 75, 150);
        greenAlienButton.setIcon(new ImageIcon(this.getClass().getResource("pictures/green-computer.png")));
        greenAlienButton.setOpaque(false);
        greenAlienButton.setContentAreaFilled(false);
        greenAlienButton.setBorderPainted(false);
        greenAlienButton.setFocusPainted(false);

        alienMenu.add(redAlienButton);
        alienMenu.add(greenAlienButton);
        alienMenu.add(blueAlienButton);

        //score of a player
        if (score != 0) {
            JLabel scoreLabel = new JLabel(score+"");
            scoreLabel.setFont(new Font("Impact", Font.BOLD, 50));
            scoreLabel.setForeground(new Color(49, 91, 164)); //blue
            scoreLabel.setBounds(200,-5,100,200);
            background.add(scoreLabel);
            

        }


        // Button action listener
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alienMenu.setVisible(true);
                playButton.setVisible(false);
                background = new JLabel(new ImageIcon(this.getClass().getResource("pictures/background.jpeg")));
            }
        });
        // Button action listener
        redAlienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the start menu and start the game
                dispose(); // Close the start menu
                // create button selection

                new AlienBattleGame("red"); // Start the game
            }
        });
        // Button action listener
        blueAlienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the start menu and start the game
                dispose(); // Close the start menu
                // create button selection

                new AlienBattleGame("blue"); // Start the game
                
            }
        });
        // Button action listener
        greenAlienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the start menu and start the game
                dispose(); // Close the start menu
                // create button selection

                new AlienBattleGame("green"); // Start the game
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StartMenu("",0); // Launch the start menu
    }
}
