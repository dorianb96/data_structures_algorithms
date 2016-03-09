package algorithms.DynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Dorian on 23-Feb-16.
 *
 * good guide:

    https://www.youtube.com/watch?v=8LusJS5-AGo

 */
public class KnapsackProblem {
    public static int knapsackProblem(int[] weights, int values[], int maxWeight){
        if (weights.length != values.length) throw new IllegalArgumentException("Weights and values must be equal");
        int[][] memo = new int[values.length+1][maxWeight+1];

        // set the first row to minimum
        for (int i = 0; i < memo[0].length; i++)  memo[0][i] = 0;

        // loop with i will go over all the elements we have at disposal
        for (int i = 1; i < values.length + 1; i++){
            // loop with  j will go over all possible weight capacities
            for (int j = 1; j < maxWeight + 1; j++){
                // because i is shifted by 1, we always need to adjust it by doing maxWeight[i-1] and values[i-1]
                // if the weight of current item is bigger than allowed weight of knapsack, use solution for row below
                if (weights[i-1] > j){
                    memo[i][j] = memo[i-1][j];
                }
                // otherwise, if you can place the item into the knapsack
                // chose the largest of:
                // 1) the value from row just below, ie the best solution without current item at the current weight j
                // 2) the value of current item plus the maximal value for the rest of empty space of knapsack
                // you access that by going one row down and looking what is the maximal value of knapsack for remaining weight
                // and the remaining weight is current weight j (which you are currently trying to fill) minus weight of the item
                else{
                    memo[i][j] = Math.max(memo[i-1][j], values[i-1] + memo[i-1][j-weights[i-1]]);
                }
            }
        }
        for (int[] row : memo) System.out.println(Arrays.toString(row));
        return memo[memo.length-1][memo[0].length-1];
    }

    @Test
    public void tester(){
        int[] weights = {2,3,4,5};
        int[] values = {3,4,5,6};
        int weight = 5;
        int max = KnapsackProblem.knapsackProblem(weights,values,weight);
        assert max == 7;
        System.out.println(max);
    }
}
