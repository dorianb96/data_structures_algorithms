package dsa_psets.Set2;

/**
 * uses an array
 * removal is done in O(n) since it has to shift all items after removing one item
 * accessing the next item runs in O(1) since all elements are indexed
 */
public class SequenceADT2<E> implements SequenceADT<E> {
    private E[] array;
    private int size;
    private int currentIndex;

    @SuppressWarnings("unchecked")
    public SequenceADT2(){
        array = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if (size == 0) throw new UnsupportedOperationException("Sequence is empty");
        return array[0];
    }

    @Override
    public void add(E value) {
        if (value == null) throw new IllegalArgumentException("Can't add null element");
        if (size == array.length-1){
            resize();
        }
        array[size++] = value;
    }

    @Override
    public void passHotPotato(int k) {
        this.currentIndex = (currentIndex + k) % size;
        System.out.println(array[currentIndex]);
        delete(currentIndex);
    }

    @SuppressWarnings("unchecked")
    public void resize(){
        E[] temp = (E[]) new Object[array.length*2];
        System.arraycopy(array,0,temp,0,array.length);
    }

    public void delete (int index){
        if (size == 0) throw new IllegalArgumentException("Array is empty");
        if (index > size) throw new ArrayIndexOutOfBoundsException();

        System.arraycopy(array, index + 1, array, index, size - index);
        // releases the object
        array[--size] = null;
    }
}
