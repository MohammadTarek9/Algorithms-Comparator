package DSA;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DSA extends Application{
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("firstscene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Algorithms"); 
          primaryStage.setResizable(false); // Prevent resizing
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch(args);


    }

}

