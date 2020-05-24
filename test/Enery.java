import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Enery{
    public int x,y;
    public int width,height;
    public int angleX,angleY;
    public Image img;
    public Enery(int x, int y,int angleX,int angleY, int width, int height,Image img) {
        this.x = x;
        this.y = y;
        this.angleX=angleX;
        this.angleY=angleY;
        this.width = width;
        this.height = height;
        this.img=img;
    }
} 