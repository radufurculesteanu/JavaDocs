package ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
public class MyClass {
    private int cifra;
    public double medie;
    public Integer intreg;

    public MyClass()
    {

    }

    public MyClass(int cifra, double medie, Integer intreg)
    {
        System.out.println("Constructor cu parametri apelat!");
        this.cifra = cifra;
        this.medie = medie;
        this.intreg = intreg;
    }

    public String toString()
    {
        return new String("Cifra: " + this.cifra + ", Medie: " + this.medie + ", Intreg: " + this.intreg);
    }


    class InnerClass
    {
        int innerInt;
        double innerDouble;
    }
}
