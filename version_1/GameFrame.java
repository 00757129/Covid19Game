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

public class GameFrame extends JFrame implements KeyListener,ActionListener{
    int t=0;
    public Timer timer;      
    public int enemyIndex = 0;               
    public int level;
    public SecureRandom rand = new SecureRandom();
    public int start=0;
    public int intervel = 1000000 / 100000; //每intervel個微秒就repaint
    public ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();  //weapon用容器裝
    public ArrayList<Enemy> EnemyList = new ArrayList<Enemy>(); //enemy也用容器裝
    public Hero testC = new Hero(screenSizeX(5), screenSizeY(5), screenSize(200), screenSize(200));      //一定要先宣告一下，不然KeyListener不給過，但是可以在initial再寫
    public ArrayList<Place> placeRect = new ArrayList<Place>();    //裝各地點位置的arraylist
    public int playerChoice;
    public Icon bGImage =new ImageIcon("covid-19.jpg");
    public Image backGroundImage =new ImageIcon("covid-19.jpg").getImage();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int backGroundImageHeight = (int)screenSize.getHeight();
    int backGroundImageWidth = (int)screenSize.getWidth();
    int correct=0;
    int uncorrect=0;
    int qNum = 3;
    // int backGroundImageHeight = backGroundImageHeight;
    // int backGroundImageWidth = 1200;
    boolean end;
    JRadioButton ans1;
    JRadioButton ans2; 
    JRadioButton ans3;
    JRadioButton ans4;
    public JLabel label,introductionlabel,bgLabel;  public JPanel mainJpanel,labelPanel,levelOnePanel,levelTwoPanel,levelThreePanel,introductionPanel,imagePanel; 
    public JButton levelOneButton,levelTwoButton,levelThreeButton,introductionButton,returnbutton,introBut; 
    


    public GameFrame(){
        super("GameFrame");
        setLayout(null); 
        setLayout(new BorderLayout());
        
        setLocationRelativeTo(null);
        //addKeyListener(this);
        //addMouseListener(new MouseAdapterDemo());
        SetStart();

        //initial();          //設定關卡初始狀況，包含怪獸的位置和英雄的速度、初始位置
        //working();          //設定timertask，讓程式定期移動腳色和檢查生命
    }

    public void initial(){
        backGroundImage=new ImageIcon("routemap2020.png").getImage();
        end = false;
        correct = 0;
        enemyIndex = 0;
        level = 0;
        testC = new Hero(screenSizeX(5), screenSizeY(5), screenSize(200), screenSize(200));
        setQuestionPlace1();
        int total = 10;                  //total of enemy
        rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        // //System.out.println("range is "+range);
        for(int i = 0; i<10; i++){
            rand = new SecureRandom();
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
            // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                x = backGroundImageWidth-testC.width;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                y = backGroundImageHeight-testC.height;
            // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
            EnemyList.add(virus);
            enemyIndex++;
        }
        
        // t=0;
        // JOptionPane.showMessageDialog(this,"武漢病毒介紹......","發現新怪物", JOptionPane.PLAIN_MESSAGE,new ImageIcon("covid.png"));
    }

    public void initial_2(){
        correct = 0;
        end = false;
        level = 1;
        enemyIndex = 0;
        //System.out.println("inital_2");
        testC = new Hero(screenSizeX(5), screenSizeY(5), screenSize(200), screenSize(200));
        setQuestionPlace2();
        backGroundImage =new ImageIcon("taiwan.jpg").getImage();
        int total = 2;
        rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        for(int i = 0; i<total; i++){
            rand = new SecureRandom();
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
            // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                x = backGroundImageWidth-testC.width;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                y = backGroundImageHeight-testC.height;
            // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(1, x,y,testC.posX,testC.posY,2,6,0,screenSize(120),screenSize(120));
            EnemyList.add(virus);
            enemyIndex++;
        } t=0;
    }

     public void initial_3(){
        correct = 0;
        end = false;
        level = 2;           //暫定     
        testC = new Hero(screenSizeX(5), screenSizeY(5), screenSize(200), screenSize(200));
        setQuestionPlace3();
        backGroundImage =new ImageIcon("worldmap.jpg").getImage();
        int total = 1;
        rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        for(int i = 0; i<total; i++){
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
            // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                x = backGroundImageWidth-testC.width;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                y = backGroundImageHeight-testC.height;
            // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(2, x,y,testC.posX,testC.posY,2,12,0,screenSize(350),screenSize(350));
            EnemyList.add(virus);
            enemyIndex++;
        } t=0;
    }

    public void working(){
                
        JLabel jlb = new JLabel("",SwingConstants.CENTER);
        JLabel jlb6 = new JLabel("<遊戲小提醒>");
        JLabel jlb7 = new JLabel("遊戲地圖裡藏了六個問題，要回答對三題題目才能過關喔~");
        JLabel jlb1 = new JLabel("<怪物介紹> 怪物名：武漢病毒");
        JLabel jlb2 = new JLabel("武漢病毒是一種由嚴重急性呼吸系統綜合徵冠狀病毒2引發的傳染病。");
        JLabel jlb3 = new JLabel("該病首名病人2019年末於中華人民共和國湖北省武漢市確診，");
        JLabel jlb4 = new JLabel("其後此病在全球各國大規模爆發並急速擴散。");
        JLabel jlb5 = new JLabel("是個不好好處理就會一直冒出來的麻煩呢！");
        int width = 200,height = 200;
        ImageIcon image = new ImageIcon("covid.png");
        image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        jlb.setIcon(image);
        jlb.setSize(width, height);
        

        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){ 
                if((end==false) && (level==0)){     //檢查位置
                Weaponhit();
                repaint();          //重畫角色的位置
                questionevent();
                 }
            }
        }, intervel, intervel);        //每個微秒就重複一次
        if(t==0){JOptionPane.showMessageDialog(null,new Object[]{jlb, jlb6, jlb7, jlb1,jlb2, jlb3, jlb4, jlb5},"遊戲前的小提醒：發現新怪物-武漢肺炎病毒！",JOptionPane.DEFAULT_OPTION);t=1;}
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(((end==false) && (level==0))){
                for(int i = 0;i < EnemyList.size();i++)    //讓enemy往hero移動
                {                        
                    EnemyList.get(i).posX += EnemyList.get(i).speedX;
                    EnemyList.get(i).posY += EnemyList.get(i).speedY;
                    EnemyList.get(i).move(EnemyList.get(i).posX,EnemyList.get(i).posY,testC.posX+(testC.width/2),testC.posY+(testC.height/2),2);
                }
                }
            }
        }, 100, 100);                   //每0.1秒就重複一次

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(((end==false) && (level==0))){
                testC.changeImg();       
                for(int i = 0;i < EnemyList.size();i++)    //讓enemy往hero移動
                {                        
                    EnemyList.get(i).changeImg(1);
                }
                 }
            }
        }, 200, 200);                   //每0.1秒就重複一次


        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(((end==false) && (level==0))){
                checkState();        //檢查所有生命
                }
            }

        }, 500, 500);                   //每0.5秒就重複一次
        

        timer.schedule(new TimerTask(){         //定時增加怪物
            @Override       
            public void run(){  
                if(((end==false) && (level==0))){                                  
                    int total = 5;
                    rand = new SecureRandom();
                    double range = (3.141515926 * 2) / total;
                    rand = new SecureRandom();
                    double angle = rand.nextDouble()*(range) + range*enemyIndex;
                    rand = new SecureRandom();
                    int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
                    // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
                    int x = (int)(length*Math.sin(angle));
                    x = testC.posX + x;
                    if(x <= 0)
                        x = 0;
                    else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                        x = backGroundImageWidth-testC.width;
                    int y = (int)(length*Math.cos(angle));
                    y = testC.posY + y;
                    if(y <= 0)
                        y = 0;
                    else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                    y = backGroundImageHeight-testC.height;
                    // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
                    Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
                    EnemyList.add(virus);
                    enemyIndex++;
                   // //System.out.println(end);
                }
                
            }
        }, 3000, 3000);                   //每0.5秒就重複一次
    }

    public void working2(){
        JLabel jlb = new JLabel("",SwingConstants.CENTER);
        JLabel jlb6 = new JLabel("<遊戲小提醒>");
        JLabel jlb7 = new JLabel("遊戲地圖裡藏了六個問題，要回答對三題題目才能過關喔~");        
        JLabel jlb1 = new JLabel("<怪物介紹> 怪物名：咳嗽的阿伯");
        JLabel jlb2 = new JLabel("阿伯總覺得什麼大風大浪他沒見過，一點小小的病毒才不會嚇到他呢！");
        JLabel jlb3 = new JLabel("最近開始莫名的發燒，還有咳嗽症狀，感覺狀況有點不妙呢...");
        JLabel jlb4 = new JLabel("每隔一段時間阿伯就會扣三滴生命值，召喚更多病毒怪物。");
        int width = 200,height = 200;
        ImageIcon image = new ImageIcon("boss2.png");
        image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        jlb.setIcon(image);
        jlb.setSize(width, height);
        
        
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){      //檢查位置
            if(((end==false) && (level==1))){
                Weaponhit();
                repaint();          //重畫角色的位置
                questionevent();
            }
            }
        }, intervel, intervel);        //每個微秒就重複一次
        if(t==0){JOptionPane.showMessageDialog(null,new Object[]{jlb, jlb6, jlb7, jlb1, jlb2, jlb3, jlb4},"遊戲前的小提醒：發現新怪物-咳嗽的阿伯！",JOptionPane.DEFAULT_OPTION);t=1;}
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
                testC.changeImg();       
                for(int i = 0;i < EnemyList.size();i++)    //讓enemy往hero移動
                {                        
                    EnemyList.get(i).changeImg(level);
                }
            }
        }, 200, 200);                   //每0.1秒就重複一次


        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkState();        //檢查所有生命
            }
        }, 200, 200);                   //每0.5秒就重複一次

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                //花三點血召喚
                int original = EnemyList.size();
                for(int i = 0;i<original;i++)
                {
                    if(EnemyList.get(i).hp>3)
                    {
                        EnemyList.get(i).hp-=3;
                        EnemyList.get(i).setHp(3,level);

                        rand = new SecureRandom();
                        double range = (3.141515926 * 2) / 3;
                        for(int j = 0;j<3;j++)
                        {
                            enemyIndex++;
                            double angle = rand.nextDouble()*(range) + range*j;
                            rand = new SecureRandom();
                            int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
                            // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
                            int x = (int)(length*Math.sin(angle));
                            x = testC.posX + x;
                            if(x <= 0)
                                x = 0;
                            else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                                x = backGroundImageWidth-testC.width;
                            int y = (int)(length*Math.cos(angle));
                            y = testC.posY + y;
                            if(y <= 0)
                                y = 0;
                            else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                                y = backGroundImageHeight-testC.height;
                            // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
                            Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
                            EnemyList.add(virus);
                            enemyIndex++;
                        }
                    }
                }
            }
        },4500,4500);        //每5秒重複一次

        timer.schedule(new TimerTask(){         //定時增加怪物
            @Override       
            public void run(){  
                if(((end==false) && (level==1))){              
                    int total = 5;
                    rand = new SecureRandom();
                    double range = (3.141515926 * 2) / total;
                    rand = new SecureRandom();
                    double angle = rand.nextDouble()*(range) + range*enemyIndex;
                    rand = new SecureRandom();
                    int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
                    // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
                    int x = (int)(length*Math.sin(angle));
                    x = testC.posX + x;
                    if(x <= 0)
                        x = 0;
                    else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                        x = backGroundImageWidth-testC.width;
                    int y = (int)(length*Math.cos(angle));
                    y = testC.posY + y;
                    if(y <= 0)
                        y = 0;
                    else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                        y = backGroundImageHeight-testC.height;
                    // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
                    Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
                    EnemyList.add(virus);
                    enemyIndex++;
                }
            }
        }, 4500, 4500);                 //每3秒就重複一次
    }

    public void working3(){
         JLabel jlb = new JLabel("",SwingConstants.CENTER);
        JLabel jlb6 = new JLabel("<遊戲小提醒>");
        JLabel jlb7 = new JLabel("遊戲地圖裡藏了六個問題，要回答對三題題目才能過關喔~");
        JLabel jlb1 = new JLabel("<怪物介紹> 怪物名：武漢病毒");
        JLabel jlb2 = new JLabel("鄰居當中熱心助人的大媽阿姨，不畏被遭受感染的風險，每天依舊去不同人家裡打麻將。");
        JLabel jlb3 = new JLabel("最近的新興趣是到處聽可能會不夠的物資，然後想辦法囤貨");
        JLabel jlb4 = new JLabel("家裡的物資已經堆得跟山一樣高了，大媽每隔一段時間就會扣生命值，召喚出更多病毒");
        int width = 200,height = 200;
        ImageIcon image = new ImageIcon("boss3.png");
        image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        jlb.setIcon(image);
        jlb.setSize(width, height);
        

        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){      //檢查位置
                Weaponhit();
                repaint();          //重畫角色的位置
                questionevent();
                  
            }
        }, intervel, intervel);        //每個微秒就重複一次
        if(t==0){JOptionPane.showMessageDialog(null,new Object[]{jlb, jlb6, jlb7, jlb1,jlb2, jlb3, jlb4},"遊戲前的小提醒：發現新怪物-愛囤貨的大媽！",JOptionPane.DEFAULT_OPTION);t=1;}
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
                testC.changeImg();       
                for(int i = 0;i < EnemyList.size();i++)    
                {                        
                    EnemyList.get(i).changeImg(level);
                }
            }
        }, 200, 200);                   //每0.1秒就重複一次


        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkState();        //檢查所有生命
            }
        }, 300, 300);                   //每0.5秒就重複一次

        //不知道為甚麼進不去這裡面，但是下面進得去
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                //System.out.println("in");
                if(EnemyList.get(0).hp>4)
                {
                    //System.out.println("in");
                     //大媽減2血量並讓敵人加速
                    EnemyList.get(0).hp-=2;
                    EnemyList.get(0).setHp(2,level);

                    for(int i = 0;i<EnemyList.size();i++)
                    {
                        EnemyList.get(i).changeSpeed(EnemyList.get(i).posX,EnemyList.get(i).posY,testC.posX,testC.posY,EnemyList.get(i).speed+=8);
                    }
                    
                    //大媽血量減2召喚
                    EnemyList.get(0).hp-=2;
                    EnemyList.get(0).setHp(2,level);

                    int total = 2;
                    rand = new SecureRandom();
                    double range = (3.141515926 * 2) / total;
                    rand = new SecureRandom();
                    double angle = rand.nextDouble()*(range) + range*enemyIndex;
                    rand = new SecureRandom();
                    int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
                    // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
                    int x = (int)(length*Math.sin(angle));
                    x = testC.posX + x;
                    if(x <= 0)
                        x = 0;
                    else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                        x = backGroundImageWidth-testC.width;
                    int y = (int)(length*Math.cos(angle));
                    y = testC.posY + y;
                    if(y <= 0)
                        y = 0;
                    else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                        y = backGroundImageHeight-testC.height;
                    // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
                    Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
                    EnemyList.add(virus);
                    enemyIndex++;
                }
                
            }
        },3000,3000);

        timer.schedule(new TimerTask(){         //定時增加怪物
            @Override       
            public void run(){                
                //System.out.println("in2");
                if(((end==false) && (level==2))){
                    int total = 5;
                    rand = new SecureRandom();
                    double range = (3.141515926 * 2) / total;
                    rand = new SecureRandom();
                    double angle = rand.nextDouble()*(range) + range*enemyIndex;
                    rand = new SecureRandom();
                    int length = (int)(rand.nextDouble()*screenSize(500)+screenSize(300));
                    // //System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
                    int x = (int)(length*Math.sin(angle));
                    x = testC.posX + x;
                    if(x <= 0)
                        x = 0;
                    else if(x >= backGroundImageWidth-testC.width)          //應該要是backGroundImageWidth-width，我先把hero的width的最大值預設成200
                        x = backGroundImageWidth-testC.width;
                    int y = (int)(length*Math.cos(angle));
                    y = testC.posY + y;
                    if(y <= 0)
                        y = 0;
                    else if(y >= backGroundImageHeight-testC.height)          //應該要是backGroundImageHeight-height，我先把hero的height的最大值預設成200
                        y = backGroundImageHeight-testC.height;
                    // //System.out.println("in number."+i+" x is "+x+" and y is "+y);
                    Enemy virus = new Enemy(0, x,y,testC.posX,testC.posY,2,1,0,screenSize(50),screenSize(50));
                    EnemyList.add(virus);
                    enemyIndex++;
                }
            }
        }, 4500, 4500);                   //每3秒就重複一次
    }

    public void checkState(){
        //testC.changeImg();              //讓角色的腳可以移動，呈現動畫的感覺
        for(int i = 0;i<EnemyList.size();i++)
        {
            
            testC.setHp(EnemyList.get(i));      //檢查hero血量
        }
    }
    
    public void SetStart(){
        /*
        bGImage =new ImageIcon("covid-19.jpg");
        bgLabel = new JLabel(bGImage);      // 把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, backGroundImageWidth, backGroundImageHeight);    // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));     // 把背景圖添加到分層窗格的最底層以作為背景
        */
        bGImage =new ImageIcon("bullet.png");
       mainJpanel=new JPanel();introductionPanel=new JPanel();
       mainJpanel.setLayout(null);introductionPanel.setLayout(null);
       introductionlabel=new JLabel("introduction......");introductionlabel.setBounds(screenSizeX(500),screenSizeX(50),100,40); 
        label=new JLabel("COVID-19");label.setBounds(screenSizeX(500),screenSizeX(50),100,40); 
        levelOneButton=new JButton("1.捷運大逃生");levelOneButton.setBounds(screenSizeX(500),screenSizeY(150),screenSizeX(100),screenSizeY(40)); 
        levelTwoButton=new JButton("2.快樂台灣");levelTwoButton.setBounds(screenSizeX(500),screenSizeY(250),screenSizeX(100),screenSizeY(40));
        levelThreeButton=new JButton("3.病毒世界");levelThreeButton.setBounds(screenSizeX(500),screenSizeY(350),screenSizeX(100),screenSizeY(40)); 
        introductionButton=new JButton("Intrduction");introductionButton.setBounds(screenSizeX(500),screenSizeY(450),screenSizeX(100),screenSizeY(40)); 
        returnbutton=new JButton("Return");returnbutton.setBounds(screenSizeX(500),screenSizeY(450),screenSizeX(100),screenSizeY(40)); 
////////////////////////////介紹圖
        bGImage =new ImageIcon("bullet.png");
        introBut=new JButton(bGImage);introBut.setBounds(screenSizeX(300),screenSizeY(100),screenSizeX(400),screenSizeY(300)); 
//////////////////////////

        levelOneButton.addActionListener(this);levelTwoButton.addActionListener(this);levelThreeButton.addActionListener(this);introductionButton.addActionListener(this);
        introductionButton.addActionListener(this);returnbutton.addActionListener(this);
        //mainJpanel.add(imagePanel);
        
        mainJpanel.add(levelOneButton);mainJpanel.add(levelTwoButton);mainJpanel.add(levelThreeButton);mainJpanel.add(introductionButton);mainJpanel.add(label);
        //this.getLayeredPane().add(introductionlabel, new Integer(Integer.MAX_VALUE)); 
        introductionPanel.add(returnbutton);introductionPanel.add(introductionlabel);introductionPanel.add(introBut);
        getContentPane().add(mainJpanel, BorderLayout.CENTER);
        mainJpanel.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);    
       

    }

    public int screenSizeX(int x){
        x = (int)(x*backGroundImageWidth/1200);
        return x;
    }

    public int screenSizeY(int y){
        y = (int)(y*backGroundImageHeight/750);
        return y;
    }

    public int screenSize(int tmp){
        double original = Math.pow(Math.pow(750, 2)+Math.pow(1200, 2), 0.5);
        double now = Math.pow(Math.pow(backGroundImageWidth, 2)+Math.pow(backGroundImageHeight, 2), 0.5);
        tmp = (int)(tmp*now/original);
        return tmp;
    }
    
    public void update(Graphics g) { 
        //this.paint(g); 
    }
    
    public void paint(Graphics g){
        // //System.out.println("test");
        if(start==0){g.drawImage(new ImageIcon("covid-19.jpg").getImage(),0,0, backGroundImageWidth, backGroundImageHeight, null); 
        levelOneButton.requestFocus();levelTwoButton.requestFocus();levelThreeButton.requestFocus();label.requestFocus();introductionButton.requestFocus();mainJpanel.requestFocus();
            //introductionPanel.requestFocus();introductionlabel.requestFocus();
            return;}else if(start==2){introductionButton.requestFocus();introBut.requestFocus();introductionPanel.requestFocus();}
            else if(start==3){//System.out.println("paint");//g.drawImage(new ImageIcon("covid-19.jpg").getImage(),0,0, backGroundImageWidth, backGroundImageHeight, null); 
        levelOneButton.requestFocus();levelTwoButton.requestFocus();levelThreeButton.requestFocus();label.requestFocus();introductionButton.requestFocus();mainJpanel.requestFocus();}
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();
        big.drawImage(backGroundImage,0,0, backGroundImageWidth, backGroundImageHeight, null);    //重複畫背景
        /////(posX+width/2,posY+height/2-screenSizeY(15),width/2,height/2);
        //big.drawRect(testC.posX+testC.width/2-screenSizeY(35), testC.posY+testC.height/2-screenSizeY(50), testC.width/2-screenSizeY(20), testC.height/2+screenSizeY(10));
        ////
		big.drawImage(testC.img.get(0), testC.posX, testC.posY, testC.width, testC.height,null);    //畫hero本身
        big.drawImage(testC.blood.get(0),testC.posX, testC.posY+20,testC.width,50,null);            //畫hero的血條

        for(int i = 0;i<EnemyList.size();i++)
        {
            //正常移動(還沒有寫被子彈打中的消失部分)  enemy.posX+enemy.width/2, enemy.posY+enemy.height/2+screenSizeY(100), enemy.width-screenSizeX(100), enemy.height-screenSizeX(200)
            //big.drawRect(EnemyList.get(i).posX+EnemyList.get(i).width/2-screenSizeY(25),EnemyList.get(i).posY+EnemyList.get(i).height/2-screenSizeY(25),EnemyList.get(i).width-EnemyList.get(i).width/3+screenSizeY(10),EnemyList.get(i).height-EnemyList.get(i).height/3+screenSizeY(10));
            big.drawImage(EnemyList.get(i).img.get(0), EnemyList.get(i).posX, EnemyList.get(i).posY, EnemyList.get(i).width, EnemyList.get(i).height, null);
            
            if(EnemyList.get(i).type == 0)
                big.drawImage(EnemyList.get(i).blood.get(0),EnemyList.get(i).posX,EnemyList.get(i).posY-30,EnemyList.get(i).width,50,null);
            else
                big.drawImage(EnemyList.get(i).blood.get(0),EnemyList.get(i).posX,EnemyList.get(i).posY-5,EnemyList.get(i).width,50,null);
        }

		for(int i = 0; i<WeaponList.size(); i++){
            if(WeaponList.get(i).overScreen()){         //Weapon超出螢幕就移出陣列且不印出來
            	WeaponList.remove(i);
            	//  //System.out.println("remove"+ i);
            }
            else{                                      //Weapon如果沒超出螢幕就更改位置後畫出來
                // //System.out.println("paint weapon");
                WeaponList.get(i).posX += WeaponList.get(i).speedX; 
                WeaponList.get(i).posY += WeaponList.get(i).speedY;
                // //System.out.println("posX is "+ WeaponList.get(i).posX+" and posY is "+ WeaponList.get(i).posY); 
		        big.drawImage(WeaponList.get(i).img.get(0), WeaponList.get(i).posX, WeaponList.get(i).posY, WeaponList.get(i).width, WeaponList.get(i).height,null);
            }

        }

        g.drawImage(bi, 0, 0, null);
    }

    public Image ChooseBackGround(int level){
        return new ImageIcon("routemap2020.png").getImage();
    } 

    //public void actionPerformed()  迴車鍵與按下Button後之處理
     @Override
      public void actionPerformed(ActionEvent event)
      {
         
          //System.out.println(event.getActionCommand());
          if(event.getActionCommand().equals("Level 1")){
                removeAll();
                start=1;
                this.requestFocus();
                addKeyListener(this);
                addMouseListener(new MouseAdapterDemo());
                initial();
                working();
          }else if(event.getActionCommand().equals("Level 2")){
                removeAll();
                start=1;
                this.requestFocus();
                addKeyListener(this);
                addMouseListener(new MouseAdapterDemo());
                initial_2();
                working2();
          }else if(event.getActionCommand().equals("Level 3")){
                removeAll();
                start=1;
                this.requestFocus();
                addKeyListener(this);
                addMouseListener(new MouseAdapterDemo());
                initial_3();
                working3();
            
          }else if(event.getActionCommand().equals("Intrduction")){
                mainJpanel.setVisible(false);
                start=2;
                add(introductionPanel);
                //getContentPane().add(introductionPanel,  new Integer(Integer.MAX_VALUE));
                getContentPane().add(introductionPanel, BorderLayout.CENTER);
                introductionPanel.setVisible(true);
                SwingUtilities.updateComponentTreeUI(this);  
                //introductionPanel.requestFocus();  
                introductionButton.requestFocus();
                introBut.requestFocus();
                introductionPanel.requestFocus(); 
                this.requestFocus();

          }else if(event.getActionCommand().equals("Return")){
              introductionPanel.setVisible(false);
             
             start=0;
                getContentPane().add(mainJpanel, BorderLayout.CENTER);
                repaint();
                mainJpanel.setVisible(true);
                mainJpanel.requestFocus();
          }

      }       

    @Override
    public void keyReleased(KeyEvent e){
        testC.moveFlag = -1;            
    }

    @Override
    public void keyTyped(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP) {        
            //move(1);
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if(key == KeyEvent.VK_DOWN){        //上下的圖片先暫時跟原本(僅限左右)的相反     
            //move(2);
             if(testC.posY<=backGroundImageHeight-testC.height)         //backGroundImageHeight-testC.height
                testC.posY += testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if( key == KeyEvent.VK_LEFT ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            //move(3);   
            if(testC.posX>=0)
                testC.posX -= testC.speedX;     
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_RIGHT ){
            //move(4);
            if(testC.posX<=backGroundImageWidth-testC.width)         //backGroundImageWidth-testC.width
                testC.posX += testC.speedX;
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        // Move the Circle

        else if( key == KeyEvent.VK_W ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            //move(1);
            //System.out.println("in W");
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_S ){        //上下的圖片先暫時跟原本(僅限左右)的相反      
            //move(2);
            if(testC.posY<=backGroundImageHeight-testC.height)         //backGroundImageHeight-testC.height
                testC.posY += testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_A ){
            //move(3);
            if(testC.posX>=0)
                testC.posX -= testC.speedX;
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_D){            
            //move(4);
            if(testC.posX<=backGroundImageWidth-testC.width)         //backGroundImageWidth-testC.width
                testC.posX += testC.speedX;
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        else
            testC.moveFlag = -1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP) {        
            //move(1);
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if(key == KeyEvent.VK_DOWN){        //上下的圖片先暫時跟原本(僅限左右)的相反     
            //move(2);
             if(testC.posY<=backGroundImageHeight-testC.height)         //backGroundImageHeight-testC.height
                testC.posY += testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        else if( key == KeyEvent.VK_LEFT ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            //move(3);   
            if(testC.posX>=0)
                testC.posX -= testC.speedX;     
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_RIGHT ){
            //move(4);
            if(testC.posX<=backGroundImageWidth-testC.width)         //backGroundImageWidth-testC.width
                testC.posX += testC.speedX;
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        // Move the Circle

        else if( key == KeyEvent.VK_W ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            //move(1);
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_S ){        //上下的圖片先暫時跟原本(僅限左右)的相反      
            //move(2);
            if(testC.posY<=backGroundImageHeight-testC.height)         //backGroundImageHeight-testC.height
                testC.posY += testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_A ){
            //move(3);
            if(testC.posX>=0)
                testC.posX -= testC.speedX;
            testC.moveFlag = 0;
            testC.lastFlag = testC.moveFlag;
        }
        else if( key == KeyEvent.VK_D){            
            //move(4);
            if(testC.posX<=backGroundImageWidth-testC.width)         //backGroundImageWidth-testC.width
                testC.posX += testC.speedX;
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        else
            testC.moveFlag = -1;
    }

    public void move(int event){        //如果放在hero裡面會無法找到(hero比較晚被編譯到)
        // //System.out.println("posX is "+testC.posX+" and posY is "+testC.posY);
        switch(event){
        case 1:             //上
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            break;
        case 2:             //下
            if(testC.posY<=backGroundImageHeight-testC.height)         //750-testC.height
                testC.posY += testC.speedY;
            break;
        case 3:             //左
            if(testC.posX>=0)
                testC.posX -= testC.speedX;
            break;
        case 4:             //右
            if(testC.posX<=backGroundImageWidth-testC.width)         //backGroundImageWidth-testC.width
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
            // //System.out.println("X is "+event.getX()+" and Y is "+event.getY());

            // 10 代表斜向速度
            Weapon tmpWeapon = new Weapon(testC.posX+testC.width/2, testC.posY+testC.height/2, event.getX(), event.getY(), 50, 50, 5);
            // Weapon tmpWeapon = new Weapon(testC.posX+testC.width/2, testC.posY+testC.height/2, event.getX(), event.getY(), screenSizeX(50), screenSizeY(50), screenSize(5));
            WeaponList.add(tmpWeapon);
        }
    }

    public void setQuestionPlace1(){     //設定question觸發位置
        int [] placeX = {104, 210, 540, 616, 966, 396};
        int [] placeY = {376, 632, 478, 576, 609, 174};
        for(int i = 0;i < 6 ;i++)
        {            
            placeX[i] = screenSizeX(placeX[i]);
            placeY[i] = screenSizeY(placeY[i]);
            Place place = new Place(placeX[i],placeY[i],i);
            placeRect.add(place);
        }
    }

    public void setQuestionPlace2(){
        int [] placeX = {770,697,510,590,633,465};
        int [] placeY = {63,365,610,345,150,420};
        for(int i = 0;i<6;i++)
        {            
            placeX[i] = screenSizeX(placeX[i]);
            placeY[i] = screenSizeY(placeY[i]);
            Place place = new Place(placeX[i],placeY[i],i);
            placeRect.add(place);
        }
    }

    public void setQuestionPlace3(){
        int [] placeX = {980,445,93,410,1130,180};  //還沒算(幫你算~)
        int [] placeY = {260,270,178,150,450,525};   //還沒算(幫你算~)
        for(int i = 0;i<6;i++)
        {
            placeX[i] = screenSizeX(placeX[i]);
            placeY[i] = screenSizeY(placeY[i]);
            Place place = new Place(placeX[i],placeY[i],i);
            placeRect.add(place);
        }
    }

    public void setQuestionFrame(int no)    //設定no題的Joption
    {
        JLabel questionplace;
        JLabel questionPart1;
        JLabel questionPart2;
        JLabel questionPart3;
        ButtonGroup ansGroup = new ButtonGroup();
        RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
        int input;

        switch(no + 1)
        {
            case 1:
                questionPart1 = new JLabel("防疫期間很多人因為擔心身旁親友的安危，所以都會瘋狂轉傳很多訊息，當遇到下列訊息該怎麼做才最合適?");
                questionPart2 = new JLabel("驚傳！林口長庚收治多名疑似武漢肺炎病患，政府尚未公開說明。");
                questionPart3 = new JLabel("但是長庚醫院現在隨時處於馬上封院的情況，請呼籲親友不要隨便去林口長庚就診");
                ans1 = new JRadioButton("A.太可怕了，趕快轉傳給其他親朋好友");
                ans2 = new JRadioButton("B.先去假訊息查證中心或看政府新聞稿想辦法查證，並將查證的結果分享給轉傳訊息的人");
                ans3 = new JRadioButton("C.隨便啦~他們愛怎麼轉傳都不關我的事");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"捷運林口長庚醫院站",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 2:
                questionPart1 = new JLabel("防疫期間，進出醫院可以看到除了平常政府發放的醫療醫用口罩醫護人員都會戴N95口罩");
                questionPart2 = new JLabel("請問N95的95代表甚麼?");
                ans1 = new JRadioButton("A.可阻擋95%直徑0.3微米以上的非油性顆粒");
                ans2 = new JRadioButton("B.一片口罩台幣95元");
                ans3 = new JRadioButton("C.可阻擋95%直徑0.1微米以上的微生物與細菌");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);           
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);   
                qNum = 3;    
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3},"捷運亞東醫院站",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 3:
                questionPart1 = new JLabel("防疫期間，離開家裡都需要仔細的洗手");
                questionPart2 = new JLabel("請問下列何者為正確洗手方法?");
                ans1 = new JRadioButton("A.內外夾弓大立腕");
                ans2 = new JRadioButton("B.內外弓夾腕立大");
                ans3 = new JRadioButton("C.外內大力夾弓腕");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);    
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);    
                qNum = 3;          
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3},"捷運台北車站",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 4:
                questionPart1 = new JLabel("師大公館校區有名住宿生於3/31確診新型冠狀病毒，");
                questionPart2 = new JLabel("為確保不要有在宿舍與校區交叉感染的風險，校方要求與該名學生密切互動的13名師生進行居家隔離。");
                questionPart3 = new JLabel("請問下列哪個選項提及的防疫措施有要求 “完全禁止外出”");
                ans1 = new JRadioButton("A.居家檢疫");
                ans2 = new JRadioButton("B.居家檢疫、居家隔離");
                ans3 = new JRadioButton("C.居家隔離");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"捷運公館站",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(3);
                }
            break;

            case 5:
                questionPart1 = new JLabel("以往動物園在假日時總是人滿為患，大家都來這邊看動物");
                questionPart2 = new JLabel("請問在防疫期間，不推薦前往動物園的原因為何?");
                ans1 = new JRadioButton("A.動物有可能會被遊客感染再傳染給人類");
                ans2 = new JRadioButton("B.動物園人過多，怕會遭到其他旅客感染");
                ans3 = new JRadioButton("C.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3},"捷運動物園站",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 6:
                questionPart1 = new JLabel("防疫期間，各地觀光景點都有防疫標準，就連新北投的泡腳溫泉也不例外");
                questionPart2 = new JLabel("請問下列何者為防疫期間前往新北投需配合的事項?");
                ans1 = new JRadioButton("A.登記實名制");
                ans2 = new JRadioButton("B.戴口罩");
                ans3 = new JRadioButton("C.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3},"捷運新北投站",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(3);
                }
            break;
        }
    }

    public void setQuestionFrame2(int no)    //設定no題的Joption
    {
        JLabel questionplace;
        JLabel questionPart1;
        JLabel questionPart2;
        JLabel questionPart3;
        JLabel questionPart4;
        ButtonGroup ansGroup = new ButtonGroup();
        RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
        int input;

        switch(no + 1)
        {
            case 1:
                questionPart1 = new JLabel("郵輪是現今不少人出遊時的選擇，但因為郵輪的封閉性、可方便快速去多國觀光以及在海上漂泊等因素，");
                questionPart2 = new JLabel("因此郵輪在防疫期間成為各國政府的燙手山芋。");
                questionPart3 = new JLabel("防疫期間基隆港曾停靠了不少郵輪(如. 寶瓶星號等)");
                questionPart4 = new JLabel("請問關於台灣政府對郵輪防疫的政策下列何者正確?");
                ans1 = new JRadioButton("A.只有有被篩檢出病情的人才需要隔離");
                ans2 = new JRadioButton("B.只要有人被篩檢出病情，全船的人都需要在船上接受隔離");
                ans3 = new JRadioButton("C.隨便啦~都不用隔離");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,questionPart4,ans1,ans2,ans3},"基隆",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 2:
                questionPart1 = new JLabel("為配合防疫政策，遠雄海洋公園下修園區遊客最大乘載量50%，從原本的兩萬多人下修到一萬多人的限制人數。");
                questionPart2 = new JLabel("世界各地的遊樂園都因應疫情而有不同的防疫措施");
                questionPart3 = new JLabel("請問下列何者是遊樂園/海生館所會做的防疫措施?");
                ans1 = new JRadioButton("A.讓館藏企鵝在沒有人參觀的海生館參觀");
                ans2 = new JRadioButton("B.讓旅客坐雲霄飛車不要尖叫");
                ans3 = new JRadioButton("C.鬼屋內嚇人的鬼需要與旅客保持安全社交距離");
                ans4 = new JRadioButton("D.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);     
                ansGroup.add(ans4);      
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);  
                ans4.addItemListener(handlerRadioButtonSource);  
                qNum = 4;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3,ans4},"花蓮",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(4);
                }
            break;

            case 3:
                questionPart1 = new JLabel("清明連假期間墾丁大街湧入了大量的觀光客，讓不少專業醫療人員擔心此舉恐會成為防疫破口。");
                questionPart2 = new JLabel("在經歷此事後，墾丁觀光大街便實施了「人車分流大街徒步區」、「口罩臨檢」、「沿街宣導口罩、保持社交距離」");
                questionPart3 = new JLabel("等防疫措施。而1968App也將墾丁大街等觀光地點加入到人潮示警點。");
                questionPart4 = new JLabel("請問下列關於1968App的敘述何者正確?");
                ans1 = new JRadioButton("A.為交通專用的App路況發生問題時可觀看即時路況");
                ans2 = new JRadioButton("B.為防疫專用的App可查詢口罩剩餘數量");
                ans3 = new JRadioButton("C.為交通專用的App可查詢火車時刻表");
                ans4 = new JRadioButton("D.為防疫專用的App可觀看中央流行指揮中心直播");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ansGroup.add(ans4);    
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);  
                ans4.addItemListener(handlerRadioButtonSource); 
                qNum = 4;            
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,questionPart4,ans1,ans2,ans3,ans4},"屏東",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 4:
                questionPart1 = new JLabel("疫情衝擊餐飲觀光業，南投日月潭風景區旁的知名飯店景聖樓於五月第歇業，資遣24名員工。");
                questionPart2 = new JLabel("近日隨著疫情緩解，政府推出「安心旅遊國旅輔助方案」，從 7/1 至 10/31一周七天皆可申請輔助");
                questionPart3 = new JLabel("請問關於該輔助方案的敘述下列何者錯誤?");
                ans1 = new JRadioButton("A.團體旅遊優惠的本島行程和離島行程每晚住宿獎助金一樣");
                ans2 = new JRadioButton("B.又分為「自由行旅客」和「團體旅遊」兩種優惠獎助");
                ans3 = new JRadioButton("C.自由行旅客優惠的本島行程和離島行程每晚住宿獎助金一樣");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"南投",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 5:
                questionPart1 = new JLabel("隨著疫情增溫，新竹縣除了對各醫院陪病及探病人數進行限縮與管制，衛生局在3月8日也發布各護理之家探病、陪病時間表");
                questionPart2 = new JLabel("以保護免疫力較低族群、慢性病患者以及高齡長者");
                questionPart3 = new JLabel("請問下列何者正確?");
                ans1 = new JRadioButton("A.只有題目提及的三種族群比較會感染新型冠狀肺炎，其他人不用擔心");
                ans2 = new JRadioButton("B.長者一旦感染新型冠狀病毒，發生重症的比率比較高");
                ans3 = new JRadioButton("C.以上皆非");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"新竹",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 6:
                questionPart1 = new JLabel("疫情在台灣已趨緩，政府在「邊境嚴管、國內鬆綁」的原則下，將推出刺激與振興經濟的措施：");
                questionPart2 = new JLabel("把新台幣1,000元變成3,000元的「三倍卷」，嘉義國中生調查校園附近因疫情而受到影響的夜市商家");
                questionPart3 = new JLabel("發現有七成接受調查的商家都贊成發放三倍卷");
                questionPart4 = new JLabel("請問關於三倍卷下列敘述何者錯誤?");
                ans1 = new JRadioButton("A.三倍卷分為「紙本」與「數位」兩種形式。");
                ans2 = new JRadioButton("B領取資格為滿20歲擁有投票權的國民");
                ans3 = new JRadioButton("C.	預計於7/1開放發放，7/15開放使用上路，期限至12/31");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;  
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,questionPart4,ans1,ans2,ans3},"嘉義",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;
        }
    }

    public void setQuestionFrame3(int no)    //設定no題的Joption
    {
        JLabel questionplace;
        JLabel questionPart1;
        JLabel questionPart2;
        JLabel questionPart3;
        JLabel questionPart4;
        ButtonGroup ansGroup = new ButtonGroup();
        RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
        int input;

        switch(no + 1)
        {
            case 1:
                questionPart1 = new JLabel("美國自四月以來，已連續多日每天新增病例超過三萬五千例，雖然近期來這個數字已經下降，但最近幾天新增確診病例依舊維持在每天超過兩萬例。");
                questionPart2 = new JLabel("請問美國確診人數之高的原因包含下列何者?");
                ans1 = new JRadioButton("A.疫情前期，政府對於疫情沒有重視");
                ans2 = new JRadioButton("B.因曾進行全國國民普篩的活動");
                ans3 = new JRadioButton("C.國民上街遊行不戴口罩");
                ans4 = new JRadioButton("D.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ansGroup.add(ans4);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                ans4.addItemListener(handlerRadioButtonSource);
                qNum = 4;
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3,ans4},"美國",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(4);
                }
            break;

            case 2:
                questionPart1 = new JLabel("中國為第一個爆發疫情的國家，為了避免疫情擴散，各級城縣市下令封城。");
                questionPart2 = new JLabel("請問關於中國疫情描述下列敘述何者正確?");
                ans1 = new JRadioButton("A.封城的城市會有警察在街上巡邏確保民眾沒有外出");
                ans2 = new JRadioButton("B.封城的當下也會封鎖物資");
                ans3 = new JRadioButton("C.政府會分配每個人的口罩數量");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);      
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);  
                qNum = 3;   
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,ans1,ans2,ans3},"中國",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 3:
                questionPart1 = new JLabel("英國政府於三月宣布英國的防疫政策為「群體免疫法」，意旨讓大部分的民眾都得過新型冠狀病毒，使他們自身帶有對該病毒的自體免疫。");
                questionPart2 = new JLabel("但英國現已成為確診病例數第四名國家，正式宣告此政策失效。");
                questionPart3 = new JLabel("請問關於群體免疫法下列敘述何者正確?");
                ans1 = new JRadioButton("A.需有50%的人口才可以有群體免疫");
                ans2 = new JRadioButton("B.不一定每個得病的人都可以痊癒，此舉有可能導致死亡人數上升");
                ans3 = new JRadioButton("C.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);  
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource); 
                qNum = 3;           
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"英國",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 4:
                questionPart1 = new JLabel("前一陣子網路瘋傳俄羅斯政府為了讓民眾乖乖在家隔離，因此在大街上放獅子、老虎威嚇民眾。");
                questionPart2 = new JLabel("因此俄羅斯外交部於４月初出來發言，宣稱他們並不會放這些動物，因為這些動物比較沒有效，他們會選擇放＿＿，＿＿的威嚇性比較大。");
                questionPart3 = new JLabel("請問空格處應該填入什麼動物呢？");
                ans1 = new JRadioButton("A.獨角獸");
                ans2 = new JRadioButton("B.響尾蛇");
                ans3 = new JRadioButton("C.熊");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"俄羅斯",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(3);
                }
            break;

            case 5:
                questionPart1 = new JLabel("截至6/11為止，巴西為全球確診人數第二高。");
                questionPart2 = new JLabel("而巴西政府對於防疫的政策也讓不少巴西國民感到憤怒，並於6/9發起遊行示威。");
                questionPart3 = new JLabel("請問關於巴西政府的狀況下列何者為正確?");
                ans1 = new JRadioButton("A.巴西中央政府直至今日都無統一的防疫方案");
                ans2 = new JRadioButton("B.總統強制封城，不顧人民的生計");
                ans3 = new JRadioButton("C.以上皆非");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"巴西",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 6:
                questionPart1 = new JLabel("南非總統於3/26 宣布進行為期三周的全國封鎖防疫，在封鎖期間除了指定人員以外，其餘所有人都必須待在家裡。");
                questionPart2 = new JLabel("當地政府會有如此快速的舉動，被猜測與當地的人口結構有關連。");
                questionPart3 = new JLabel("請問關於前文所提及南非的「人口結構」下列敘述何者正確?");
                ans1 = new JRadioButton("A.因營養不良，部分民眾免疫力差");
                ans2 = new JRadioButton("B.因當地水資源不足，環境較難清潔");
                ans3 = new JRadioButton("C.以上皆是");
                ansGroup = new ButtonGroup();
                ansGroup.add(ans1);
                ansGroup.add(ans2);
                ansGroup.add(ans3);
                ans1.addItemListener(handlerRadioButtonSource);
                ans2.addItemListener(handlerRadioButtonSource);
                ans3.addItemListener(handlerRadioButtonSource);
                qNum = 3;
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"南非",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(3);
                }
            break;
        }
    }

    public void checkCorrect(int correctAns)
    {
        //System.out.println(playerChoice);
        if(playerChoice == correctAns)
        {
            JOptionPane.showMessageDialog(null,"甚麼?竟然對了!真令人無法置信!!","答題結果",JOptionPane.INFORMATION_MESSAGE);
            correct++;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"哈哈~恭喜答錯~","答題結果",JOptionPane.INFORMATION_MESSAGE);
            uncorrect++;//System.out.println("uncorrect");
        }
    }

    private class RadioButtonHandlerSource implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            playerChoice = -1;
            if((qNum >= 1)&&(ans1.isSelected()))
            {
                playerChoice = 1;
            }
            else if ((qNum >= 2)&&(ans2.isSelected()))
            {
                playerChoice = 2;
            }
            else if ((qNum >= 3)&&(ans3.isSelected()))
            {
                playerChoice = 3;
            }
            else if((qNum >= 4)&&(ans4.isSelected())){
                playerChoice = 4;
            }
            else{
                playerChoice = -1;
            }
        }
    }


    public void questionevent(){
        Rectangle heroRect = new Rectangle(testC.posX+testC.width/2,testC.posY+testC.height/2,screenSizeX(50),screenSizeY(50));
        int removeTime = 0;
        for(int i = 0;i<placeRect.size();i++)
        {
            if(heroRect.intersects(placeRect.get(i).rect))       //踩到有事件的位置
            {
                if(level == 0)
                    setQuestionFrame(placeRect.get(i).no);
                else if(level == 1)
                    setQuestionFrame2(placeRect.get(i).no);
                else if(level == 2)
                    setQuestionFrame3(placeRect.get(i).no);
                placeRect.remove(i);
            }
        }
    }

    public void Weaponhit(){
        if(WeaponList.size() > 0){
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
                        ////System.out.println("hit");
                        EnemyList.get(i).setHp(1,level);
                    }
                    if(EnemyList.get(i).hp<=0){
                        EnemyList.remove(i);
                    }
                }            
            }
        }
        if((correct>=3))     //殺光敵人後顯示視窗並關閉整個程式
            {
                //JOptionPane.showMessageDialog(null,"Win!!","Game Result:",JOptionPane.INFORMATION_MESSAGE);
                end = true;
                
                JOptionPane.showMessageDialog(this,"你真棒！前往下一關吧！");


                 if(level==0){
                      timer.cancel();
                     initial_2();
                    working2();
                }else if(level==1){
                     timer.cancel();
                     initial_3();
                    working3();
                }
                else {
                    JOptionPane.showMessageDialog(this,"恭喜你成為防疫大使！");
                    System.exit(1);
                    }
                
            }
            ////System.out.println(uncorrect);
            if(uncorrect>=3){
                JOptionPane.showMessageDialog(this,"答錯太多啦！重新再來吧！");
                uncorrect=0;
                
                
                for (int i = 0; i < EnemyList.size(); i++) {
                     EnemyList.remove(i);
                }
                for (int p = 0; p < WeaponList.size(); p++) {
                     WeaponList.remove(p);
                }
                if(level==0){
                     initial();
               working();
                }else if(level==1){
                     initial_2();
               working2();
                }else{
                 initial_3();
                working3();
                }
              
            }
            
    }
}
