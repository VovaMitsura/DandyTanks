package oop.interfaces;

import java.awt.*;

public interface Drawable {

    public static final int BF_WIDTH = 576;
    public static final int BF_HEIGHT = 576;

    public static final int QUADRANT_SIZE = 64;

    public static final int TOP_Y = BF_HEIGHT - QUADRANT_SIZE;
    public static final int TOP_X = BF_WIDTH - QUADRANT_SIZE;


    void draw(Graphics g);
}
