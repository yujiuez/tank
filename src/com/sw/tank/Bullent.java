package com.sw.tank;

import java.awt.*;

public class Bullent {
     private static final int SPEED = 10;
     private int x,y;
     private Dir dir = Dir.DOWN;
     private int WIDTH = 20;
     private int HEIGHT = 20;

     public Bullent(int x,int y,Dir Dir){
         this.x = x;
         this.y =y;
         this.dir = dir;
     }

     public void paint(Graphics g){
            Color c = g.getColor();
         g.setColor(Color.RED);
         g.fillOval(x,y,WIDTH,HEIGHT);
         g.setColor(c);
         
         move();
     }

    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }
    }


}
