package com.sw.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws InterruptedException {
      TankFrame tf = new TankFrame();
      int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

      //初始化敌方坦克
      for(int i=0;i<initTankCount;i++){
          tf.tanks.add(tf.gf.createTank(50+ i*30,200,Dir.DOWN,tf,Group.BAD));
      }
      while(true){
          Thread.sleep(50);
          tf.repaint();
      }
    }
}
