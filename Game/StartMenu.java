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
        //Backgroung label
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("background.jpeg")));
        background.setSize(800,600);
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

        // Button action listener
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the start menu and start the game
                dispose(); // Close the start menu
                new AlienBattleGame(); // Start the game
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StartMenu(); // Launch the start menu
    }
}
