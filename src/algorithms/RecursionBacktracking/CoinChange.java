package algorithms.RecursionBacktracking;

/**
 * Created by Dorian on 23-Feb-16.
 *
 * counts in how many ways you can make a change of coins
 */
public class CoinChange {
    private int count = 0;
    private int[] memo;
    public void coinChange(int amount, int[] coins){
        coinChange(amount,coins,0);
        System.out.println(count);
    }
    public void coinChange(int amount, int[] coins, int position){
        if (amount == 0){
            count++;
        }
        else if (amount < 0){
            return;
        }
        else{
            // this is a great trick with the position pointer minimizing recursion
            for (int i = position; i < coins.length ; i++) {
              coinChange(amount-coins[i], coins, i);
            }
        }
    }
}
