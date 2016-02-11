package data_structures;

/**
 * Created by Dorian on 28-Jan-16.
 * @ based on doubly linked list from same package
 */
public class Deque<E> {
    DoublyLinkedList<E> dList;

    public Deque(){
        dList = new DoublyLinkedList<>();
    }
    int size( ){
        return dList.size();
    }
    public boolean isEmpty(){
        return dList.isEmpty();
    }
    public E first(){
        return dList.getFirst();
    }

    public E last( ){
        return dList.getLast();
    }
    public void addFirst(E e){
        dList.add(0,e);
    }
    public void addLast(E e){
        dList.add(dList.size(),e);
    }
    public E removeFirst( ){
        return dList.remove(0);
    }
    public E removeLast(){
        return dList.remove(dList.size()-1);
    }
}
