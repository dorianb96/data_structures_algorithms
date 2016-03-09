package algorithms.RecursionBacktracking;

/**
 * Created by Dorian on 22-Feb-16.
 */
public class  StringPermutation {
    public static void stringPermutation(String prefix, String str){
        if (str.length() == 0) {
            System.out.println(prefix);
        }
        else {
            for (int i = 0; i < str.length(); i++)
                stringPermutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, str.length()));
        }
    }
}
