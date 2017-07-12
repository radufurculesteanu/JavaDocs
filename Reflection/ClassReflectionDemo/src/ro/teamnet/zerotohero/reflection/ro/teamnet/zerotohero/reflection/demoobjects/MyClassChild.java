package ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
public class MyClassChild extends MyClass{
    int cifra2;

    public MyClassChild(int cifra, double medie, Integer intreg, int cifra2)
    {
        super(cifra,medie,intreg);
        this.cifra2 = cifra2;
    }
}
