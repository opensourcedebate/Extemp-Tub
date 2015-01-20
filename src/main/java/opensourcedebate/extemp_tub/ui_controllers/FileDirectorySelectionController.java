package opensourcedebate.extemp_tub.ui_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileDirectorySelectionController {
	@FXML private Button OpenFileNavigation;
	
	@FXML protected void fileNavigation(ActionEvent event) {
		//http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/file-chooser.htm#CCHICECF
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.showOpenDialog((Stage) OpenFileNavigation.getScene().getWindow());
	}
	
	@FXML protected void onContinue(ActionEvent event) {
		
	}
}
