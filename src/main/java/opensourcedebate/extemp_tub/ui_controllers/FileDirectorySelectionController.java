package opensourcedebate.extemp_tub.ui_controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import opensourcedebate.extemp_tub.config.CreateDirectory;
import opensourcedebate.extemp_tub.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class FileDirectorySelectionController {
	@FXML private Button OpenFileNavigation;
	@FXML private Button Continue;
	@FXML private TextArea FileDirectoryInput;
	@FXML public String directory;
	@FXML private Date Date = new Date();
	
	@FXML protected void fileNavigation(ActionEvent event) {
		File default_directory = new File(System.getenv("USERPROFILE"));
		
		if (default_directory != null) {
			directory = default_directory.getPath();
		}
		
		System.out.println(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Default System Path: " + default_directory.toString());
		
		//http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/file-chooser.htm#CCHICECF
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Directory: Selected.");
		directoryChooser.setInitialDirectory(default_directory);
		
		File user_directory = directoryChooser.showDialog(null);
		if (user_directory != null) {
			directory = user_directory.getPath();
			OpenFileNavigation.getScene().getWindow();
			setFileDirectoryPromptText(directory);
		}
		System.out.println(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Directory: " + directory.toString());
		
	}
	
	private void setFileDirectoryPromptText(String directory) {
		FileDirectoryInput.setText(directory);
	}

	@FXML protected void onContinue(ActionEvent event) {
		System.out.println(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Detected: Continue.");
		directory = FileDirectoryInput.getText();
		
		System.out.println(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Directory: " + directory.toString());
		
		if (directory != null) {
			new CreateDirectory(directory);
		}
		
		if (CreateDirectory.getDirectory() != null) {
			changeScene();
		}
	}
	
	private void changeScene() {
		System.out.println(new Timestamp(Date.getTime()) + " FileDirectorySelectionController: Process: ChangeScene");
		if (Main.getPrimaryStage() != null) {
			try {
				Stage primaryStage = Main.getPrimaryStage();
				
				Parent root = FXMLLoader.load(getClass().getResource("/subreddit_selection/subreddit_selection.fxml"));
				
			    
		        Scene scene = new Scene(root);
		    
		        primaryStage.setTitle("Extemp-Tub");
		        primaryStage.setScene(scene);
		        primaryStage.show();
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
