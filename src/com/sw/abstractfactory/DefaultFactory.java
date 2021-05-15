package com.sw.abstractfactory;

import com.sw.tank.*;

public class DefaultFactory extends GameFactory {
    @Override
   public RectTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectTank(x,y,dir,tf,group);
    }

    @Override
   public RectBullent createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectBullent(x,y,dir,tf,group);
    }

    @Override
   public  BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }


}
