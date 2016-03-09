package algorithms.Sorting;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static <E extends Comparable<E>> void quickSort(List<E> list){
        int size = list.size();
        if (size < 2) return;
        // just store the first element as pivot
        E pivot = list.get((int)(Math.random() * size));
        // here are 3 array list's for each part of array
        List<E> smallerPart = new ArrayList<>();
        List<E> equalPart = new ArrayList<>();
        List<E> largerPart = new ArrayList<>();
        // now distribute parts to array list's depending on pivot
        while (!list.isEmpty()){
            E value = list.remove(0);

            if(value.compareTo(pivot) < 0){
                smallerPart.add(value);
            }
            else if (value.compareTo(pivot) == 0){
                equalPart.add(value);
            }
            else{
                largerPart.add(value);
            }
        }
        // this makes a recursive call
        quickSort(smallerPart);
        quickSort(largerPart);
        // after the parts are sorted add them back in
        while(!smallerPart.isEmpty()){
            list.add(smallerPart.remove(0));
        }
        while(!equalPart.isEmpty()){
            list.add(equalPart.remove(0));
        }
        while(!largerPart.isEmpty()){
            list.add(largerPart.remove(0));
        }
    }

    public static <E extends Comparable<E>> void inPlaceQuickSort(List<E> list, int start, int end){
        // sometimes in -- inPlaceQuickSort(list,start,left-1), left-1 happens to be less than 0
        if (start>= end) return;
        // do the basic assignments
        E pivot = list.get(end);
        int right = end-1;
        int left = start;
        // make sure the pointers don't crossover
        // because if they cross you won't change the sides of pivot
        while (left <= right) {
            // find the first element from left bigger than pivot
            while (left <= right && list.get(left).compareTo(pivot) < 0) {
                left++;
            }
            // find the first element from right smaller than pivot
            while (right >= left && list.get(right).compareTo(pivot) > 0) {
                right--;
            }
            // in case that left < right, than it makes sense to perform a swap
            if (left <= right) {
                E temp = list.get(left); list.set(left, list.get(right)); list.set(right, temp);
                left--; right++;
            }
        }
        // swap left with the pivot
        E temp = list.get(left); list.set(left, list.get(end)); list.set(end, temp);
        // eliminate the pivot from array
        inPlaceQuickSort(list,start,left-1);
        inPlaceQuickSort(list,left+1,end);
    }
}

