package opensourcedebate.extemp_tub.ui_controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class SubredditSelectionController {
	@FXML private TextArea selected_subreddits_content_box;
	
    @FXML protected void subredditSelected(ActionEvent event) {
    	System.out.println("Selected Subreddits: "+ selected_subreddits_content_box.getText());
    	CheckBox check = (CheckBox) event.getTarget();
    	String id = check.getId();
    	boolean checkedAlready = false;
    	
    	if (selected_subreddits_content_box.getText().isEmpty()) {
    		selected_subreddits_content_box.setText(id);
    	} else {
    		if (selected_subreddits_content_box.getText().contains(",")) {
	    		String[] subreddits = selected_subreddits_content_box.getText().split(", ");
	    		
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
    			if (selected_subreddits_content_box.getText().equals(id)) {
    				selected_subreddits_content_box.clear();
    			} else {
    				selected_subreddits_content_box.appendText(", " + id);
    			}
    		}
    		
    		
    	}
    	
    }
}
