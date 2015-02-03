package opensourcedebate.extemp_tub.ui_controllers;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import opensourcedebate.extemp_tub.config.Configuration;
import opensourcedebate.extemp_tub.config.CreateDirectory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;

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
			Configuration configuration = new Configuration();
			String DatabaseName = configuration.getDatabaseName();
			setDirectoryConfiguration(directory + File.separator + DatabaseName);
			new ChangeScene(this.getClass().getSimpleName(), "subreddit_selection");
		}
	}

	private void setDirectoryConfiguration(String directory) {
		Configuration config = new Configuration();
		config.setDirectory(directory);
	}
	
	
}
