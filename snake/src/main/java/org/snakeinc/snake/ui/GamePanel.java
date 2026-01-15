package org.snakeinc.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SnakeMalnutrition;
import org.snakeinc.snake.model.*;
import org.snakeinc.snake.api.ApiClient;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_PIXEL_SIZE = 20;
    public static final int GAME_PIXEL_WIDTH = TILE_PIXEL_SIZE * GameParams.TILES_X;
    public static final int GAME_PIXEL_HEIGHT = TILE_PIXEL_SIZE * GameParams.TILES_Y;

    private final String username;
    private Timer timer;
    private Game game;
    private boolean running = false;
    private Integer bestScore = null;
    private boolean statsLoaded = false;


    public GamePanel(String username) {
        this.username = username;
        this.setPreferredSize(new Dimension(GAME_PIXEL_WIDTH, GAME_PIXEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    private void startGame() {
        game = new Game();
        timer = new Timer(100, this);
        timer.start();
        running = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            UIUtils.draw(g, game);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());

        int y = GAME_PIXEL_HEIGHT / 3;

        g.drawString(
                "Game Over",
                (GAME_PIXEL_WIDTH - metrics.stringWidth("Game Over")) / 2,
                y
        );

        y += 40;
        String scoreText = "Your score : " + game.getSnake().getScore();
        g.drawString(
                scoreText,
                (GAME_PIXEL_WIDTH - metrics.stringWidth(scoreText)) / 2,
                y
        );

        y += 40;

        if (!statsLoaded) {
            g.drawString("Loading best score...",
                    (GAME_PIXEL_WIDTH - metrics.stringWidth("Loading best score...")) / 2,
                    y
            );
        } else if (bestScore != null) {
            String bestText = "Best Score : " + bestScore;
            g.drawString(
                    bestText,
                    (GAME_PIXEL_WIDTH - metrics.stringWidth(bestText)) / 2,
                    y
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            try {
                game.iterate(game.getSnake().getDirection());
            } catch (OutOfPlayException | SelfCollisionException | SnakeMalnutrition exception) {
                timer.stop();
                running = false;
                fetchBestScoreAsync();
            }
        }
        repaint();
    }

    private void fetchBestScoreAsync() {
        new Thread(() -> {
            try {
                String snake = switch (game.getSnake()) {
                    case Anaconda anaconda -> "Anaconda";
                    case Python python -> "Python";
                    case BoaConstrictor boaconstrictor -> "Boa";
                };
                ApiClient.postScore( game.getSnake().getScore(), snake, 1);

                this.bestScore = ApiClient.getBestScore(1);
                statsLoaded = true;

                repaint();
            }
            catch (Exception e) {
                this.bestScore = null;
                statsLoaded = true;
            }
        }).start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (game.getSnake().getDirection() != Snake.Direction.R) {
                    game.getSnake().setDirection(Snake.Direction.L);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (game.getSnake().getDirection() != Snake.Direction.L) {
                    game.getSnake().setDirection(Snake.Direction.R);
                }
                break;
            case KeyEvent.VK_UP:
                if (game.getSnake().getDirection() != Snake.Direction.D) {
                    game.getSnake().setDirection(Snake.Direction.U);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (game.getSnake().getDirection() != Snake.Direction.U) {
                    game.getSnake().setDirection(Snake.Direction.D);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
