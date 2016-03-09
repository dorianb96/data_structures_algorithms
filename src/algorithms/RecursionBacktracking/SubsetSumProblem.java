package algorithms.RecursionBacktracking;

import org.junit.Test;

/**
 * Created by Dorian on 23-Feb-16.
 */
public class SubsetSumProblem {
    public static boolean subsetSumProblem(int[] array, int target, int position){
        if (position > array.length || target < 0){
            return false;
        }
        else if (target == 0){
            return true;
        }
        else{
            for (int i = position; i < array.length; i++){
                if (subsetSumProblem(array, target-array[position], i+1)) {
                    return true;
                }
                else{
                    return subsetSumProblem(array,target,i+1);
                }
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] c = new int[]{20,10,5};

        System.out.println(SubsetSumProblem.subsetSumProblem(c,20,0));
    }

}
