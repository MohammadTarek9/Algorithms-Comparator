package DSA;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Heap_sort {
     int timeHeap = 0;

    public void heapify(ArrayList<Integer> arr, int n, int i) {
        timeHeap++;
        int l = 2 * i + 1;
        timeHeap++;
        int r = 2 * i + 2;
        timeHeap++;
        int max = i;

        if (l < n && arr.get(l) > arr.get(max)) {
            max = l;
            timeHeap++;
        }

        if (r < n && arr.get(r) > arr.get(max)) {
            max = r;
            timeHeap++;
        }

        if (max != i) {

            int temp = arr.get(i);
            arr.set(i, arr.get(max));
            arr.set(max, temp);
            timeHeap += 3;
            heapify(arr, n, max);
            timeHeap++;
        }
    }

    public  void BuildHeap(ArrayList<Integer> arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            timeHeap++;
            heapify(arr, n, i);
        }
    }

    public  void HeapSort(ArrayList<Integer> arr) {
        timeHeap=0;
        int n = arr.size();
        timeHeap++;
        BuildHeap(arr, n);
        for (int i = n - 1; i >= 0; i--) {
            timeHeap += 3;
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);
            timeHeap++;
            heapify(arr, i, 0);
        }
    }

}
