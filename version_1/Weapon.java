package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Weapon extends Character{

    String[] Src = {"medicine.png"};

    public Weapon(int heroX, int heroY, int mouseX, int mouseY, int speed){
        super(heroX, heroY, mouseX, mouseY, speed);
        this.width = 50;
        this.height = 50;
        img = new ImageIcon(Src[0]).getImage();
    }

    @Override
    public void changeImg(){

    }

}