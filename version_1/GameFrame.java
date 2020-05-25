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
                // checkState();        //檢查所有生命和碰撞和enemy位置
                repaint();
            }
        }, intervel, intervel);
    }

    public void initial(){
        testC = new Hero(20, 20);
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
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP)
            move(1);
        if(key == KeyEvent.VK_DOWN)
            move(2);
        if( key == KeyEvent.VK_LEFT )
            move(3);        
        if( key == KeyEvent.VK_RIGHT )
            move(4);
        // Move the Circle

        if( key == KeyEvent.VK_W )
            move(1);
        
        if( key == KeyEvent.VK_S )
            move(2);
        
        if( key == KeyEvent.VK_A )
            move(3);

        if( key == KeyEvent.VK_D)
            move(4);
    }

    public void move(int event){        //如果放在hero裡面會無法找到(hero比較晚被編譯到)
        switch(event){
            case 1:             //上
                testC.posY -= testC.speedY;
                break;
            case 2:             //下
                testC.posY += testC.speedY;
                break;
            case 3:             //左
                testC.posX -= testC.speedX;
                break;
            case 4:             //右
                testC.posX += testC.speedX;
                break;
        }
    }
}

