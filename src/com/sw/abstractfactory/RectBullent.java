package com.sw.abstractfactory;

import com.sw.tank.*;

import java.awt.*;

public class RectBullent extends BaseBullet {
     private static final int SPEED = 10;
     private int x,y;
     public  static int WIDTH = ResourceMgr.bulletD.getWidth();
     public  static int HEIGHT = ResourceMgr.bulletD.getHeight();

     Rectangle rect = new Rectangle();
     private Dir dir;
     private Boolean living = true;
     private Group group = Group.BAD;
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
        RectBullent.WIDTH = WIDTH;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

public RectBullent(int x, int y, Dir dir, TankFrame tf, Group group) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
    rect.x = this.x;
    rect.y = this.y;
    rect.width = WIDTH;
    rect.height = HEIGHT;
    tf.bullets.add(this);
    this.group = group;


}

     public void paint(Graphics g){
        if(! living){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(group == Group.GOOD ?Color.RED:Color.white.YELLOW);
        g.fillRect(x,y,20,20);
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

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }


    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return ;
        //TODO:一个rect来记录子弹的位置
        if(rect.intersects(tank.rect)){
            this.die();
            tank.die();
            new  Thread(()->new Audio("audio/explode.wav").play()).start();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(eX,eY,tf));
        }
    }

    private void die() {
        living =false;
    }
}
