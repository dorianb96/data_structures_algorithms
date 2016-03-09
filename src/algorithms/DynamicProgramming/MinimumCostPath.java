package algorithms.DynamicProgramming;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Dorian on 24-Feb-16.
 * DS&A
 *
 *
 * http://www.ideserve.co.in/learn/minimum-cost-path
 *
 * Given a cost matrix having a cost at each cell.
 * Find the minimum cost it will take to reach cell (m, n) from top left corner cell (0, 0)
 * if the only allowed directions to move from a cell are right, down and diagonally down.
 */
public class MinimumCostPath {
    public static void minimumCostPath(int[][] array, int targetRow, int targetCol){
        // first we have to make a good representation of the input array
        int[][] path = new int[array.length+1][array[0].length+1];
        int row = 0;
        while (row < path.length) {
            path[row][0] = Integer.MAX_VALUE;
            row++;
        }
        int column = 0;
        while (column < path[0].length) {
            path[0][column] = Integer.MAX_VALUE;
            column++;
        }
        for (int i  = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++ ){
                path[i+1][j+1] = array[i][j];
            }
        }
        // so after we cleared up the arrays, we can fire up the iterative algo
        for (int i = 1; i < path.length; i++) {
            for (int j = 1; j < path[i].length; j++){
                if (i==1  && j==1){
                    path[i][j]= path[i][j];
                }
                else{
                    path[i][j] = path[i][j] + Math.min(path[i-1][j-1], Math.min(path[i-1][j],path[i][j-1]));
                }
            }
        }
        System.out.println("Matrix with costs at each step:");
        for (int i =  0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Matrix with minimal costs at each step:");
        for (int i =  1; i < path.length; i++){
            for (int j = 1; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("The minimal cost of path is:");
        System.out.println(path[path.length-1][path[0].length-1]);
    }


    @Test
    public void tester(){
        int[][] costMatrix = { { 3, 2, 8 }, { 5, 9, 7 }, { 0, 5, 2 }, {6, 4, 3} };
        MinimumCostPath.minimumCostPath(costMatrix,costMatrix.length, costMatrix[0].length);
    }
}
