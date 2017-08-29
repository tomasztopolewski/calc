package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calc extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocCalc.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../../FXMLDoc_archive/FXMLDocCalc_08-29th-2017_11-30.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Calc");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
