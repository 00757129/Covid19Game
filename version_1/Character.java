package covid19.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character{
    public int full, hp, atk;       //full：全滿血量
    public int speedX, speedY;      //移動速度
    public int rectX, rectY;         //初始位置
    public int posX, posY;          //現在位置
    public int width, height;        //圖片大小
    public int lastFlag, moveFlag, srcFlag;      //lastFlag, moveFlag:-1：沒有移動 0：向左移動 1：向右移動
                                                 //lastFlag：讓上或下跟原本的動作相反
    public Image img, blood;

    public Character(int sX, int sY, int rX, int rY){
        this.speedX = sX;
        this.speedY = sY;
        this.rectX = rX;
        this.rectY = rY;
        this.posX = this.rectX;
        this.posY = this.rectY;
    }

    public Character(int sX, int sY){
         this.speedX = sX;
        this.speedY = sY;
        this.rectX = 1200 / 2;          //先預設正中間
        this.rectY = 750 / 2;
        this.posX = this.rectX;
        this.posY = this.rectY;
    }

    public abstract void changeImg();
}