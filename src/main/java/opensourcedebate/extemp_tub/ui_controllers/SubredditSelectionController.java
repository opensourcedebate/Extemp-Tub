package opensourcedebate.extemp_tub.ui_controllers;

import java.sql.Timestamp;
import java.util.Date;

import opensourcedebate.extemp_tub.config.CreateDirectory;
import opensourcedebate.extemp_tub.config.DirectoryManager;
import opensourcedebate.extemp_tub.validation.SubredditValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class SubredditSelectionController {
	@FXML private TextArea selected_subreddits_content_box;
	@FXML private Button continue_button;
	@FXML private Date Date = new Date();
	
    @FXML protected void subredditSelected(ActionEvent event) {
    	String selectedSubreddits = new String();
    	selectedSubreddits = selected_subreddits_content_box.getText();
    	System.out.println(new Timestamp(Date.getTime()) + " SubredditSelectionController: Selected:" + selectedSubreddits);
    	CheckBox check = (CheckBox) event.getTarget();
    	String id = check.getId();
    	boolean checkedAlready = false;
    	
    	if (selectedSubreddits.isEmpty()) {
    		selected_subreddits_content_box.setText(id);
    	} else {
    		if (selectedSubreddits.contains(",")) {
	    		String[] subreddits = selectedSubreddits.split(", ");
	    		
	    		String newList = new String();
	    		for (int i=0; i < subreddits.length; i++) {
	    			if (subreddits[i].equals(id)) {
	    				checkedAlready = true;
	    			} else {
	    				if (newList.isEmpty()) {
	    					newList = newList + subreddits[i];
	    				} else {
	    					newList = newList + ", " + subreddits[i];
	    				}
	    			}
	    		}
	    		if (checkedAlready == true) {
	    			selected_subreddits_content_box.setText(newList);
	    		} else {
	    			selected_subreddits_content_box.appendText(", " + id);
	    		}
    		} else {
    			if (selectedSubreddits.equals(id)) {
    				selected_subreddits_content_box.clear();
    			} else {
    				selected_subreddits_content_box.appendText(", " + id);
    			}
    		}
    		
    		
    	}
    	
    }
    
    @FXML protected void onContinue(ActionEvent event) {
    	System.out.println(new Timestamp(Date.getTime()) + " SubredditSelectionController: Detected: Continue.");
    	
    	SubredditValidator subreddit_validator = new SubredditValidator();
    	String selectedSubreddits = selected_subreddits_content_box.getText();
    	boolean validSubreddits = subreddit_validator.validate(selectedSubreddits);
    	
    	if (validSubreddits == true) {
    		if (CreateDirectory.getDirectory() != null) {
    			DirectoryManager directoryManager = new DirectoryManager();
    			directoryManager.generateFullDirectory(subreddit_validator.getSubreddits());
    			new ChangeScene(this.getClass().getSimpleName(), "operation_screen");
    		}
    	} else {
    		//TODO: Style Invalid User Input UI
    	}
	}
}
