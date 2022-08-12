package oop.pojo.impl;

import oop.interfaces.Destroyable;
import oop.pojo.abstracts.AbstractBFObject;

import java.awt.*;

public class Brick extends AbstractBFObject implements Destroyable {
    public Brick(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(155, 38, 38, 255));
        g.fillRect(getX(), getY(), QUADRANT_SIZE, QUADRANT_SIZE);
    }

    @Override
    public void destroy(Graphics g) {
        g.setColor(new Color(238, 218, 201));

    }

}
