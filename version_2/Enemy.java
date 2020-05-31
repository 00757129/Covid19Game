package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Enemy extends Character{

    String[] Src = {"covid.png"};

    public Enemy(int enenmyX, int enenmyY, int heroX, int heroY, int speed){
        super(enenmyX, enenmyY, heroX, heroY, speed);
        this.width = 30;
        this.height = 30;
        img.add(new ImageIcon(Src[0]).getImage());
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
    public void changeImg(){

    }
}