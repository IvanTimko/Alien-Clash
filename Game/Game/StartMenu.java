package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {

    public StartMenu() {
        this.setTitle("Alien Battle Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        // Backgroung label
        JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("background.jpeg")));
        background.setSize(800, 600);
        this.add(background);
        // Title label
        JLabel titleLabel = new JLabel("Alien Battle Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        background.add(titleLabel, BorderLayout.CENTER);

        // Play Game button
        JButton playButton = new JButton("Play Game");
        playButton.setBounds(300, 250, 200, 50);
        playButton.setIcon(new ImageIcon(this.getClass().getResource("playButton.png")));

        playButton.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(playButton, BorderLayout.SOUTH);

        // Background for alien choice
        JLabel alienMenu = new JLabel();
        alienMenu.setBackground(new Color(0, 0, 0, 255));
        alienMenu.setBounds(100, 100, 600, 400);
        this.add(alienMenu);
        alienMenu.setOpaque(true);
        this.setComponentZOrder(alienMenu, 0);
        alienMenu.setVisible(false);
        this.add(alienMenu);

        // Button for Red Alien
        JButton redAlienButton = new JButton("");
        redAlienButton.setBounds(140, 125, 75, 150);
        redAlienButton.setIcon(new ImageIcon(this.getClass().getResource("red-computer.png")));
        // Button for Blue Alien
        JButton blueAlienButton = new JButton("");
        blueAlienButton.setBounds(259, 125, 75, 150);
        blueAlienButton.setIcon(new ImageIcon(this.getClass().getResource("blue-computer.png")));
        // Button for Red Alien
        JButton greenAlienButton = new JButton("");
        greenAlienButton.setBounds(378, 125, 75, 150);
        greenAlienButton.setIcon(new ImageIcon(this.getClass().getResource("green-computer.png")));

        alienMenu.add(redAlienButton);
        alienMenu.add(greenAlienButton);
        alienMenu.add(blueAlienButton);

        // Button action listener
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alienMenu.setVisible(true);
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
        new StartMenu(); // Launch the start menu
    }
}
