package com.nrj.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    // length of the snake
    int length;
    // direction of the snake
    String direction;
    // boolean to indicate if the game is in progress
    boolean isStart = false;
    // Timer to control the snake's movement
    Timer timer;
    // two arrays to store x and y coordinates respectively
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    //coordinate of food
    int foodX;
    int foodY;
    // points
    int score;
    // snake's status
    boolean alive = true;

    // initialization
    public void init() {
        length = 3;
        snakeX[0] = 185;
        snakeY[0] = 275;
        snakeX[1] = 160;
        snakeY[1] = 275;
        snakeX[2] = 135;
        snakeY[2] = 275;
        direction = "R";
        foodX = 260;
        foodY = 375;
        score = 0;
        alive = true;
    }

    // constructor
    public GamePanel() {
        init();
        // get focus or the panel cannot read input from keyboard by default
        this.setFocusable(true);
        this.requestFocusInWindow();
        // add listener
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    if (alive) {
                        isStart = !isStart;
                        repaint();
                    } else {
                        init();
                    }
                } else if (keyCode == KeyEvent.VK_UP) {
                    direction = "U";
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    direction = "D";
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    direction = "L";
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    direction = "R";
                }
            }
        });

        // timer initialization
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && alive) {
                    for (int i = length-1; i > 0; i--) {
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    if ("R".equals(direction)) {
                        snakeX[0] += 25;
                    } else if ("U".equals(direction)) {
                        snakeY[0] -= 25;
                    } else if ("D".equals(direction)) {
                        snakeY[0] += 25;
                    } else if ("L".equals(direction)) {
                        snakeX[0] -= 25;
                    }
                    // prevent the snake from running outside the bound
                    if (snakeX[0] > 660) {
                        snakeX[0] = 10;
                    }
                    if (snakeX[0] < 10) {
                        snakeX[0] = 660;
                    }
                    if (snakeY[0] > 625) {
                        snakeY[0] = 100;
                    }
                    if (snakeY[0] < 100) {
                        snakeY[0] = 625;
                    }
                    // check if the food is eaten by the snake
                    if (foodX == snakeX[0] && foodY == snakeY[0]) {
                        length++;
                        foodX = 10 + 25 * ((int)(Math.random() * 27));
                        foodY = 100 + 25 * ((int)(Math.random() * 22));
                        score += 10;
                    }
                    for (int i = 1; i < length; i++) {
                        if ((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
                            alive = false;
                        }
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    // this method will be automatically called
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fill color for the background
        this.setBackground(new Color(208, 220, 226));

        // paint the header image
        Images.headerImg.paintIcon(this, g,10, 10);

        // set the color of the paintbrush
        g.setColor(new Color(218, 212, 147));
        // draw the rectangle
        g.fillRect(10, 100, 675, 550);

        // draw the snake
        // draw the head
        if ("R".equals(direction)) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("L".equals(direction)) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("D".equals(direction)) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("U".equals(direction)) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        // draw the body
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        // draw the food
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        // write words to indicate the status if the game is paused
        if (!isStart) {
            g.setColor(new Color(150, 97, 182, 207));
            g.setFont(new Font("Calibri", Font.BOLD, 40));
            g.drawString("press space to start", 180, 350);
        }

        // record the score
        g.setColor(new Color(255, 248, 248));
        g.setFont(new Font("Calibri", Font.BOLD, 24));
        g.drawString("score: "+score, 570, 60);

        // if the snake is dead, print words to indicate it and let the player restart it
        if (!alive) {
            g.setColor(new Color(236, 69, 56));
            g.setFont(new Font("Calibri", Font.BOLD, 24));
            g.drawString("The snake is dead. Press space to restart", 150, 300);
        }

    }
}
