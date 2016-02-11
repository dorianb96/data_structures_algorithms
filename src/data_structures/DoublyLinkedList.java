package data_structures;

import java.util.*;

public class DoublyLinkedList<E> {
    private static class Node<E> {
        E value;
        Node<E> previousNode;
        Node<E> nextNode;
        public Node(E value, Node<E> previousNode, Node<E> nextNode){
            this.value = value;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.nextNode = trailer;
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Object o) {
        if (size == 0){
            return false;
        }
        Node<E> node = header.nextNode;
        while (node != null){
            System.out.println(node.value);
            if (node.value == o){
                return true;
            }
            node = node.nextNode;
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public <E> ArrayList<E> toArray() {
        ArrayList<E> array = new ArrayList<>(size);
        Node<E> node = (Node<E>) header.nextNode;
        int index = 0;
        while (index < size){
            array.add(node.value);
            node = node.nextNode;
            index++;
        }
        return array;
    }

    // by default it adds to the end of the list
    public boolean add(E e) {
        if ( e == null){
            return false;
        }
        if (size == 0){
            Node<E> newNode= new Node<>(e,header,trailer);
            header.nextNode = newNode;
            trailer.previousNode = newNode;
            size++;
        }
        else {
            Node<E> node = header.nextNode;
            while (node.nextNode.value != null) {
                node = node.nextNode;
            }
            node.nextNode = new Node<E>(e, node, node.nextNode);
            node = node.nextNode;
            node.nextNode.previousNode = node;
            size++;
        }
        return true;
    }

    public void add(int index, E element) {
        if (index > size){
            throw new Error("The list doesn't have such an element");
        }
        if (size == 0){
            Node<E> newNode= new Node<>(element,header,trailer);
            header.nextNode = newNode;
            trailer.previousNode = newNode;
        }
        Node<E> node = header;
        for (int i = 0; i < index; i++){
            node = node.nextNode;
        }
        Node<E> newNode = new Node<E>(element,node,node.nextNode);
        node.nextNode.previousNode = newNode;
        node.nextNode = newNode;
        size++;
    }

    public E remove(int index) {
        if (size == 0){
            return null;
        }
        Node<E> node = header.nextNode;
        for (int i = 0; i < index; i++){
            node=node.nextNode;
        }
        E returnValue = node.value;
        node.nextNode.previousNode = node.previousNode;
        node.previousNode.nextNode = node.nextNode;
        size--;
        return returnValue;
    }

    public E get (int index){
        Node<E> node = header.nextNode;
        for (int i = 0; i < index; i++){
            node= node.nextNode;
        }
        return node.value;
    }

    public E getFirst(){
        return header.nextNode.value;
    }

    public E getLast(){
        return trailer.previousNode.value;
    }
}
