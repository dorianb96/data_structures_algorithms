package data_structures.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dorian on 07-Feb-16.
 *  rules of the heap are:
 *  1) parent is always bigger than child (in min-heap parent is smaller)
 *  2) a new element is always added to the leftmost and bottommost position
 *
 *  some guidelines are:
 *  1) parent of a node is: (index - 1) / 2
 *  2) children are at nodes: 2*index + 1, 2*index + 2
 *  3) when removing the max element we replace it with the last element
 *  and then call a down-heapify in order to restore heap properties
 */
@SuppressWarnings("unchecked")
public class Heap<E extends Comparable<E>> {
    // it seems that this description fo also holds then for the rest of the class

    public Heap(){
        heap = new ArrayList<>();
    }
    public Heap(List<E> list){
        heap = list;
        // bottom up build heap
    }
    public Heap(E[] array){
        heap = new ArrayList<>(array.length);
        Collections.addAll(heap, array);
        // buttom up build heap
    }


    private List<E> heap;

    // this easily swaps the elements at two different positions
    public void swap(int n1, int n2){
        E element = heap.get(n1);
        heap.set(n1,heap.get(n2));
        heap.set(n2,element);
    }


    /**
     * insert adds the element to the last position in the heap
     * but after we insert the element, we need to perform
     * a up heapify to keep the heap properties intact
     */
    public void insert(E element){
        if (element == null) throw new IllegalArgumentException("Element can't be null");
        heap.add(element);
        upHeapify(heap.size()-1);
    }


    /**
     * up-heapify works by simply checking whether the parent-child rule is followed
     * it is used after inserting a new element into the heap
     * and very efficiently restores any potential imbalances by checking
     * whether  the child is bigger than parent until the parent is bigger
     * or we got to the root
     */
    public void upHeapify(int index1){
        int index = index1;
        int parent = parent(index);
        while (index != 0) {
            if (heap.get(parent).compareTo(heap.get(index)) < 0) {
                swap(parent, index);
                index = parent;
            }
            else{
                break;
            }
        }
    }

    /**
     * remove max removes the root of the heap, the element at index 0
     * it then puts the last element into it's place and performs a
     * down-heapify operation to make sure the heap property is intact
     */
    public E removeMax() {
        if (heap.size() == 0) throw new IllegalArgumentException("Heap is empty");
        E value = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downHeapify(0);
        return value;
    }


    public void downHeapify(int index){
        // if it has a child
        while(hasLeftChild(index)){
            // if it has a right child
            // determine whether to compare left or right child with the parent
            if(hasRightChild(index)){
                // if left child is bigger than the right child
                if (heap.get(leftChild(index)).compareTo(heap.get(rightChild(index))) >= 0){
                    // then compare left child with parent for possible swap
                    if (heap.get(leftChild(index)).compareTo(heap.get(index)) > 0){
                        // swap the child with parent if it bigger
                        // and update the index for next
                        swap(index,leftChild(index));
                        index = leftChild(index);
                    }
                }
                // if right child is bigger than the left child
                else{
                    //then compare  right child with the parent for possible swap
                    if (heap.get(rightChild(index)).compareTo(heap.get(index)) > 0){
                        swap(index,rightChild(index));
                        index = rightChild(index);
                    }

                }
            }
            // if it doesn't have a right child
            else{
                //compare the left child with parent for possible swap
                if (heap.get(leftChild(index)).compareTo(heap.get(index)) > 0){
                    swap(index,leftChild(index));
                    index = leftChild(index);
                }
                // if it has a left child that isn't bigger than break the loop
                else{
                    break;
                }
            }
        }
    }


    /**
     * this just creates a heap faster than otherwise if we performed
     * the making of heap with just insert functions
     * but this isn't the final order useful for heap sort
     * to do the heapsort we have to remove the max element n times
     * and each removal is log n complex
     */
    public void bottomUpHeapContruction(){
        // this is at exactly n/2 position
        int index = parent(heap.size()-1);
        // this n/2 index makes sure we actually start to
        // down-heapify at nodes with children
        for (int i = index; i >= 0; i--){
            downHeapify(i);
        }
    }

    /**
     * when performing a heap sort an  a heap
     * we know that the heap has certain properties
     * ie. parent > children, fill bottommost and leftmost first
     * .. parent = child - 1 / 2 ... child = parent * 2 + 1 or 2
     *
     *
     * but after we make a heap with bottomUpHeapConstruction
     * we can then perform the heapsort
     */
    public List<E> heapSort(List<E> list){
        bottomUpHeapContruction();
        List<E> alist = new ArrayList<>();
        while(heap.size() > 0 ){
            alist.add(this.removeMax());
        }
        return alist;
    }

    public String toString(){
        return heap.toString();
    }
    public int parent(int index){
        return ((index -1)/2);
    }
    public int leftChild(int index){
        return 2*index+1;
    }
    public int rightChild(int index){
        return 2*index+2;
    }
    public boolean hasLeftChild(int index){
        return leftChild(index) < heap.size();
    }
    public boolean hasRightChild(int index){
        return rightChild(index) < heap.size();
    }
}
