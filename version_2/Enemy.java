package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Enemy extends Character{

    String[][] Src = {{"covid.png"}, {"boss2.png", "boss2_2.png"}, {"boss3.png", "boss3_2.png"}};
    String[][] Blood = {{"1-1.png","0-1.png"},{"6-6.png","5-6.png","4-6.png","3-6.png","2-6.png","1-6.png","0-6.png"},{"12-12.png","11-12.png","10-12.png","9-12.png","8-12.png","7-12.png","6-12.png","5-12.png","4-12.png","3-12.png","2-12.png","1-12.png","0-12.png"}};   //血量圖片
    int[] limit = {1, 2, 2};
    int type;

    public Enemy(int type, int enenmyX, int enenmyY, int heroX, int heroY, int speed,int hp,int src,int width,int height){
        super(enenmyX, enenmyY, heroX, heroY, speed);
        this.hp=hp;
        this.width = width;
        this.height = height;
        this.type = type;
        img.add(new ImageIcon(Src[type][0]).getImage());
        blood.add(new ImageIcon(Blood[type][0]).getImage());
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
    public void changeImg(int tmp){  

        String src = Src[this.type][srcFlag];
        img.set(0, new ImageIcon(src).getImage());
        srcFlag++;
        if(srcFlag == limit[type])
            srcFlag = 0;
        
    }

    public void setHp(int minus,int level)
    {
        String hp = Blood[level][bloodFlag];   
        blood.set(0, new ImageIcon(hp).getImage());
        bloodFlag+=minus;
    }
}