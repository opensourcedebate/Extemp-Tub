package opensourcedebate.extemp_tub.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import opensourcedebate.extemp_tub.config.Configuration;

import org.json.JSONArray;
import org.json.JSONObject;

public class DirectoryTree {
	private Date Date = new Date();
	public static HashMap<String, String> primaryHash = new HashMap<String, String>();
	public static HashMap<String, String> secondaryHash = new HashMap<String, String>();
	
	public DirectoryTree() {
		
	}
	
	public DirectoryTree(HashMap<String, String> primaryHash, HashMap<String, String> secondaryHash) {
		DirectoryTree.primaryHash = primaryHash;
		DirectoryTree.secondaryHash = secondaryHash;
	}
	
	public DirectoryTree getDirectoryTree() {
		return new DirectoryTree(primaryHash, secondaryHash);
	}
	
	
	public DirectoryTree generateTree(ArrayList<String> selectedSubreddits) {
		JSONObject currentConfig = new Configuration().getConfiguration();
		JSONArray defaultJSONArray = currentConfig.getJSONArray("default_subreddits");
		
		String header;
		String branch;
		String stem;
		
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryTree: Process: Generate Default Tree");
		for (int i = 0; i < defaultJSONArray.length(); i++) {
			JSONObject eventObject = defaultJSONArray.getJSONObject(i);
			for (int j = 0; j < eventObject.length(); j++) {
				header = eventObject.names().getString(j);
				JSONArray eventCategories = eventObject.getJSONArray(header);
				for (int k = 0; k < eventCategories.length(); k++) {
					JSONObject subCategories = eventCategories.getJSONObject(k);
					for (int l = 0; l < subCategories.length(); l++) {
						branch = subCategories.names().getString(l);
						System.out.println("Attempting to put: Branch: " + branch + " Header: " + header);
						primaryHash.put(branch, header);
						JSONArray subreddits = subCategories.getJSONArray(branch);
						for (int m = 0; m < subreddits.length(); m++) {
							stem = subreddits.getString(m);
							secondaryHash.put(stem, branch);
						}
					}
				}
			}
		}
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryTree: Process: Trim Tree.");
		Iterator<Map.Entry<String, String>> secondary_iterator = secondaryHash.entrySet().iterator();
		while(secondary_iterator.hasNext()) {
			Map.Entry<String, String> entry = secondary_iterator.next();
			if (!selectedSubreddits.contains(entry.getKey())) {
				secondary_iterator.remove();
			}
		}
		
		Iterator<Map.Entry<String, String>> primary_iterator = primaryHash.entrySet().iterator();
		while(primary_iterator.hasNext()) {
			Map.Entry<String, String> entry = primary_iterator.next();
			if (!secondaryHash.containsValue(entry.getKey())) {
				primary_iterator.remove();
			}
		}
		
		
		System.out.println(new Timestamp(Date.getTime()) + " DirectoryTree: Process: Complete.");
		return new DirectoryTree(primaryHash, secondaryHash);
	}
}
