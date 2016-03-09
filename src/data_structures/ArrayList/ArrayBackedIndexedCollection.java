package data_structures.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBackedIndexedCollection<T> implements Iterable<T>{
    @Override
    public String toString() {
        return "ArrayBackedIndexedCollection{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayBackedIndexedCollection<?> that = (ArrayBackedIndexedCollection<?>) o;

        if (size != that.size) return false;
        if (capacity != that.capacity) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, that.elements);

    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + capacity;
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }
// duplicate elements allowed, null references not allowed

    private int size; // the number of real elements in the array
    private int capacity;  // number of elements that can be in the array
    private T[] elements;

    public ArrayBackedIndexedCollection(){
        this(16);
    }
    public ArrayBackedIndexedCollection ( int capacity) throws IllegalArgumentException{
        if (capacity < 1){
            throw new IllegalArgumentException("Capacity must be at least one");
        }
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }
    public boolean isEmpty(){
        return (this.size == 0);
    }
    public int size(){
        return this.size;
    }


    // complexity is: for array copy O(n)
    // otherwise O(1)
    public void add(T value) throws NullPointerException{
        if (value == null) throw new NullPointerException("Collection doesn't hold null references");
        if (this.size == this.capacity){
            T[] tempArray;
            tempArray = (T[]) new Object[capacity*2];

            for (int i = 0; i < capacity; i++){
                tempArray[i] = elements[i];
            }
            this.elements = tempArray;
            this.capacity *= 2;
        }
        elements[size] = value;
        size++;
    }
    // complexity is O(1)
    public T get(int index) throws ArrayIndexOutOfBoundsException{
        if (index >= size) throw new ArrayIndexOutOfBoundsException("Array index out of bounds");
        return (elements[index]);
    }
    // complexity  is O(n/2-1)
    public void remove(int index){
        for (int i = index; i < capacity-1; i++){
            elements[i] = elements[i+1];
        }
        size--;
    }
    // complexity of insert is O(n/2 + 1)
    public void insert(T value, int position){
        if (value == null) throw new NullPointerException("Collection doesn't hold null references");
    //    if (position > size) throw new ArrayIndexOutOfBoundsException("Array index out of bounds");
        if (this.size == this.capacity){
            T[] tempArray;
            tempArray = (T[]) new Object[capacity*2];
            for (int i = 0; i < capacity; i++){
                tempArray[i] = elements[i];
            }
            this.elements = tempArray;
            this.capacity *= 2;
        }
        if (position < size) {
            for (int i = position; i < size; i++) {
                elements[i + 1] = elements[i];
            }
        }
        elements[position] = value;
        size++;
    }
    // complexity is O(n/2)
    public int indexOf(T value){
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }
    // complexity is 0(n)
    public boolean contains(T value){
        if (size != 0) {
            for (T element : elements) {
                if (element.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void clear(){
        elements =(T[]) new Object[capacity];
        size = 0;
    }

    private class ArrayIterator implements Iterator<T>{
        private int index;
        @Override
        public boolean hasNext() {
            return(index < size);
        }

        @Override
        public T next() {
            if (this.hasNext()){
                return elements[index++];
            }
            throw new NoSuchElementException("No next element");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Removal not supported");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
}
