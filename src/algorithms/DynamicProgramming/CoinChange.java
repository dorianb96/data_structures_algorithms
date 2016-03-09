package algorithms.DynamicProgramming;

import org.junit.Test;
import org.junit.experimental.max.MaxCore;

import java.util.Arrays;

/**
 * Created by Dorian on 27-Feb-16.
 * DS&A
 * Given an infinite supply of coins of values: {C1, C2, ..., Cn} and a sum.
 * Find minimum number of coins that can represent the sum.
 *
 * http://www.ideserve.co.in/learn/minimum-number-of-coins-to-make-change
 */
public class CoinChange {
    public static int coinChange(int[] coins, int target){
        int min = 0;
        int[] minCoins = new int[target+1];
        minCoins[0] = 0;
        for(int i = 1; i <= target; i++){
            min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++) {
                if(i >= coins[j])
                    min = Math.min(min, minCoins[i - coins[j]]);
            }
            if(min != Integer.MAX_VALUE)
                minCoins[i] = min + 1;
            else
                minCoins[i] = Integer.MAX_VALUE;
            }
        return minCoins[target];
    }



    @Test
    public void tester(){
        int target = 9;
        int[] coins = new int[]{2,5,4,6};
        int min = CoinChange.coinChange(coins, target);
        System.out.println(min);
    }
}
