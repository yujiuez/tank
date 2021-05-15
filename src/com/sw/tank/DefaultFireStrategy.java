package com.sw.tank;

public class DefaultFireStrategy implements FireStrategy{
    public void fire(Tank t) {
        int bx = t.x+ Tank.WIDTH/2 - Bullent.WIDTH/2;
        int by =t.y +Tank.HEIGHT/2 - Bullent.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir :dirs){
            t.tf.gf.createBullet(bx,by,dir,t.tf,t.group);
        }
//        new Bullent(bx,by,t.dir,t.tf,t.group);
        if(t.group == Group.GOOD) new  Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
