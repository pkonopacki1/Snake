package com.company;

import javax.swing.*;

public class GamePanel extends JPanel {
    JLabel startGameLabel;

    public GamePanel() {
        startGameLabel = new JLabel("Press any button to start game.");
        add(startGameLabel);
    }

    public void updateScore (int score) {
        startGameLabel.setText("Score: " + score);
    }

    public void newGame() {
        startGameLabel.setText("You lost! \n Press any button to start game.");
    }
}
