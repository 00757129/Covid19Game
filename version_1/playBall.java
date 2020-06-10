import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class playBall extends JFrame{
 
	MyPanel mp=null;
	public static void main(String[] args) {
		playBall pb=new playBall();
	}
	public playBall()
	{
		mp=new MyPanel();
		this.add(mp);
		
		//事件监听
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
 
}
 
//定义自己的面板
class MyPanel extends JPanel implements KeyListener
{
	int x=10;
	int y=10;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}
 
	@Override//键被按下
	public void keyPressed(KeyEvent e) {
		//System.out.println("被按下"+(char)e.getKeyCode());
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				y-=2;
				//调用repaint()函数，重新绘制小球位置
				this.repaint();
				break;
			case KeyEvent.VK_DOWN:
				y+=2;
				//调用repaint()函数，重新绘制小球位置
				this.repaint();
				break;
			case KeyEvent.VK_LEFT:
				x-=2;
				//调用repaint()函数，重新绘制小球位置
				this.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				x+=2;
				//调用repaint()函数，重新绘制小球位置
				this.repaint();
				break;
		}
		
	}
 
	@Override//表示具体一个值被输出，例如：F1
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override//键被释放
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
