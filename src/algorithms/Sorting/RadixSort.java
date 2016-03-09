package algorithms.Sorting;

import com.sun.scenario.effect.Merge;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dorian on 19-Feb-16.
 */
public class RadixSort {
    public static int[] radixSort(int[] input, int maxLength){
        int[] temp = new int[input.length];
        int radix = 10;

        for (int digit = 0; digit < maxLength; digit++){
            int[] digitCount = new int[radix+1];
            int remainder = (int) Math.pow(radix,digit);
            int divisor = (int) Math.pow(radix,digit+1);

            // now traverse all the elements in original array
            // and store the count of them in the count array
            for (int i = 0; i < input.length; i++){
                // here you add one to match digit 7 with array index of 7
                digitCount[(input[i]%divisor / remainder) +1]++;
            }
            // now add up the cumulative sums to digitCount array
            for (int i = 1; i < digitCount.length; i++){
                digitCount[i] += digitCount[i-1];
            }
            System.out.println(Arrays.toString(digitCount));
            // now we have to transform the initial array with the
            // help of temporary matrix
            for (int i  = 0; i < input.length; i++){
                temp[digitCount[input[i] % divisor / remainder]++] = input[i];
            }
            // now just copy the temporary array back into the original
            for (int i = 0; i < input.length; i++){
                input[i] = temp[i];
            }
        }
        return input;
    }


    @Test
    public void tester(){
        int width = 5;
        int radix = 10;
        int arrayLength = 1000000;

        int[] array = new int[arrayLength];
        Integer[] array2 = new Integer[arrayLength];

        for (int i = 0; i < arrayLength; i++){
            array[i] = (int) (Math.random() * Math.pow(radix,width) + 1);
            array2[i] = (int) (Math.random() * Math.pow(radix,width) + 1);
        }

        RadixSort.radixSort(array,width); System.out.println("Radix is done");

    }
}
