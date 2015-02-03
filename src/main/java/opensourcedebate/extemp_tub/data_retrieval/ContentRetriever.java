package opensourcedebate.extemp_tub.data_retrieval;

import java.sql.Timestamp;
import java.util.Date;

public class ContentRetriever {
	Date Date = new Date();
	boolean Paused;
	
	public ContentRetriever() {
		System.out.println(new Timestamp(Date.getTime()) + " ContentRetriever: Process: Start");
		
		while (Paused == false) {
			
		}
		
		
	}

	public void pause() {
		Paused = true;
	}
	
	public void start() {
		Paused = false;
	}
	
	
}
