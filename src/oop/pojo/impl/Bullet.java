package oop.pojo.impl;

import oop.enums.Direction;
import oop.interfaces.Destroyable;
import oop.pojo.abstracts.AbstractMovableField;
import java.awt.*;
import oop.field.*;

public class Bullet extends AbstractMovableField implements Destroyable {

    public Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction direction) {
        Tank.bullet.setX(Field.tank.getX() + 25);
        Tank.bullet.setY(Field.tank.getY() + 25);

        while (Tank.bullet.getX() > 0 && Tank.bullet.getX() < BF_WIDTH && Tank.bullet.getY() > 0 && Tank.bullet.getY() < BF_HEIGHT){
            switch (direction) {
                case UP:
                    Tank.bullet.setY(getY() - 1);
                    break;
                case DOWN:
                    Tank.bullet.setY(getY() + 1);
                    break;
                case LEFT:
                    Tank.bullet.setX(getX() - 1);
                    break;
                case RIGHT:
                    Tank.bullet.setX(getX() + 1);
                    break;
            }

            if (Field.field.processInterception()) {
                destroy();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Field.field.repaint();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getX(), getY(), 14, 14);
    }


    @Override
    public void destroy(Graphics g) {
        Tank.bullet.setY(-100);
        Tank.bullet.setX(-100);
        repaint();
    }

    public void destroy(){
        Tank.bullet.setY(-100);
        Tank.bullet.setX(-100);
    }

}
