package opensourcedebate.extemp_tub.ui_controllers;

import java.sql.Timestamp;
import java.util.Date;

import opensourcedebate.extemp_tub.data_retrieval.ContentRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;

public class OperationScreenController {
	@FXML private ToggleButton process_toggle_button;
	@FXML private TextField current_process_text;
	@FXML private ProgressBar current_process_indicator;
	@FXML private TreeView tree_view;
	@FXML private Group node_group;
	@FXML private Date Date = new Date();
	
	@FXML protected void processToggle(ActionEvent event) {
		new ContentRetriever();
		ContentRetriever retriever = new ContentRetriever();
		
		if (process_toggle_button.getText().equals("Start")) {
			System.out.println(new Timestamp(Date.getTime()) + " OperationScreenController: Event: Start");
			retriever.start();
			process_toggle_button.setText("Pause");
			
		} else {
			System.out.println(new Timestamp(Date.getTime()) + " OperationScreenController: Event: Pause");
			retriever.pause();
			process_toggle_button.setText("Start");
			
		}
	}
}
