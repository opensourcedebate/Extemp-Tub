package opensourcedebate.extemp_tub.ui_controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileDirectorySelectionController {
	@FXML private Button OpenFileNavigation;
	
	@FXML protected void fileNavigation(ActionEvent event) {
		File default_directory = new File(System.getenv("USERPROFILE"));
		System.out.println("Default System Path: " + default_directory.toString());
		
		//http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/file-chooser.htm#CCHICECF
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select a File Directory");
		directoryChooser.setInitialDirectory(default_directory);
		directoryChooser.showDialog((Stage) OpenFileNavigation.getScene().getWindow());
		
	}
	
	@FXML protected void onContinue(ActionEvent event) {
		
	}
}
