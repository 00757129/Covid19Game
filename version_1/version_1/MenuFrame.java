package covid19.game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//import peekaboo.Music;
//import peekaboo.*;
public class MenuFrame extends JFrame {
     public JLabel label;  public JPanel mainJpanel; 
    public JButton levelOneButton,levelTwoButton,levelThreeButton,introductionButton; 
     GameFrame gameFrame;

    //Music music = new Music("/MUSIC/startmusic.wav");
    public MenuFrame(){//constructor
        super("COVID");
        GameFrame gameFrame=new GameFrame();
        setLayout(new BorderLayout());
        setStartPanel();
        add(mainJpanel);
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
  
    private void setStartPanel(){
        mainJpanel=new JPanel();
        mainJpanel.setLayout(null);
        label=new JLabel("COVID-19");label.setBounds(550,50,100,40); 
        levelOneButton=new JButton("第一關");levelOneButton.setBounds(new Rectangle(550,150,100,40)); 
        levelTwoButton=new JButton("第二關");levelTwoButton.setBounds(new Rectangle(550,250,100,40));
        levelThreeButton=new JButton("第三關");levelThreeButton.setBounds(new Rectangle(550,350,100,40)); 
        introductionButton=new JButton("遊戲介紹");introductionButton.setBounds(new Rectangle(550,450,100,40));
        ButtonClick buttonClick =new  ButtonClick();
        levelOneButton.addActionListener(buttonClick);levelTwoButton.addActionListener(buttonClick);levelThreeButton.addActionListener(buttonClick);
        introductionButton.addActionListener(buttonClick);
        
        add(label);
        add(levelOneButton);add(levelTwoButton);add(levelThreeButton);add(introductionButton);
        //add(mainJpanel);
        //mainJpanel.setVisible(false);
    }
   
    private class ButtonClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
                GameFrame gf;
                if(e.getSource()== levelOneButton){
                    mainJpanel.setVisible(false);
                    gameFrame.setVisible(true);
                    gameFrame.initial();
                    gameFrame.working();
                }
               
                
        }
    }
}