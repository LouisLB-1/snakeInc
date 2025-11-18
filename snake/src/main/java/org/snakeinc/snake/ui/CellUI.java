package org.snakeinc.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.AllArgsConstructor;
import org.snakeinc.snake.model.*;

@AllArgsConstructor
public class CellUI {

    private Cell cell;
    private int upperPixelX;
    private int upperPixelY;

    public void drawRectangle(Graphics g) {
        g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
        Graphics2D g2 = (Graphics2D) g;
        switch(cell.getSnake()){
            case Anaconda anaconda:
                g2.setColor(Color.GRAY.darker());;
                break;
            case Python python:
                g2.setColor(Color.GREEN.darker());
                break;
            case BoaConstrictor boaconstrictor:
                g2.setColor(Color.BLUE.darker());
                break;
        }
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void drawOval(Graphics g) {
        g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void draw(Graphics g) {

        if (cell.containsAFruit()) {
            switch(cell.getFruit()){
                case Apple apple:
                    if (cell.getFruit().getStatus() == Fruit.Status.N){
                        g.setColor(Color.RED);
                        drawOval(g);
                    }
                    else {
                        g.setColor(Color.MAGENTA);
                        drawOval(g);
                    }
                    break;
                case Lemon lemon:
                    if (cell.getFruit().getStatus() == Fruit.Status.N){
                        g.setColor(Color.YELLOW);
                        drawOval(g);
                    }
                    else{
                        g.setColor(Color.GREEN);
                        drawOval(g);
                    }
                    break;
            }

        }
        if (cell.containsASnake()) {
            switch(cell.getSnake()){
                case Anaconda anaconda:
                    g.setColor(Color.GRAY);
                    drawRectangle(g);
                    break;
                case Python python:
                    g.setColor(Color.GREEN);
                    drawRectangle(g);
                    break;
                case BoaConstrictor boaconstrictor:
                    g.setColor(Color.BLUE);
                    drawRectangle(g);
                    break;
            }
        }
    }

}
