package data_structures;

import dsa_psets.ResizableQueue;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Dorian on 02-Feb-16.
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    class Node<E extends Comparable<? super E>>  {
        E value;
        Node<E> parent;
        //children
        Node<E> left;
        Node<E> right;

        /** you mostly add new elements to empty tree
         *  so when making a new node we will construct
         *  it's parent and value. but not childer
         */

        public Node(Node<E> parent , E value){
            this.parent = parent;
            this.value = value;
        }

        public String toString(){
            return this.value.toString();
        }
    }

    Node<E> root;
    int size;
    /**
     * first a binary search tree should have a way of computing height at any node
     */
    public int height(){
        return height(root);
    }
    public int height (Node<E> node){
        if (node == null) return 0;
        else{
            return 1 + Math.max(height(node.right),height(node.left));
        }
    }



    public void add(E value){
        add(root,null,value);
    }
    /**
     * we also need a good algorithm for adding items to the tree
     * we will return the node element which we added to the tree
     * @param value of the new node, root
     * @param node
     * @return node with it's position
     */
    public Node<E> add (Node<E> node,Node<E> parent, E value){
        if (root == null){
            this.root = new Node<>(parent,value);
            return root;
        }
        else{
            // if compare to is > 0 then value is less than node's value
            if (node == null){
                node = new Node<>(parent, value);
            }
            else if (node.value.compareTo(value) > 0){
                node.left = add(node.left,node,value);
            }
            else if(node.value.compareTo(value) < 0){
                node.right = add(node.right,node,value);
            }
            else {
                node.value = value;
            }

        }
        return node;
    }



    public Node<E> find (E value){
        return find(root,value);
    }

    public Node<E> find(Node<E> node, E value){
        if (node == null) {
            return null;
        }
        else {
            if (node.value.compareTo(value) > 0){
                node = find(node.left,value);
            }
            else if (node.value.compareTo(value) < 0){
                node = find(node.right,value);
            }
            else if (node.value.compareTo(value) ==0){
                return node;
            }
        }
        return node;
    }

    public void delete(E value){
        if (value == null) throw new IllegalArgumentException("Can't remove null");
        Node<E> node = find(value);

        // no children
        if (node.left == null && node.right == null){
            //check if it's parent's left or right child
            //then delete the reference to it since it is safe
            if (node.parent.left == node){
                node.parent.left = null;
            }
            else {
                node.parent.right = null;
            }
            // this speeds up the garbage collection
            node = null;
        }
        // two children
        // go find the rightmost child of left child or leftmost child of right child
        // i searched for rightmost child of left node
        else if (node.left != null && node.right != null){
            // go to the left child
            node = node.left;
            // find the rightmost node of left child
            while (node.right != null) {
                node = node.right;
            }
            // call this function again to remove that (rightmost child of left node)
            // usually it is trivial since it should have 0 or 1 child
            delete(node.value);
            // then again find the basic node you want to replace
            Node<E> baseNode = find(value);
            //replace the value of the node which you wish to remove
            baseNode.value = node.value;
        }
        // one child
        // just promote the child to parent's place
        else {
            // 1. find out on which side of the parent the node is
            // since you have to link the node's parent to the node's child

            // if on the left side of your parent
            if (node.parent.left.value == node.value){
                // which child exists? left -> then make left the parent's new child
                // else make it the right child
                node.parent.left = node.left != null ? node.left : node.right;
            }
            // if on the right side of your parent
            else{
                // which child exists? left -> then make left the parent's new child
                // else make it the right child
                node.parent.right = node.left != null ? node.left : node.right;
            }
        }
    }

    public void preOrderTraverse(){
        preOrderTraverse(root);
    }
    public void preOrderTraverse(Node<E> node){
        System.out.println(node);
        if (node.left != null)
            preOrderTraverse(node.left);
        if(node.right != null)
            preOrderTraverse(node.right);
    }

    public void inOrderTraverse(){
        inOrderTraverse(root);
    }
    public void inOrderTraverse(Node<E> node){
        if (node.left != null)
            inOrderTraverse(node.left);
        System.out.println(node);
        if(node.right != null)
            inOrderTraverse(node.right);
    }

    public void postOrderTraverse(){
        postOrderTraverse(root);
    }
    public void postOrderTraverse(Node<E> node){
        if (node.left != null)
            postOrderTraverse(node.left);
        if(node.right != null)
            postOrderTraverse(node.right);
        System.out.println(node);
    }


    public E max(){
        Node<E> node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.value;
    }

    public E min(){
        Node<E> node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.value;
    }

    private ArrayList<LinkedList<Node<E>>> collection;

    public ArrayList<LinkedList<Node<E>>> levelDepth(int target, boolean onlyOneLevel){
        collection = new ArrayList<LinkedList<Node<E>>>();
        int height = height(root);
        for (int i = 0; i < height ; i++) {
            collection.add(new LinkedList<Node<E>>());
        }
        if (onlyOneLevel) {
            levelDepth(root, 0, target);
        }
        else{
            levelDepth(root,0);
        }
        return collection;
    }
    public void levelDepth(Node<E> node,int level, int target) {
        if (level == target){
            LinkedList<Node<E>> list = collection.get(level);
            list.add(node);
        }
        if (node.left != null){
            levelDepth(node.left, 1 + level, target);
        }
        if (node.right != null){
            levelDepth(node.right, 1 + level, target);
        }
    }
    public void levelDepth(Node<E> node,int level) {
        LinkedList<Node<E>> list = collection.get(level);
        list.add(node);
        if (node.left != null){
            levelDepth(node.left, 1 + level);
        }
        if (node.right != null){
            levelDepth(node.right, 1 + level);
        }
    }



    public void breadthFirstSearch(){
        Node<E> node = root;
        ResizableQueue<Node<E>> queue = new ResizableQueue<Node<E>>();
        queue.enqueue(node);
        while (!queue.isEmpty()){
            node = queue.dequeue();
            if (node.left != null){
                queue.enqueue(node.left);
            }
            if (node.right != null){
                queue.enqueue(node.right);
            }
        }
    }

    public E nthLargest(int n){
        Node<E> node = root;
        this.n = n;
        while (node.right != null){

            node = node.right;
        }
        nthLargest(node);
        return value;

    }
    private int n;
    private E value;
    public void nthLargest(Node<E> node){
        this.n -= 1;
        if (this.n == 0) {
            this.value = node.value;
        }
        if (node.left != null) {
            node = node.left;
            nthLargest(node);
        }
    }
}
