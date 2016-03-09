package data_structures.Stack;

import data_structures.ArrayList.ArrayBackedIndexedCollection;

import java.util.Iterator;

public class StackObject <T> implements Iterable<T> {

    private ArrayBackedIndexedCollection<T> array = new ArrayBackedIndexedCollection<>();

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
    public static class EmptyStackException extends RuntimeException{
        public EmptyStackException(){}
        public EmptyStackException(String message){super(message);}
    }
    public boolean isEmpty(){
        return array.isEmpty();
    }
    public int size(){
        return array.size();
    }
    public void push(T value){
        array.insert(value,array.size());
    }
    public T pop() throws EmptyStackException{
        if(array.size()==0){
            throw new EmptyStackException("Stack is empty");
        }
        T value = array.get(array.size()-1);
        array.remove(array.size()-1);
        return value;
    }
    public T peek() throws EmptyStackException{
        if(array.size()==0){
            throw new EmptyStackException("Stack is empty");
        }
        T value = array.get(array.size()-1);
        return value;
    }
    public void clear(){
        array.clear();
    }

}
