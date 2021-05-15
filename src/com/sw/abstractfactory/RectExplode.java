package com.sw.abstractfactory;

import com.sw.tank.ResourceMgr;
import com.sw.tank.TankFrame;

import java.awt.*;
import java.util.Random;

public class RectExplode  extends BaseExplode{

    private int x,y;
    public static int WIDTH = ResourceMgr.exploades[0].getWidth();
    public static int HEIGHT = ResourceMgr.exploades[0].getHeight();
    private Random random = new Random();
    //    private boolean living = true;
    TankFrame tf = null;
    private int step = 0;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public RectExplode(int x, int y, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;

    }
    @Override
    public void paint(Graphics g){

//        g.drawImage(ResourceMgr.exploades[step++],x,y,null);
//        brokenSound();
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if(step >=15)
            tf.explodes.remove(this);
        g.setColor(c);
    }
}
