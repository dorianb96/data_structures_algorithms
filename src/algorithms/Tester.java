package algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dorian on 09-Feb-16.
 */
public class Tester {
    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(-2);
        list.add(9);
        list.add(1);
        list.add(-4);
        list.add(11);
        list.add(32);
        list.add(5);

        System.out.println(list);
        MergeSort.mergeSort(list);
        System.out.println(list);
    }
}
