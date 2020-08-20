package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;

// TODO: 13.08.2020 Zabezpiecz przed pojawieniem się w miejscu węża 

public class Apple extends Ellipse2D.Double {
    private boolean isPut;

    public Apple() {
        x = 0;
        y = 0;
        width = Utils.BOARD_CELL;
        height = Utils.BOARD_CELL;
        isPut = false;
    }


    public void putRandom(int frameHeight, int frameWidth) {
        int maxX = frameWidth / Utils.BOARD_CELL;
        int maxY = frameHeight / Utils.BOARD_CELL;

        x = ThreadLocalRandom.current().nextInt(maxX) * Utils.BOARD_CELL;
        y = ThreadLocalRandom.current().nextInt(maxY) * Utils.BOARD_CELL;
        isPut  = true;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fill(this);
    }

    public boolean isPut() {
        return isPut;
    }
}
