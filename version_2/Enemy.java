package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Enemy extends Character{

    String[][] Src = {{"covid.png"}, {"boss2.png", "boss2_2.png"}};
    int[] limit = {1, 2};

    public Enemy(int level, int enenmyX, int enenmyY, int heroX, int heroY, int speed,int hp,int src,int width,int height){
        super(enenmyX, enenmyY, heroX, heroY, speed);
        this.hp=hp;
        this.width = width;
        this.height = height;
        img.add(new ImageIcon(Src[level][0]).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public void move(int enenmyX, int enenmyY, int heroX, int heroY, int speed)
    {
        double x = heroX - enenmyX;
        double y = heroY - enenmyY;
        double rate = Math.pow(speed, 2) / (Math.pow(x,2) + Math.pow(y,2));
        rate = Math.pow(rate, 0.5);
        x = x*rate;
        y = y*rate;
        this.speedX = (int)x;
        this.speedY = (int)y;
    }

    @Override
    public void changeImg(){}

    @Override
    public void changeImg(int level){   
        String src = Src[level][srcFlag];
        img.set(0, new ImageIcon(src).getImage());
        srcFlag++;
        if(srcFlag == limit[level])
            srcFlag = 0;
        
    }
}