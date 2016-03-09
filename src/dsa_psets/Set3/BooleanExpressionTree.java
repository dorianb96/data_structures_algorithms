package dsa_psets.Set3;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Dorian on 24-Feb-16.
 * DS&A
 */

public class BooleanExpressionTree<E> implements Tree<E> {
    private Node root;

    // I didn't specify Node with a special generic type since the Tree's scope already defines it
    // the node class implements position and contains value,parent,leftChild and rightChild references
    private class Node implements Position<E> {
        @Override
        public E value() {
            return null;
        }
        public Node(E value, Node parent){
            this.value = value;
            this.parent = parent;
        }

        private E value;
        private Node parent;
        private Node left;
        private Node right;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }

    // this is a helper method for casting from position interface type back to Node type
    public Node validate(Position<E> p){
        if(p instanceof BooleanExpressionTree.Node){
            return (Node) p;
        }
        return null;
    }

    @Override
    public Position<E> root() {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws NullPointerException {
        if(p == null) throw new NullPointerException("Element is null");
        Node node = validate(p);
        return node.getParent();
    }

    @Override
    public List<Position<E>> children(Position<E> p)  {
        List<Position<E>> list = new ArrayList<>(2);
        Node node = validate(p);
        list.add(node.getLeft());
        list.add(node.getRight());
        return list;
    }

    @Override
    public boolean isInternal(Position<E> p) throws NullPointerException {
        if(p == null) throw new NullPointerException("Element is null");
        Node node = validate(p);
        return node.getLeft() != null || node.getRight() != null;
    }

    @Override
    public boolean isExternal(Position<E> p) throws NullPointerException {
        if(p == null) throw new NullPointerException("Element is null");
        Node node = validate(p);
        return node.getLeft() == null && node.getRight() == null;
    }

    @Override
    public boolean isRoot(Position<E> p)throws NullPointerException  {
        if(p == null) throw new NullPointerException("Element is null");
        return parent(p) == null;
    }

    @Override
    public int size() {
        return elements().size();
    }

    // returns elements in a level order by performing breadth first traversal
    @Override
    public Set<E> elements() {
        Queue<Node> queue = new ConcurrentLinkedQueue<>();
        Set<E> set = new LinkedHashSet<>();
        // we start the queue with root
        queue.offer(validate(root()));
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            set.add(temp.getValue());
            if(temp.getLeft()!= null){
                queue.offer(temp.getLeft());
            }
            if(temp.getRight()!= null){
                queue.offer(temp.getRight());
            }
        }
        return set;
    }

    // swap elements works by swapping the values of each positional node
    @Override
    public void swapElements(Position<E> p1, Position<E> p2)  {
        Node node1 = validate(p1);
        Node node2 = validate(p2);
        E temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    // root can't have null value
    public Position<E> addRoot(E value ) {
        this.root = new Node(value,null);
        return root();
    }

    public Position<E> addLeft(Position<E> p, E value ) throws NullPointerException{
        if (p == null ) throw new NullPointerException();
        Node node = validate(p);
        Node newLeftNode =  new Node(value, node);
        node.setLeft(newLeftNode) ;
        return newLeftNode;
    }

    public Position<E> addRight(Position<E> p, E value) throws NullPointerException{
        if (p == null ) throw new NullPointerException();
        Node node = validate(p);
        Node newRightNode = new Node(value, node);
        node.setRight(newRightNode) ;
        return newRightNode;
    }

    // StringBuilder is instance variable so that recursive functions can access it easily
    // it is used instead of string since string concatenation is very inefficient
    StringBuilder preOrder;
    public String preOrderTraversal(){
        preOrder = new StringBuilder(size()*3);
        preOrderTraversal(root);
        return preOrder.toString();
    }

    public void preOrderTraversal(Position<E> p){
        Node node = validate(p);
        // this is the tricky part
        // first we add the parenthesis
        // and
        if(node.getLeft() != null){
            preOrder.append("(");
        }
        preOrder.append(" ");
        preOrder.append(node.getValue());
        preOrder.append(" ");
        if(node.getLeft() != null){
            preOrderTraversal(node.getLeft());
        }
        if(node.getRight() != null){
            preOrderTraversal(node.getRight());
        }
        if(node.getRight() != null){
            preOrder.append(")");
        }
    }

    StringBuilder postOrder;
    public String postOrderTraversal(){
        postOrder = new StringBuilder(size()*3);
        postOrderTraversal(root);
        return postOrder.toString();
    }

    // post order traverse first traverses the children
    // and only after all is done it return to root
    public void postOrderTraversal(Position<E> p){
        Node node = validate(p);
        if (node.getLeft() != null) {
            postOrder.append("(");
            postOrderTraversal(node.getLeft());
        }
        if(node.getRight() != null) {
            postOrderTraversal(node.getRight());
            postOrder.append(")");
        }
        postOrder.append(" ");
        postOrder.append(node.getValue());
        postOrder.append(" ");
    }

    // I added in order just to showcase it in the tester
    StringBuilder inOrder;
    public String inOrderTraversal(){
        inOrder = new StringBuilder(size()*3);
        inOrderTraversal(root);
        return inOrder.toString();
    }
    public void inOrderTraversal(Position<E> p){
        Node node = validate(p);
        if (node.getLeft() != null) {
            inOrder.append("(");
            inOrderTraversal(node.getLeft());
        }
        inOrder.append(" ");
        inOrder.append(node.value);
        inOrder.append(" ");
        if(node.getRight() != null) {
            inOrderTraversal(node.right);
            inOrder.append(")");
        }
    }

    // this is done without parenthesis
    public String evaluateTree(Position <E> p){
        Node node = validate(p);
        if (isExternal(node)){
            return (String) node.getValue();
        }
        else{
            // we pass a finished form to special function which parses the expression
            return parse((String) node.getValue() ,evaluateTree(node.getLeft()),evaluateTree(node.getRight()));
        }
    }

    public String parse(String operator, String n1, String n2){
        switch (operator) {
            case "AND":
                return Boolean.toString(Boolean.parseBoolean(n1) && Boolean.parseBoolean(n2));
            case "XOR":
                return Boolean.toString(Boolean.parseBoolean(n1) ^ Boolean.parseBoolean(n2));
            case "OR":
                return Boolean.toString(Boolean.parseBoolean(n1) || Boolean.parseBoolean(n2));
            case "->":
                if (Boolean.parseBoolean(n1))
                    return Boolean.toString(Boolean.parseBoolean(n2));
                else
                    return Boolean.toString(true);
            default:
                return "";
        }
    }
}
