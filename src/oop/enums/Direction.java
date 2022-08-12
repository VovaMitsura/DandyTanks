package oop.enums;

public enum Direction {

    UP, DOWN, LEFT, RIGHT;

    public static Direction turnLeft(Direction direction){
        switch (direction){
            case UP:
                return LEFT;
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case RIGHT:
                return UP;
        }
        return LEFT;
    }
}
