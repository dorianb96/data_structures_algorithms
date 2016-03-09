package algorithms.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeBubbleSort<E> {
    public static <E extends Comparable<E>> void mergeSort(List<E> array) {
        // we don't tolerate empty Lists
        if (array == null) return;
        // once we hit size = 1, then we are done
        if (array.size() < 2) {
            return;
        }
        if (array.size() < 20) {
            bubbleSort1(array);
            return;
        }
        // index divides the list into half
        int index = array.size() / 2 + 1;

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
        merge(array, S1, S2);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> void merge(List<E> S, List<E> S1, List<E> S2) {
        // the merge method can also be written as part of the merge sort instead
        // of calling the merge method
        int i = 0, j = 0;
        // while you can (i<size && j<size) compare the items
        // place the smaller item into the output list
        // after there are no more possible items in one list, you can't compare further
        // thus stop and all the remaining items
        while (i < S1.size() && j < S2.size()) {
            if (S1.get(i).compareTo(S2.get(j)) < 0) {
                S.set(i + j, S1.get(i));
                i++;
            } else {
                S.set(i + j, S2.get(j));
                j++;
            }
        }
        // if some items from first list remained, add them up
        while (i < S1.size()) {
            S.set(i + j, S1.get(i));
            i++;
        }
        // if some items from first list remained, add them up
        while (j < S2.size()) {
            S.set(i + j, S2.get(j));
            j++;
        }
    }


    public static <E extends Comparable<E>> List<E> bubbleSort1(List<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            // a trick is to also subtract i since
            // it represents the elements that already
            // bubbled up and don't have to be removed again
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // swap the elements
                    E elem = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, elem);
                }
            }
        }
        return list;
    }

    @Test
    public void tester() {
        // here we can change the array sizes
        int size = 100000;
        ArrayList<Double> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++){
            list.add(Math.random() * 1000);
        }
        float startTime = System.nanoTime();
        MergeBubbleSort.mergeSort(list);
        System.out.println((System.nanoTime() - startTime)/1e6);
        System.out.println(list);
    }
}
