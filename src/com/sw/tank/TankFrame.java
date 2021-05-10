package com.sw.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200,200,Dir.DOWN);
    Bullent b = new Bullent(300,400,Dir.DOWN);
    public TankFrame(){
        setSize(800,800);
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

    @Override
    public void paint(Graphics g){
        myTank.paint(g);
        b.paint(g);
    }

    class MyKeyListener extends KeyAdapter{
        boolean BL = false;
        boolean BU = false;
        boolean BR = false;
        boolean BD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key =e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    BL = true;
                    break;
                case KeyEvent.VK_UP:
                    BU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR= true;
                    break;
                case KeyEvent.VK_DOWN:
                    BD= true;
                    break;
                default:
                    break;
            }
                setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
           int key =e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    BL = false;
                    break;
                case KeyEvent.VK_UP:
                    BU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR= false;
                    break;
                case KeyEvent.VK_DOWN:
                    BD= false;
                    break;
                default:
                    break;
            }

        }
        private void setMainTankDir() {
            if(!BL && !BD && !BR && !BU) myTank.setMoving(false);
            else{
                myTank.setMoving(true);
                if(BL) myTank.setDir(Dir.LEFT);
                if(BU) myTank.setDir(Dir.UP);
                if(BR) myTank.setDir(Dir.RIGHT);
                if(BD) myTank.setDir(Dir.DOWN);
            }

        }


    }




}
