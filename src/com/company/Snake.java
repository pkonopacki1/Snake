package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<Ellipse2D.Double> bodys = new LinkedList<>();
    private int initSize = 3;
    private boolean isPut;
    private boolean isRepainted;  // Variable preventing from changing direction again, before painting snake
    private Direction snakeDirection;

    public Snake() {
        isPut = false;
        isRepainted = false;

        snakeDirection = Direction.Right;

        for (int i = 0; i < initSize; i++) {
            bodys.add(new Ellipse2D.Double(0.0, 0.0, Utils.BOARD_CELL, Utils.BOARD_CELL));
        }
    }

    public void put(int x, int y) {
        bodys.getFirst().x = x;
        bodys.getFirst().y = y;

        for (Ellipse2D.Double ellipse : bodys.subList(1, bodys.size())) {
            ellipse.x = x - (bodys.indexOf(ellipse)) * Utils.BOARD_CELL;
            ellipse.y = y;
        }

        isPut = true;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        g2.fill(bodys.getFirst());

        g2.setColor(Color.GREEN);
        for (Ellipse2D.Double ellipse : bodys.subList(1, bodys.size())) {
            g2.fill(ellipse);
        }
        isRepainted = true;
    }

    public void move() {
        for (int i = bodys.size() - 1; i > 0; i--) {
            bodys.get(i).x = bodys.get(i - 1).x;
            bodys.get(i).y = bodys.get(i - 1).y;
        }

        switch (snakeDirection) {
            case Right:
                bodys.getFirst().x += Utils.BOARD_CELL;
                break;
            case Left:
                bodys.getFirst().x -= Utils.BOARD_CELL;
                break;
            case Down:
                bodys.getFirst().y += Utils.BOARD_CELL;
                break;
            case Up:
                bodys.getFirst().y -= Utils.BOARD_CELL;
                break;
        }

    }

    public void grow() {
        int lastButOneInedx = bodys.size() - 1;
        bodys.add(lastButOneInedx, new Ellipse2D.Double(0.0, 0.0, Utils.BOARD_CELL, Utils.BOARD_CELL));
        bodys.get(lastButOneInedx).x = bodys.getLast().x;
        bodys.get(lastButOneInedx).y = bodys.getLast().y;
    }

    public void changeDirection(Direction direction) {
        if (!direction.isOpposite(snakeDirection) && isRepainted) {
            switch (direction) {
                case Right:
                    if (snakeDirection != Direction.Left) {
                        snakeDirection = Direction.Right;
                    }
                    break;
                case Left:
                    if (snakeDirection != Direction.Right) {
                        snakeDirection = Direction.Left;
                    }
                    break;
                case Down:
                    if (snakeDirection != Direction.Up) {
                        snakeDirection = Direction.Down;
                    }
                    break;
                case Up:
                    if (snakeDirection != Direction.Down) {
                        snakeDirection = Direction.Up;
                    }
                    break;

            }
            isRepainted = false;
        }
    }

    public boolean isPut() {
        return isPut;
    }

    public boolean intersects(Rectangle rectangle) {
        return bodys.getFirst().intersects(rectangle);
    }

    public boolean intersectsItsSelf() {
        for (int i = 1; i < bodys.size(); i++) {
            if (bodys.getFirst().getBounds2D().intersects(bodys.get(i).getBounds2D())) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getBodiesPoints() {
        List<Point> pointList = new ArrayList<>();

        for (Ellipse2D.Double body : bodys) {
            pointList.add(new Point((int) body.x, (int) body.y));
        }

        return pointList;
    }

    public int getLength() {
        return bodys.size() - initSize;
    }
}
