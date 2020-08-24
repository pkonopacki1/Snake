package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: 13.08.2020 Zabezpiecz przed zmianą kierunku zanim wykonano ruch (zmienna isMoved) 

public class Snake {
    private LinkedList<Rectangle> bodys = new LinkedList<>();
    private int initSize = 3;
    private boolean isPut;
    private Direction snakeDirection;

    public Snake() {
        isPut = false;
        snakeDirection = Direction.Right;

        for (int i = 0; i < initSize; i++) {
            bodys.add(new Rectangle(Utils.BOARD_CELL, Utils.BOARD_CELL));
        }
    }

    public void put(int x, int y) {
        bodys.getFirst().x = x;
        bodys.getFirst().y = y;

        for (Rectangle rectangle : bodys.subList(1, bodys.size())) {
            rectangle.x = x - (bodys.indexOf(rectangle)) * Utils.BOARD_CELL;
            rectangle.y = y;
        }

        isPut = true;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fill(bodys.getFirst());

        g2.setColor(Color.CYAN);
        for (Rectangle rectangle : bodys.subList(1, bodys.size())) {
            g2.fill(rectangle);
        }
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
        bodys.add(lastButOneInedx, new Rectangle(Utils.BOARD_CELL, Utils.BOARD_CELL));
        bodys.get(lastButOneInedx).x = bodys.getLast().x;
        bodys.get(lastButOneInedx).y = bodys.getLast().y;
    }

    public void changeDirection(Direction direction) {
        if (!direction.isOpposite(snakeDirection)) {
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
            if(bodys.getFirst().intersects(bodys.get(i))) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getBodiesPoints () {
        List<Point> pointList = new ArrayList<>();

        for (Rectangle body: bodys) {
            pointList.add(new Point(body.x, body.y));
        }

        return pointList;
    }
}
