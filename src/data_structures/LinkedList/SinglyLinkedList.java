package data_structures.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E>{

    class Node <E>{
        // don't forget to say the next node is also of
        // type Node<E>
        Node<E> next;
        E value;
        public Node(E value){
            this.value = value;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // just add to the end of the array
    public void add(E value){
        if (value == null){
            throw  new NullPointerException("Please send a real object");
        }
        else if (head == null){
            this.head = new Node<E>(value);
        }
        else if (tail == null){
            tail = new Node<E>(value);
            head.next = tail;
        }
        else{
            this.tail.next = new Node<E>(value);
            tail = tail.next;
        }
        size++;
    }

    public void add(E value, int index){
        Node<E> newNode = new Node<E>(value);
        Node<E> node = this.head;
        if (index > size){
            throw new IllegalArgumentException("The index is too large");
        }
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        Node<E> tempNode = node.next;
        node.next = newNode;
        newNode.next = tempNode;
    }

    public E remove (int index){
        Node<E> node = head;
        for (int i = 0; i < index-1; i++){
            node = node.next;
        }
        E tempValue = node.next.value;
        node.next = node.next.next;
        size--;
        return  tempValue;
    }

    public E get(int index){
        if (index > size){
            throw new ArrayIndexOutOfBoundsException("The List isnt that big");
        }
        Node<E> node = head;
        for (int i = 0; i< index; i++){
            node = node.next;
        }
        return node.value;
    }

    public int size(){
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E>{
        Node<E> node = head;
        @Override
        public boolean hasNext() {
            if (size == 0){
                return false;
            }
            return (node != null);
        }

        @Override
        public E next() {
            E value = node.value;
            node  = node.next;
            return value;
        }
    }

}
