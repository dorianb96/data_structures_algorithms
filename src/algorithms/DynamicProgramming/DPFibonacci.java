package algorithms.DynamicProgramming;

import java.math.BigInteger;

/**
 * Created by Dorian on 20-Feb-16.
 */
public class DPFibonacci {
    // bottom down
    public static BigInteger DPFibonacci1(int n) {
        BigInteger[] Fib = new BigInteger[n+1];
        Fib[0] = BigInteger.ONE;
        Fib[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++){
            Fib[i] = Fib[i-1].add(Fib[i-2]);
        }
        return Fib[n];
    }
    // top down
    public static int DPFibonacci2(int n,int[] fibs) {
        if (n == 0 || n==1){
            return 1;
        }
        if (fibs[n] != 0 ){
            return fibs[n];
        }
        return DPFibonacci2(n-1,fibs) + DPFibonacci2(n-2,fibs);
    }

    public static BigInteger RecFibonacci(int n) {
        if (n == 0 || n==1) {
            return BigInteger.ONE;
        }
        else{
            return RecFibonacci(n-1).add(RecFibonacci(n-2));
        }
    }
}
