package dsa_psets.Set2;

/**
 * Created by Dorian on 15-Feb-16.
 */
public class HotPotatoGame<E> {
    @SuppressWarnings("unchecked")
    public E playHotPotato(SequenceADT<E> sequenceADT, int k){
        if (sequenceADT.size() != 1){
            sequenceADT.passHotPotato(k);
            playHotPotato(sequenceADT,k);
        }
        return sequenceADT.first();
    }
}
