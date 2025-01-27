package DSA;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SortingComparisonController {

    @FXML
    private TextField inputField; // TextField for user input
    @FXML
    private TextArea outputArea; // TextArea to display results
    @FXML
    private CheckBox bubbleSortCheckBox; 
    @FXML
    private CheckBox quickSortCheckBox; 
    @FXML
    private CheckBox mergeSortCheckBox; 
    @FXML
    private CheckBox treeSortCheckBox; 
    @FXML
    private CheckBox insertionSortCheckBox; 
    @FXML
    private CheckBox countingSortCheckBox; 
    @FXML
    private CheckBox shellSortCheckBox; 
    @FXML
    private CheckBox selectionSortCheckBox; 
    @FXML
    private CheckBox heapSortCheckBox; 

    @FXML
    private Button compareButton;
    @FXML
    private Button clearButton; 
    @FXML
    private Button showGraphButton; 

    private int bubbleSteps, quickSteps, mergeSteps, treeSteps, insertionSteps, countingSteps, shellSteps, selectionSteps, heapSteps;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

   @FXML
public void handleCompareSorts() {
    try {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            outputArea.setText("Please enter numbers separated by spaces.");
            return;
        }

        String[] numbersArray = input.split("\\s+");
        List<Integer> numbers = new ArrayList<>();
        for (String number : numbersArray) {
            try {
                numbers.add(Integer.parseInt(number));
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid input! Please enter integers separated by spaces.");
                return;
            }
        }

        // Reset step counts to a high value
        bubbleSteps = quickSteps = mergeSteps = treeSteps = insertionSteps =
            countingSteps = shellSteps = selectionSteps = heapSteps = Integer.MAX_VALUE;

        StringBuilder result = new StringBuilder();
        String mostEfficientAlgorithm = "None";
        int minSteps = Integer.MAX_VALUE;

        // Bubble Sort
        if (bubbleSortCheckBox.isSelected()) {
            BubbleSort bubbleSort = new BubbleSort();
            List<Integer> bubbleSortedList = new ArrayList<>(numbers);
            bubbleSort.bubbleSort(bubbleSortedList);
            bubbleSteps = bubbleSort.BubbleSortTime;
            result.append("Bubble Sort Steps: ").append(bubbleSteps).append("\n");
            result.append("Sorted Array: ").append(bubbleSortedList).append("\n");
            if (bubbleSteps < minSteps) {
                minSteps = bubbleSteps;
                mostEfficientAlgorithm = "Bubble Sort";
            }
        }

        // Quick Sort
        if (quickSortCheckBox.isSelected()) {
            QuickSort quickSort = new QuickSort();
            List<Integer> quickSortedList = new ArrayList<>(numbers);
            quickSort.quickSort(quickSortedList, 0, quickSortedList.size() - 1);
            quickSteps = quickSort.timeQuickSort;
            result.append("Quick Sort Steps: ").append(quickSteps).append("\n");
            result.append("Sorted Array: ").append(quickSortedList).append("\n");
            if (quickSteps < minSteps) {
                minSteps = quickSteps;
                mostEfficientAlgorithm = "Quick Sort";
            }
        }

        // Merge Sort
        if (mergeSortCheckBox.isSelected()) {
            Mergesort mergeSort = new Mergesort();
            List<Integer> mergeSortedList = new ArrayList<>(numbers);
            mergeSort.mergeSort(mergeSortedList, 0, mergeSortedList.size() - 1);
            mergeSteps = mergeSort.timeMergeSort;
            result.append("Merge Sort Steps: ").append(mergeSteps).append("\n");
            result.append("Sorted Array: ").append(mergeSortedList).append("\n");
            if (mergeSteps < minSteps) {
                minSteps = mergeSteps;
                mostEfficientAlgorithm = "Merge Sort";
            }
        }

        // Tree Sort
        if (treeSortCheckBox.isSelected()) {
            TreeSort treeSort = new TreeSort();
            List<Integer> treeSortedList = new ArrayList<>(numbers);
            treeSort.treeSort(treeSortedList);
            treeSteps = treeSort.timeTreeSort;
            result.append("Tree Sort Steps: ").append(treeSteps).append("\n");
            result.append("Sorted Array: ").append(treeSortedList).append("\n");
            if (treeSteps < minSteps) {
                minSteps = treeSteps;
                mostEfficientAlgorithm = "Tree Sort";
            }
        }

        // Insertion Sort
        if (insertionSortCheckBox.isSelected()) {
            InsertionSort insertionSort = new InsertionSort();
            List<Integer> insertionSortedList = new ArrayList<>(numbers);
            insertionSort.insertionSort(insertionSortedList);
            insertionSteps = insertionSort.timeInsertionSort;
            result.append("Insertion Sort Steps: ").append(insertionSteps).append("\n");
            result.append("Sorted Array: ").append(insertionSortedList).append("\n");
            if (insertionSteps < minSteps) {
                minSteps = insertionSteps;
                mostEfficientAlgorithm = "Insertion Sort";
            }
        }

        // Counting Sort
       if (countingSortCheckBox.isSelected()) {
    CountingSort countingSort = new CountingSort();
    List<Integer> countingSortedList = new ArrayList<>(numbers);
    List<Integer> sortedArray = countingSort.countingSort(countingSortedList); // Return or update sorted list here
    countingSteps = countingSort.timeCountingSort;
    result.append("Counting Sort Steps: ").append(countingSteps).append("\n");
    result.append("Sorted Array: ").append(sortedArray).append("\n"); // Use the sorted array
    if (countingSteps < minSteps) {
        minSteps = countingSteps;
        mostEfficientAlgorithm = "Counting Sort";
    }
}


        // Shell Sort
        if (shellSortCheckBox.isSelected()) {
            Shell_sort shellSort = new Shell_sort();
            List<Integer> shellSortedList = new ArrayList<>(numbers);
            shellSort.ShellSort((ArrayList<Integer>) shellSortedList);
            shellSteps = shellSort.timeShell;
            result.append("Shell Sort Steps: ").append(shellSteps).append("\n");
            result.append("Sorted Array: ").append(shellSortedList).append("\n");
            if (shellSteps < minSteps) {
                minSteps = shellSteps;
                mostEfficientAlgorithm = "Shell Sort";
            }
        }

        // Selection Sort
        if (selectionSortCheckBox.isSelected()) {
            Selection_sort selectionSort = new Selection_sort();
            List<Integer> selectionSortedList = new ArrayList<>(numbers);
            selectionSort.SelectionSort((ArrayList<Integer>) selectionSortedList);
            selectionSteps = selectionSort.time_selection;
            result.append("Selection Sort Steps: ").append(selectionSteps).append("\n");
            result.append("Sorted Array: ").append(selectionSortedList).append("\n");
            if (selectionSteps < minSteps) {
                minSteps = selectionSteps;
                mostEfficientAlgorithm = "Selection Sort";
            }
        }

        // Heap Sort
        if (heapSortCheckBox.isSelected()) {
            Heap_sort heapSort = new Heap_sort();
            List<Integer> heapSortedList = new ArrayList<>(numbers);
            heapSort.HeapSort((ArrayList<Integer>) heapSortedList);
            heapSteps = heapSort.timeHeap;
            result.append("Heap Sort Steps: ").append(heapSteps).append("\n");
            result.append("Sorted Array: ").append(heapSortedList).append("\n");
            if (heapSteps < minSteps) {
                minSteps = heapSteps;
                mostEfficientAlgorithm = "Heap Sort";
            }
        }

        // Add the most efficient algorithm to the result
        result.append("\nMost Efficient Algorithm: ").append(mostEfficientAlgorithm)
              .append(" (").append(minSteps).append(" steps)");

        outputArea.setText(result.toString());

    } catch (Exception e) {
        outputArea.setText("An error occurred.");
        e.printStackTrace();
    }
}


    @FXML
public void showGraphScene() {
    try {
        // Get user input
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            outputArea.setText("Please enter numbers separated by spaces.");
            return;
        }

        String[] numbersArray = input.split("\\s+");
        List<Integer> baseArray = new ArrayList<>();
        for (String number : numbersArray) {
            try {
                baseArray.add(Integer.parseInt(number));
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid input! Please enter integers separated by spaces.");
                return;
            }
        }

        // Load GraphScene.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphScene.fxml"));
        AnchorPane root = loader.load();

        // Get the controller
        graphController graphCtrl = loader.getController();

        // Generate array sizes based on user input
        List<Integer> arraySizes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) { // Scale array size 1x to 5x
            arraySizes.add(baseArray.size() * i);
        }

        // Get selected algorithms
        List<String> selectedAlgorithms = new ArrayList<>();
        if (bubbleSortCheckBox.isSelected()) selectedAlgorithms.add("Bubble Sort");
        if (quickSortCheckBox.isSelected()) selectedAlgorithms.add("Quick Sort");
        if (mergeSortCheckBox.isSelected()) selectedAlgorithms.add("Merge Sort");
        if (treeSortCheckBox.isSelected()) selectedAlgorithms.add("Tree Sort");
        if (insertionSortCheckBox.isSelected()) selectedAlgorithms.add("Insertion Sort");
        if (countingSortCheckBox.isSelected()) selectedAlgorithms.add("Counting Sort");
        if (shellSortCheckBox.isSelected()) selectedAlgorithms.add("Shell Sort");
        if (selectionSortCheckBox.isSelected()) selectedAlgorithms.add("Selection Sort");
        if (heapSortCheckBox.isSelected()) selectedAlgorithms.add("Heap Sort");

        // Pass data to the graph controller
        graphCtrl.setGraphData(selectedAlgorithms, arraySizes, baseArray);

        // Show the graph stage
        Stage currentStage = (Stage) outputArea.getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @FXML
    public void handleClearOutput() {
        outputArea.clear();
        inputField.clear();

        bubbleSortCheckBox.setSelected(false);
        quickSortCheckBox.setSelected(false);
        mergeSortCheckBox.setSelected(false);
        treeSortCheckBox.setSelected(false);
        insertionSortCheckBox.setSelected(false);
        countingSortCheckBox.setSelected(false);
        shellSortCheckBox.setSelected(false);
        selectionSortCheckBox.setSelected(false);
        heapSortCheckBox.setSelected(false);
    }
    
    @FXML
void switchToFirstScene(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("firstscene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
 }
}
