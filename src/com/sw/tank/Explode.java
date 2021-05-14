package com.sw.tank;

import java.awt.*;
import java.util.Random;

public class Explode {
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


    public Explode(int x, int y, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;

    }

    public void paint(Graphics g){

        g.drawImage(ResourceMgr.exploades[step++],x,y,null);
//        brokenSound();
        if(step >=ResourceMgr.exploades.length)
            tf.explodes.remove(this);
    }
//    public void brokenSound(){
//        for(int i =0;i < 6;i++)
//            new  Thread(()->new Audio("audio/explode.wav").play()).start();
//    }
}
