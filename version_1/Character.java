package covid19.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Character{
    public int full, hp, atk;       //full：全滿血量
    public int speedX, speedY;      //移動速度
    public int rectX, rectY;         //初始位置
    public int posX, posY;          //現在位置
    public int width, height;        //圖片大小
    public int lastFlag, moveFlag, srcFlag, bloodFlag;      //lastFlag, moveFlag:-1：沒有移動 0：向左移動 1：向右移動
                                                 //lastFlag：讓上或下跟原本的動作相反
    //public Image img, blood;
    public ArrayList<Image> img = new ArrayList<Image>();
    public ArrayList<Image> blood = new ArrayList<Image>();

    public Character(int sX, int sY, int rX, int rY){
        this.speedX = sX;
        this.speedY = sY;
        this.rectX = rX;
        this.rectY = rY;
        this.posX = this.rectX;
        this.posY = this.rectY;
        this.srcFlag = 0;
        this.bloodFlag = 0;
        this.moveFlag = -1;
    }

    public Character(int sX, int sY){
        this.speedX = sX;
        this.speedY = sY;
        this.rectX = 1200 / 2;          //先預設正中間
        this.rectY = 750 / 2;
        this.posX = this.rectX - this.width/2;
        this.posY = this.rectY - this.height/2;
        this.srcFlag = 0;
        this.bloodFlag = 0;
        this.moveFlag = -1;
    }

    public Character(int heroX, int heroY, int mouseX, int mouseY, int speed){
        this.changeSpeed(heroX, heroY, mouseX, mouseY, speed);
        this.rectX = heroX;
        this.rectY = heroY;
        this.posX = this.rectX;
        this.posY = this.rectY;
    }

    public void changeSpeed(int heroX, int heroY, int mouseX, int mouseY, int speed){
        double x = mouseX - heroX;
        double y = mouseY - heroY;        
        double rate = Math.pow(speed, 2) / (Math.pow(x, 2)+Math.pow(y, 2));
        rate = Math.pow(rate, 0.5);
        x = x*rate;
        y = y*rate;
        this.speedX = (int)x;
        this.speedY = (int)y;
    }

    public Boolean overScreen(){        //如果超出螢幕就回傳true
        if(((posX>=0)&&(posX<=1200-this.width))&&((posY>=0)&&(posY<=750-this.height)))
            return false;
        else
            return true;
    }

    public abstract void changeImg(int level);
    public abstract void changeImg();

}