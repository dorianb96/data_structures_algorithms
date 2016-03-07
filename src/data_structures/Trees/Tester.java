package data_structures.Trees;

import data_structures.Trees.BinarySearchTree;
import org.junit.Test;


public class Tester {
    @Test
    public void test() {


        BinarySearchTree<Integer> heapList = new BinarySearchTree<Integer>();
        heapList.add(-2);
        heapList.add(9);
        heapList.add(1);
        heapList.add(-4);
        heapList.add(11);
        heapList.add(32);
        heapList.add(0);
        heapList.add(5);

        heapList.depthFirstSearch();
        heapList.breadthFirstSearch();

    }
}
