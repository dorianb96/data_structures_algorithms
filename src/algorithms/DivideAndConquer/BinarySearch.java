package algorithms.DivideAndConquer;

/**
 * Created by Dorian on 14-Feb-16.
 */
public class BinarySearch {
    @SuppressWarnings("unchecked")
    // this is in place binary search, also a recursive version can be made
    public static <E extends Comparable<E>> boolean BinarySearch(E element, E[] array){
        int low = 0, high = array.length-1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (array[mid].compareTo(element) > 0){
                high = mid-1;
            }
            else if (array[mid].compareTo(element) < 0){
                low = mid+1;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
