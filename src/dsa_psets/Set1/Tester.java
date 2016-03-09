package dsa_psets.Set1;

/**
 * Created by Dorian on 31-Jan-16.
 */
public class Tester {
    public static void main (String[] args){
        Queue<Integer> queue = new ResizableQueue<Integer>();

        //QQ D QQQQQ D Q D QQQQQ DDDDDDDDDDD
        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println(queue.dequeue());

        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println("Queue: " + queue);

        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);

        System.out.println(queue.dequeue());

        queue.enqueue(8);

        System.out.println("Queue: " + queue);

        System.out.println(queue.dequeue());

        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);

        System.out.println("Queue: " + queue);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("Queue: " + queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("Queue: " + queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }
}
