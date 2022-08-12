package oop.pojo.impl;

import oop.pojo.abstracts.AbstractBFObject;

import java.awt.*;

public class Ground extends AbstractBFObject {
    public Ground(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(238, 218, 201));
        g.fillRect(this.getX(), this.getY(), QUADRANT_SIZE, QUADRANT_SIZE);
    }
}
