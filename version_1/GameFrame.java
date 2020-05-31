package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.Math; 
import java.util.Timer; 
import java.util.TimerTask; 
import java.awt.image.BufferedImage;
import java.security.*;

public class GameFrame extends JFrame implements KeyListener{

    public Timer timer;                 
    public int level;
    public int intervel = 10000 / 10000; //每intervel個微秒就repaint
    public ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();  //weapon用容器裝
    public ArrayList<Enemy> EnemyList = new ArrayList<Enemy>(); //enemy也用容器裝
    public Hero testC;      //一定要先宣告一下，不然KeyListener不給過，但是可以在initial再寫

    public GameFrame(){
        super("GameFrame");
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        addKeyListener(this);
        addMouseListener(new MouseAdapterDemo());
        initial();          //設定關卡初始狀況，包含怪獸的位置和英雄的速度、初始位置
        working();          //設定timertask，讓程式定期移動腳色和檢查生命
    }

    public void working(){
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){      //檢查位置
                Weaponhit();
                repaint();          //重畫角色的位置
            }
        }, 10, 10);        //每個微秒就重複一次

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                for(int i = 0;i < EnemyList.size();i++)    //讓enemy往hero移動
                {                        
                    EnemyList.get(i).posX += EnemyList.get(i).speedX;
                    EnemyList.get(i).posY += EnemyList.get(i).speedY;
                    EnemyList.get(i).move(EnemyList.get(i).posX,EnemyList.get(i).posY,testC.posX+(testC.width/2),testC.posY+(testC.height/2),2);
                }
            }
        }, 100, 100);                   //每0.1秒就重複一次

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkState();        //檢查所有生命
            }
        }, 500, 500);                   //每0.5秒就重複一次
    }

    public void checkState(){
        testC.changeImg();              //讓角色的腳可以移動，呈現動畫的感覺
        for(int i = 0;i<EnemyList.size();i++)
        {
            testC.setHp(EnemyList.get(i));      //檢查hero血量
        }
    }
    
    public void initial(){
        testC = new Hero(5, 5);
        int total = 10;                  //total of enemy
        SecureRandom rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        System.out.println("range is "+range);
        for(int i = 0;i < total;i++)        //巧恩晚點改，敵人不知道為什麼只有一隻
        {
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*500+300);
            // System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= 1000)          //應該要是1200-width，我先把width的最大值預設成200
                x = 1000;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= 550)          //應該要是750-height，我先把width的最大值預設成200
                y = 550;
            System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(x,y,testC.posX,testC.posY,2);
            EnemyList.add(virus);
        }
    }

    public void update(Graphics g) { 
        this.paint(g); 
    }
    
    public void paint(Graphics g){
        // System.out.println("test");
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();
        big.drawImage(new ImageIcon("routemap2020.png").getImage(), 0, 0, null);    //重複畫背景
		big.drawImage(testC.img.get(0), testC.posX, testC.posY, testC.width, testC.height,null);    //畫hero本身
        big.drawImage(testC.blood.get(0),testC.posX, testC.posY+15,testC.width,50,null);            //畫hero的血條

        for(int i = 0;i<EnemyList.size();i++)
        {
            //正常移動(還沒有寫被子彈打中的消失部分)
            big.drawImage(EnemyList.get(i).img.get(0), EnemyList.get(i).posX, EnemyList.get(i).posY, EnemyList.get(i).width, EnemyList.get(i).height, null);
        }

		for(int i = 0; i<WeaponList.size(); i++){
            if(WeaponList.get(i).overScreen()){         //Weapon超出螢幕就移出陣列且不印出來
            	WeaponList.remove(i);
            	System.out.println("remove"+ i);
            }
            else{                                      //Weapon如果沒超出螢幕就更改位置後畫出來
                WeaponList.get(i).posX += WeaponList.get(i).speedX; 
                WeaponList.get(i).posY += WeaponList.get(i).speedY;
                // System.out.println("posX is "+ WeaponList.get(i).posX+" and posY is "+ WeaponList.get(i).posY); 
		        big.drawImage(WeaponList.get(i).img.get(0), WeaponList.get(i).posX, WeaponList.get(i).posY, WeaponList.get(i).width, WeaponList.get(i).height,null);
            }

        }

        g.drawImage(bi, 0, 0, null);
    }

    public Image ChooseBackGround(int level){
        return new ImageIcon("routemap2020.png").getImage();
    }

    @Override
    public void keyReleased(KeyEvent e){
        testC.moveFlag = -1;            
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP) {        
            move(1);
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if(key == KeyEvent.VK_DOWN){        //上下的圖片先暫時跟原本(僅限左右)的相反     
            move(2);
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if( key == KeyEvent.VK_LEFT ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            move(3);        
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_RIGHT ){
            move(4);
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        // Move the Circle

        else if( key == KeyEvent.VK_W ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            move(1);
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_S ){        //上下的圖片先暫時跟原本(僅限左右)的相反      
            move(2);
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_A ){
            move(3);
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_D){            
            move(4);
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        else
            testC.moveFlag = -1;
    }

    public void move(int event){        //如果放在hero裡面會無法找到(hero比較晚被編譯到)
        // System.out.println("posX is "+testC.posX+" and posY is "+testC.posY);
        switch(event){
        case 1:             //上
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            break;
        case 2:             //下
            if(testC.posY<=550)         //750-testC.height
                testC.posY += testC.speedY;
            break;
        case 3:             //左
            if(testC.posX>=0)
                testC.posX -= testC.speedX;
            break;
        case 4:             //右
            if(testC.posX<=1000)         //1200-testC.width
                testC.posX += testC.speedX;
            break;
        }

        for(int i = 0; i<EnemyList.size();i++)
        {
            testC.setHp(EnemyList.get(i));
        }
    }

    public class MouseAdapterDemo extends MouseAdapter {
			
        public void mousePressed(MouseEvent event) {
            // System.out.println("X is "+event.getX()+" and Y is "+event.getY());

            // 10 代表斜向速度
            Weapon tmpWeapon = new Weapon(testC.posX+testC.width/2, testC.posY+testC.height/2, event.getX(), event.getY(), 5);
            WeaponList.add(tmpWeapon);
        }
    }
     public void Weaponhit(){
            for (int j = 0; j < WeaponList.size(); j++) {
                
            Rectangle weaponRect = new Rectangle(WeaponList.get(j).posX+WeaponList.get(j).width/2,WeaponList.get(j).posY+WeaponList.get(j).height/2,50,50);
            Rectangle rect =null;
            for (int i = 0; i < EnemyList.size(); i++) {
                Enemy enemy = EnemyList.get(i);                
                rect = new Rectangle(enemy.posX+enemy.width/2,enemy.posY+enemy.height/2,30,30);
                //碰撞檢測
                if(weaponRect.intersects(rect)){
                    WeaponList.remove(j);
                    EnemyList.get(i).hp -= 1;
                    //System.out.println("hit");
                }
                if(EnemyList.get(i).hp<=0){
                    EnemyList.remove(i);
                }
            }
            
            }
            
    }
}