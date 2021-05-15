package com.sw.abstractfactory;

import com.sw.tank.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class RectTank extends BaseTank {
    int x,y;
    Dir dir =Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = true;
    TankFrame tf = null;
    Group group = Group.BAD;
    FireStrategy fs;
    public Rectangle rect = new Rectangle();
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

    public RectTank(int x, int y, Dir dir, TankFrame tf, Group group){
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
        if(group == Group.GOOD){
            String goodFSName = (String)PropertyMgr.get("goodFs");

                //利用反射机制将对象从内存load到该位置
            try {
                fs = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else fs = new DefaultFireStrategy();

    }

    public void paint(Graphics g){
        if(! living) tf.tanks.remove(this);

        Color c =g.getColor();
        g.setColor(group==Group.GOOD?Color.RED:Color.YELLOW);
        g.fillRect(x,y,40,40);
        g.setColor(c);
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
            if(this.group == Group.GOOD) new  Thread(()->new Audio("audio/tank_move.wav").play()).start();

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
//        fs.fire(this);
        int bx = this.x+ RectTank.WIDTH/2 - Bullent.WIDTH/2;
        int by =this.y + RectTank.HEIGHT/2 - Bullent.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir :dirs){
            this.tf.gf.createBullet(bx,by,dir,this.tf,this.group);
        }
//        new Bullent(bx,by,t.dir,t.tf,t.group);
        if(this.group == Group.GOOD) new  Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        living =false;
    }
}

