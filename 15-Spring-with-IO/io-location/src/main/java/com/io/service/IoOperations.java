package com.io.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class IoOperations {

	/**
	 * In this class I will shows:
	 * several ways to define a path of a file
	 * I didn'g had to put the file in the location of Project main folder and src/main/resources
	 * I just did it for the example , to know that I can store the files every where I want , 
	 * And get the access to the path where files are located 
	 * 
	 */
	
	public void filePath_1() throws IOException {

		System.out.println();

		String currentDirectory = System.getProperty("user.dir");
		File file = new File(currentDirectory);

		File jsonFilePath = new File(currentDirectory + "/project_folder.json");

		System.out.println("option 1 = " + currentDirectory);
		System.out.println("option 1 = " + file.getPath());
		System.out.println("option 1 = " + file.getCanonicalPath());
		System.out.println("option 1 = " + file.getAbsolutePath());
		System.out.println("option 1 = " + jsonFilePath.getAbsolutePath());
		
		/**
		 * option 1 = F:\Spring\workspace-STS.4.21.0\io-location
		 * option 1 = F:\Spring\workspace-STS.4.21.0\io-location
		 * option 1 = F:\Spring\workspace-STS.4.21.0\io-location
		 * option 1 = F:\Spring\workspace-STS.4.21.0\io-location
		 * option 1 = F:\Spring\workspace-STS.4.21.0\io-location\project_folder.json
		 */

	}

	public void filePath_2() {

		System.out.println();

		String currentDirectory = System.getProperty("user.dir");
		Path filePath = Paths.get(currentDirectory, "project_folder.json");

		System.out.println("option 2 = " + currentDirectory);
		System.out.println("option 2 = " + filePath.toString());
		
		/**
		 * option 2 = F:\Spring\workspace-STS.4.21.0\io-location
		 * option 2 = F:\Spring\workspace-STS.4.21.0\io-location\project_folder.json
		 */
	}

	public void filePath_3() {

		System.out.println();
		
		File file = new File(".");
		String absolutePath = file.getAbsolutePath();
		String jsonAbsolutePath = absolutePath.substring(0, absolutePath.length() - 1) + "project_folder.json";

		System.out.println("option 3 = " + absolutePath);
		System.out.println("option 3 = " + jsonAbsolutePath);
		/**
		 * option 3 = F:\Spring\workspace-STS.4.21.0\io-location\. 
		 * option 3 = F:\Spring\workspace-STS.4.21.0\io-location\project_folder.json
		 */

	}

	public void filePath_4() {

		System.out.println();

		File file = new File("project_folder.json");
		System.out.println("option 4 = " + file.getAbsolutePath());
		/**
		 * option 4 = F:\Spring\workspace-STS.4.21.0\io-location\project_folder.json
		 */
	}

	public void filePath_5() throws FileNotFoundException {

		System.out.println();

		/**
		 * File Location in src/main/resources
		 * If I want a file to open a file inside src/main/resources , Then use the
		 */
		 
		/**
		 * Option (1)
		 */
		
		File fileResources_1 = ResourceUtils.getFile("classpath:json_resources.json");
		System.out.println("option 5 = " + fileResources_1.getAbsolutePath());
		
		/**
		 * Option (2)
		 */
		String SRC_MAIN_RESOURCES = "src/main/resources/";
		File fileResources_2 = new File(SRC_MAIN_RESOURCES + "json_resources.json");
		System.out.println("option 5 = " + fileResources_2.getAbsolutePath());
		
		/**
		 * Option (3)
		 */
		 File file = new File("."); 
		 String path = file.getAbsolutePath();
		 String filePath = path.substring(0, path.length() - 1) + SRC_MAIN_RESOURCES + "json_resources.json";
		 System.out.println("option 5 = " + filePath);
		
		 /**
		  * option 5 = F:\Spring\workspace-STS.4.21.0\io-location\target\classes\json_resources.json
		  * option 5 = F:\Spring\workspace-STS.4.21.0\io-location\src\main\resources\json_resources.json
		  * option 5 = F:\Spring\workspace-STS.4.21.0\io-location\src/main/resources/json_resources.json
		  */
	}
	


}
