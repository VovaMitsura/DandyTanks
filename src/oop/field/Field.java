package oop.field;

import oop.enums.Direction;
import oop.pojo.abstracts.AbstractBFObject;
import oop.pojo.impl.*;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import static oop.interfaces.Drawable.*;

public class Field extends JPanel{
    public static Tank tank;
    public static Field field;

    public Field(){

    }

    public Field(int x, int y){
        field = new Field();
        tank = new Tank(x, y);
        field.prepareFrame();
    }

    private static AbstractBFObject[][] objects = {{new Brick(0,0), new Brick(64, 0), new Brick(128, 0), new Ground(192, 0), new Ground(256, 0), new Water(320, 0), new Ground(384, 0), new Water(448, 0), new Brick(512, 0)}
            , {new Ground(0,64), new Ground(64, 64), new Ground(128, 64), new Ground(192, 64), new Ground(256, 64), new Ground(320, 64), new Ground(384, 64), new Ground(448, 64), new Brick(512, 64)}
            , {new Brick(0,128), new Brick(64, 128), new Brick(128, 128), new Ground(192, 128), new Ground(256, 128), new Ground(320, 128), new Ground(384, 128), new Ground(448, 128), new Brick(512, 128)}
            , {new Brick(0,192), new Brick(64, 192), new Brick(128, 192), new Ground(192, 192), new Ground(256, 192), new Ground(320, 192), new Ground(384, 192), new Brick(448, 192), new Brick(512, 192)}
            , {new Brick(0,256), new Brick(64, 256), new Brick(128, 256), new Ground(192, 256), new Ground(256, 256), new Ground(320, 256), new Ground(384, 256), new Brick(448, 256), new Brick(512, 256)}
            , {new Ground(0,320), new Ground(64, 320), new Ground(128, 320), new Ground(192, 320), new Ground(256, 320), new Ground(320, 320), new Ground(384, 320), new Ground(448, 320), new Brick(512, 320)}
            , {new Brick(0,384), new Ground(64, 384), new Ground(128, 384), new Ground(192, 384), new Ground(256, 384), new Water(320, 384), new Ground(384, 384), new Water(448, 384), new Brick(512, 384)}
            , {new Ground(0,448), new Brick(64, 448), new Brick(128, 448), new Ground(192, 448), new Ground(256, 448), new Water(320, 448), new Ground(384, 448), new Water(448, 448), new Brick(512, 448)}
            , {new Brick(0,512), new Brick(64, 512), new Brick(128, 512), new Ground(192, 512), new Ground(256, 512), new Water(320, 512), new Ground(384, 512), new Water(448, 512), new Brick(512, 512)} };

    public void start(){

        if(tank.moveToQuadrant(0, 0)){
            JOptionPane.showMessageDialog(null, "You`ve got to the point!!!", "Message", JOptionPane.PLAIN_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null, "You`ve got to the point!!!", "Message", JOptionPane.PLAIN_MESSAGE);
    }

    public void prepareFrame(){
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(BF_WIDTH + 10, BF_HEIGHT + 34));
        frame.getContentPane().add(this);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        //draw field
        for (int y = 0; y < objects.length; y++) {
            for (int x = 0; x < objects[y].length; x++) {
                objects[y][x].draw(g);
            }
        }

        //draw tank
        tank.draw(g);

        //draw bullet
        Tank.bullet.draw(g);
    }

    public boolean processInterception() {
        int y = Tank.bullet.getY() / 64;
        int x = Tank.bullet.getX() / 64;



        if (y <= 8 && x <= 8 && objects[y][x] instanceof Brick) {
            objects[y][x] = new Ground(x * QUADRANT_SIZE, y * QUADRANT_SIZE);
            return true;
        }

        return false;
    }

    public AbstractBFObject nextObject(Direction direction) {
        int y = tank.getY();
        int x = tank.getX();

        switch (direction) {
            case UP:
                y -= 64;
                break;
            case DOWN:
                y += 64;
                break;
            case LEFT:
                x -= 64;
                break;
            case RIGHT:
                x += 64;
                break;
        }
        return objects[y / QUADRANT_SIZE][x / QUADRANT_SIZE];
    }

    public boolean dontCanMove() {
        return (tank.getDirection() == Direction.UP && tank.getY() == 0) || (tank.getDirection() == Direction.DOWN && tank.getY() == TOP_Y)
                || (tank.getDirection() == Direction.LEFT && tank.getX() == 0) || (tank.getDirection() == Direction.RIGHT && tank.getX() == TOP_X)
                || (nextObject(tank.getDirection()) instanceof Brick);
    }
}
