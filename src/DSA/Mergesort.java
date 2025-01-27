package DSA;
import java.util.ArrayList;
import java.util.List;

public class Mergesort {
    public int timeMergeSort = 0;
    public void merge(List<Integer> arr, int p, int q, int r) {
        int n1 = q - p + 1;
        timeMergeSort++;
        int n2 = r - q;
        timeMergeSort++;

        List<Integer> L = new ArrayList<>(n1);
        timeMergeSort++;
        List<Integer> R = new ArrayList<>(n2);
        timeMergeSort++;

        for (int i = 0; i < n1; i++) {
            timeMergeSort++;
            L.add(arr.get(p + i));
            timeMergeSort++;
        }
        for (int j = 0; j < n2; j++) {
            timeMergeSort++;
            R.add(arr.get(q + j + 1));
            timeMergeSort++;
        }

        int i = 0;
        timeMergeSort++;
        int j = 0;
        timeMergeSort++;

        for (int k = p; k <= r; k++) {
            timeMergeSort++;
            if (i < n1 && j < n2) {
                timeMergeSort++;
                if (L.get(i) <= R.get(j)) {
                    timeMergeSort++;
                    arr.set(k, L.get(i));
                    timeMergeSort++;
                    i++;
                    timeMergeSort++;
                } else {
                    arr.set(k, R.get(j));
                    timeMergeSort++;
                    j++;
                    timeMergeSort++;
                }
            } else if (i < n1) {
                timeMergeSort++;
                arr.set(k, L.get(i));
                timeMergeSort++;
                i++;
                timeMergeSort++;
            } else {
                arr.set(k, R.get(j));
                timeMergeSort++;
                j++;
                timeMergeSort++;
            }
        }
    }

    public void mergeSort(List<Integer> arr, int p, int r) {
        if (p < r) {
            timeMergeSort++;
            int q = (p + r) / 2;
            timeMergeSort++;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }


}
