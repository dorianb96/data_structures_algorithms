package algorithms.Sorting;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort{
    public static <E extends Comparable<E>> List<E> bubbleSort1(List<E> list){
        for (int i = 0; i < list.size()-1; i++){
            // a trick is to also subtract i since
            // it represents the elements that already
            // bubbled up and don't have to be removed again
            for (int j = 0; j < list.size()- 1 - i ; j++) {
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

    // this is slightly more efficient
    // because you save a few cycles
    // of list iteration with the flag
    public static <E extends Comparable<E>> List<E> bubbleSort2(List<E> list){
        boolean flag = true;
        int k = 0;
        while(flag){
            // when there are no swaps we stop
            flag = false;
            // in the iteration we also count k, the bubbled up elements
            // this makes the algorithm even more efficient
            for (int j = 0; j < list.size()- 1 - k ; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // swap elements
                    E elem = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, elem);
                    // if there is a swap, we can continue
                    flag = true;
                }
            }
            k++;
        }
        return list;
    }
}



