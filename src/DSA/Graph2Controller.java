package DSA;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.util.Random;
public class Graph2Controller {
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    // Method to plot the graph based on average case times for different algorithms
    public void setGraphDataForSteps(List<String> selectedAlgorithms, int maxInputSize) {
        lineChart.setTitle("Sorting Steps vs Input Size");
        xAxis.setLabel("Input Size");
        yAxis.setLabel("Steps");

        // Clear any previous data
        lineChart.getData().clear();
        xAxis.getCategories().clear();

        // Generate input sizes from 5 to maxInputSize
        List<Integer> inputSizes = new ArrayList<>();
        for (int size = 5; size <= maxInputSize; size += 5) {
            inputSizes.add(size);
            xAxis.getCategories().add(String.valueOf(size));
        }

        // Loop over selected algorithms and calculate the average case times
        for (String algorithm : selectedAlgorithms) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(algorithm);

            // Calculate steps for each input size using the average case function
            for (int size : inputSizes) {
                List<Integer> averageCaseArray = generateAverageCaseArray(size);
                int steps = graphController.getSortingSteps(algorithm, averageCaseArray);
                series.getData().add(new XYChart.Data<>(String.valueOf(size), steps));
            }

            // Add series to the chart
            lineChart.getData().add(series);
        }
    }
    
    public void generateCSVWithRandomArrays(String fileName, int maxInputSize, int stepSize) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the header
            writer.append("InputSize,Array\n");

            // Generate random arrays and write to CSV
            for (int size = stepSize; size <= maxInputSize; size += stepSize) {
                List<Integer> randomArray = generateAverageCaseArray(size);
                writer.append(size + ",");
                writer.append(arrayToString(randomArray));
                writer.append("\n");
            }

            System.out.println("CSV file created successfully at: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Helper function to convert a list to a space-separated string
    private String arrayToString(List<Integer> array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            sb.append(array.get(i));
            if (i < array.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    // Function to generate average case array
    public List<Integer> generateAverageCaseArray(int n) {
        List<Integer> averageCaseArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            averageCaseArray.add((int) (Math.random() * n) + 1);
        }
        return averageCaseArray;
    }
    
    public void setGraphDataForAsymptotics(List<String> algorithms, int maxInputSize) {
    try {
        // Validate maxInputSize
        if (maxInputSize <= 0) {
            throw new IllegalArgumentException("Input size must be greater than zero.");
        }

        // Generate input sizes for the graph (e.g., 10, 20, ..., maxInputSize)
        List<Integer> inputSizes = new ArrayList<>();
        for (int i = 1; i <= maxInputSize; i += Math.max(1, maxInputSize / 10)) { // At least 10 points
            inputSizes.add(i);
        }

        // Call generateComplexityData to compute complexities
        Map<String, List<Double>> complexityData = generateComplexityData(algorithms, inputSizes);

        // Pass the data to the graphing library
        displayAsymptoticGraph(inputSizes, complexityData);

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error while generating asymptotic data: " + e.getMessage());
    }
}
    public void displayAsymptoticGraph(List<Integer> inputSizes, Map<String, List<Double>> complexityData) {
        // Clear previous data
        lineChart.getData().clear();
        xAxis.getCategories().clear();

        // Add data series for each algorithm
        for (Map.Entry<String, List<Double>> entry : complexityData.entrySet()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(entry.getKey());
            List<Double> complexities = entry.getValue();

            for (int i = 0; i < inputSizes.size(); i++) {
                series.getData().add(new XYChart.Data<>(String.valueOf(inputSizes.get(i)), complexities.get(i)));
            }

            lineChart.getData().add(series);
        }

        // Add input sizes to the x-axis
        for (int size : inputSizes) {
            xAxis.getCategories().add(String.valueOf(size));
        }
    }
    @FXML
    public void displayCombinedGraph(List<String> selectedAlgorithms, int maxInputSize) {
        lineChart.setTitle("Steps and Asymptotic Notation");
        xAxis.setLabel("Input Size");
        yAxis.setLabel("Steps / Complexity");

        // Clear existing data
        lineChart.getData().clear();
        xAxis.getCategories().clear();

        // Fetch input sizes
        List<Integer> inputSizes = new ArrayList<>();
        for (int size = 5; size <= maxInputSize; size += 5) {
            inputSizes.add(size);
            xAxis.getCategories().add(String.valueOf(size));
        }

        // Plot steps
        for (String algorithm : selectedAlgorithms) {
            XYChart.Series<String, Number> stepSeries = new XYChart.Series<>();
            stepSeries.setName(algorithm + " - Steps");

            for (int size : inputSizes) {
                List<Integer> averageCaseArray = generateAverageCaseArray(size);
                int steps = graphController.getSortingSteps(algorithm, averageCaseArray);
                stepSeries.getData().add(new XYChart.Data<>(String.valueOf(size), steps));
            }

            lineChart.getData().add(stepSeries);
        }

        // Plot asymptotic notation
        Map<String, List<Double>> complexityData = generateComplexityData(selectedAlgorithms, inputSizes);
        for (Map.Entry<String, List<Double>> entry : complexityData.entrySet()) {
            XYChart.Series<String, Number> complexitySeries = new XYChart.Series<>();
            complexitySeries.setName(entry.getKey() + " - Asymptotic Notation");

            List<Double> complexities = entry.getValue();
            for (int i = 0; i < inputSizes.size(); i++) {
                complexitySeries.getData().add(new XYChart.Data<>(String.valueOf(inputSizes.get(i)), complexities.get(i)));
            }

            lineChart.getData().add(complexitySeries);
        }
    }
    private Map<String, List<Double>> generateComplexityData(List<String> algorithms, List<Integer> inputSizes) {
        Map<String, List<Double>> complexityData = new HashMap<>();

        for (String algorithm : algorithms) {
            List<Double> complexities = new ArrayList<>();
            for (int size : inputSizes) {
                double value;
                switch (algorithm) {
                    case "Bubble Sort":
                    case "Insertion Sort":
                    case "Selection Sort":
                    case "Cycle Sort":    
                        value = Math.pow(size, 2); // O(n^2)
                        break;
                    case "Quick Sort":
                    case "Merge Sort":
                    case "Heap Sort":
                        value = size * Math.log(size); // O(n log n)
                        break;
                    case "Counting Sort":
                        value = size; // O(n)
                        break;
                    case "Shell Sort":
                        value = size * Math.pow(Math.log(size), 2); // O(n (log n)^2)
                        break;
                    case "Tree Sort":
                        value = size * Math.log(size); // O(n log n)
                        break;
                    default:
                        value = 0; // Unknown complexity
                        break;
                }
                complexities.add(value);
            }
            complexityData.put(algorithm, complexities);
        }

        return complexityData;
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
