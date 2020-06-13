package covid19.game;

import javax.swing.JFrame;
import java.awt.*;

public class GameTest{

    public static void main(String[] args){
        GameFrame test = new GameFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        test.setTitle("version 1");
        // test.setSize(1200, 750);
        test.setSize(screenSize.width, screenSize.height);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }
}