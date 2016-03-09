package data_structures.HashTable;

import java.util.Arrays;

public class SimpleHashTable<K,V> {
    static class TableEntry<K,V>{
        private K key;
        private V value;
        TableEntry<K,V> next;

        public TableEntry(K key, V value, TableEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public K getKey (){
            return this.key;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public V getValue(){
            return this.value;
        }
        @Override
        public String toString() {
            return key +
                    " = " + value;
        }
    }
    /**
     * instance fields
     */
    private int size = 0;
    private int capacity;

    private TableEntry<K,V> [] hashTable;

    /**
     * constructors
     */
    public SimpleHashTable(){
        this(16);
    }
    @SuppressWarnings("unchecked")
    public SimpleHashTable(int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException("Minimal capacity is 1");
        }
        this.capacity = (int) (Math.pow(2,
                (int) Math.ceil(Math.log(capacity)/ Math.log(2) ) ) );
        /**
         * KEY STATEMENT, ABSOLUTELY CRUCIAL !!!
         *
         * it would work without the <K ,V> cast addition
         *
         */
        hashTable = (TableEntry<K,V> []) new TableEntry[this.capacity];
    }


    /**
     * methods
     */
    public void put(K key, V value){
        if (key == null){
            throw new UnsupportedOperationException("Key can't be null");
        }
        int index = Math.abs(key.hashCode() % capacity);
        if (hashTable[index] == null){
            hashTable[index] = new TableEntry<K,V>(key,value,null);
            size++;
        }
        else {
            TableEntry<K,V> element = hashTable[index];
            // VERY COOL ALGORITHM
            while (element.next != null && (!element.key.equals(key))) {
                element = element.next;
            }
            if (element.key == key) {
                element.value = value;
            }
            else {
                element.next = new TableEntry<>(key,value,null);
                size++;
            }

        }
        checkUsage();
    }

    @SuppressWarnings("unchecked")
    public void checkUsage(){
        if (size > 0.75 * capacity){
            this.capacity *= 2;
            this.size = 0;
            TableEntry<K,V>[] table = hashTable;
            hashTable = (TableEntry<K,V>[]) new TableEntry[capacity];
            for (TableEntry<K,V> element : table){
                while (element != null){
                    put(element.key,element.value);
                    element = element.next;
                }
            }
        }
    }

    public V get(K key){
        if (key == null){
            throw new UnsupportedOperationException("Key can't be null");
        }
        int index = Math.abs(key.hashCode() % capacity);
        if (hashTable[index] == null){
            return null;
        }
        else {
            TableEntry<K, V> element = hashTable[index];
            while (element != null && (!element.key.equals(key))) {
                element = element.next;
            }
            return element == null ? null : element.value;
        }
    }

    public void remove (K key){
        if (key == null){
            throw new UnsupportedOperationException("Key can't be null");
        }
        int index = Math.abs(key.hashCode() % capacity);
        TableEntry<K,V> previous = null;
        TableEntry<K,V> element = hashTable[index];
        while (element != null && !element.key.equals(key)){
            previous = element;
            element = element.next;
        }
        if (element == null) return;
        if (element.key.equals(key)){
            size--;
            if (previous == null){
                hashTable[index] = element.next;
            }
            else{
                previous.next = element.next;
            }

        }

    }

    // because of hash code we can easily find and access the sought element
    public boolean containsKey(K key){
        if (key == null){
            throw new UnsupportedOperationException("Key can't be null");
        }
        int index = Math.abs(key.hashCode() % capacity);
        if (hashTable[index] != null){
            TableEntry<K,V> element = hashTable[index];
            while (element.next != null){
                if (element.key.equals(key)){
                    return true;
                }
                element = element.next;
            }
        }
        return false;
    }

    // values aren't hashable and thus they aren't easy to find in O(1) as key
    public boolean containsValue(V value){
        if (value == null){
            throw new UnsupportedOperationException("Value can't be null");
        }
        // we have to search every element of the array to find it, this takes us O(n) complexity
        for (TableEntry<K,V> element : hashTable){
            if (element.value.equals(value)){
                return true;
            }
            // since each array element is a part of linked list, we also have to check the linked list
            while (element.next != null){
                element = element.next;
                if (element.value.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isEmpty(){
        return size==0;
    }

    public int size() {
        return size;
    }

    public void clear(){
        for (int i =0; i < hashTable.length; i++){
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (size ==0){
            return "";
        }
        StringBuilder builder = new StringBuilder("[");
        for (TableEntry<K, V> element : hashTable) {
            while (element != null) {
                builder.append(element.toString());
                builder.append(", ");
                element = element.next;
            }
        }
        // this cleans us from the final ", " added to string builder
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append("]");
        return builder.toString();
    }
    public String hashTable(){
        return Arrays.toString(hashTable);
    }
    public int getCapacity(){
        return this.capacity;
    }


}
