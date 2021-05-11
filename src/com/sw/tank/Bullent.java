package com.sw.tank;

import java.awt.*;

public class Bullent {
     private static final int SPEED = 10;
     private int x,y;
     private static int WIDTH = 20;
     private static int HEIGHT = 20;
     private Dir dir;
     private Boolean live = true;
     TankFrame tf = null;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Bullent.WIDTH = WIDTH;
    }

//    public Bullent(int x, int y, Dir Dir){
//         this.x = x;
//         this.y =y;
//         this.dir = dir;
//     }
public Bullent(int x, int y, Dir dir,TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
}

     public void paint(Graphics g){
        if(! live){
            tf.bullents.remove(this);
        }
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }


}
