package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GameView gameView;

    public MainFrame(String title) throws HeadlessException {
        setLayout(new BorderLayout());

        gameView = new GameView();
        add(gameView, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

}
