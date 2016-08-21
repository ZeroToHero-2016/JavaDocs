package exercise.exercise3;

import java.util.Comparator;

/**
 * Created by user on 7/1/2016.
 */
public class Compare implements Comparator<String> {
    public int compare(String o1, String o2) {
        if (o1.length() > o2.length())
            return 1;
        if (o1.length() < o2.length())
            return -1;
        return 0;
    }
}
