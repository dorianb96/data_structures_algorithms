package dsa_psets;

/**
 * Created by Dorian on 31-Jan-16.
 */
public class ResizableQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int size;

    /**
     * default capacity is 4
     * the cast from Object[] to E[] is safe
     * so we supressed the warnings
     */
    @SuppressWarnings("unchecked")
    public ResizableQueue(){
        data =(E[]) new Object[4];
    }

    @Override
    /**
     * shifts the queue to display how it really looks
     * the elements won't look like a circular array but
     * like a real queue
     * @return shifted string representation of the queue
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = front; i < front + size; i++){
            stringBuilder.append(data[i % data.length]).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    /**
     * adds element to the end of the queue
     * but the element can't be null
     * @param element to be added to the queue
     */
    @SuppressWarnings("unchecked")
    public void enqueue(E element) {
        // this queue implementation doesn't allow null elements
        if (element == null){
            throw new IllegalArgumentException("Element can't be null");
        }
        // if array is full, make a 2N array
        if (size == data.length-1){
            E[] newData = (E[]) new Object[data.length + 100];
            // this is the key algorithm
            // it add's the wrapped parts to the end of bigger queue
            for (int i = front; i < front + size; i++){
                newData[i % (data.length*2)] = data[i % data.length];
            }
            // our data array now stores the reference for larger
            data = newData;
        }
        int end = (front + size) % data.length ;
        data[end] = element;
        size++;
    }

    @Override
    /**
     * removes and returns the element from front of the queue
     * @return element at the front of the queue
     */
    public E dequeue() {
        if (isEmpty()){
            return null;
        }
        E tempElement = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return tempElement;
    }

    /**
     * @return size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * @return the element at front of queue
     */
    @Override
    public E front() {
        if (isEmpty()){
            return null;
        }
        return data[front] ;
    }
}
