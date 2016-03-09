package algorithms.DynamicProgramming;

/**
 * Created by Dorian on 22-Feb-16.
 */
public class IterativeMaxSubArray {
    public static int MaxSubArray(int[] array){
        // this is the global maximum of the array
        int maxArray = Integer.MIN_VALUE;
        // scan through each element in array
        for (int i = 0;  i< array.length; i++){
            // make a local maximal element which
            int maxSub = Integer.MIN_VALUE;
            // sum up all the values preceding the current element of iteration
            // and then after each addition compare the sum to maximal sum so far
            int sum = 0;
            for (int j = i; j < array.length; j++){
                sum+= array[j];
                if (sum > maxSub){
                    maxSub = sum;
                }
            }
            // after we computed the maximal sum for 0...i,
            // we compare it with the global solution for all i elements
            if (maxSub > maxArray){
                maxArray = maxSub;
            }
        }
        return maxArray;
    }
}
