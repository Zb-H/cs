package CollectionDemo;

import java.util.*;

/**
 * return value | method()
 * boolean add(E e);
 * boolean remove(E e);
 * void clear();
 * boolean isEmpty();
 * int Size();
 * Object[] toArray();
 */
public class App {
    public static void main(String[] args) {

        boolean result;
        Collection<String> col = new ArrayList<>();

        col.add("a");
        col.add("b");
        col.add("c");
        col.add("d");

        System.out.println(col);

        result = col.remove("a");
        System.out.println(result);
        System.out.println(col);

        col.clear();
        System.out.println(col);

        col.add(null);
        col.add(null);
        result = col.contains(null);
        System.out.println(result);
        System.out.println(col);

        System.out.println(col.isEmpty());

        System.out.println(col.size());

        Object[] arr = col.toArray();

        for(Object obj : arr){
            System.out.println(obj);
        }
    }
}
