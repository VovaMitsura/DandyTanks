package oop.pojo.impl;

import oop.enums.Direction;
import oop.field.Field;
import oop.pojo.abstracts.AbstractMovableField;

import java.awt.*;

public class Tank extends AbstractMovableField {

    private Direction direction = Direction.DOWN;

    public static Bullet bullet;


    public Tank(int x, int y) {
        super(x, y);
        bullet = new Bullet(-100, -100);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    @Override
    public void move(Direction direction) {
        setDirection(direction);

        if (Field.field.dontCanMove()) {
            System.out.println("Tank can`t move!!! " +
                    "----" +
                    " Fire!!");
            bullet.move(getDirection());
        }

        if(Field.field.nextObject(direction) instanceof Water){
            this.direction = direction = Direction.turnLeft(direction);
        }

        for (int i = 0; i < QUADRANT_SIZE; i++) {

            if (direction == Direction.UP) {
                Field.tank.setY(Field.tank.getY() - 1);
            } else if (direction == Direction.DOWN) {
                Field.tank.setY(Field.tank.getY() + 1);
            } else if (direction == Direction.LEFT) {
                Field.tank.setX(Field.tank.getX() - 1);
            } else if (direction == Direction.RIGHT) {
                Field.tank.setX(Field.tank.getX() + 1);
            }

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Field.field.repaint();
        }
    }

    public boolean moveToQuadrant(int x, int y) {
        while (Field.tank.getX() != x || Field.tank.getY() != y) {
            if (y > Field.tank.getY()) {
                while (y != Field.tank.getY())
                    move(Direction.DOWN);
            }
            if (y < Field.tank.getY()) {
                while (y != Field.tank.getY())
                    move(Direction.UP);
            }
            if (x > Field.tank.getX()) {
                while (x != Field.tank.getX())
                    move(Direction.RIGHT);
            }
            if (x < Field.tank.getX()) {
                while (x != Field.tank.getX())
                    move(Direction.LEFT);
            }
        }
        if(Field.tank.getX() == x && Field.tank.getY() == y){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), QUADRANT_SIZE, QUADRANT_SIZE);

        //draw gun
        g.setColor(Color.GREEN);

        switch (getDirection()) {
            case UP:
                g.fillRect(getX() + 20, getY(), 24, 34);
                break;
            case DOWN:
                g.fillRect(getX() + 20, getY() + 30, 24, 34);
                break;
            case LEFT:
                g.fillRect(getX(), getY() + 20, 34, 24);
                break;
            case RIGHT:
                g.fillRect(getX() + 30, getY() + 20, 34, 24);
                break;
        }
    }

}
