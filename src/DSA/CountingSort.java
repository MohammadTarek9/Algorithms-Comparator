package DSA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountingSort {
    public int timeCountingSort = 0; 

    public ArrayList<Integer> countingSort(List<Integer> A) {
        // Find the maximum value in the list
        int k = Collections.max(A);
        timeCountingSort += A.size();

        ArrayList<Integer> C = new ArrayList<>(Collections.nCopies(k + 1, 0));
        timeCountingSort ++; 

        // Count occurrences of each element in A
        for (int j = 0; j < A.size(); j++) {
            timeCountingSort++; // Iteration
            C.set(A.get(j), C.get(A.get(j)) + 1);
            timeCountingSort ++; 
        }
        for (int i = 1; i <= k; i++) {
            timeCountingSort++; // Iteration
            C.set(i, C.get(i) + C.get(i - 1));
            timeCountingSort ++; 
        }
        ArrayList<Integer> B = new ArrayList<>(Collections.nCopies(A.size(), 0));
        timeCountingSort += A.size(); // Creating and initializing array B

        for (int j = A.size() - 1; j >= 0; j--) {
            timeCountingSort++; // Iteration
            B.set(C.get(A.get(j)) - 1, A.get(j)); 
            timeCountingSort++;
            C.set(A.get(j), C.get(A.get(j)) - 1);
            timeCountingSort++;
        }
        return B; // Return sorted array
    }
}