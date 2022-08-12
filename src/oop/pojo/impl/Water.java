package oop.pojo.impl;

import oop.pojo.abstracts.AbstractBFObject;

import java.awt.*;

public class Water extends AbstractBFObject {

    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(62, 62, 252));
        g.fillRect(getX(), getY(), QUADRANT_SIZE, QUADRANT_SIZE);
    }
}
