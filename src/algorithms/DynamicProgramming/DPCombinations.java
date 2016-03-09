package algorithms.DynamicProgramming;

import java.util.Arrays;

/**
 * Created by Dorian on 20-Feb-16.
 *
 *
 consider the problem of determining the
 number of combinations of 2, 3, and 7 point plays that can generate a score of
 222. Let C(s) be the number of combinations that can generate a score of s. Then
 C(222) = C(222 - 7) + C(222 - 3) + C(222 - 2), since a combination ending with a 2
 point play is different from one ending with a 3 point play, and a combination ending
 with a 3 point play is different from one ending with a 7 point play, etc.
 */
public class DPCombinations {
    public static int DPCombos(int target, int[] args, int[] memo) {
        if (target < 0) {
            return 0;
        } else if (target == 0){
            return 1;
        }
        else if (memo[target] != 0){
            return memo[target];
        }
        else{
            for (int i = 0; i < args.length; i++) {
                //  System.out.println(target);
                memo[target] += DPCombos(target - args[i], args, memo);
            }
            System.out.println(Arrays.toString(memo));
            return memo[target];
        }
    }
}
