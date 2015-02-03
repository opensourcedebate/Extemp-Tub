package opensourcedebate.extemp_tub.config;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import opensourcedebate.extemp_tub.config.DirectoryTree;

public class DirectoryManager {
	Date Date = new Date();
	
	public DirectoryManager() {
		
	}
	
	private void makeDirectory(File file) {
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Process: Create: Complete.");
			} else {
				System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Process: Create: Failed.");
			}
		} else {
			System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Process: Create: Skip.");
		}
	}
	
	public void generateFullDirectory(ArrayList<String> subreddits) {
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Recieved: " + subreddits.toString());
		Configuration currentConfig = new Configuration();
		File directory = new File(currentConfig.getDirectory());
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Directory: " + directory.getAbsolutePath());
		
		DirectoryTree directoryTree = new DirectoryTree().generateTree(subreddits);
		HashMap<String, String> primary = DirectoryTree.primaryHash;
		HashMap<String, String> secondary = DirectoryTree.secondaryHash;
		
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Received PrimaryHash: " + primary.toString());
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Received SecondaryHash: " + secondary.toString());
		
		for (String eventCategory : primary.values()) {
			File eventCategoryFile = new File(directory + File.separator + eventCategory);
			makeDirectory(eventCategoryFile);
			for (String subCategory : primary.keySet()) {
				if (primary.get(subCategory).equals(eventCategory)) {
					File subCategoryFile = new File(eventCategoryFile.getAbsolutePath() + File.separator + subCategory);
					makeDirectory(subCategoryFile);
					for (String subreddit : secondary.keySet()) {
						if (secondary.get(subreddit).equals(subCategory)) {
							File subredditFile = new File(subCategoryFile.getAbsolutePath() + File.separator + subreddit);
							makeDirectory(subredditFile);
						}
					}
				}
			}
		}
		
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryManager: Process: Generate: Complete.");
	}
}
