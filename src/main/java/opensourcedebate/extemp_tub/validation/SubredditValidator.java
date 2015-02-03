package opensourcedebate.extemp_tub.validation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import opensourcedebate.extemp_tub.config.Configuration;
import opensourcedebate.extemp_tub.data_retrieval.BaseConnection;

public class SubredditValidator {
	static ArrayList<String> subreddits;
	Date Date = new Date();
	
	public boolean validate(String selectedSubreddits) {
		boolean validSubreddits = false;
		
		if (selectedSubreddits.isEmpty()) {
			System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Process: Default Subreddits");
			ArrayList<String> defualtSubreddits = new Configuration().getDefaultSubredditsAsList();
			setSubreddits(defualtSubreddits);
			validSubreddits = true;
		} else {
			setSubreddits(getSubredditsAsList(selectedSubreddits));
			System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Process: Valid Notation.");
			boolean validNotation = checkNotation(selectedSubreddits);
			if (validNotation == true) {
				
				boolean subredditsExist = checkSubredditsExist();
				if (subredditsExist == true) {
					System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Process: Subreddit Exists.");
					validSubreddits = true;
				}
			}
		}
		
		return validSubreddits;
	}
	
	

	private boolean checkNotation(String selectedSubreddits) {
		boolean validNotation = true;
		
		for (int i = 0; i<selectedSubreddits.length(); i++) {
			if (Character.isLetter(selectedSubreddits.charAt(i))) {
				continue;
			}
			else if (Character.isWhitespace(selectedSubreddits.charAt(i))) {
				continue;
			}
			else if (Character.toString(selectedSubreddits.charAt(i)).equals(",")) {
				continue;
			} else {
				validNotation = false;
				System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Valid Notation: Non-Valid Character: " + selectedSubreddits.charAt(i));
			}
		}
		
		System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Valid Notation: Result: " + validNotation);
		return validNotation;
	}
	
	private boolean checkSubredditsExist() {
		boolean subredditExists = true;
		ArrayList<String> defualtSubreddits = new Configuration().getDefaultSubredditsAsList();
		for (String subreddit : getSubreddits()) {
			if (defualtSubreddits.contains(subreddit.toLowerCase())) {
				System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Detected: Default Subreddit: " + subreddit);
				continue;
			} else {
				System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Detected: Unique Subreddit: " + subreddit);
				if (testConnectionToSubreddit(subreddit) == true) {
					continue;
				} else {
					subredditExists = false;
					System.out.println(new Timestamp(Date.getTime()) + " SubredditValidator: Detected: Invalid Subreddit: " + subreddit);
				}
			}
		}
		
		return subredditExists;
	}
	
	private ArrayList<String> getSubredditsAsList(String selectedSubreddits) {
		ArrayList<String> listOfSubreddits;
		
		if (selectedSubreddits.contains(",")) {
			String[] subreddits = selectedSubreddits.split(", ");
			listOfSubreddits = new ArrayList<String>(Arrays.asList(subreddits));
		} else {
			listOfSubreddits = new ArrayList<String>();
			listOfSubreddits.add(selectedSubreddits);
		}
		return listOfSubreddits;
	}
	
	public ArrayList<String> getSubreddits() {
		return subreddits;
	}
	
	public void setSubreddits(ArrayList<String> selectedSubreddits) {
		subreddits = selectedSubreddits;
	}
	
	private boolean testConnectionToSubreddit(String subreddit) {
		
		boolean validSubreddit = new BaseConnection().testSubreddit(subreddit);
		return validSubreddit;
	}
}
