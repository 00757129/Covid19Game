package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Enemy extends Character{

    String[][] Src = {{"covid.png"}, {"boss2.png", "boss2_2.png"}};
    String[][] Blood = {{"1-1.png","0-1.png"},{"6-6.png","5-6.png","4-6.png","3-6.png","2-6.png","1-6.png","0-6.png"}};   //血量圖片
    int[] limit = {1, 2};

    public Enemy(int level, int enenmyX, int enenmyY, int heroX, int heroY, int speed,int hp,int src,int width,int height){
        super(enenmyX, enenmyY, heroX, heroY, speed);
        this.hp=hp;
        this.width = width;
        this.height = height;
        img.add(new ImageIcon(Src[level][0]).getImage());
        blood.add(new ImageIcon(Blood[level][0]).getImage());
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

    public void setHp(int minus,int level)
    {
        String hp = Blood[level][bloodFlag];   
        blood.set(0, new ImageIcon(hp).getImage());
        for(int i = 0;i<minus;i++)
        {
            bloodFlag++;
        }
    }
}