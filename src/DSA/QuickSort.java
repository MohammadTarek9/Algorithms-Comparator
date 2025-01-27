package DSA;
import java.util.List;

public class QuickSort {
    public int timeQuickSort = 0;

    void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        timeQuickSort++; 
        arr.set(i, arr.get(j));
        timeQuickSort++;
        arr.set(j, temp);
        timeQuickSort++;
    }

    int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        timeQuickSort++;
        int i = low - 1;
        timeQuickSort++;

        for (int j = low; j < high; ++j) {
            if (arr.get(j) < pivot) {
                timeQuickSort++;
                i++;
                timeQuickSort++; 
                swap(arr, i, j);
                timeQuickSort++;
            }
        }
        swap(arr, i + 1, high);
        timeQuickSort++; 
        return i + 1;
    }

    void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            timeQuickSort++;
            int pi = partition(arr, low, high);
            timeQuickSort++;
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
