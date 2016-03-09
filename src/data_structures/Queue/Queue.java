package data_structures.Queue;

/**
 * Created by Dorian on 28-Jan-16.
 * @uses circular array as background storage
 */
public class Queue<E> {

    private E[] data;    // circular array
    private int size;
    private int position;
    private static final int CAPACITY = 1000;

    // DO NOT USE GENERICS IN THE CONSTRUCTOR NAME
    public Queue(){
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Queue (int capacity){
        data = (E[]) new Object[capacity];
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public void enqueue(E element) throws IllegalStateException{
        if (size == data.length) throw new IllegalStateException("Queue is full");
        int index = (position + size) % data.length;
        System.out.println(index);
        data[index] = element;
        size++;
    }

    public E dequeue(){
        if (isEmpty()) return null;
        E returnElement = data[position];
        data[position] = null;
        position = (position + 1) % data.length;
        size--;
        return returnElement;
    }

    public E first(){
        if (isEmpty()){return null;}
        return data[position];
    }

    public boolean contains(E value){
        for (E elem : data){
            if (value.equals(elem)){
                return true;
            }
        }
        return false;
    }
}
