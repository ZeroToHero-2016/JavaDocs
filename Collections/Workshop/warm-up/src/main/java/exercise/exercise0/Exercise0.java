package exercise.exercise0;

import java.util.ListIterator;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public static int id;

    public Exercise0(int val){

        this.id = val;

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        ArrayList<Integer> list = new ArrayList<>();

        for (int idx = Exercise0.id; idx < Exercise0.id + 10; ++idx){
        list.add(idx);
        }

        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)


        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        ListIterator<Integer> iterator = list.listIterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.next()+" ");
        }
        System.out.println();

        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        for(int temp : list) {
            System.out.print(temp+" ");
        }

    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        Exercise0 instance = new Exercise0(randomInt);
        instance.iterateThroughList();
    }
}
