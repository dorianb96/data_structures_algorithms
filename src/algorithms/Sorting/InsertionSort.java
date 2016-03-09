package algorithms.Sorting;

import java.util.List;

public class InsertionSort {
    public static <E extends Comparable<E>> List<E> insertionSort(List<E> list){
        // we say the first element with index 0 is well inserted so we skip it
        for (int i = 1; i < list.size(); i++){
            // say the key is next element
            E key = list.get(i);
            // j will be used for comparison
            // the array from 0 to j aka from  0 to a-1 will be where we insert
            // we iterate backwards from i-1 (position before i) to zero
            int j = i - 1;
            // loop from position on left of current element
            //  i-1 to 0 until you find a smaller key
            while(j >= 0 && list.get(j).compareTo(key) > 0){
                list.set(j+1,list.get(j));
                j-=1;
            }
            // this corrects the former imbalance of setting
            list.set(j+1,key);
        }
        return list;
    }
}
