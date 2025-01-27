
package DSA;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstSceneController {
      
    @FXML
void switchToInputScene(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("SortingComparison.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
void switchToAsypNotationScene(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AsypNotation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
void switchToStepsWithNotationScene(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("StepsWithNotation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
void switchToTestFileScene(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("TestFileScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
