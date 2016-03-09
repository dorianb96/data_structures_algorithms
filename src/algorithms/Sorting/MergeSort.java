package algorithms.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static<E extends Comparable<E>> void mergeSort(List<E> array){
        // we don't tolerate empty Lists
        if (array == null) return;
        // once we hit size = 1, then we are done
        if (array.size() < 2){
            return;
        }
        // index divides the list into half
        int index = array.size() / 2;

        // we must perform a full clone, not just copy the references
        // S1 has the first half of the original array
        List<E> S1 = new ArrayList<>(index);
        for (int i = 0; i < index; i++)
                S1.add(array.get(i));

        // S2 contains the second half of the original array
        List<E> S2 = new ArrayList<>(index);
        for (int i = index; i < array.size(); i++)
            S2.add(array.get(i));

        // call a recursive merge sort for each half
        mergeSort(S1);
        mergeSort(S2);

        // after the mergesort is performed, merge the 2 half back into the original
        merge(array,S1,S2);
        S1 = null;
        S2 = null;
    }
    private static <E extends Comparable<E>> void merge(List<E> S, List<E> S1, List<E> S2){
        // mark the start
        int i = 0;
        // mark the beginning
        int j = 0;
        while(i+j < S.size()) {
            if (j >= S2.size()) S.set(i+j,S1.get(i++));
            else if (i >= S1.size() ) S.set(i+j,S2.get(j++));
            else if (S1.get(i).compareTo(S2.get(j)) >= 0) {
                S.set(i+j,S2.get(j));
                j++;
            }
            else {
                S.set(i+j,S1.get(i));
                i++;
            }
        }
    }

    @Test
    public void tester(){
        // here we can change the array sizes
        int size = 100000;
        ArrayList<Double> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++){
            list.add(Math.random() * 1000);
        }
        float startTime = System.nanoTime();
        MergeSort.mergeSort(list);
        System.out.println((System.nanoTime() - startTime)/1e6);

        //System.out.println(list);

    }

}
