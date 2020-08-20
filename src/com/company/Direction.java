package com.company;

public enum Direction {
    Up, Down, Left, Right;

    public boolean isOpposite(Direction dir) {
        switch (this) {
            case Up:
                if(dir == Down) return true;
                break;
            case Down:
                if(dir == Up) return true;
                break;
            case Left:
                if (dir == Right) return true;
                break;
            case Right:
                if (dir == Left) return true;
                break;
        }
        return false;
    }
}
