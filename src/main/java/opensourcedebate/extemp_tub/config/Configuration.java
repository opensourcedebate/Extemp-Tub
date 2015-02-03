package opensourcedebate.extemp_tub.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import opensourcedebate.extemp_tub.data_storage.Database;

import org.json.JSONArray;
import org.json.JSONObject;

public class Configuration {
	private Date Date = new Date();

	
	public Configuration() {
		boolean database_exists = getDatabaseCreated();
		
		if (database_exists == false) {
			System.out.println(new Timestamp(Date.getTime()) + " Configuration: Process: Create Database");
			new Database(getDatabaseName());
			setDatabaseCreated(true);
		} else {
			System.out.println(new Timestamp(Date.getTime()) + " Configuration: Event: Skip");
		}
	}
	
	public JSONObject getConfiguration() {
		JSONObject configuration = null;
		String json_string = new String();
		try {
			File config_file = new File(System.getProperty("user.dir") + "/src/main/resources/configuration/configuration.json");
			BufferedReader input = new BufferedReader(new FileReader(config_file));
			while (input.ready()) {
				json_string += input.readLine();
			}
			input.close();
			configuration = new JSONObject(json_string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configuration;
	}
	
	public void setDatabaseCreated(boolean flag) {
		JSONObject currentConfig = getConfiguration();
		boolean database_created = currentConfig.getBoolean("database_created");
		if (database_created == false) {
			currentConfig.put("database_created", String.valueOf(flag));
		}
		setNewConfiguration(currentConfig);
	}
	
	public void setNewConfiguration(JSONObject newConfig) {
		File config_file = new File(System.getProperty("user.dir") + "/src/main/resources/configuration/configuration.json");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(config_file));
			writer.write(newConfig.toString(4));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getDatabaseCreated() {
		JSONObject currentConfig = getConfiguration();
		boolean database_created = currentConfig.getBoolean("database_created");
		return database_created;
	}
	
	public String getDatabaseName() {
		JSONObject currentConfig = getConfiguration();
		String database_name = currentConfig.getString("database_name");
		return database_name;
	}
	
	public String getDirectory() {
		JSONObject currentConfig = getConfiguration();
		String directory = currentConfig.getString("directory");
		return directory;
	}
	
	public void setDirectory(String directory) {
		JSONObject currentConfig = getConfiguration();
		currentConfig.put("directory", directory);
		setNewConfiguration(currentConfig);
	}
	
	public ArrayList<String> getDefaultSubredditsAsList() {
		//TODO: Find An Alternative Method Other Than Iteration Down the JSON Heirarchy. Thar be dragons!
		JSONObject currentConfig = getConfiguration();
		JSONArray defaultJSONArray = currentConfig.getJSONArray("default_subreddits");
		ArrayList<String> defaultSubreddits = new ArrayList<String>();
		String key;
		for (int i = 0; i < defaultJSONArray.length(); i++) {
			JSONObject eventObject = defaultJSONArray.getJSONObject(i);
			for (int j = 0; j < eventObject.length(); j++) {
				key = eventObject.names().getString(j);
				JSONArray eventCategories = eventObject.getJSONArray(key);
				for (int k = 0; k < eventCategories.length(); k++) {
					JSONObject subCategories = eventCategories.getJSONObject(k);
					for (int l = 0; l < subCategories.length(); l++) {
						key = subCategories.names().getString(l);
						JSONArray subreddits = subCategories.getJSONArray(key);
						for (int m = 0; m < subreddits.length(); m++) {
							defaultSubreddits.add(subreddits.getString(m).toLowerCase());
						}
					}
				}
			}
		}
		return defaultSubreddits;
	}
	
	public void resetConfigurationToDefault() {
		JSONObject defaultConfiguration = new JSONObject("{‘database_created’:false,’database_name’:’ExtempTubDB’,’directory’:’’,’defualt_subreddits’:[{‘sub_categories’:[{‘Middle East’:[‘MidEastRegionalWar’,’SyrianCivilWar’,’LevantineWar’,’MiddleEastNews’]},{‘Africa’:[‘Africa’,’southafrica’]},{‘Europe’:[‘europe’,’Europeans’,’EuropeanFederalists’,’euroskeptics’]},{‘Asia’:[‘asia’,’China’,’India’,’Japan’,’Korea’,’Phillipines’,’Taiwan’,’Vietnam’,’SriLanka’]},{‘North America’:[‘CanadaPolitics’,’canada’,’CanadaNews’,’mexico’]},{‘South America’:[‘SouthAmerica’,’southamerican’,’LatinAmerica’,’LATO’]},{‘Central America’:[‘centralamerica’]},{‘Oceania’:[‘Australia’,’AusFinance’,’newzealand’]}]},{‘sub_categories’:[]}]}");
		setNewConfiguration(defaultConfiguration);
	}
	
	
}
