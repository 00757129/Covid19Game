package covid19.game;
import java.awt.*; 

public class Place
{
    public Rectangle rect;
    public int placeX;
    public int placeY;
    public int no;

    public Place(int placeX,int placeY,int no)
    {
        this.placeX = placeX;
        this.placeY = placeY;
        this.rect = new Rectangle(placeX,placeY,5,5);
        this.no = no;
    }
}