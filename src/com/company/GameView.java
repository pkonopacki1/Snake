package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameView extends JPanel implements ActionListener {
    private Timer timer;
    private Snake snake;
    private Apple apple;
    private Rectangle bounds;
    private JLabel startLabel;
    private boolean gameStarted;
    private boolean isUpdating;
    private int gameSpeeed;

    public GameView() {
        startLabel = new JLabel("Press any key to start game");
        startLabel.setFont(new Font("Script", Font.BOLD, 30));
        startLabel.setForeground(Color.WHITE);
        setPreferredSize(new Dimension(510, 510));
        setupNewGame();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            int key = e.getKeyCode();

            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (!gameStarted) {
                    startGame();
                } else if (!isUpdating){
                    switch (key) {
                        case KeyEvent.VK_UP:
                            snake.changeDirection(Direction.Up);
                            break;
                        case KeyEvent.VK_DOWN:
                            snake.changeDirection(Direction.Down);
                            break;
                        case KeyEvent.VK_LEFT:
                            snake.changeDirection(Direction.Left);
                            break;
                        case KeyEvent.VK_RIGHT:
                            snake.changeDirection(Direction.Right);
                            break;
                    }
                }
            }
            return false;
        });
    }

    private void setupNewGame() {
        gameSpeeed = 200;
        snake = new Snake();
        apple = new Apple();
        timer = new Timer(gameSpeeed, this);
        gameStarted = false;
        isUpdating = false;

        add(startLabel);
    }

    private void startGame() {
        remove(startLabel);
        timer.start();
        gameStarted = true;
    }

    private void stopGame() {
        timer.stop();
        setupNewGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        bounds = new Rectangle(0, 0, getWidth(), getHeight());
        g2.fill(bounds);

        if (!snake.isPut()) {
            snake.put(getWidth() / Utils.BOARD_CELL / 2 * Utils.BOARD_CELL,
                    getHeight() / Utils.BOARD_CELL / 2 * Utils.BOARD_CELL); // X and Y given as discrete values
        }
        snake.paint(g2);

        if (!apple.isPut()) {
            apple.putRandom(getWidth(), getHeight(), snake.getBodiesPoints());
        }
        apple.paint(g2);

    }

    public void update() {
        isUpdating = true;
        snake.move();

        if (snake.intersectsItsSelf() || outOfBounds()) {
            System.out.println("You lost!");
            stopGame();
        }
        else if (snake.intersects(apple.getBounds())) {
            snake.grow();
            apple.putRandom(getWidth(), getHeight(), snake.getBodiesPoints());
            gameSpeeed -= gameSpeeed*0.02;
            timer.setDelay(gameSpeeed);
        }

        repaint();
        isUpdating = false;
    }


    // Detects weather snake got out of GameView bounds
    private boolean outOfBounds() {
        return !snake.intersects(bounds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
