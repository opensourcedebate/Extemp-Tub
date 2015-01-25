package opensourcedebate.extemp_tub.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/file_directory_selection/file_directory_selection.fxml"));
	    
        Scene scene = new Scene(root);
    
        primaryStage.setTitle("Extemp-Tub");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}

	public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
