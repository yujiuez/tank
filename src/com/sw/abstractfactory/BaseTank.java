package com.sw.abstractfactory;

import com.sw.tank.Dir;
import com.sw.tank.Group;
import com.sw.tank.TankFrame;

import java.awt.*;

public abstract class BaseTank {

    public Rectangle rect = new Rectangle();
//    public int x;
//    public int y;

    public Group group =Group.BAD;
//    public Dir dir;
//    public TankFrame tf;
    public abstract void die();
    public Group getGroup() {
        return this.group;
    }

    public abstract void paint(Graphics g);

    public abstract int getX();

    public abstract int getY();
}
