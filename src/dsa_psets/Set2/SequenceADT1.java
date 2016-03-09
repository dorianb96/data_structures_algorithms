package dsa_psets.Set2;

/**
 * uses a doubly linked list
 * removal is done in O(1)
 * accessing the next item runs in O(k), where k is number of iterations needed to reach the wanted object
 */
public class SequenceADT1<E> implements SequenceADT<E>{
    // adapter design pattern
    private CircularDoublyLinkedList doublyLinkedList = new CircularDoublyLinkedList();
    // private field storing the current node, used for next hot potato method
    private CircularDoublyLinkedList.Node currentNode;

    public SequenceADT1(){
        currentNode = doublyLinkedList.first();
    }

    @Override
    public int size() {
        return doublyLinkedList.size();
    }

    @Override
    public E first() {
        return doublyLinkedList.first().value;
    }

    @Override
    public void add(E value) {
        doublyLinkedList.add(value);
    }

    @Override
    public void passHotPotato(int k) {
        // start the search from head
        while (k>=0){
            currentNode = currentNode.next;
            if (currentNode.value != null) {
                System.out.print(k-- + ". " + currentNode.value + " ");
            }
        }
        System.out.println("--Get rid of " + currentNode.value);
        // may be null but already taken care of inside remove method and program execution
        // the remove method removes links from previous and next node but doesn't change
        // the current node so we can still use it for iteration
        doublyLinkedList.remove(currentNode);
    }
    // using the adapter pattern, sequence will perform operation on the CircularDoublyLinkedList
    // the generic class E is already in the scope
    class CircularDoublyLinkedList {
        public CircularDoublyLinkedList(){
            head = new Node(null,null,null);
            tail = new Node(head,head,null);
            head.next = tail;
            head.previous = tail;
            size = 0;
        }
        protected class Node{
            public Node(Node previous, Node next, E value){
                this.previous = previous; this.next = next; this.value = value;
            }
            Node  next;
            Node previous;
            E value;
        }

        private Node head;
        private Node tail;
        private int size;

        public int size() {
            return this.size;
        }

        public void add(E value) {
            // only head and tail can have null values
            if (value == null) throw new IllegalArgumentException("Sequence ADT doesn't accept null values");
            // in case the size is 0 and head is null, we will assign it first
            if (size == 0){
                Node node = new Node(head,tail,value);
                head.next = node;
                tail.previous = node;
            }
            else{
                Node node = new Node(tail.previous,tail,value);
                tail.previous.next = node;
                tail.previous = node;
            }
            size++;
        }
        // this is where the first element is stored
        public Node first(){
            return head.next == null ?  null : head.next;
        }

        public void remove(Node node) {
            if (node == null ) throw new IllegalArgumentException("Can't remove null node");
            if (node.value == null) throw new IllegalArgumentException("Can't remove internal nodes");
            if (size == 0) throw new IllegalArgumentException("The collection is empty");
            // change the pointers
            node.previous.next = node.next;
            node.next.previous = node.previous;
            // facilitate garbage collection
            --size;
        }
    }
}

