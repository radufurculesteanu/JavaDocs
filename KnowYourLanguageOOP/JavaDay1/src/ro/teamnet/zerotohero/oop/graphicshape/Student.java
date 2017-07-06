package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class Student {
    private String nume;
    private double medie;

    public Student(String nume, double medie) throws MyException, MyException2
    {
        if(medie<0) throw new MyException();
        if(medie > 10) throw new MyException2();
        this.nume = nume;
        this.medie = medie;
    }

    public String toString()
    {
        return "Nume: " + this.nume + ", Medie: " + this.medie;
    }
}
