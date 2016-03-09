package dsa_psets.Set2;

/**
 * Created by Dorian on 15-Feb-16.
 */
public interface SequenceADT<E> {
    int size();
    E first();
    void add (E value);
    void passHotPotato(int k);
}
