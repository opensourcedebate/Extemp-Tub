package opensourcedebate.extemp_tub.ui_controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import opensourcedebate.extemp_tub.main.Main;

public class ChangeScene {
	public ChangeScene(String currentClass, String sceneName) {
		Date Date = new Date();
		System.out.println(new Timestamp(Date.getTime()) + " " + currentClass + ": " + "Process: ChangeScene");
		
		if (Main.getPrimaryStage() != null) {
			Stage primaryStage = Main.getPrimaryStage();
			Scene scene = getRootScene(currentClass, sceneName);
			
			if (scene != null) {
				primaryStage.setTitle("Extemp-Tub");
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		}
	}
	
	private Scene getRootScene(String currentClass, String sceneName) {
		try {
			if (sceneName.equals("subreddit_selection")) {
				Parent root = FXMLLoader.load(currentClass.getClass().getResource("/subreddit_selection/subreddit_selection.fxml"));
				Scene scene = new Scene(root);
				return scene;
			} if (sceneName.equals("operation_screen")) {
				Parent root = FXMLLoader.load(currentClass.getClass().getResource("/operation_screen/operation_screen.fxml"));
				Scene scene = new Scene(root);
				return scene;
			} else {
				return null;
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
		}
	}
}
