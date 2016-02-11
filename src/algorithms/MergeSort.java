package algorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static<E extends Comparable<E>> void mergeSort(List<E> array){
        if (array == null) return;

        if (array.size() < 2){
            return;
        }
        int index = array.size() / 2;

        List<E> S1 = new ArrayList<>(index);
        for (int i = 0; i < index; i++)
                S1.add(array.get(i));

        List<E> S2 = new ArrayList<>(index);
        for (int i = index; i < array.size(); i++)
            S2.add(array.get(i));

        mergeSort(S1);
        mergeSort(S2);

        merge(array,S1,S2);
    }
    @SuppressWarnings("unchecked")
    private static  <E extends Comparable<E>> void merge(List<E> S, List<E> S1, List<E> S2){
        int i = 0,j = 0;
        while (i < S1.size() && j < S2.size()){
            if (S1.get(i).compareTo(S2.get(j)) < 0){
                S.set(i+j,S1.get(i));
                i++;
            }
            else{
                S.set(i+j,S2.get(j));
                j++;
            }
        }
        while (i < S1.size()){
            S.set(i+j,S1.get(i));
            i++;
        }
        while(j < S2.size()) {
            S.set(i + j, S2.get(j));
            j++;
        }
    }
}
