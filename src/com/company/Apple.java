package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Apple extends Ellipse2D.Double {
    private boolean isPut;

    public Apple() {
        x = 0;
        y = 0;
        width = Utils.BOARD_CELL;
        height = Utils.BOARD_CELL;
        isPut = false;
    }


    public void putRandom(int frameHeight, int frameWidth, List<Point> forbidenPoints) {
        int maxX = frameWidth / Utils.BOARD_CELL;
        int maxY = frameHeight / Utils.BOARD_CELL;

        do {
            x = ThreadLocalRandom.current().nextInt(maxX) * Utils.BOARD_CELL;
            y = ThreadLocalRandom.current().nextInt(maxY) * Utils.BOARD_CELL;
        }
        while (forbidenPoints.contains(new Point( (int) x, (int) y)));

        isPut = true;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fill(this);
    }

    public boolean isPut() {
        return isPut;
    }
}
