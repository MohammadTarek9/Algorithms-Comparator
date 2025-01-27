package DSA;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StepsWithNotationController implements Initializable {

    @FXML
    private ChoiceBox<String> choices;
    @FXML
    private TextField sizeInputField;
    @FXML
    private Label outputArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> choice = FXCollections.observableArrayList(
                "Bubble Sort", "Merge Sort", "Tree Sort", "Quick Sort", 
                "Selection Sort", "Counting Sort", "Shell Sort", 
                "Heap Sort", "Insertion Sort");
        choices.setItems(choice);
    }

    @FXML
public void showGraphScene() throws IOException {
    try {
        // Get the user-selected algorithm
        String choice = choices.getValue();
        if (choice == null || choice.isEmpty()) {
            outputArea.setText("Please select an algorithm.");
            return;
        }

        String input = sizeInputField.getText().trim();
        if (input.isEmpty()) {
            outputArea.setText("Please enter the maximum input size.");
            return;
        }

        int maxInputSize;
        try {
            maxInputSize = Integer.parseInt(input);
            if(maxInputSize > 1000){
                outputArea.setText("maximum size must be less than or equal to 1000.");
                return;
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input! Please enter a valid integer.");
            return;
        }

        // Generate CSV file
        Graph2Controller graphCtrl = new Graph2Controller();
        String fileName = "random_arrays.csv";
        int stepSize = 5; // Adjust step size as needed
        graphCtrl.generateCSVWithRandomArrays(fileName, maxInputSize, stepSize);
        outputArea.setText("CSV generated: " + fileName);

        // Load the GraphScene2.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphScene2.fxml"));
        AnchorPane root = loader.load();

        // Get the graph controller
        graphCtrl = loader.getController();

        // Create a list with the selected algorithm
        List<String> selectedAlgorithm = new ArrayList<>();
        selectedAlgorithm.add(choice);

        // Plot steps and asymptotic notations for the selected algorithms
        graphCtrl.displayCombinedGraph(selectedAlgorithm, maxInputSize);
        
        // Show the new scene
        Stage currentStage = (Stage) outputArea.getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();

    } catch (IOException e) {
        e.printStackTrace();
        outputArea.setText("An error occurred while loading the graph scene.");
    }
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
