package data_structures;

import org.junit.Test;

import java.util.LinkedList;


public class Tester {
    @Test
    public void test() {


        LinkedList<Integer> heapList = new LinkedList<>();
        heapList.add(-2);
        heapList.add(9);
        heapList.add(1);
        heapList.add(-4);
        heapList.add(11);
        heapList.add(32);
        heapList.add(0);
        heapList.add(5);

        Heap<Integer> heap = new Heap<>(heapList);
        System.out.println(heapList);
        System.out.println(heap.heapSort(heapList));
    }
}
