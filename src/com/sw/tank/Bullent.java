package com.sw.tank;

import java.awt.*;

public class Bullent {
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
        Bullent.WIDTH = WIDTH;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //    public Bullent(int x, int y, Dir Dir){
//         this.x = x;
//         this.y =y;
//         this.dir = dir;
//     }
public Bullent(int x, int y, Dir dir,TankFrame tf,Group group) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
    rect.x = this.x;
    rect.y = this.y;
    rect.width = WIDTH;
    rect.height = HEIGHT;
    this.group = group;


}

     public void paint(Graphics g){
        if(! living){
            tf.bullets.remove(this);
        }
            switch(dir){
                case LEFT:
                    g.drawImage(ResourceMgr.bulletL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.bulletR,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.bulletU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.bulletD,x,y,null);
                    break;
            }

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


    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return ;
        //TODO:一个rect来记录子弹的位置
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        living =false;
    }
}
