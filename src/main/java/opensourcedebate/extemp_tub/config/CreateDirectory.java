package opensourcedebate.extemp_tub.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

public class CreateDirectory {
	private String DirectoryName = "Extemp-Tub";
	private static File Directory;
	private Date Date = new Date();
	public CreateDirectory(String inputDirectory) {
		if (checkPathValid(inputDirectory) == true) {
			System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Path: Valid.");
			System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Initiate: New Directory.");
			
			File directory = new File(inputDirectory + File.separator + DirectoryName);
			if (!directory.exists()) {
				if (directory.mkdir()) {
					Directory = directory;
					System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Process: Complete.");
				} else {
					System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Process: Failed.");
				}
			} else {
				System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Process: Skip.");
				Directory = directory;
			}
		}
	}
	private boolean checkPathValid(String inputDirectory) {
		System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Path: Checking.");
		Path path = Paths.get(inputDirectory);
		if (!Files.exists(path)) {
			System.out.println(new Timestamp(Date.getTime()) + " CreateDirectory: Path: Invalid.");
			return false;
		} else {
			return true;
		}
	}
	
	public static File getDirectory() {
		if (Directory != null) {
			return Directory;
		} else {
			return null;
		}
	}
}
