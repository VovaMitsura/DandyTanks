package oop.pojo.abstracts;

import oop.enums.Direction;
import oop.interfaces.Drawable;

public abstract class AbstractMovableField extends AbstractBFObject {

    public AbstractMovableField(){

    }

    public AbstractMovableField(int x, int y) {
        super(x, y);
    }

    abstract public void move(Direction direction);

}
