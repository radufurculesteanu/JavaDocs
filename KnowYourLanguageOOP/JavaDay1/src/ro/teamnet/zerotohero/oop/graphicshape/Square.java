package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class Square extends Shape{
    private int side;

    public Square(int side)
    {
        super();
        this.side = side;
    }

    public double area()
    {
        return (double)this.side * this.side;
    }
}
