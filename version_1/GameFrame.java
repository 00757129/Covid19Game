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
    public int intervel = 1000000 / 100000; //每intervel個微秒就repaint
    public ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();  //weapon用容器裝
    public ArrayList<Enemy> EnemyList = new ArrayList<Enemy>(); //enemy也用容器裝
    public Hero testC;      //一定要先宣告一下，不然KeyListener不給過，但是可以在initial再寫
    public ArrayList<Place> placeRect = new ArrayList<Place>();    //裝各地點位置的arraylist
    public int playerChoice;
    public Image backGroundImage =new ImageIcon("routemap2020.png").getImage();
    JRadioButton ans1;
    JRadioButton ans2; 
    JRadioButton ans3;

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
                questionevent();
            }
        }, intervel, intervel);        //每個微秒就重複一次

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
            }
        }, 200, 200);                   //每0.1秒就重複一次


        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkState();        //檢查所有生命
            }
        }, 500, 500);                   //每0.5秒就重複一次
    }

    public void checkState(){
        //testC.changeImg();              //讓角色的腳可以移動，呈現動畫的感覺
        for(int i = 0;i<EnemyList.size();i++)
        {
            testC.setHp(EnemyList.get(i));      //檢查hero血量
        }
    }
    
    public void initial(){
        testC = new Hero(5, 5);
        setQuestionPlace();
        int total = 10;                  //total of enemy
        SecureRandom rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        // System.out.println("range is "+range);
        for(int i = 0;i < total;i++)
        {
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*500+300);
            // System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= 1000)          //應該要是1200-width，我先把hero的width的最大值預設成200
                x = 1000;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= 550)          //應該要是750-height，我先把hero的height的最大值預設成200
                y = 550;
            // System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(x,y,testC.posX,testC.posY,2,1,0);
            EnemyList.add(virus);
        }
    }

    public void initial_2(){
       
        JOptionPane.showMessageDialog(this,"通過第一關！");
        System.out.println("inital_2");

        testC = new Hero(5, 5);
        //setQuestionPlace();
        backGroundImage =new ImageIcon("taiwan.jpg").getImage();
        int total = 10;                  //total of enemy
        SecureRandom rand = new SecureRandom();
        double range = (3.141515926 * 2) / total;       //
        // System.out.println("range is "+range);
        for(int i = 0;i < total;i++)
        {
            double angle = rand.nextDouble()*(range) + range*i;
            rand = new SecureRandom();
            int length = (int)(rand.nextDouble()*500+300);
            // System.out.println("in number."+i+" angle is "+angle+" and cos is "+Math.cos(angle));
            int x = (int)(length*Math.sin(angle));
            x = testC.posX + x;
            if(x <= 0)
                x = 0;
            else if(x >= 1000)          //應該要是1200-width，我先把hero的width的最大值預設成200
                x = 1000;
            int y = (int)(length*Math.cos(angle));
            y = testC.posY + y;
            if(y <= 0)
                y = 0;
            else if(y >= 550)          //應該要是750-height，我先把hero的height的最大值預設成200
                y = 550;
            // System.out.println("in number."+i+" x is "+x+" and y is "+y);
            Enemy virus = new Enemy(x,y,testC.posX,testC.posY,2,6,1);
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
        big.drawImage(backGroundImage, 0, 0, null);    //重複畫背景
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
            	// System.out.println("remove"+ i);
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
             if(testC.posY<=550)         //750-testC.height
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
            if(testC.posX<=1000)         //1200-testC.width
                testC.posX += testC.speedX;
            testC.moveFlag = 1;
            testC.lastFlag = testC.moveFlag;
        }
        // Move the Circle

        else if( key == KeyEvent.VK_W ){        //上下的圖片先暫時跟原本(僅限左右)的相反  
            //move(1);
            System.out.println("in W");
            if(testC.posY>=0)
                testC.posY -= testC.speedY;
            if(testC.lastFlag==0)
                testC.moveFlag = 1;
            else
                testC.moveFlag = 0;
        }
        
        else if( key == KeyEvent.VK_S ){        //上下的圖片先暫時跟原本(僅限左右)的相反      
            //move(2);
            if(testC.posY<=550)         //750-testC.height
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
            if(testC.posX<=1000)         //1200-testC.width
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
             if(testC.posY<=550)         //750-testC.height
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
            if(testC.posX<=1000)         //1200-testC.width
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
            if(testC.posY<=550)         //750-testC.height
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
            if(testC.posX<=1000)         //1200-testC.width
                testC.posX += testC.speedX;
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

    public void setQuestionPlace(){     //設定question觸發位置
        int [] placeX = {104, 210, 540, 616, 966, 396};
        int [] placeY = {376, 632, 478, 576, 609, 174};
        for(int i = 0;i < 6 ;i++)
        {
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
                questionplace = new JLabel("1.林口長庚醫院：假訊息查證");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,questionPart3,ans1,ans2,ans3},"問題一",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 2:
                questionplace = new JLabel("2.亞東醫院：疫情期間進出醫院");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,ans1,ans2,ans3},"問題二",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 3:
                questionplace = new JLabel("3.台北車站：出入人多場合");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,ans1,ans2,ans3},"問題三",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(1);
                }
            break;

            case 4:
                questionplace = new JLabel("4.公館：宿舍內、校園內可以注意的防疫");
                questionPart1 = new JLabel("師大公館有名住宿生於3/31確診新型冠狀病毒");
                questionPart2 = new JLabel("為確保不要在宿舍與校區交叉感染的疑慮，校方要求與該名學生密切互動的13名師生進行居家隔離<br/>請問下列哪個選項提及的防疫措施有要求 “完全禁止外出”");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,ans1,ans2,ans3},"問題四",JOptionPane.DEFAULT_OPTION);
                if(input == 0)
                {
                    checkCorrect(3);
                }
            break;

            case 5:
                questionplace = new JLabel("5.動物園：公共空間的防疫辦法");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,ans1,ans2,ans3},"問題五",JOptionPane.DEFAULT_OPTION);
                //RadioButtonHandlerSource handlerRadioButtonSource = new RadioButtonHandlerSource();
                if(input == 0)
                {
                    checkCorrect(2);
                }
            break;

            case 6:
                questionplace = new JLabel("6.新北投：就算在戶外泡溫泉依舊需要實名制");
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
                input = JOptionPane.showConfirmDialog(null,new Object[]{questionplace,questionPart1,questionPart2,ans1,ans2,ans3},"問題六",JOptionPane.DEFAULT_OPTION);
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
        System.out.println(playerChoice);
        if(playerChoice == correctAns)
        {
            JOptionPane.showMessageDialog(null,"恭喜答對","答題結果",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"嗚嗚答錯囉~","答題結果",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class RadioButtonHandlerSource implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if(ans1.isSelected())
            {
                playerChoice = 1;
            }
            else if (ans2.isSelected())
            {
                playerChoice = 2;
            }
            else if (ans3.isSelected())
            {
                playerChoice = 3;
            }
        }
    }


    public void questionevent(){
        Rectangle heroRect = new Rectangle(testC.posX+testC.width/2,testC.posY+testC.height/2,50,50);
        int removeTime = 0;
        for(int i = 0;i<placeRect.size();i++)
        {
            if(heroRect.intersects(placeRect.get(i).rect))       //踩到有事件的位置
            {
                setQuestionFrame(placeRect.get(i).no);
                placeRect.remove(i);
            }
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
            if(EnemyList.size()==0)     //殺光敵人後顯示視窗並關閉整個程式
            {
                //JOptionPane.showMessageDialog(null,"Win!!","Game Result:",JOptionPane.INFORMATION_MESSAGE);
                

                initial_2();
                //System.exit(1);
            }
        }
            
    }
}