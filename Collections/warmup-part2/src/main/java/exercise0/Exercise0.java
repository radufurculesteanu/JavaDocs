package exercise0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        // Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        // Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(101, "Radu");
        hmap.put(102,"Mircea");
        hmap.put(110,"Maria");

        // Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        // Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]
        System.out.println(hmap);

        Set<Integer> keySet = hmap.keySet();
        Iterator it = keySet.iterator();
        while(it.hasNext())
        {
            System.out.println(hmap.get(it.next()));
        }

    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
