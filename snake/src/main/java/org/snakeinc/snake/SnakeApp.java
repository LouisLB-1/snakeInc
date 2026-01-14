package org.snakeinc.snake;

import javax.swing.*;

import org.snakeinc.snake.ui.GamePanel;
import org.snakeinc.snake.ui.LaunchPanel;

public class SnakeApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            LaunchPanel launchPanel = new LaunchPanel(frame);
            frame.add(launchPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}