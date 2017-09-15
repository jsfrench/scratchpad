package sorting;

import java.util.Arrays;

public class Sorter {

    public static void main(String[] args){
        final int[] testNumbers = {3,7,9,4,1,6,8,5,7,2,9,5,3,7,2,4,8,5,6,8,4,9,2,3,5,6,7,8,5,3};
        System.out.println("Input:  " + Arrays.toString(testNumbers));

        final long start = System.nanoTime();
        int[] sortedNumbers = bubbleSort(testNumbers.clone());
        final long end = System.nanoTime();

        System.out.println("***** Bubble Sort (" + (end-start)/1000.0 + " ms)");
        System.out.println("Output: " + Arrays.toString(sortedNumbers));
    }

    private static int[] bubbleSort(int[] numbers) {
        final int length = numbers.length;

        for (int end = 0; end < length - 1; end++) {
            for (int index = length - 1; index > end; index--) {
               if (numbers[index] < numbers[index-1]) {
                   int tmp = numbers[index];
                   numbers[index] = numbers[index-1];
                   numbers[index-1] = tmp;
               }
            }
        }
        return numbers;
    }
}
