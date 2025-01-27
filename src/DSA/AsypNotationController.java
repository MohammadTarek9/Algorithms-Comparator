package DSA;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;

public class AsypNotationController implements Initializable {

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
    private CheckBox cycleSortCheckBox;

    @FXML
    private TextField sizeInputField;

    @FXML
    private Button graphButton;

    @FXML
    private Label outputArea;
    
    @FXML
    private ChoiceBox<String> choices;
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    ObservableList<String> choice = FXCollections.observableArrayList("Steps", "Asymptotic Notation");
    choices.setItems(choice);
}

    @FXML
public void showGraphScene() {
    try {
        // Get the user-selected comparison type
        String choice = choices.getValue();
        if (choice == null || choice.isEmpty()) {
            outputArea.setText("Please select an option (Steps or Asymptotic Notation).");
            return;
        }

        // Get the maximum input size from the user
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

        // Load the GraphScene2.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphScene2.fxml"));
        AnchorPane root = loader.load();

        // Get the graph controller
        Graph2Controller graphCtrl = loader.getController();

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
       

        // Pass data based on user choice
        if (choice.equals("Steps")) {
            // Generate data based on steps
            graphCtrl.setGraphDataForSteps(selectedAlgorithms, maxInputSize);
        } else if (choice.equals("Asymptotic Notation")) {
            // Generate data based on asymptotic complexity
            graphCtrl.setGraphDataForAsymptotics(selectedAlgorithms, maxInputSize);
        } else {
            outputArea.setText("Invalid choice selected.");
            return;
        }

        // Show the graph stage
        Stage currentStage = (Stage) outputArea.getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();

    } catch (IOException e) {
        e.printStackTrace();
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
