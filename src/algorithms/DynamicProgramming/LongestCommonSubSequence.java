package algorithms.DynamicProgramming;

import org.junit.Test;

/**
 * Created by Dorian on 24-Feb-16.
 DS&A
 */
public class LongestCommonSubSequence {
    public static void longestCommonSubSequence(String str1, String str2) {
        int[][] memo = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    memo[i + 1][j + 1] = memo[i][j] + 1;
                } else {
                    memo[i + 1][j + 1] = Math.max(memo[i + 1][j], memo[i][j + 1]);
                }
            }
        }

        // this part just build the maximal sub sequence string
        StringBuilder sb = new StringBuilder();
        for (int x = str1.length(), y = str2.length(); x != 0 && y != 0; ) {
            // check if the increased number is coming from left side
            if (memo[x][y] == memo[x-1][y])
                x--;
            // check if the increased number is coming from above
            else if (memo[x][y] == memo[x][y-1])
                y--;
            // otherwise go to the diagonal
            else {
                // basically this just confirms those strings are the same
                assert str1.charAt(x-1) == str2.charAt(y-1);
                // since the matrix is shifted, normalize it
                sb.append(str1.charAt(x-1));
                x--;
                y--;
            }
        }

        System.out.println(memo[str1.length()][str2.length()]);
        System.out.println(sb);

    }
    @Test
    public void tester(){
        LongestCommonSubSequence.longestCommonSubSequence("thisisatest","testing123testing");
    }

}