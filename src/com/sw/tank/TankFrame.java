package com.sw.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(330, 300, Dir.DOWN, this,Group.GOOD);
    List<Bullent> bullents = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    Bullent b = new Bullent(300, 300, Dir.DOWN, this,Group.GOOD);
    static final int GAME_HEIGHT = 800;
    static final int GAME_WIDTH = 800;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + bullents.size(), 10, 60);
        g.drawString("坦克的数量：" + tanks.size(), 10, 70);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < bullents.size(); i++) {
            bullents.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for(int i=0;i<bullents.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullents.get(i).collideWith(tanks.get(j));
            }

        }

//        for(Iterator<Bullent> it = bullents.iterator();it.hasNext();){
//            Bullent b = it.next();
//            if(!b.live) it.remove();
//        }
        //使用如下代码会报错
//
//        for(Bullent b : bullents){
//            b.paint(g);
//        }
//    }
    }
        class MyKeyListener extends KeyAdapter {
            boolean bL = false;
            boolean bU = false;
            boolean bR = false;
            boolean bD = false;

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        bL = true;
                        break;
                    case KeyEvent.VK_UP:
                        bU = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = true;
                        break;
                    default:
                        break;
                }
                setMainTankDir();

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        bL = false;
                        break;
                    case KeyEvent.VK_UP:
                        bU = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = false;
                        break;
                    case KeyEvent.VK_CONTROL:
                        myTank.fire();
                        break;
                    default:
                        break;
                }
                setMainTankDir();
            }

            private void setMainTankDir() {
                if (!bL && !bD && !bR && !bU) {
                    myTank.setMoving(false);
                } else {
                    myTank.setMoving(true);
                    if (bL) myTank.setDir(Dir.LEFT);
                    if (bU) myTank.setDir(Dir.UP);
                    if (bR) myTank.setDir(Dir.RIGHT);
                    if (bD) myTank.setDir(Dir.DOWN);
                }

            }


        }


    }
