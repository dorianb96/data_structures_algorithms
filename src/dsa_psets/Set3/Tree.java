package dsa_psets.Set3;

import java.util.List;
import java.util.Set;

/**
 * Created by Dorian on 24-Feb-16.
 * DS&A
 * root(): Return the root of the tree.
 * parent(v): Return the parent of node v; an error occurs if v is root.
 * children(v): Return a set containing the children of node v.
 * isInternal(v): Test whether node v is internal.
 * isExternal(v): Test whether node v is external.
 * isRoot(v): Test whether node v is the root.
 * size(): Return the number of nodes in the tree.
 * elements(): Return a set containing all the elements stored at nodes of the tree.
 * swapElements(v,w): Swap the elements stored at the nodes v and w.

 */
public interface Tree<E> {
    Position<E> root();
    Position<E> parent(Position<E> p) throws NullPointerException;
    List<Position<E>> children(Position<E> p) throws NullPointerException;
    boolean isInternal(Position<E> p) throws NullPointerException;
    boolean isExternal(Position<E> p) throws NullPointerException;
    boolean isRoot(Position<E> p) throws NullPointerException;
    int size();
    Set<E> elements();
    void swapElements(Position<E> p1,Position<E> p2) throws NullPointerException;
}
