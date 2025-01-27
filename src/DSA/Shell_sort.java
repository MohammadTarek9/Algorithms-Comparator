package DSA;
import java.util.ArrayList;

public class Shell_sort {
     int timeShell = 0;

    public  void ShellSort(ArrayList<Integer> arr) {
        int n = arr.size();
        timeShell++;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            timeShell++;
            for (int i = gap; i < n; i++) {
                timeShell++;
                int temp = arr.get(i);
                timeShell++;
                int j;
                for (j = i; j >= gap && arr.get(j - gap) > temp; j -= gap) {
                    arr.set(j, arr.get(j - gap));
                    timeShell++;
                }
                arr.set(j, temp);
                timeShell++;
            }
        }
    }

}
