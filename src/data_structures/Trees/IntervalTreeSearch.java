package data_structures.Trees;

import algorithms.DivideAndConquer.BinarySearch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 05-Mar-16.
 * DS&A
 *
 * searches for all elements in a tree within a range
 */
public class IntervalTreeSearch<E extends Comparable<E>> {
    public List<E> intervalTreeSearch(BinarySearchTree tree, E high, E low){
        ArrayList<E> list = new ArrayList<>();
        intervalFind(high, low , tree.root,list);
        return list;
    }
    public void intervalFind(E high, E low, BinarySearchTree.Node node, ArrayList list){
        if(node == null){
            return;
        }
        // if the value of node is more than maximal value of range
        // go to the elements smaller then node ie. to the left
        else if (node.value.compareTo(high) > 0){
            intervalFind(high, low ,node.left,list);
        }
        // if the value is smaller than lowest value of interval
        // go the elements larger than node ie to the right
        else if(node.value.compareTo(low) < 0){
            intervalFind(high,low,node.right,list);
        }
        // if the value is within the range, and it to the list
        // also then explore both children
        else{
            list.add(node.value);
            intervalFind(high, low ,node.left,list);
            intervalFind(high,low,node.right,list);
        }
    }


    @Test
    public void tester(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(-2);
        tree.add(3);
        tree.add(7);
        tree.add(8);
        tree.add(-4);
        tree.add(12);
        tree.add(10);
        tree.add(8);
        tree.add(-9);
        tree.add(99);
        tree.add(11);

        IntervalTreeSearch its = new IntervalTreeSearch();
        System.out.println(its.intervalTreeSearch(tree,12,0));

    }
}
