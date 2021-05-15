package com.sw.abstractfactory;

import com.sw.tank.*;

public abstract class GameFactory {
  public   abstract RectTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);
  public   abstract RectBullent createBullet(int x, int y, Dir dir, TankFrame tf, Group group);
  public   abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
