package algorithms;

import java.util.List;

public class SelectionSort {
    public static <E extends Comparable<E>> List<E> selectionSort(List<E> list){
        for (int i = 0; i < list.size(); i++){
            // assume the smallest item is first
            int smallest = i;
            // find the smallest element in remainder of the list
            for (int j = i; j < list.size(); j++){
                if (list.get(j).compareTo(list.get(smallest)) < 0){
                    smallest = j;
                }
            }
            // swap the elements -- current with smallest
            E smaller = list.get(smallest);
            // at the position of smallest set the initial
            list.set(smallest,list.get(i));
            // at the initial position set the smallest element index
            list.set(i,smaller);
        }
        return list;
    }
}
