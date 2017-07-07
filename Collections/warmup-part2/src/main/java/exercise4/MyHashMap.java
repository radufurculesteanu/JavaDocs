package exercise4;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {

        // TODO Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>(BUCKET_ARRAY_SIZE);
        for(int i=0; i< BUCKET_ARRAY_SIZE; i++)
        {
            buckets.add(new LinkedList<MyEntry>());
        }

    }

    private int hash(String key) {
        int result = 0;
        if(key != null)
            result = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        return result;
    }

    public String get(String key) {
        // TODO
        int index = hash(key);
        LinkedList<MyEntry> entryList = buckets.get(index);
        for(MyEntry entry : entryList)
            if(entry.getKey().equals(key))
                return entry.getValue();
        return null;
    }

    public void put(String key, String value) {
        // TODO
        int index = hash(key);
        boolean existent = false;
        //buckets.get(index).add(new MyEntry(key,value));
        LinkedList<MyEntry> entryList = buckets.get(index);

        for(MyEntry entry : entryList)
            if(entry.getKey().equals(key))
            {
                existent = true;
                entry.setValue(value);
            }
        if(!existent)
            entryList.add(new MyEntry(key,value));
    }

    public Set<String> keySet() {
        // TODO
        Set<String> keySet = new HashSet<String>();

        for(LinkedList<MyEntry> bucket : buckets)
        {
            for(MyEntry entry : bucket)
                keySet.add(entry.getKey());
        }
        if(keySet.size() > 0)
            return keySet;
        else
            return null;
    }

    public Collection<String> values() {
        // TODO
        Set<String> valueSet = new HashSet<String>();
        Iterator<LinkedList<MyEntry>> it = buckets.iterator();
        while(it.hasNext())
        {
            LinkedList<MyEntry> list = it.next();
            Iterator<MyEntry> it2 = list.iterator();
            while(it2.hasNext())
            {
                valueSet.add(it2.next().getValue());
            }
        }

        if(valueSet.size() > 0)
            return valueSet;
        else
        return null;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int index = hash(key);
        LinkedList<MyEntry> entryList = buckets.get(index);
        for(MyEntry entry : entryList)
            if(entry.getKey().equals(key))
            {
                String value = new String(entry.getValue());
                entryList.remove(entry);
                return value;
            }


        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        int index = hash(key);
        LinkedList<MyEntry> entryLinkedList = buckets.get(index);
        for(MyEntry entry : entryLinkedList)
            if(entry.getKey().equals(key))
                return true;
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        for(LinkedList<MyEntry> bucket : buckets)
        {
            for(MyEntry entry : bucket)
                if(entry.getValue().equals(value))
                    return true;
        }
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int size = 0;
        for(LinkedList<MyEntry> bucket : buckets)
        {
            size += bucket.size();
        }
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        return null;
    }

    public boolean isEmpty() {
        // TODO
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
