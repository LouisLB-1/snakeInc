package org.snakeinc.snake.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LaunchPanel extends JPanel {

    private final JFrame frame;
    private JTextField usernameField;

    public LaunchPanel(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(GamePanel.GAME_PIXEL_WIDTH, GamePanel.GAME_PIXEL_HEIGHT));
        this.setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JLabel title = new JLabel("SNAKE");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.GREEN);
        this.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(Color.BLACK);

        JLabel usernameLabel = new JLabel("Enter your username: ");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);
        JButton startButton = new JButton("Start Game");

        startButton.addActionListener((ActionEvent e) -> startGame());

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(startButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void startGame() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        GamePanel gamePanel = new GamePanel(username);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();
        gamePanel.requestFocusInWindow();
    }
}
