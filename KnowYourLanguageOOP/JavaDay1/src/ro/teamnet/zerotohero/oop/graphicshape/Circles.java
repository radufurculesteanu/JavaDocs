package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class Circles {
    public double getAreaPub()
    {
        Circle c = new Circle();
        return c.area();
    }

    public void getAreaDef()
    {
        Circle c = new Circle();
        c.fillColour();
        c.fillColour(4);
        c.fillColour(3.1f);
    }
}
