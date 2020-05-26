package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Hero extends Character{

    String[][] Src = {{"hero_1.png", "hero_2.png", "hero_3.png", "hero_4.png"}, {"hero_5.png", "hero_6.png", "hero_7.png", "hero_8.png"}};
    
    public Hero(int sX, int sY, int rX, int rY){
        super(sX, sY, rX, rY);
        this.width = 200;
        this.height = 200;
        srcFlag = 0;
        moveFlag = -1;
        img = new ImageIcon("hero_1.png").getImage();
    }

    public Hero(int sX, int sY){
        super(sX, sY);
        this.width = 200;
        this.height = 200;
        srcFlag = 0;
        moveFlag = -1;
        img = new ImageIcon("hero_1.png").getImage();
    }

    @Override
    public void changeImg(){                //跑動畫
        if(moveFlag>=0){           
            // System.out.println("in 33 line in Hero"); 
            String src = Src[moveFlag][srcFlag];
            img = new ImageIcon(src).getImage();
            if(srcFlag==3)
                srcFlag = 0;
            else
                srcFlag++;
        }
    }

    public boolean hit(String dir){
            Rectangle myrect = new Rectangle(posX,posY,30,30);
            Rectangle rect =null;
            //for (int i = 0; i < eneryList.size(); i++) {
             //   Enery enery = eneryList.get(i);
                if(dir.equals("Left")){
                    rect = new Rectangle(100+2,100,30,30);
                }
                else if(dir.equals("Right")){
                    rect = new Rectangle(100-2,100,30,30);
                }
                else if(dir.equals("Up")){
                    rect = new Rectangle(100,100+1,30,30);
                }else if(dir.equals("Down")){
                    rect = new Rectangle(100,100-2,30,30);
                }
                //碰撞檢測
                if(myrect.intersects(rect)){
                    return true;
                }
            //}
            return false;
    }

    public void check(){
        System.out.println("speedX is "+speedX+" while speedY is "+speedY);
        System.out.println("rectX is "+rectX+" while rectY is "+rectY);
        System.out.println("posX is "+posX+" while posY is "+posY);
    }
}