package dsa_psets;

/**
 * Created by Dorian on 31-Jan-16.
 */
public interface Queue<E> {
    void enqueue(E element);

    E dequeue();

    E front();

    int size();

    boolean isEmpty();

    String toString();
}
