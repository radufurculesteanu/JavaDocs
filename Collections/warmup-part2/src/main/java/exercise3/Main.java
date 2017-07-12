package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Radu.Furculesteanu on 7/7/2017.
 */
public class Main {
    public static void main(String[] args) {
        HashMap<StudentTest, Integer> hmap1 = new HashMap<StudentTest, Integer>();
        HashMap<StudentTest, Integer> hmap2 = new HashMap<StudentTest, Integer>();
        HashMap<StudentTest, Integer> hmap3 = new HashMap<StudentTest, Integer>();
        HashMap<StudentTest, Integer> hmap4 = new HashMap<StudentTest, Integer>();
        StudentDer1 stud11 = new StudentDer1("Radu", "Furculesteanu");
        StudentDer1 stud12 = new StudentDer1("Radu", "Furculesteanul");
        StudentDer2 stud21 = new StudentDer2("Radu", "Furculesteanu");
        StudentDer2 stud22 = new StudentDer2("Radu", "Furculesteanul");
        StudentDer3 stud31 = new StudentDer3("Radu", "Furculesteanu");
        StudentDer3 stud32 = new StudentDer3("Radu", "Furculesteanul");
        StudentDer4 stud41 = new StudentDer4("Radu", "Furculesteanu");
        StudentDer4 stud42 = new StudentDer4("Radu", "Furculesteanul");

        hmap1.put(stud11, 21); hmap1.put(stud12, 22);
        hmap2.put(stud21, 21); hmap2.put(stud22, 22);
        hmap3.put(stud31, 21); hmap3.put(stud32, 22);
        hmap4.put(stud41, 21); hmap4.put(stud42, 22);

        System.out.println("Equals:");
        System.out.println(stud11.equals(stud12));
        System.out.println(stud21.equals(stud22));
        System.out.println(stud31.equals(stud32));
        System.out.println(stud41.equals(stud42));

        System.out.println("HashCode:");
        System.out.println(stud11.hashCode() == stud12.hashCode());
        System.out.println(stud21.hashCode() == stud22.hashCode());
        System.out.println(stud31.hashCode() == stud32.hashCode());
        System.out.println(stud41.hashCode() == stud42.hashCode());

        System.out.println("--------------------");

        System.out.println(hmap1);
        System.out.println(hmap2);
        System.out.println(hmap3);
        System.out.println(hmap4);

    }
}
