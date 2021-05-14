package com.sw.tank;

public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {

        int bx = t.x+ Tank.WIDTH/2 - Bullent.WIDTH/2;
        int by =t.y +Tank.HEIGHT/2 - Bullent.HEIGHT/2;
        new Bullent(bx,by,t.dir,t.tf,t.group);
        if(t.group == Group.GOOD) new  Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
