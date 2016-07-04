package exercise3;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private int capacity;

    public MyHashMap(int capacity) {
        this.capacity = capacity;

        // Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for(Integer i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO
        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void put(String key, String value) {
        int hashCode;
        if (key == null)
        {
            hashCode = 0;
        }
        else{
        hashCode = Math.abs(key.hashCode());}
        if (hashCode >= capacity)
            hashCode %= capacity;
        int ok = 0;
        for (Integer i = 0; i < buckets.get(hashCode).size(); i++)
            if (buckets.get(hashCode).get(i).key.equals(key))
            {
                 buckets.get(hashCode).get(i).value = value;
                 ok = 1;
            }
        if (ok == 0) {
            MyEntry e = new MyEntry(key,value);
            buckets.get(hashCode).add(e);
        }
    }

    public Set<String> keySet() {
        // TODO
        Set<String> key = new HashSet<String>();
        for (int i = 0; i < buckets.size(); i++) {
            LinkedList<MyEntry> myLinkList = buckets.get(i);
            for (Iterator it = myLinkList.iterator(); it.hasNext(); ) {
                MyEntry entry = (MyEntry) it.next();
                key.add(entry.getKey());
            }
        }
        return key;
    }

    public Collection<String> values() {
        // TODO
        Collection<String> values = new HashSet<String>();
        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        String deSters=null;
        int indexArray =Math.abs(key.hashCode()) % capacity;
        for (int i = 0; i < buckets.get(indexArray).size(); i++) {
            if (!buckets.get(indexArray).isEmpty())
                if (buckets.get(indexArray).get(i).getKey().equals(key)) {
                    deSters=buckets.get(indexArray).get(i).getValue();
                    buckets.get(indexArray).remove(buckets.get(indexArray).get(i));
                }


        }
        return deSters;
    }

    public boolean containsKey(String key) {
        for (Integer i = 0; i < capacity ; i++)
            for (Integer j = 0; j < buckets.get(i).size(); j++)
                if (buckets.get(i).get(j).getKey().equals(key))
                    return true;
        return false;
    }

    public boolean containsValue(String value) {
        for (Integer i = 0; i < capacity ; i++)
            for (Integer j = 0; j < buckets.get(i).size(); j++)
                if (buckets.get(i).get(j).getValue().equals(value))
                    return true;
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int nr = 0;
        for (Integer i = 0 ; i < capacity; i++)
            for (Integer j = 0; j < buckets.get(i).size(); j++)
                nr++;
        return nr;
    }

    public void clear() {
        for (Integer i = 0; i < capacity; i++)
            for (Integer j = 0; j < buckets.get(i).size(); j++)
                buckets.get(i).remove();
    }

    public Set<MyEntry> entrySet() {
        Set<MyEntry> s = new HashSet<MyEntry>();
        for (Integer i = 0; i < capacity; i++)
            for (Integer j = 0; j < buckets.get(i).size(); j++) {
//               MyEntry e = new MyEntry(buckets.get(i).get(j).getKey(),buckets.get(i).get(j).getValue());
                s.add(buckets.get(i).get(j));
            }
        return s;
    }

    public boolean isEmpty() {
        for (Integer i = 0 ; i < capacity ; i++)
            if (buckets.get(i).size() > 0)
                return false;
        return true;
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
