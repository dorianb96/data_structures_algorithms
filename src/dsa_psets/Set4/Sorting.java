package dsa_psets.Set4;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 a) Implement Mergesort for sorting an ArrayList of doubles (so you do not have to use a Comparator or Sequence).
 What is the average runtime for a list of length 1,000?
 What is the average runtime for a list of length 10,000?
 What is the average runtime for a list of length 100,000?

 b) Now implement a second version of Mergesort that uses Bubblesort to sort sublists of less than 20 elements.
 What is now the average runtime for a list of length 1,000?
 What is now the average runtime for a list of length 10,000?
 What is now the average runtime for a list of length 100,000?
 */
public class Sorting {
    // this approach is more memory intensive, but after experimenting with a merge sort version which
    // send the pivots low and high. i found this implementation to run much faster, although the memory cost is probably
    // higher since we make two sublists of each half in the mergesort recursive part of algorithm
    public static<E extends Comparable<E>> void mergeSort1(List<E> array){
        // we don't tolerate empty Lists
        if (array == null) return;
        // once we hit size = 1, then we are done
        if (array.size() < 2){
            return;
        }
        // index divides the list into half
        int index = array.size() / 2;


        // we will make two copies of the array, one for each half
        // the trick is not to perform a reference copy, but a real "deep" copy
        // this part takes 0(n)
        List<E> S1 = new ArrayList<>(index);
        for (int i = 0; i < index; i++)
            S1.add(array.get(i));
        List<E> S2 = new ArrayList<>(index);
        for (int i = index; i < array.size(); i++)
            S2.add(array.get(i));

        // we call the same recursive procedure on each half of the array
        mergeSort1(S1);
        mergeSort1(S2);

        // after the mergesort is performed, we merge the 2 half's back into the original
        merge(array,S1,S2);
        // this should help with garbage collection and reduce the overall memory hangover
        S1 = null;
        S2 = null;
    }

    // the merge method is shared by both merge sorts
    private static <E extends Comparable<E>> void merge(List<E> S, List<E> S1, List<E> S2){
        // this is pointer for list S1
        // we traverse the list with get(i)
        int i = 0;
        // this is pointer for list S2
        int j = 0;
        // we want to traverse the whole list S
        while(i+j < S.size()) {
            // if we already placed all e
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

    // this mergesort performs the bubble sort when neccessary
    public static<E extends Comparable<E>> void mergeSort2(List<E> array){
        // we don't tolerate empty Lists
        if (array == null) return;
        // if size = 20, use bubble sort
        if (array.size() < 20){
            bubbleSort(array);
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
        mergeSort2(S1);
        mergeSort2(S2);

        // after the mergesort is performed, merge the 2 half back into the original
        merge(array,S1,S2);
        S1 = null;
        S2 = null;
    }

    public static <E extends Comparable<E>> List<E> bubbleSort(List<E> list){
        // flag marks when we came to the point without any swaps, then it is already sorted
        boolean flag = true;
        // we count the number of bubbled up elements to reduce the execution of for loop
        int bubbledUpCount = 0;
        // stops when all elements are ordered since no swaps were performed
        while (flag){
            flag = false;
            for (int i = 0; i < list.size() - bubbledUpCount - 1; i++){
                // these temp variable indeed slow down the execution, but the maximal number
                // if of such assignments is (n(n-1)/2) * 2 = 400
                // and PC can perform 10^8 assignments per second
                E next = list.get(i+1);
                E current = list.get(i);
                if (current.compareTo(next) > 0){
                    list.set(i+1,current);
                    list.set(i,next);
                    flag = true;
                }
            }
            // we only now increment this counter
            bubbledUpCount++;
        }
        return list;
    }


    @Test
    public void compareRuntimes(){
        // here we can compare the runtimes of each mergesort application
        // size is constant for all mergesorts
        int size =  100000;
        int numbersRange = 1000;
        System.out.println("Size of the array is: " + size);
        System.out.println("range of numbers(Doubles) is: " + numbersRange);


        // make the first arraylist for mergesort1
        ArrayList<Double> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            list1.add(Math.random()*numbersRange);
        }
        // nano time nicely calculates miliseconds
        float startTime = System.nanoTime();
        mergeSort1(list1);
        System.out.println("Mergesort1 runtime: " + (System.nanoTime() - startTime)/1e6 + "ms");



        // perform the same for mergesort2
        // to remeber mergesort 2 uses bubblesort for lists of size 20 or less
        ArrayList<Double> list2 = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            list2.add(Math.random()*numbersRange);
        }
        float startTime2 = System.nanoTime();
        mergeSort2(list2);
        System.out.println("Mergesort2 runtime: " + (System.nanoTime() - startTime2)/1e6 + "ms");

    }


    // run this to see that merge sort 1 sort indeed works
    @Test
    public void isSortedMergeSort1(){
        int size =  50;
        int numbersRange = 1000;
        System.out.println("Size of the array is: " + size);
        System.out.println("range of numbers(Doubles) is: " + numbersRange);


        // make the first arraylist for mergesort1
        ArrayList<Double> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            list1.add(Math.random()*numbersRange);
        }
        // nano time nicely calculates miliseconds
        float startTime = System.nanoTime();
        mergeSort1(list1);
        System.out.println("Mergesort1 runtime: " + (System.nanoTime() - startTime)/1e6 + "ms");
        System.out.println(list1);
    }

    // run this to see that merge sort 2 sort indeed works
    @Test
    public void isSortedMergeSort2(){
        int size =  50;
        int numbersRange = 1000;
        System.out.println("Size of the array is: " + size);
        System.out.println("range of numbers(Doubles) is: " + numbersRange);


        // make the first arraylist for mergesort1
        ArrayList<Double> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            list1.add(Math.random()*numbersRange);
        }
        // nano time nicely calculates miliseconds
        float startTime = System.nanoTime();
        mergeSort2(list1);
        System.out.println("Mergesort2 runtime: " + (System.nanoTime() - startTime)/1e6 + "ms");
        System.out.println(list1);
    }


    // run this to see that bubble sort indeed works
    @Test
    public void isSortedBubbleSort(){
        int size =  50;
        int numbersRange = 1000;
        System.out.println("Size of the array is: " + size);
        System.out.println("range of numbers(Doubles) is: " + numbersRange);


        // make the first arraylist for mergesort1
        List<Double> list1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            list1.add(Math.random()*numbersRange);
        }
        // nano time nicely calculates miliseconds
        float startTime = System.nanoTime();
        list1  = bubbleSort(list1);
        System.out.println("Bubble sort runtime: " + (System.nanoTime() - startTime)/1e6 + "ms");
        System.out.println(list1);
    }
}
