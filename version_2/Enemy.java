package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Enemy extends Virus{
    public Enemy(int initX,int initY)
    {
        super(initX,initY);
        this.width = 30;
        this.height = 30;
        this.moveX = 0;
        this.moveY = 0;
        img = new ImageIcon("covid.png").getImage();
    }

    
}