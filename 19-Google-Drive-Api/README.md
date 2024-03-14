<img src="https://img.shields.io/badge/- Google Drive Api  %20- blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [video links](#video_links)             |
|  1  | [google account](#1_google_account)             |
|  2  | [Creating Google Cloud Project (GCP) in google console](#2_creating_google_cloud_project_in_google_console)             |
|  3  | [Create a folder in google drive where I store files](#3_create_folder_in_google_drive_where_files_stored)             |
|  4  | [Spring Boot App](#4_spring_boot_app)             |
|     |  4.1. [copy JSON Credentials to spring src folder](#4_1_copy_json_credentials_to_spring_src_folder)             |
|     |  4.2. [add google drive api dependencies](#4_2_add_google_drive_dependencies)             |
|     |  4.3. [service layer](#4_3_service_layer)             |
|     |  4.4. [controller layer](#4_4_controller_layer)             |
|  5  | 4.2[]()  	|
|  6  | []()  	|
|  7  | []()  	|
|  8  | []()  	|
|  9  | []()  	|
|  10  | []()  	|
|  1  | [Servers](#)             |
|     | 1.1. []()              |
|     | 1.2. []()              |
|     | 1.3. []()             |


###### video_links

### [videos](#-)

1. https://www.youtube.com/watch?v=rANfiSmyMTQ&ab_channel=TechWithDen



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



###### 1_google_account

<img src="https://img.shields.io/badge/- 1. google acount %20-blue" height=40px>

I've created a google account </br>

first name : fullstack
last name  : application

- email : [`frontend.application@gmail.com` ](#-)
- password: same as my gmail



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 2_creating_google_cloud_project_in_google_console

<img src="https://img.shields.io/badge/- 2. GCP Creating Google Cloud Project in google console %20-blue" height=40px>


1. Go to the `Google Cloud console` and navigate to the IAM & Admin section. click on console

![image](https://github.com/sshalem/DevOps/assets/36256986/a32dd477-4879-44b6-a913-911c7bfc0766)

2. click on the top left , to see the menu

![image](https://github.com/sshalem/DevOps/assets/36256986/61fe9d1c-5648-4576-aa14-612be4af7fd3)

3. clip on `API and services`

4. click on `select a project`

![image](https://github.com/sshalem/DevOps/assets/36256986/765f12b6-316f-4c1b-ba96-3a2654c6db85)


5. click `new project`

![image](https://github.com/sshalem/DevOps/assets/36256986/b35ba33b-4620-4ddd-a8a7-a9a753efd3ed)

6. type your project name the click on create

![image](https://github.com/sshalem/DevOps/assets/36256986/517d090d-8d22-44f3-8f9e-e91f443fc8d0)


Then I will see this dashboards page 

![image](https://github.com/sshalem/DevOps/assets/36256986/d2b4ce95-94d0-4179-a596-0aaa0ee10e1b)


7. On the search field typ `goolge drive api` to add it to the project

![image](https://github.com/sshalem/DevOps/assets/36256986/1352e0a5-58cc-4c2a-80ec-96944a9b7e37)

click on `Enable` it

![image](https://github.com/sshalem/DevOps/assets/36256986/3a0419f8-ee16-4160-b224-38a4c14dae15)

8. In brings us to this page, then click on `credentials`

![image](https://github.com/sshalem/DevOps/assets/36256986/9468f63e-d1b9-4d8b-8aa4-a581283fa9cd)

9. scroll down and click on `manage service accounts`

![image](https://github.com/sshalem/DevOps/assets/36256986/008d60cd-748a-4e68-9343-2c060af72f46)

10. click on `create service account`

![image](https://github.com/sshalem/DevOps/assets/36256986/4bfc7448-d449-43bd-8b23-b7065138edd5)

11. type my service account details `googleDriveUpload` then click on `create and continue`

![image](https://github.com/sshalem/DevOps/assets/36256986/78c6e093-e799-4468-9f99-e5cc2c155dec)

12. In this pgae , I'm going to add a role to the service of `basic` + `editor`

![image](https://github.com/sshalem/DevOps/assets/36256986/77e83ec8-170d-4af6-a20a-1f965a2f8e2f)

Click on continue

![image](https://github.com/sshalem/DevOps/assets/36256986/79f5d283-af8f-44e2-9104-5ffd154d5510)

13. then click on done

![image](https://github.com/sshalem/DevOps/assets/36256986/5edea8f0-2e48-4ada-9813-4a148d3c1149)

14. Now on this page we can see the details, click on the marked details

![image](https://github.com/sshalem/DevOps/assets/36256986/a85bbc22-1634-4a13-bb9e-83a9c5ed53b7)

15. Now click on the `keys` tab

![image](https://github.com/sshalem/DevOps/assets/36256986/ad585579-1bc8-438c-b6b9-b8d8b05db6e6)

16. click on `add key` + create new key

![image](https://github.com/sshalem/DevOps/assets/36256986/c3deee12-3f86-475c-ad81-5f41be5b4053)

![image](https://github.com/sshalem/DevOps/assets/36256986/816ab1af-464e-4234-8a53-ee86cf271f2a)


17. Select the JSON

![image](https://github.com/sshalem/DevOps/assets/36256986/9c4f7bf6-c4ab-4cc3-8757-2370c092ef88)

18. It creates a key and also downloads it to dowanloads folder `robotic-gasket-416916-0fb74c5b7360.json`. We are going to use that key with our Spring boot app

![image](https://github.com/sshalem/DevOps/assets/36256986/c0fd5c71-c072-407b-8745-dd87370612a5)

![image](https://github.com/sshalem/DevOps/assets/36256986/4942b1ea-7d9a-4204-8185-4221eb5c401e)

This is how the `JSON` credencials file is build inside:

```json
{
  "type": "service_account",
  "project_id": "robotic-gasket-416916",
  "private_key_id": "0fb74c5b7360e58edbd5c43b918287b7d15202a9",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDTW4KuS5aH5Auo\nw0Y+5qltsHYVIW6KuZUMLe7X2g4xgdY1fUFJixsNSyHBidPplNwBWwwiGk3486Pk\nT7XYFHoE+qMGmsO26xWiMmVxSPfYxDsPejBuNMQXnmTSPGyipQDaq8N4P7c4LKt3\ndgitRJf02lTtzB4gBUBp/BYvVQMT7wqHMu6cyrPhXYkVEinNJW9ztzCGp09iC7WR\nqlFmCuoKp3WGcbIrunBZ7qb9u7zdGEUQqUkiIFx5+AEfKN3WkE3+gVi3t503mdmR\nNvJmt73eC1vOAyX72fimh3oldh7l19kelLdSey+tFf2Py6Z5+XRM6kJ8vRfQjHe/\n0GxNj7mbAgMBAAECggEAGV9KPW4pheqkU6q4ILjulz0Fuoem438KcMna0DgHAtPD\n8+veDdwQb/TC+p7MZ6VNES9C3PKCGw6fGrtO6GpHiO1660IM7e/OoF1NvDRhkbi3\ndlDZcNa4wN/CLqgTn9AQXYUdFARiFEZDLIto2JP8Fz1We1geOFh+YRANVw/s11Q0\nu0ZlC0vcaScKc5BMJhF/gFQ03r9tIXVngNOxOM0Df8re0ad583kNnGNy/kb/q9Cy\nPW5mc5HxTwSPPtBdHoscxLA3+mJ9WYGV0NcT3t8le+7Lch97kNbdl6qgQkJBfl5p\nrElXanOeQdhHcLZ9BtaB3MGmdr1fSna+lKm+zDhtoQKBgQD/oFQoLhityrcRhpde\nW9zFEUxaBZjrmN2TSuv2aLAJWOsQjlvfMg5TGqOAyTpRW1+RU2xbUQJkSKI14UdJ\ntXF46kPuGg8tsoKP0luqsr4dFYbqQq7ah9//xwD0ozwitUdstSMdlNb0lD31DMvG\nsQ1jgz9aCVbx28T76zo756TcuQKBgQDTqp0T/sXnd9/x4sa61d1MgJ+kqm/cWVA0\nQJviL8MU3JFV4BVF39Mrfv5KAfByYO5pkQG177wgd5pxpeP3kAeT/BA60/vgNy6x\n/RYs5FxoIRTEHyMriDO6Det7yHlADAk7GjsjkEqBxNSxr3j+vbjTT9Vdc1baiOBw\nrYHAAeTm8wKBgQDl2RPSzSADpLLXX13byl3IFLXCfCX0MY+KUWO6PIeM4Bb83ARf\nvHKE5v79XfLCFJbp5UkZEs5DvcudBuNCs5NEVeCex2++dzROfadO5UlsfV7CZRM0\nC9it9BI35k68JqQCax0YDCz73g+Xt9nC3pBbWjgsjRlQf3uvyOKj2rWygQKBgQDL\nczXSb1OoPefqZxCfq0sv6n4IV653fP2DjAqJqWD4n12uzlmENTSTnVgsqJqGRjdD\nDxc22jzYdD3mOQ7a+PzfuCudb/6tMD1Y5aw38DQF/q4oKMm0YRFfJ1wOOc6Zyjfh\nMEBh2aJUA25xb/l72b5almS+k0uLeH9KruuFNAziEwKBgQDOvDN+rC4afJdVd8pj\n3hZhDu1FWw7+ZypAfh44h+pjODE1IwNkjGHvpeFi7XyDokP+jVTp2cu3rMaJIz36\n3VRGRCZsRBtdzn+53kw9XJCFNEHw8ghJim1BxX4dhDoeICmOYDdi/Qh4dOND6Uuj\nsAh7Uo1emxEjTrkebzDQxGWQ/Q==\n-----END PRIVATE KEY-----\n",
  "client_email": "googledriveupload@robotic-gasket-416916.iam.gserviceaccount.com",
  "client_id": "114229170616089762924",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/googledriveupload%40robotic-gasket-416916.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}

```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------


###### 3_create_folder_in_google_drive_where_files_stored

<img src="https://img.shields.io/badge/- 3. create folder in google drive where files stored %20-blue" height=40px>

1. I will created a folder `files_upload` in google drive , under the account of `frontend.application@gmail.com`
2. In this folder , I will use Spring boot to upload/ download / delete files
3. I will share this folder , with the GCP url , from the `credentianls.json file`
4. Use the `"client_email": "googledriveupload@robotic-gasket-416916.iam.gserviceaccount.com",` to share the folder with GCP

![image](https://github.com/sshalem/DevOps/assets/36256986/e452c37e-7bed-4346-a707-b5cdf0dcb64d)

Let's share the `files_upload` with `client_email` 

![image](https://github.com/sshalem/DevOps/assets/36256986/af1919b4-bacf-4678-a157-e0aba9305ec2)

paste the `client_email` in the share field, disable the `notify people` , click share

![image](https://github.com/sshalem/DevOps/assets/36256986/1db07075-a3d8-4677-a55f-0bd224c2e918)

Again click on share and change the General Access to `viewer` 

![image](https://github.com/sshalem/DevOps/assets/36256986/faf83c8f-5219-4744-9d42-4b2d2b32944a)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


---------------------------------------------------------------------------------------------





###### 4_spring_boot_app

<img src="https://img.shields.io/badge/- 4. spring boot app %20-blue" height=40px>

For this tutorial I will use IntelliJ.
So , II use Spring Initializer  with basic dependencies of 
1. dev tools
2. web

###### 4_1_copy_json_credentials_to_spring_src_folder

<img src="https://img.shields.io/badge/- 4.1. copy JSON credentials to spring src folder %20- green" height=30px>

- I copy the credentials file that was downloaded and copy it to Spring project.
- I refactor the name of the file to `credentials.json`

![image](https://github.com/sshalem/DevOps/assets/36256986/bc990e6e-facd-47d6-ba4e-05f8d22d6129)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




###### 4_2_add_google_drive_dependencies

<img src="https://img.shields.io/badge/- 4.2. add google drived ependencies %20- green" height=30px>

To integrate Google Drive functionality into your Spring Boot application, Include the necessary dependencies in your project’s pom.xml file. </br>
Add the following dependencies to enable interaction with the Google Drive API.

In mvnrepository search for 
1. google drive api (this is the service api)
2. google client api
3. google auth library

Use the following version , which are [compatible with each other.](#-)

```xml
		<!-- https://mvnrepository.com/artifact/com.google.apis/google-api-services-drive -->
		<dependency>
			<groupId>com.google.apis</groupId>
			<artifactId>google-api-services-drive</artifactId>
			<version>v3-rev197-1.25.0</version>
		</dependency>
		
		<dependency>
			<groupId>com.google.api-client</groupId>
			<artifactId>google-api-client</artifactId>
			<version>1.32.1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.google.auth/google-auth-library-oauth2-http -->
		<dependency>
			<groupId>com.google.auth</groupId>
			<artifactId>google-auth-library-oauth2-http</artifactId>
			<version>1.23.0</version>
		</dependency>

```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)





###### 4_3_service_layer

<img src="https://img.shields.io/badge/- 4.3. service layer %20- green" height=30px>


```java
package com.google.drive.api.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.drive.api.model.Response;

@Service
public class GoogleService {

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private static final String CREDENTIALS_FILE_KEY_PATH = getPathToGoogleCredentials();

	private Set<String> SCOPES = Collections.singleton(DriveScopes.DRIVE);

	/**
	 * get the directory where the the credentials.json resides
	 */
	private static String getPathToGoogleCredentials() {
		String currentDirectory = System.getProperty("user.dir");
		Path filePath = Paths.get(currentDirectory, "credentials.json");
		return filePath.toString();
	}

	public Response uploadFileToDrive(MultipartFile multipartFile) throws GeneralSecurityException, IOException {

		try {
			/**
			 * this folderId is taken from google drive account where I created the
			 * "files_upload" folder.
			 * https://drive.google.com/drive/folders/1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7 . I
			 * take only the id which is : 1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7
			 */
			String folderId = "1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7";

			Drive drive = createDriveInstance();

			/**
			 * File - used from from package of com.google.api.services.drive.model.File
			 * File - and not from Java.io package
			 */
			File googleFileMetaData = new File();

			googleFileMetaData.setName(multipartFile.getOriginalFilename());
			googleFileMetaData.setParents(Collections.singletonList(folderId));

			/**
			 * File - used from "com.google.api.services.drive.model.File"
			 * AbstractInputStreamContent - used from
			 * "com.google.api.client.http.AbstractInputStreamContent" create(File content,
			 * AbstractInputStreamContent mediaContent)
			 */
			String contentType = multipartFile.getContentType();

			// first get the bytes[] of the multipartFile and place them in a
			// ByteArrayInputStream
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());

			InputStreamContent inputStreamContent = new InputStreamContent(contentType, byteArrayInputStream);

			/**
			 * File - used from "com.google.api.services.drive.model.File"
			 * AbstractInputStreamContent - used from "com.google.api.client.http.AbstractInputStreamContent" 
			 * create(File content, AbstractInputStreamContent mediaContent)
			 */
			// This saves the file in google drive
			File uploadedFile = drive.files().create(googleFileMetaData, inputStreamContent).setFields("id").execute();

			String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
			System.out.println("IMAGE URL: " + imageUrl);

			return Response.builder().setStatus(200).setMessage("Image Uploaded To Drive").setUrl(imageUrl).build();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.builder().setStatus(500).setMessage(e.getMessage()).build();
		}

	}

	
	public List<File> getAllFiles() throws GeneralSecurityException, IOException {		
		Drive driveInstance = createDriveInstance();		
		FileList fileList = driveInstance.files().list().execute();		
		List<File> files = fileList.getFiles();
		return files;
	}
	
	private Drive createDriveInstance() throws GeneralSecurityException, IOException {

		FileInputStream fileInputStream = new FileInputStream(CREDENTIALS_FILE_KEY_PATH);
		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(fileInputStream).createScoped(SCOPES);
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);
		NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer).build();
	}
}
```


```java
public class Response {

	private int status;
	private String message;
	private String url;

	public Response() {
	}

	private Response(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.url = builder.url;
	}

	G/S/ToString

	public static Builder builder() {
		return new Builder();
	}

	// Internal Builder class
	
	public static class Builder {

		private int status;
		private String message;
		private String url;

		public Builder() {
			super();
		}

		public Builder setStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Response build() {
			return new Response(this);
		}
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




###### 4_4_controller_layer

<img src="https://img.shields.io/badge/- 4.4. controller_layer %20- green" height=30px>

```java
package com.google.drive.api.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.drive.api.service.GoogleService;

@RestController
@RequestMapping("/")
public class GoogleDriveController {

	@Autowired
	private GoogleService googleService;

	@PostMapping("/uploadToGoogleDrive")
	public ResponseEntity<?> fileUpload(@RequestParam("image") MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		try {
			return ResponseEntity.status(HttpStatus.OK).body(googleService.uploadFileToDrive(multipartFile));
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@GetMapping("/getAllFiles")
	public ResponseEntity<?> sample() throws IOException, GeneralSecurityException {
		return ResponseEntity.ok(googleService.getAllFiles());
	}
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------
