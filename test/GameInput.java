import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Timer; 
import java.util.TimerTask; 
public class GameInput extends JFrame implements KeyListener{
	//容器裝敵人
    public ArrayList<Enery> eneryList = new ArrayList<Enery>();

	final int SCREEN_WIDTH = 400;
	final int SCREEN_HEIGHT = 400;
	final int RECT_WIDTH = 20;
	final int RECT_HEIGHT = 20;
	final int SPRITES_NUM = 2;
	private Timer timer; 
	 	// 定時器 
    private int intervel = 1000 / 100; 
     	//畫人物
    Image img = new ImageIcon("mari1.png").getImage();
	int xSpeed = 20;
	int ySpeed = 20;

	int xPos = SCREEN_WIDTH / 2;
	int yPos = SCREEN_HEIGHT / 2;
	
	int[] spritePosX = new int[SPRITES_NUM];
	int[] spritePosY = new int[SPRITES_NUM];

	public GameInput() {
        setTitle("遊戲基礎-鍵盤控制");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
		
        addMouseListener(new MouseAdapterDemo());
        for(int i = 0; i < SPRITES_NUM; i++) {
        	spritePosX[i] = xPos;
        	spritePosY[i] = yPos;
        }

		 timer = new Timer(); 
        // 主流程控制 
        timer.schedule(new TimerTask() { 
            @Override 
            public void run() { 
               
                repaint(); 
                // 重繪，呼叫paint()方法 
            } 
        }, intervel, intervel); 

		//////////////////////////增加敵人

		
	}

   
    public void update(Graphics g) { 
        this.paint(g); 
    } 
 
    public void paint(Graphics g) { 
        super.paint(g);

        g.setColor(Color.RED);
        g.fillOval(spritePosX[1], spritePosY[1], RECT_WIDTH, RECT_HEIGHT);
        g.drawImage(img, spritePosX[0], spritePosY[0], 30, 30,null);
		for(int i=0;i<eneryList.size();i++){
			eneryList.get(i).x += 2;
			//eneryList.get(0).y -= eneryList.get(0).angleY;
			g.drawImage(eneryList.get(i).img, eneryList.get(i).x, eneryList.get(i).y, eneryList.get(i).width, eneryList.get(i).height,null);
			System.out.println(eneryList.get(i).angleX + "  " +eneryList.get(i).angleY);
			if(eneryList.get(i).x>400||eneryList.get(i).y>400){
				eneryList.remove(i);
				System.out.println("remove"+ i);
				}
		}
    }

	public static void main(String[] args) {
		new GameInput().show();

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// Move the Square
		if( key == KeyEvent.VK_UP ){
			spritePosY[0] -= ySpeed;
			if(hit("Up")){
				System.out.println("hit");
			}
		}
		if( key == KeyEvent.VK_DOWN ){
			spritePosY[0] += ySpeed;
			if(hit("Down")){
				System.out.println("hit");
			}
		}
		if( key == KeyEvent.VK_LEFT ){
			spritePosX[0] -= xSpeed;
            img = new ImageIcon("mari2.png").getImage();
			if(hit("Left")){
				System.out.println("hit");
			}
        }
		
		if( key == KeyEvent.VK_RIGHT ){
			spritePosX[0] += xSpeed;
            img = new ImageIcon("mari1.png").getImage();
			if(hit("Right")){
				System.out.println("hit");
			}
        }
		// Move the Circle
		if( key == KeyEvent.VK_W )
			spritePosY[1] -= ySpeed;
		
		if( key == KeyEvent.VK_X )
			spritePosY[1] += ySpeed;
		
		if( key == KeyEvent.VK_A )
			spritePosX[1] -= xSpeed;
		
		if( key == KeyEvent.VK_D)
			spritePosX[1] += xSpeed;
		
		checkSpritePosRange();
		repaint();
	}

	private void checkSpritePosRange() {
		for(int i = 0; i < SPRITES_NUM; i++) {
			if( spritePosX[i] < 0)	spritePosX[i] = SCREEN_WIDTH;
			if( spritePosY[i] < 0)	spritePosY[i] = SCREEN_HEIGHT;
			if( spritePosX[i] > SCREEN_WIDTH) spritePosX[i] = 0;
			if( spritePosY[i] > SCREEN_HEIGHT) spritePosY[i] = 0;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	public class MouseAdapterDemo extends MouseAdapter {
			
		public void mousePressed(MouseEvent event) {
		double vector =(event.getY()-spritePosY[0]) /(event.getX()-spritePosX[0]);
        int angleX=(int)vector*2;
		int angleY=(int)(1/vector)*2;
        Enery enery=new Enery(spritePosX[0],spritePosY[0],angleX,angleY,20,20,new ImageIcon("bullet.png").getImage());
		
		eneryList.add(enery);
    }
     

	}


	 //檢測碰撞
    public boolean hit(String dir){
            Rectangle myrect = new Rectangle(spritePosX[0],spritePosY[0],30,30);
            Rectangle rect =null;
            for (int i = 0; i < eneryList.size(); i++) {
                Enery enery = eneryList.get(i);
                if(dir.equals("Left")){
                    rect = new Rectangle(enery.x+2,enery.y,enery.width,enery.height);
                }
                else if(dir.equals("Right")){
                    rect = new Rectangle(enery.x-2,enery.y,enery.width,enery.height);
                }
                else if(dir.equals("Up")){
                    rect = new Rectangle(enery.x,enery.y+1,enery.width,enery.height);
                }else if(dir.equals("Down")){
                    rect = new Rectangle(enery.x,enery.y-2,enery.width,enery.height);
                }
                //碰撞檢測
                if(myrect.intersects(rect)){
                    return true;
                }
            }
            return false;
    }
}