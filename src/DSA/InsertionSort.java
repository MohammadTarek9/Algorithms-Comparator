package DSA;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertionSort {
    public int timeInsertionSort = 0;

    public void insertionSort(List<Integer> arr) {
        timeInsertionSort = 0;
        int n = arr.size();
        timeInsertionSort++;
        for (int j = 1; j < n; j++) {
            timeInsertionSort++;
            int key = arr.get(j);
            timeInsertionSort++;
            int i = j - 1;
            timeInsertionSort++;

            while (i >= 0 && arr.get(i) > key) {
                timeInsertionSort++;
                arr.set(i + 1, arr.get(i)); 
                timeInsertionSort++;
                i--;
                timeInsertionSort++;
            }

            arr.set(i + 1, key); 
            timeInsertionSort++;
        }
    }
    

}
