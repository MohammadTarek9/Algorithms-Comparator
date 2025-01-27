package DSA;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;

public class graphController {
    @FXML
    private Button backButton;

    @FXML
    private BarChart<String, Number> BarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void setGraphData(List<String> selectedAlgorithms, List<Integer> arraySizes, List<Integer> baseArray) {
    // Set chart title and axis labels
    BarChart.setTitle("Sorting Steps vs Algorithm");
    xAxis.setLabel("Algorithms");
    yAxis.setLabel("Steps");

    // Clear previous data and categories
    BarChart.getData().clear();
    xAxis.getCategories().clear();

    // Add algorithms as categories to the X-axis
    if (selectedAlgorithms.isEmpty() || baseArray.isEmpty()) {
        System.out.println("No algorithms or data provided to graph.");
        return;
    }
    xAxis.getCategories().addAll(selectedAlgorithms);

    // Generate step counts for each algorithm
    for (String algorithm : selectedAlgorithms) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Array Size: " + baseArray.size());

        // Calculate steps for the selected algorithm
        int steps = getSortingSteps(algorithm, baseArray);

        // Add the data point
        series.getData().add(new XYChart.Data<>(algorithm, steps));
        BarChart.getData().add(series);
    }

    // Refresh the chart layout
    BarChart.layout();
}

  public static int getSortingSteps(String algorithm, List<Integer> array) {
        switch (algorithm) {
            case "Bubble Sort":
                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.bubbleSort(new ArrayList<>(array));
                return bubbleSort.BubbleSortTime;

            case "Quick Sort":
                QuickSort quickSort = new QuickSort();
                quickSort.quickSort(new ArrayList<>(array), 0, array.size() - 1);
                return quickSort.timeQuickSort;

            case "Merge Sort":
                Mergesort mergeSort = new Mergesort();
                mergeSort.mergeSort(new ArrayList<>(array), 0, array.size() - 1);
                return mergeSort.timeMergeSort;

            case "Tree Sort":
                TreeSort treeSort = new TreeSort();
                treeSort.treeSort(new ArrayList<>(array));
                return treeSort.timeTreeSort;

            case "Insertion Sort":
                InsertionSort insertionSort = new InsertionSort();
                insertionSort.insertionSort(new ArrayList<>(array));
                return insertionSort.timeInsertionSort;

            case "Counting Sort":
    CountingSort countingSort = new CountingSort();

    // Convert the array to a List<Integer>
    List<Integer> inputList = new ArrayList<>(array);

    // Perform counting sort
    countingSort.countingSort(inputList);

    // Return the time taken for the sorting
    return countingSort.timeCountingSort;


            case "Shell Sort":
                Shell_sort shellSort = new Shell_sort();
                shellSort.ShellSort(new ArrayList<>(array));
                return shellSort.timeShell;

            case "Selection Sort":
                Selection_sort selectionSort = new Selection_sort();
                selectionSort.SelectionSort(new ArrayList<>(array));
                return selectionSort.time_selection;

            case "Heap Sort":
                Heap_sort heapSort = new Heap_sort();
                heapSort.HeapSort(new ArrayList<>(array));
                return heapSort.timeHeap;

            default:
                return 0;
        }
    }

    public static List<Integer> getSortedArray(String algorithm, List<Integer> array){
        ArrayList<Integer> sortedArray = new ArrayList<>(array);
        switch (algorithm) {
            case "Bubble Sort":
                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.bubbleSort(sortedArray);
                return sortedArray;

            case "Quick Sort":
                QuickSort quickSort = new QuickSort();
                quickSort.quickSort(sortedArray, 0, array.size() - 1);
                return sortedArray;

            case "Merge Sort":
                Mergesort mergeSort = new Mergesort();
                mergeSort.mergeSort(sortedArray, 0, array.size() - 1);
                return sortedArray;

            case "Tree Sort":
                TreeSort treeSort = new TreeSort();
                treeSort.treeSort(sortedArray);
                return sortedArray;

            case "Insertion Sort":
                InsertionSort insertionSort = new InsertionSort();
                insertionSort.insertionSort(sortedArray);
                return sortedArray;

            case "Counting Sort":
                CountingSort countingSort = new CountingSort();
                List<Integer> inputList = new ArrayList<>(array);
                sortedArray = countingSort.countingSort(inputList);
                return sortedArray;

            case "Shell Sort":
                Shell_sort shellSort = new Shell_sort();
                shellSort.ShellSort(sortedArray);
                return sortedArray;

            case "Selection Sort":
                Selection_sort selectionSort = new Selection_sort();
                selectionSort.SelectionSort(sortedArray);
                return sortedArray;

            case "Heap Sort":
                Heap_sort heapSort = new Heap_sort();
                heapSort.HeapSort(sortedArray);
                return sortedArray;

            default:
                return new ArrayList<>();
        }
    }

    public void handleBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SortingComparison.fxml"));
            AnchorPane inputRoot = loader.load();

            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene inputScene = new Scene(inputRoot, 800, 600);
            currentStage.setScene(inputScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
