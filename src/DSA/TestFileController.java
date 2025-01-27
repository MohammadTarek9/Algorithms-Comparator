package DSA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestFileController implements Initializable{
    
    @FXML
    private ChoiceBox<String> choices;
    @FXML
    private TextArea TextArea;
    @FXML
    private TextField filePath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> choice = FXCollections.observableArrayList(
                "Bubble Sort", "Merge Sort", "Tree Sort", "Quick Sort", 
                "Selection Sort", "Counting Sort", "Shell Sort", 
                "Heap Sort", "Insertion Sort");
        choices.setItems(choice);
    }
    
    @FXML
void DisplaySteps(ActionEvent event) {
    try {
        // Get the selected algorithm
        String selectedAlgorithm = choices.getValue();
        if (selectedAlgorithm == null || selectedAlgorithm.isEmpty()) {
            TextArea.setText("Please select a sorting algorithm.");
            return;
        }
        
        String file = filePath.getText();
            if (file == null || file.isEmpty()) {
                TextArea.setText("Please enter the CSV file path.");
                return;
            }
        file = file.replaceAll("^\"|\"$", "");

        // Read the CSV file to get arrays
        List<List<Integer>> arraysFromCSV = readArraysFromCSV(file);

        // Prepare to calculate steps and display results
        StringBuilder result = new StringBuilder();
        int minSteps = Integer.MAX_VALUE;
        List<Integer> mostEfficientArray = null;

        for (List<Integer> array : arraysFromCSV) {
            int steps = graphController.getSortingSteps(selectedAlgorithm, array);
            List<Integer> sortedArray = graphController.getSortedArray(selectedAlgorithm, array);

            // Append steps to the result
            result.append("Original Array: ").append(array).append("\n")
                  .append("Sorted Array: ").append(sortedArray).append("\n")
                  .append("Steps: ").append(steps).append("\n\n");

            // Check for most efficient array
            if (steps < minSteps) {
                minSteps = steps;
                mostEfficientArray = array;
            }
        }

        // Display the most efficient array
        if (mostEfficientArray != null) {
            result.append("Most Efficient Array: ").append(mostEfficientArray)
                  .append("\nSteps: ").append(minSteps);
        }

        // Set the result to the TextArea
        TextArea.setText(result.toString());
    } catch (Exception e) {
        TextArea.setText("An error occurred while processing: " + e.getMessage());
        e.printStackTrace();
    }
}

private List<List<Integer>> readArraysFromCSV(String fileName) throws IOException {
    List<List<Integer>> arrays = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("InputSize")) continue;

            String[] parts = line.split(",", 2); // Split only into two parts
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                List<Integer> array = new ArrayList<>();
                for (String numStr : parts[1].trim().split(",")) {
                    numStr = numStr.trim();
                    if (!numStr.isEmpty()) {
                        try {
                            array.add(Integer.parseInt(numStr));
                        } catch (NumberFormatException e) {
                            System.err.println("Skipping invalid number: " + numStr);
                        }
                    }
                }
                arrays.add(array);
            }
        }
    } catch (IOException e) {
        throw new IOException("Error reading CSV file: " + fileName, e);
    }
    return arrays;
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
