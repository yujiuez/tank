package com.sw.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = true;
    private TankFrame tf = null;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();
    private boolean living = true;
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g){
        if(! living) tf.tanks.remove(this);



        switch(dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        if(!moving) return ;
        else {
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
            if(this.group == Group.BAD && random.nextInt(100) > 95)
                this.fire();
            if(this.group == Group.BAD && random.nextInt(100) > 95)
                randomDir();

            boundCheck();
            //update rect
            rect.x = this.x;
            rect.y = this.y;
        }


    }

    private void boundCheck() {
        if (this.x < 0) x = 0;
        else if (this.y < 30) y = 30;
        else if (this.x > TankFrame.GAME_WIDTH - this.WIDTH) x = TankFrame.GAME_WIDTH - this.WIDTH;
        else if (this.y > TankFrame.GAME_HEIGHT -  this.HEIGHT) y = TankFrame.GAME_HEIGHT - this.HEIGHT ;

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x+ Tank.WIDTH/2 - Bullent.WIDTH/2;
        int by =this.y +Tank.HEIGHT/2 - Bullent.HEIGHT/2;
        tf.bullets.add(new Bullent(bx,by,this.dir,tf,this.group));

    }

    public void die() {
        living =false;
    }
}

