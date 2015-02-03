package opensourcedebate.extemp_tub.data_retrieval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

public class BaseConnection {
	Date Date = new Date();
	
    public String getHTML(String urlToRead) {
      System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Process: Retrieve");
      System.setProperty("http.agent", "Extemp-Tub Client"); 
      URL url;
      HttpURLConnection connection;
      BufferedReader reader;
      String line;
      String result = "";
      try {
         url = new URL(urlToRead + ".json");
         System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Requested URL: " + url);
         connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("GET");
         connection.setRequestProperty("User-Agent", "Extemp-Tub Beta Client");
         reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         while ((line = reader.readLine()) != null) {
            result += line;
         }
         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
    }
    
    public boolean testSubreddit(String subredditToTest) {
    	boolean validURL = false;
    	String baseURL = "http://www.reddit.com/r/";
    	
    	System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Process: Test");
        System.setProperty("http.agent", "Extemp-Tub Client"); 
        URL url;
        HttpURLConnection connection = null;
        int timeout = 2000;
        try {
           url = new URL(baseURL + subredditToTest);
           System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Testing Subreddit: " + subredditToTest);
           connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("HEAD");
           connection.setRequestProperty("User-Agent", "Extemp-Tub Beta Client");
           connection.setReadTimeout(timeout);
           connection.setConnectTimeout(timeout);
           connection.setInstanceFollowRedirects(false);
           int responseCode = connection.getResponseCode();
           
           System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Response: " + responseCode);
           
           if (responseCode == 200) {
        	   System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Valid Subreddit.");
        	   validURL = true;
           } else {
        	   System.out.println(new Timestamp(Date.getTime()) + " BaseConnection: Invalid Subreddit.");
           }
           
        } catch (IOException e) {
           e.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        	if (connection != null) {
                connection.disconnect();
            }
        }
        return validURL;
    }
}
