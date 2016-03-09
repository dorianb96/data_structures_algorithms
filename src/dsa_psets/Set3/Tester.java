package dsa_psets.Set3;

import org.junit.Test;

/**
 * Created by Dorian on 24-Feb-16.
 *
 *
 * root(): Return the root of the tree.
 * parent(v): Return the parent of node v; an error occurs if v is root.
 * children(v): Return a set containing the children of node v.
 * isInternal(v): Test whether node v is internal.
 * isExternal(v): Test whether node v is external.
 * isRoot(v): Test whether node v is the root.
 * size(): Return the number of nodes in the tree.
 * elements(): Return a set containing all the elements stored at nodes of the tree.
 * swapElements(v,w): Swap the elements stored at the nodes v and w.
 *
 Boolean Expression Tree (Group 2: Task 3)

 Write a program that prints a boolean expression tree in pre- and postorder
 and computes the value of the expression that it represents.

 Implement the position ADT and a tree node class that implements it.
 A tree node should have references to its parent node, to the element stored in it,
 and to its two children. The boolean expression tree we are going to build is binary.
 Implement the tree ADT. You don't need to implement exceptions and the methods iterator(), positions() and replace(v,e).
 For testing, use a tree representing the following expression (with all nodes containing strings):
 (((X AND Y) OR (X XOR Y))->(X AND Y)) You don't need to build the tree from the string, you can create it yourself node by node.
 X and Y represents variables that can be either false or true.
 Implement two methods that return a parenthetic string representation of the tree in preorder and in postorder.
 Implement a method that traverses the tree in a suitable order and computes the value of the expression it represents.
 */

public class Tester {
    @Test
    public void tester(){
        BooleanExpressionTree<String> tree = new BooleanExpressionTree<>();

        String X = "false";
        String Y = "false  ";

        Position<String> root = tree.addRoot("->");
        Position<String> node1 = tree.addLeft(root,"OR");
        Position<String> node2 = tree.addRight(root,"AND");

        Position<String> node3 = tree.addLeft(node1,"AND");
        Position<String> node4 = tree.addRight(node1,"XOR");

        Position<String> node5 = tree.addLeft(node3, X);
        Position<String> node6 = tree.addRight(node3, Y);

        Position<String> node7 = tree.addLeft(node4, X);
        Position<String> node8 = tree.addRight(node4, Y);

        Position<String> node9 = tree.addLeft(node2, X);
        Position<String> node10 = tree.addRight(node2, Y);

        System.out.println("Pre order: " + tree.preOrderTraversal() + "   results in: " + tree.evaluateTree(root));
        System.out.println("In order: " + tree.inOrderTraversal() + "   results in: " + tree.evaluateTree(root));
        System.out.println("Post order: "+ tree.postOrderTraversal() + "   results in: " + tree.evaluateTree(root));
       // System.out.println(tree.elements());
    }
}
