package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private GameView gameView;
    private GamePanel gamePanel;

    public MainFrame(String title) throws HeadlessException {
        setLayout(new BorderLayout());
        gameView = new GameView();
        gamePanel = new GamePanel();

        add(gameView, BorderLayout.CENTER);
        add(gamePanel, BorderLayout.NORTH);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED && !gameView.isGameStarted()) {
                gameView.startGame();
            }
            return true;
        });

        gameView.setGameViewListener(new GameViewListener() {
            @Override
            public void gameViewUpdated(int score) {
                gamePanel.updateScore(score);
            }

            @Override
            public void gameViewGameOver() {
                gamePanel.newGame();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

}
