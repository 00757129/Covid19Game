import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class MyGame extends JPanel{
	JFrame frame;
	int x = 10;
	int y = 10;
	int width = 50;
	int height = 50;
	int step = 5;
	boolean U=false,D=false,L=false,R=false;
	MyGame(){
		frame = new JFrame();
		frame.setSize( 800, 600);
		frame.setLayout(null);
		
		this.setLayout(null);
		this.setBackground(Color.blue);
		this.setBounds(0, 0, 800, 600);
		this.setFocusable(true);
		
		this.addKeyListener(new Mykey());
		
		frame.add(this);
 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MyGame();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
		repaint();
		run();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void run(){
		if(!U&&!D&&L&&!R){
			x-=step;
		}else if(!U&&!D&&!L&&R){
			x+=step;
		}else if(U&&!D&&!L&&!R){
			y-=step;
		}else if(!U&&D&&!L&&!R){
			y+=step;
		}else if(U&&!D&&L&&!R){
			x-=step;
			y-=step;
		}else if(!U&&D&&L&&!R){
			x-=step;
			y+=step;
		}else if(U&&!D&&!L&&R){
			x+=step;
			y-=step;
		}else if(!U&&D&&!L&&R){
			x+=step;
			y+=step;
		}
	}
	
	class Mykey extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 自动生成的方法存根
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自动生成的方法存根
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				L=true;
				break;
			case KeyEvent.VK_RIGHT:
				R=true;
				break;
			case KeyEvent.VK_UP:
				U=true;
				break;
			case KeyEvent.VK_DOWN:
				D=true;
				break;
			}
			if(x<=0) L = false;
			else if(x>=700) R = false;
			if(y<=0) U = false;
			else if(y>=490) D = false;
		}
 
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自动生成的方法存根
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				L=false;
				break;
			case KeyEvent.VK_RIGHT:
				R=false;
				break;
			case KeyEvent.VK_UP:
				U=false;
				break;
			case KeyEvent.VK_DOWN:
				D=false;
				break;
			}
		}
	}
}

