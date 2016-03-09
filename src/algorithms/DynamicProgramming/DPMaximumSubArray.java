package algorithms.DynamicProgramming;

import org.junit.Test;

/**
 * Created by Dorian on 22-Feb-16.
 * given an array of integers A of length n,
 * find the interval indices a and b such that
 * sub array sum is maximized.
 *
 */
public class DPMaximumSubArray {
    public static int DPMaximumSubArray(int[] array){
        int maxEndingHere = 0, maxSoFar = 0;
        for (int x : array){
            // what is larger - current maximum sum up to here plus element, or just current element
            maxEndingHere = Math.max(x, maxEndingHere + x);
            // How do you handle cases when the x is larget than maxEndingHere but still isn't larger than the overall max sum
            maxSoFar = Math.max(maxSoFar,maxEndingHere);
        }
        return maxSoFar;
    }


    @Test
    public void tester(){
        int[] array = new int[]{904,40,523, 12, -335, -385, -124, 481, -31};
        int maxSum = DPMaximumSubArray(array);
        System.out.println(maxSum);
    }
}
