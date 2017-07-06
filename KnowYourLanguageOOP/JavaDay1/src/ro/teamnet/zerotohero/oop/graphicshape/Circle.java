package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle()
    {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 1;
    }

    public Circle(int radius)
    {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = radius;
    }

    public Circle(int x, int y)
    {
       this.xPos = x;
       this.yPos = y;
       this.radius = 1;
    }

    public Circle(int x, int y, int radius)
    {
        this.xPos = x;
        this.yPos = y;
        this.radius = radius;
    }

    public double area()
    {
        return (double)this.radius * this.radius * Math.PI;
    }

    public String toString()
    {
        return "center = (" + this.xPos + "," + this.yPos + ") and radius = " + this.radius;
    }

    public void fillColour()
    {
        System.out.println(super.color);
    }

    public void fillColour(int color)
    {
        super.setColor(color);
        System.out.println("The color is now " + super.color);
    }

    public void fillColour(float sat)
    {
        super.setSaturation(sat);
        System.out.println("The saturation is now " + super.saturation);
    }

}
