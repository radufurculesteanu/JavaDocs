package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects.Echipe;
import ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects.MyClass;
import ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects.MyClassChild;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Wrapper;
import java.util.ArrayList;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //TODO get the class for a String object, and print it
		String stringObject = "Radu";
		MyClass my1 = new MyClass(2,7.80,7);
        System.out.println(my1);
        System.out.println(stringObject.getClass());
        System.out.println(my1.getClass());

        //TODO get the class of an Enum, and print it
        System.out.println(Echipe.DINAMO.getClass());
        

        //TODO get the class of a collection, and print it
        ArrayList<String> strings = new ArrayList<String>();
        System.out.println(strings.getClass());
        System.out.println("--------------------");

        //TODO get the class of a primitive type, and print it
        System.out.println(int.class);

        //TODO get and print the class for a field of primitive type
        System.out.println(MyClass.class.getDeclaredField("medie").getType());


        //TODO get and print the class for a primitive type, using the wrapper class
        System.out.println(Integer.TYPE);

        //TODO get the class for a specified class name
        System.out.println(Class.forName("ro.teamnet.zerotohero.reflection.ro.teamnet.zerotohero.reflection.demoobjects.MyClass"));
        System.out.println("--------------------");

        //TODO get the superclass of a class, and print it
        //TODO get the superclass of the superclass above, and print it

        MyClassChild mcc1 = new MyClassChild(2,8.75,8,5);
        System.out.println(mcc1.getClass().getSuperclass());
        System.out.println(mcc1.getClass().getSuperclass().getSuperclass());
        

        //TODO get and print the declared classes within some other class
        Class[] classArray = MyClass.class.getDeclaredClasses();
        for(Class clasa : classArray)
            System.out.println(clasa);
        System.out.println("--------------------");

        //TODO print the number of constructors of a class
        Constructor[] constructors = MyClass.class.getDeclaredConstructors();
        for(Constructor constructor : constructors)
            System.out.println(constructor);
        

        //TODO get and invoke a public constructor of a class
        System.out.println(MyClass.class.getConstructor(int.class, double.class, Integer.class));
        MyClass.class.getConstructor(int.class,double.class,Integer.class).newInstance(2,7.80,5);


        //TODO get and print the class of one private field 
        System.out.println(MyClass.class.getDeclaredField("cifra"));
        System.out.println("-------------------");

        //TODO set and print the value of one private field for an object
        MyClass mc1 = new MyClass(2,7.80,8);
        Field cifra = mc1.getClass().getDeclaredField("cifra");
        cifra.setAccessible(true);
        cifra.set(mc1,6);
        System.out.println(cifra.get(mc1));


        //TODO get and print only the public fields class
        Field[] fields = mc1.getClass().getFields();
        for(Field field : fields)
            System.out.println(field.get(mc1));

        //TODO get and invoke one public method of a class
        Method method = mc1.getClass().getMethod("toString");
        System.out.println(method.invoke(mc1));

        //TODO get and invoke one inherited method of a class
       MyClassChild myClassChild = new MyClassChild(2,8.90,5,3);
       Method method1 = myClassChild.getClass().getMethod("toString");
        System.out.println(method1.invoke(myClassChild));

        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?
        long time1 = System.currentTimeMillis();
        for(int i=0; i<10; i++)
            method.invoke(mc1);
        long time2 = System.currentTimeMillis();
        System.out.println("time2 - time1 = " + (time2-time1));
        for(int i=0; i<100; i++)
            method.invoke(mc1);
        long time3 = System.currentTimeMillis();
        System.out.println("time3 - time2 = " + (time3-time2));

    }
}
