package covid19.game;

import java.awt.*;

public abstract class Virus{
    public int full, hp, atk;
    public int distanceX, distanceY;  //和主角的距離
    public int initX, initY; //初始生成位置
    public int nowX, nowY;  //現在所在位置
    public int width, height; //圖片大小
    public int moveX, moveY;    //移動方向
    public Image img, blood; //放入圖片和血條


    //建構子建構怪物出現位置
    public Virus(int initX,int initY)
    {
        this.initX = initX;
        this.initY = initY;
        this.nowX = initX;
        this.nowY = initY;
    }

    //計算小怪和角色的距離
    public void setMoveDistance(int characterPlaceX,int characterPlaceY)
    {
        distanceX = nowX - characterPlaceX;
        distanceY = nowY - characterPlaceY;
    }
}