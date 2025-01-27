package DSA;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleSort {

    public int BubbleSortTime = 0;
 
    public void bubbleSort(List<Integer> arr) { 
        BubbleSortTime=0;        
        // Step for initializing size
        int n = arr.size();
        BubbleSortTime++;
        for (int i = 0; i < n - 1; i++) {
            BubbleSortTime++; // Outer loop condition
            for (int j = 0; j < n - i - 1; j++) {
                BubbleSortTime++; // Inner loop condition
                if (arr.get(j) > arr.get(j + 1)) {
                    // Swap elements
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    BubbleSortTime += 3; // Count swap operations
                }
            }
        }
        BubbleSortTime++; // Outer loop completion
    }

}
