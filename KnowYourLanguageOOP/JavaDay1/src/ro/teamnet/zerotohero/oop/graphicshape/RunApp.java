package ro.teamnet.zerotohero.oop.graphicshape;

import ro.teamnet.zerotohero.canvas.Canvas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles cs = new Circles();
        System.out.println("The default circle area is " + cs.getAreaPub());
        cs.getAreaDef();
        System.out.println("--------------------");

        Canvas cnv = new Canvas();
        Shape s1 = new Circle(10);
        System.out.println("S1 area: " + s1.area());
        ShapeBehaviour sb = new Square(10);
        System.out.println("SB area: " + sb.area());
        System.out.println("--------------------");

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try
        {
            Planet p = new Planet(4700000,"XMC-011", sdf.parse("15-10-1990"));
            System.out.println("Date of discovery: " + sdf.format(p.getDateOfDiscovery()));
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }

        //TRY#1
        try
        {
            Student stud1 = new Student("Radu", 9.25);
            System.out.println(stud1);
        }
        catch (MyException mye)
        {
            System.out.println("Media nu poate fi negativa!");
        }
        catch(MyException2 mye2)
        {
            System.out.println("Medie nu poate fi mai mare ca 10!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Shape1Area.txt")))
        {
            writer.write(String.valueOf(s1.area()));
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            System.out.println("fisier creat!");
        }
    }
}
