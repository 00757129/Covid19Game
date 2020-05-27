package covid19.game;

import javax.swing.JFrame;

public class GameTest{

    public static void main(String[] args){
        GameFrame test = new GameFrame();
        test.setTitle("version 1");
        test.setSize(1200, 750);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }
}