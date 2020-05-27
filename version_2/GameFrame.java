package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Timer; 
import java.util.TimerTask; 
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame implements KeyListener{

    public Timer timer;                 
    public int level;
    public int intervel = 10000 / 1000; //每intervel個微秒就repaint
    public Character testC;      //一定要先宣告一下，不然KeyListener不給過，但是可以在initial再寫
    public Virus virus;

    public GameFrame(){
        super("GameFrame");
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        addKeyListener(this);
        initial();
        working();
    }

    public void working(){
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                repaint();
            }
        }, intervel, intervel);
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkState();        //檢查所有生命和碰撞和enemy位置
            }
        }, 100, 100);                   //每0.5秒就重複一次
    }

    public void checkState(){
        testC.changeImg();
    }
    
    public void initial(){
        testC = new Hero(5, 5);
        testC.changeImg();
    }

    public void update(Graphics g) { 
        this.paint(g); 
    }
    
    public void paint(Graphics g){
        // System.out.println("test");
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();
        big.drawImage(new ImageIcon("routemap2020.png").getImage(), 0, 0, null);
		big.drawImage(testC.img, testC.posX, testC.posY, testC.width, testC.height,null);

		// if(testC.posX>400||testC.posY>400){
		// 	eneryList.remove(i);
		// 	System.out.println("remove"+ i);
		// }

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
        System.out.println("posX is "+testC.posX+" and posY is "+testC.posY);
        switch(event){
        case 1:             //上
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            break;
        case 2:             //下
            if(testC.posY<=550)
                testC.posY += testC.speedY;
            break;
        case 3:             //左
            if(testC.posX>=0)
                testC.posX -= testC.speedX;
            break;
        case 4:             //右
            if(testC.posX<=1000)
                testC.posX += testC.speedX;
            break;
        }
    }
}