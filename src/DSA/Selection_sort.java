package DSA;
import java.util.ArrayList;

public class Selection_sort {
    public int time_selection = 0;
    public void SelectionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        time_selection++;
        

        for (int i = 0; i < n - 1; i++) {
            time_selection++;
            int min_index = i;
            time_selection++;
            for (int j = i + 1; j < n; j++) {
                time_selection++;
                if (arr.get(j) < arr.get(min_index)) {
                    min_index = j;
                    time_selection++;
                }
                
            }
            if (min_index != i) {
                
                int temp = arr.get(min_index);
                arr.set(min_index, arr.get(i));
                arr.set(i, temp);
                time_selection += 3;
            }
        }
    }

    
}
