###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction - links](#Introduction)                          |
|  1  | [`LOB` , `BLOB` , `CLOB`](#1_lob_blob_clob)                          |
|  2  | [upload/download](#2_upload_download)                          |
|     | [2.0.0. Steps to Upload/Download](#2_0_0_steps_to_upload_download)                          |
|     | [2.0.1. Byte Array conversion](#2_0_1_byte_array_conversion)                          |
|  3  | [upload/download file to Data Base](#3_upload_download_file_to_data_base)                          |
|  4  | [upload/download file to File System](#4_upload_download_file_to_file_system)                          |
|  5  | [upload file as Base64 (and not as form append)](#5_upload_file_as_base64_without_form_appending)                          |5
|  6  | [Test with postman](#6_test_with_postman)                          |
|  7  | [ReactJS code with CSS](#7_reactjs_code_with_css)                          |
|  7  | [x](#5_main_jsx)                          |
|  8  | [6.1. x](#4_1_)                          |



--- 

###### Introduction

<img src="https://img.shields.io/badge/- Introduction  %20-blue" height=40px>

Links for Spring Boot File upload example with Multipart File

1. [Bezkoder file-upload Using @Controller](https://www.bezkoder.com/spring-boot-file-upload/)
2. [Multipart File Uploads using REST API](https://medium.com/@patelsajal2/how-to-create-a-spring-boot-rest-api-for-multipart-file-uploads-a-comprehensive-guide-b4d95ce3022b)
3. [Java Techie](https://www.youtube.com/watch?v=XUL60-Ke-L8)
4. [Daily Code Buffer](https://www.youtube.com/watch?v=dqm9Ciy-cjc)

#### [Bezkoder](#-)
- [bezkoder - download/upload from File System](https://www.bezkoder.com/spring-boot-file-upload/)
- [bezkoder - upload/download from Data base](https://www.bezkoder.com/spring-boot-upload-file-database/)

#### [JavaTechie](#-)
- [Java Techie - download/upload from File System](https://www.youtube.com/watch?v=7L1BSy5pnGo)
- [java techie - upload/download from Data base](https://www.youtube.com/watch?v=XUL60-Ke-L8)
- [GitHub JavaTechie file upload](https://github.com/Java-Techie-jt/file-storage)

[Definition of `LOB` , `BLOB` , `CLOB` ](https://www.geeksforgeeks.org/hibernate-save-image-and-other-types-of-values-to-database/)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---



###### 1_lob_blob_clob 

<img src="https://img.shields.io/badge/- 1. LOB, CLOB, BLOB %20-blue" height=40px>

Uploading and downloading files are a common task for any web application. </br>



Since we add the `@Lob` annotation to the image we want to save in DB , the article below exaplains the differences between 
- [Definition of `LOB` , `BLOB` , `CLOB` ](https://www.geeksforgeeks.org/hibernate-save-image-and-other-types-of-values-to-database/)
- `LOB`
- `CLOB`
- `BLOB` 

In this example I wil  show how to :
1. Upload file from server using @Controller/ @RestController
2. Download file from server using @Controller/ @RestController

Link : </br>
[you tube video Daily Code Buffer spring file upload/download](https://www.youtube.com/watch?v=dqm9Ciy-cjc)

GitHub code  </br>
[GitHub - DailyCodeBuffer spring-boot-file-upload](https://github.com/shabbirdwd53/spring-boot-file-upload)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 2_upload_download

<img src="https://img.shields.io/badge/- 2_upload_download  %20-blue" height=40px>

After the FrontEnd uploads a file, There are 2 places where we can store file:
1. File system (Meaning on local folder)
2. Strore on Data Base

###### 2_0_0_steps_to_upload_download

<img src="https://img.shields.io/badge/- 2_0_1_byte_array_conversion  %20-yellow" height=32px>

#### [Upload](#-)

To upload file to server, there are 2 ways  :
1. By using `form` with input tags . for single file add `<input type="file" >` , or , for multiple files use  `<input type="file" multiple>`
2. W/o using `form` tag 



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)





###### 2_0_1_byte_array_conversion

<img src="https://img.shields.io/badge/- 2_0_1_byte_array_conversion  %20-yellow" height=32px>

In Java ,to convert `byte[]` array to String, 2 ways:
- text data byte[], try `new String(bytes, StandardCharsets.UTF_8)`.
- binary data byte[] like images , try `Base64` encoding.
- Another way to conver byte[] to String is use the method from `Arrays.toString(bytes)` , which return a String as a array format (see implemented code in the class)

For text or character data, we use `new String(bytes, StandardCharsets.UTF_8)` to convert the `byte[]` to a `String` directly. </br>
However, for cases that `byte[]` is holding the binary data like the `image` or other `non-text` data, </br>
the best practice is to convert the `byte[]` into a `Base64` encoded string. </br>
This `Base64` encode decode string is still widely use in
1. email attachment
2. embed image files inside HTML or CSS

Note:

```java
  // string to byte[]
  byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);

  // byte[] to string
  String s = new String(bytes, StandardCharsets.UTF_8);

//-------------------------------------------------------------

 // Java 8 - Base64 class, finally.
  byte[] bytes = (from image , non-text data)

  // Option 1:
  // ---------
  // encode, convert byte[] to base64 encoded string
  String s = Base64.getEncoder().encodeToString(bytes);

  // decode, convert base64 encoded string back to byte[]
  byte[] decode = Base64.getDecoder().decode(s);

  // Option 2:
  // ---------
  // Converts the byte[] array to String. The String formed as an array (see code implementation)
  String byteArrayAsString = Arrays.toString(bytes);
```

#### [FrontEnd](#-)

This code converts the image file to a byeArray , then we can send it to server as `Uint8Array` , instead of sending a File ,and server will receive a `MultipartFile` object.

```js
const buffer = await selectedFile.arrayBuffer();
let uint8Array = new Uint8Array(buffer);
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 3_upload_download_file_to_data_base 

<img src="https://img.shields.io/badge/- 3_upload_download_file_to_data_base %20-blue" height=40px>

In this example I use database to store the file (image text, none-text ) in DB. </br>

### [FrontEnd](#-)

In the `FrontEnd`  code I use :
1. `input type=file` tag
2. Function to Upload to DB by creating a Form instance and appending the file to the `form`
3. Function to Load Image/File From Server (as base64) and show image on img tag
4. Button to Download file From DB and save it in downloads folder

### [BackEnd](#-)

In this example ,I save the FIle/Image in DB. </br> 
More precise, I will upload/download/load image </br>
In `Controller` I have 3 methods
1. `uploadAttachmentToDB` which receives a `MultipartFile` . `MultipartFile` has several fields , one of them is the content as `byte[]`.
2. `downloadAttachmentFromDB` - To download the image to computer
3. `loadAttachmentFromDB` - to load image and dsiplay it in an img tag

In DB , I will save the image file as `byte[]` , which will have also the `@Lob` annotaion


#### [1. Packge layout + application.properties](#-)

![image](https://github.com/sshalem/Spring-Boot/assets/36256986/12b3c65f-5d33-4aab-9e09-568ddbd9002f)

```js
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# Enabling H2 Console
spring.h2.console.enabled=true
# Custom H2 Console URL from /h2-console to /h2
spring.h2.console.path=/h2

# spring.servlet.multipart.max-file-size: max file size for each request.
spring.servlet.multipart.max-file-size=5000KB

# spring.servlet.multipart.max-request-size: max request size for a multipart/form-data.
spring.servlet.multipart.max-request-size=5000KB

# enable color on console
spring.output.ansi.enabled=always
```


#### [2. Entity](#-)

Notice , </br>
In this example I save the file in DB as `byte []` byte Array. </br>
I add to it the annotation of `@Lob` </br>
The `@Lob` annotation specifies that the database should store the property as `Large Object`.  </br>
Since we’re going to save `byte array`, we’re using `BLOB`. </br>
- BLOB is for storing binary data, while
- CLOB is for storing text data.

```java
package com.database.upload.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DB_ATTACHMENT_TB")
public class DataBaseAttachmentEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fileName;
	private String fileType;
	@Lob
	private byte[] data;

	public DataBaseAttachmentEntity() {
	}

	public DataBaseAttachmentEntity(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	G/S/ToString
}
```


#### [3. Repository](#-)

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.database.upload.entity.DataBaseAttachmentEntity;

@Repository
public interface DataBaseRepository extends JpaRepository<DataBaseAttachmentEntity, String> {
}
```

#### [3. Service](#-)

```java
import org.springframework.web.multipart.MultipartFile;
import com.database.upload.entity.DataBaseAttachmentEntity;

public interface StorageService {
    DataBaseAttachmentEntity uploadAttachmentToDB(MultipartFile multipartFile) throws Exception;
    DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception; 
}
```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.database.upload.entity.DataBaseAttachmentEntity;
import com.database.upload.repository.DataBaseRepository;

@Service
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private DataBaseRepository dataBaseRepository;

	@Override
	public DataBaseAttachmentEntity uploadAttachmentToDB(MultipartFile multipartFile) throws Exception {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName);
			}
			DataBaseAttachmentEntity dataBaseAttachmentEntity = new DataBaseAttachmentEntity(
				fileName,
				multipartFile.getContentType(),
				multipartFile.getBytes());

			return dataBaseRepository.save(dataBaseAttachmentEntity);
		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName);
		}
	}

	@Override
	public DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception {
		return dataBaseRepository.findById(attachmentId)
			.orElseThrow(() -> new Exception("File not found with Id: " + attachmentId));
	}
}
```

#### [4. Model](#-)


```java
public class ResponseData {
	private String id;
	private String fileName;
	private String downloadURL;
	private String fileType;
	private long fileSize;

	public ResponseData() {
	}

  G/S/ToString
}
```

#### [5. Controller](#-)


```java
package com.database.upload.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.database.upload.entity.DataBaseAttachmentEntity;
import com.database.upload.model.ResponseData;
import com.database.upload.service.StorageService;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private StorageService storageService;


	@PostMapping(path = "/database/upload")
	public ResponseEntity<?> uploadAttachmentToDB(@RequestParam("attachment") MultipartFile multipartFile) throws Exception {

		/**
		 *  the @RequestParam("attachment") comes from frontEnd code:
		 *  `formData.append('attachment', selectedFile);
		 */
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.uploadAttachmentToDB(multipartFile);
		
		// Here I setup the download URL
		// Where FrontEnd will click the link
		// and will download the file
		String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/database/download/") // this path need to same path of the @GetMapping
				.path(dataBaseAttachmentEntity.getId()) // concatenate the Id of the attachment to the url
				.toUriString();

		ResponseData responseData = new ResponseData(
				dataBaseAttachmentEntity.getId(),
				dataBaseAttachmentEntity.getFileName(),
				downloadURl,
				multipartFile.getContentType(),
				multipartFile.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	/**
	 * 
	 * This method is used for downloading the image from server
	 * the url MUST be same as the downloadURl I define in the POST method uploadAttachmentToDB 
	 * 
	 */
	@GetMapping(path = "/database/download/{attachmentId}")
	public ResponseEntity<?> downloadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {

		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
				.body(new ByteArrayResource(dataBaseAttachmentEntity.getData()));
	}
	
	
	/**
	 * 
	 * This method I use to load an image from server
	 * And display it in an <img> tag as Base64
	 * 
	 */
	@GetMapping(path = "/database/loadAttachment/{attachmentId}")
	public ResponseEntity<?> loadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {

		// I must converts the byte[] Array , to String 
		// see the implementation inside Arrays.toString(x)
		// And let the FrontENd , convert the byteArray to an image so I can display it on the page
		
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);

		// Option 1 Best Practice: 
		// convert Byte[] to Base64 String type	
		String base64String = Base64.getEncoder().encodeToString(dataBaseAttachmentEntity.getData());
		
		// Option 2: 
		// convert Byte[] to String		
		// String byteArrayAsString = Arrays.toString(dataBaseAttachmentEntity.getData());
		
		// return ResponseEntity.ok()
		//		.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
		//		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
		//		.body(byteArrayAsString);
					
		return ResponseEntity.ok().body(base64String);
	}
}
```


#### [FrontEnd code with ReactJS](#-)

```js
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileName, setFileName] = useState(null);
  const [attachmentId, setAttachmentId] = useState(null);
  const [image, setImage] = useState(null);
  const [downloadUrl, setDownloadUrl] = useState(null);
  const [fileType, setFileType] = useState(null);

  /**
   * save the selected file in a state
   */
  const handleSelectedFileToUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  /**
   * [1] - Upload file to server
   */
  const handleUploadToServer = async () => {
    const formData = new FormData();
    // this is what the @RequestParam will see at Backend with Spring Controller
    formData.append('attachment', selectedFile);
    const { data } = await axios.post(`http://localhost:8080/database/upload`, formData);

    console.log(data);
    setFileName(data.fileName);
    setAttachmentId(data.id);
    // This url, when I click it , It triggers `download` from server
    setDownloadUrl(data.downloadURL);
    setFileType(data.fileType);
  };

  /**
   * [2] - get image (as Base64) from server (load image) and display it on html page
   * (Postman shows , this is faster to download + file size 3x smaller)
   */
  const handleLoadImageFromServer = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/loadAttachment/${attachmentId}`);
    console.log(data);
    setImage(data);
  };

  return (
    <>
      <div style={{ padding: '2rem' }}>
        <h3>
          file upload to <span>&#8594;</span> DataBase in Server
        </h3>
        <br />
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleUploadToServer}>
            Upload to Server
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleLoadImageFromServer}>
            load (get Base64) image from Server
          </button>
        </div>
        <br />
        {/*  */}
        {/* To download Image */}
        {/*  */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <br />
        <h5>to download Image , (to dowanloads folder)</h5>
        <h5>1. click on the button which triggers anchor tag with the url link of download from server</h5>
        <h5>2. click on the anchor link whcih does the same</h5>
        <br />
        <div>
          <a href={downloadUrl}>
            <button className="btn upload-download">Download image link</button>
          </a>
          <br />
          <br />
          <div>
            <p>
              Image link , when clicked ,it will download image from server, to <em style={{ fontWeight: '700' }}>downloads folder</em>:
            </p>
            <div style={{ marginTop: '1rem' }}>
              <a href={downloadUrl}>
                {downloadUrl ? downloadUrl : <span style={{ color: 'red' }}>Only after I upload image I wll see the url link</span>}
              </a>
            </div>
          </div>
        </div>
        <br />
        <div>{image ? <img src={`data:${fileType};base64, ${image}`} /> : null}</div>

        {/* ********************************************************** */}
        {/* **********  More styling options for input-file     ****** */}
        {/* **********                                           ***** */}
        {/* ********************************************************** */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <h3>More styling options for input-file </h3>
        <h4>The upload button ,is not configured for these options</h4>
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn input-file" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <br />
        {/* option 2 for styling */}
        <div>
          <input type="file" className="input-file-option-2" />
        </div>
        <br />
        {/* option 3 for styling by using the label tag along with input tag + htmlFor attrinute*/}
        <div>
          <label className="file-input-label-3" htmlFor="file-input">
            Select a File
          </label>
          <input type="file" id="file-input" name="file-input" className="file-input-3" />
        </div>
        <br />
      </div>
    </>
  );
}

export default App;
```





[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



###### 4_upload_download_file_to_file_system

<img src="https://img.shields.io/badge/- 4_upload_download_file_to_file_system  %20-blue" height=40px>

In this example , I store the file (image, text , not-text) in FileSystem. </br>
But, </br>
I store a reference to the file in DB , in order to track it.

### [1. Package Layout](#-)

![image](https://github.com/sshalem/Spring-Boot/assets/36256986/86a4ce72-1a66-4779-a6ac-1224de64dc46)

```js
spring.output.ansi.enabled=always

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# Enabling H2 Console
spring.h2.console.enabled=true
# Custom H2 Console URL from /h2-console to /h2
spring.h2.console.path=/h2

# spring.servlet.multipart.max-file-size: max file size for each request.
spring.servlet.multipart.max-file-size=5000KB

# spring.servlet.multipart.max-request-size: max request size for a multipart/form-data.
spring.servlet.multipart.max-request-size=5000KB
```

### [2. Entity](#-)

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FILE_DATA")
public class FileSystemAttachmentEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String type;
	private String filePath;

	public FileSystemAttachmentEntity() {
		super();
	}

	G/S/ToString
}
```

### [3. Repository](#-)

```java
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.filesystem.entity.FileSystemAttachmentEntity;

@Repository
public interface FileDataRepository extends JpaRepository<FileSystemAttachmentEntity, String> {

	Optional<FileSystemAttachmentEntity> findByName(String fileName);
}
```

### [4. Service](#-)

```java
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.repository.FileDataRepository;

@Service
public class FileService {

	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String FOLDER_PATH = "c:/Localdata/";
	
	public FileSystemAttachmentEntity uploadToFileSystem(MultipartFile file) throws IOException {
		
		// clean path removes any `/` or `.` from url
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String path = FOLDER_PATH + fileName;
		
		FileSystemAttachmentEntity fileSystemAttachmentEntity = new FileSystemAttachmentEntity();
		fileSystemAttachmentEntity.setName(fileName);
		fileSystemAttachmentEntity.setFilePath(path);
		fileSystemAttachmentEntity.setType(file.getContentType());

		FileSystemAttachmentEntity returnedFileDataEntity = fileDataRepository.save(fileSystemAttachmentEntity);

		// this saves the file in the the filePath I declare
		// transferTo - a method from `MultipartFile` class 
		file.transferTo(new File(path));

        return returnedFileDataEntity;
    }

	public FileSystemAttachmentEntity downloadFromFileSystem(String fileName) throws IOException {

		FileSystemAttachmentEntity fileDataEntity = fileDataRepository.findByName(fileName).get();
		return fileDataEntity;
	}
}
```


### [5. Model](#-)

```java
public class ResponseData {

	private String id;
	private String fileName;
	private String downloadURL;
	private String fileType;
	private long fileSize;

	public ResponseData() {
	}

	G/S/ToString
}
```

### [6. Contoller](#-)

```java
package com.filesystem.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.model.ResponseData;
import com.filesystem.service.FileService;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadAttachmentToFileSystem(@RequestParam("attachment") MultipartFile multipartFile) throws IOException {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.uploadToFileSystem(multipartFile);

		// Here I setup the download URL
		// Where FrontEnd will click the link
		// and will download the file
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/fileSystem/download/") // this path need to same path of the @GetMapping
				.path(fileSystemAttachmentEntity.getName())
				.toUriString();

		ResponseData responseData = new ResponseData(
				fileSystemAttachmentEntity.getId(),
				fileSystemAttachmentEntity.getName(), 
				downloadUrl, 
				multipartFile.getContentType(), 
				multipartFile.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping("/fileSystem/download/{fileName}")
	public ResponseEntity<?> downloadAttachmentFromFileSystem(@PathVariable String fileName) throws IOException {
		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(fileSystemAttachmentEntity.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemAttachmentEntity.getName() + "\"")
				.body(new ByteArrayResource(data));

	}
	
	/**
	 * 
	 * This method I use to load an image from server
	 * And display it in an <img> tag as Base64
	 * 
	 */
	@GetMapping(path = "/fileSystem/loadAttachment/{fileName}")
	public ResponseEntity<?> loadAttachmentFromFileSystem(@PathVariable String fileName) throws Exception {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());
		
		String base64String = Base64.getEncoder().encodeToString(data);
		
		return ResponseEntity.ok().body(base64String);
	}		
}
```


#### [FrontEnd code with ReactJS](#-)


```js
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileName, setFileName] = useState(null);
  const [attachmentId, setAttachmentId] = useState(null);
  const [image, setImage] = useState(null);
  const [downloadUrl, setDownloadUrl] = useState(null);
  const [fileType, setFileType] = useState(null);

  /**
   * save the selected file in a state
   */
  const handleSelectedFileToUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  /**
   * [1] - Upload file to server
   */
  const handleUploadToServer = async () => {
    const formData = new FormData();
    // this is what the @RequestParam will see at Backend with Spring Controller
    formData.append('attachment', selectedFile);
    const { data } = await axios.post(`http://localhost:8080/fileSystem/upload`, formData);

    console.log(data);
    setFileName(data.fileName);
    setAttachmentId(data.id);
    // This url, when I click it , It triggers `download` from server
    setDownloadUrl(data.downloadURL);
    setFileType(data.fileType);
  };

  /**
   * [2] - get image (as Base64) from server (load image) and display it on html page
   * (Postman shows , this is faster to download + file size 3x smaller)
   */
  const handleLoadImageFromServer = async () => {
    const { data } = await axios.get(`http://localhost:8080/fileSystem/loadAttachment/${fileName}`);
    console.log(data);
    setImage(data);
  };

  return (
    <>
      <div style={{ padding: '2rem' }}>
        <h3>
          file upload to <span>&#8594;</span> File System in Server
        </h3>
        <br />
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleUploadToServer}>
            Upload to Server
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleLoadImageFromServer}>
            load (get Base64) image from Server
          </button>
        </div>
        <br />
        {/*  */}
        {/* To download Image */}
        {/*  */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <br />
        <h5>to download Image , (to dowanloads folder)</h5>
        <h5>1. click on the button which triggers anchor tag with the url link of download from server</h5>
        <h5>2. click on the anchor link whcih does the same</h5>
        <br />
        <div>
          <a href={downloadUrl}>
            <button className="btn upload-download">Download image link</button>
          </a>
          <br />
          <br />
          <div>
            <p>
              Image link , when clicked ,it will download image from server, to <em style={{ fontWeight: '700' }}>downloads folder</em>:
            </p>
            <div style={{ marginTop: '1rem' }}>
              <a href={downloadUrl}>
                {downloadUrl ? downloadUrl : <span style={{ color: 'red' }}>Only after I upload image I wll see the url link</span>}
              </a>
            </div>
          </div>
        </div>
        <br />
        <div>{image ? <img src={`data:${fileType};base64, ${image}`} /> : null}</div>

        {/* ********************************************************** */}
        {/* **********  More styling options for input-file     ****** */}
        {/* **********                                           ***** */}
        {/* ********************************************************** */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <h3>More styling options for input-file </h3>
        <h4>The upload button ,is not configured for these options</h4>
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn input-file" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <br />
        {/* option 2 for styling */}
        <div>
          <input type="file" className="input-file-option-2" />
        </div>
        <br />
        {/* option 3 for styling by using the label tag along with input tag + htmlFor attrinute*/}
        <div>
          <label className="file-input-label-3" htmlFor="file-input">
            Select a File
          </label>
          <input type="file" id="file-input" name="file-input" className="file-input-3" />
        </div>
        <br />
      </div>
    </>
  );
}

export default App;
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




--------------------------------------------------------------------------------------------------------------------------------




###### 5_upload_file_as_base64_without_form_appending

<img src="https://img.shields.io/badge/- 5_upload_file_as_base64_without_form_appending  %20-blue" height=40px>

In this example I will upload a file w/o the usage of a `form` Instance as in section 3. </br>
Here, I will convert the file/image , to base64 , then I will send it to server. </br>


### [Backend](#-)

See in project `file-upload-base64-file-system` 

### [FrontEnd](#-)

This is the frontend code (Project 03) , see how I convert the image to base64 using:
1.  `FileReader`
2.  use `promise` to return the converted file


```js
function App() {
  const [attachmentId, setAttachmentId] = useState(null);
  const [downloadUrl, setDownloadUrl] = useState(null);
  const [base64Image, setBase64Image] = useState('');
  const [fileName, setFileName] = useState(null);
  const [fileType, setFileType] = useState(null);
  const [fileSize, setFileSize] = useState(null);
  /**
   * save the selected file in a state
   */
  const handleSelectedFileToUpload = async (event) => {
    const fileImage = event.target.files[0];
    console.log(fileImage);
    setFileName(fileImage.name);
    setFileType(fileImage.type);
    setFileSize(fileImage.size);
    const convertedBase64Image = await convertToBase64(fileImage);
    setBase64Image(convertedBase64Image);
  };

  const convertToBase64 = async (fileImage) => {
    const reader = new FileReader();
    reader.readAsDataURL(fileImage);
    const base64Data = await new Promise((resolve, reject) => {
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });
    return base64Data;
  };

  /**
   * [1] - Upload file to server
   */
  const handleUploadToServer = async () => {
    const dataToSend = {
      image: base64Image,
      name: fileName,
      type: fileType,
      size: fileSize,
    };

    const { data } = await axios.post(`http://localhost:8080/fileSystem/upload`, dataToSend);

    console.log(data);
    setFileName(data.fileName);
    setAttachmentId(data.id);
    // This url, when I click it , It triggers `download` from server
    setDownloadUrl(data.downloadURL);
    setFileType(data.fileType);
  };

  /**
   * [2] - get image (as Base64) from server (load image) and display it on html page
   * (Postman shows , this is faster to download + file size 3x smaller)
   */
  const handleLoadImageFromServer = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/loadAttachment/${attachmentId}`);
    console.log(data);
    // setImage(data);
  };

  return (
    <>
      <div style={{ padding: '2rem' }}>
        <h3>
          file upload <span>&#8594;</span> as Base64 to DataBase in Server
        </h3>
        <br />
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleUploadToServer}>
            Upload to Server
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleLoadImageFromServer}>
            load (get Base64) image from Server
          </button>
        </div>
        <br />
        {/*  */}
        {/* To download Image */}
        {/*  */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <br />
        <h5>to download Image , (to dowanloads folder)</h5>
        <h5>1. click on the button which triggers anchor tag with the url link of download from server</h5>
        <h5>2. click on the anchor link whcih does the same</h5>
        <br />
        <div>
          <a href={downloadUrl}>
            <button className="btn upload-download">Download image link</button>
          </a>
          <br />
          <br />
          <div>
            <p>
              Image link , when clicked ,it will download image from server, to <em style={{ fontWeight: '700' }}>downloads folder</em>:
            </p>
            <div style={{ marginTop: '1rem' }}>
              <a href={downloadUrl}>
                {downloadUrl ? downloadUrl : <span style={{ color: 'red' }}>Only after I upload image I wll see the url link</span>}
              </a>
            </div>
          </div>
        </div>
        <br />
        <div>{base64Image ? <img src={base64Image} /> : null}</div>

        {/* ********************************************************** */}
        {/* **********  More styling options for input-file     ****** */}
        {/* **********                                           ***** */}
        {/* ********************************************************** */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <h3>More styling options for input-file </h3>
        <h4>The upload button ,is not configured for these options</h4>
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn input-file" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <br />
        {/* option 2 for styling */}
        <div>
          <input type="file" className="input-file-option-2" />
        </div>
        <br />
        {/* option 3 for styling by using the label tag along with input tag + htmlFor attrinute*/}
        <div>
          <label className="file-input-label-3" htmlFor="file-input">
            Select a File
          </label>
          <input type="file" id="file-input" name="file-input" className="file-input-3" />
        </div>
        <br />
      </div>
    </>
  );
}

export default App;
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




--------------------------------------------------------------------------------------------------------------------------------


###### 6_test_with_postman

<img src="https://img.shields.io/badge/- 6_test_with_postman  %20-blue" height=40px>

###### 3_1_test_using_data_base

<img src="https://img.shields.io/badge/- 3_1_test_using_data_base  %20-yellow" height=32px>

Open Postman and send `POST` request to url of `localhost:8080/database/upload` and add to `body`: 
- set `key` with name `attachment` , because I set this name in controller the `@RequestParam("attachment")`
- change the type to `file`
- choose the file to upload

![image](https://github.com/sshalem/Spring-Boot/assets/36256986/7a511887-9ef5-4b2d-af1c-3d694aced913)







[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_2_test_using_file_system 




<img src="https://img.shields.io/badge/- 3_2_test_using_file_system  %20-yellow" height=32px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


----------------

###### 7_reactjs_code_with_css

<img src="https://img.shields.io/badge/- 7_reactjs_code_with_css  %20-blue" height=40px>

#### [Step 1:  create vite app](#-)

- also Install `axios`

```js
npm create vite@latest frontend-file-upload --template react
npm install
npm install axios
```

#### [step 2: Setup code to upload file (any file)](#-)

Note: </br>
In order to display image that I download from server I need to use `Data URLs`, so do the following in the `img` tag. </br>
In addition I need to set the fileType that I receive from server


```html
<img src={`data:image/png;base64, ${image}`} />

// Modify with fileType from server
<img src={`data:${fileType};base64, ${image}`} />
```

Final code in `App.jsx` 

```js
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileName, setFileName] = useState(null);
  const [attachmentId, setAttachmentId] = useState(null);
  const [image, setImage] = useState(null);
  const [downloadUrl, setDownloadUrl] = useState(null);
  const [fileType, setFileType] = useState(null);

  /**
   * save the selected file in a state
   */
  const handleSelectedFileToUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  /**
   * [1] - Upload file to server
   */
  const handleUploadToServer = async () => {
    const formData = new FormData();
    // this is what the @RequestParam will see at Backend with Spring Controller
    formData.append('attachment', selectedFile);
    const { data } = await axios.post(`http://localhost:8080/database/upload`, formData);

    console.log(data);
    setFileName(data.fileName);
    setAttachmentId(data.id);
    // This url, when I click it , It triggers `download` from server
    setDownloadUrl(data.downloadURL);
    setFileType(data.fileType);
  };

  /**
   * [2] - get image (as Base64) from server (load image) and display it on html page
   * (Postman shows , this is faster to download + file size 3x smaller)
   */
  const handleLoadImageFromServer = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/loadAttachment/${attachmentId}`);
    console.log(data);
    setImage(data);
  };

  return (
    <>
      <div style={{ padding: '2rem' }}>
        <h3>file upload</h3>
        <br />
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleUploadToServer}>
            Upload to Server
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleLoadImageFromServer}>
            load (get Base64) image from Server
          </button>
        </div>
        <br />
        {/*  */}
        {/* To download Image */}
        {/*  */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <br />
        <h5>to download Image , (to dowanloads folder)</h5>
        <h5>1. click on the button which triggers anchor tag with the url link of download from server</h5>
        <h5>2. click on the anchor link whcih does the same</h5>
        <br />
        <div>
          <a href={downloadUrl}>
            <button className="btn upload-download">Download image link</button>
          </a>
          <br />
          <br />
          <div>
            <p>
              Image link , when clicked ,it will download image from server, to <em style={{ fontWeight: '700' }}>downloads folder</em>:
            </p>
            <div style={{ marginTop: '1rem' }}>
              <a href={downloadUrl}>
                {downloadUrl ? downloadUrl : <span style={{ color: 'red' }}>Only after I upload image I wll see the url link</span>}
              </a>
            </div>
          </div>
        </div>
        <br />
        <div>{image ? <img src={`data:${fileType};base64, ${image}`} /> : null}</div>

        {/* ********************************************************** */}
        {/* **********  More styling options for input-file     ****** */}
        {/* **********                                           ***** */}
        {/* ********************************************************** */}
        <div>
          ____________________________________________________________________________________________________________________________________
        </div>
        <h3>More styling options for input-file </h3>
        <h4>The upload button ,is not configured for these options</h4>
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn input-file" onChange={handleSelectedFileToUpload} />
        </div>
        <br />
        <br />
        {/* option 2 for styling */}
        <div>
          <input type="file" className="input-file-option-2" />
        </div>
        <br />
        {/* option 3 for styling by using the label tag along with input tag + htmlFor attrinute*/}
        <div>
          <label className="file-input-label-3" htmlFor="file-input">
            Select a File
          </label>
          <input type="file" id="file-input" name="file-input" className="file-input-3" />
        </div>
        <br />
      </div>
    </>
  );
}

export default App;
```

### [CSS] 

```css
/* ============= GLOBAL CSS =============== */

*,
::after,
::before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  font-size: 100%;
} /*16px*/

:root {
  /* colors */
  --primary-50: #e0fcff;
  --primary-100: #bef8fd;
  --primary-200: #87eaf2;
  --primary-300: #54d1db;
  --primary-400: #38bec9;
  --primary-500: #2cb1bc;
  --primary-600: #14919b;
  --primary-700: #0e7c86;
  --primary-800: #0a6c74;
  --primary-900: #044e54;

  /* grey */
  --grey-50: #f8fafc;
  --grey-100: #f1f5f9;
  --grey-200: #e2e8f0;
  --grey-300: #cbd5e1;
  --grey-400: #94a3b8;
  --grey-500: #64748b;
  --grey-600: #475569;
  --grey-700: #334155;
  --grey-800: #1e293b;
  --grey-900: #0f172a;
  /* rest of the colors */
  --black: #222;
  --white: #fff;
  --red-light: #f8d7da;
  --red-dark: #842029;
  --green-light: #d1e7dd;
  --green-dark: #0f5132;

  --small-text: 0.875rem;
  --extra-small-text: 0.7em;
  /* rest of the vars */

  --border-radius: 0.25rem;
  --letter-spacing: 1px;
  --transition: 0.3s ease-in-out all;
  --max-width: 1120px;
  --fixed-width: 600px;
  --fluid-width: 90vw;
  --nav-height: 6rem;
  /* box shadow*/
  --shadow-1: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  --shadow-2: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  --shadow-3: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
  --shadow-4: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
  /* DARK MODE */

  --background-color: var(--grey-50);
  --text-color: var(--grey-900);
  --background-secondary-color: var(--white);
  --text-secondary-color: var(--grey-500);
}

body {
  background: var(--background-color);
  color: var(--text-color);
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  font-weight: 400;
  line-height: 1;
}
p {
  margin: 0;
}
h1,
h2,
h3,
h4,
h5 {
  margin: 0;
  font-weight: 400;
  line-height: 1;
  text-transform: capitalize;
  letter-spacing: var(--letter-spacing);
}

h1 {
  font-size: clamp(2rem, 5vw, 5rem); /* Large heading */
}

h2 {
  font-size: clamp(1.5rem, 3vw, 3rem); /* Medium heading */
}

h3 {
  font-size: clamp(1.25rem, 2.5vw, 2.5rem); /* Small heading */
}

h4 {
  font-size: clamp(1rem, 2vw, 2rem); /* Extra small heading */
}

h5 {
  font-size: clamp(0.875rem, 1.5vw, 1.5rem); /* Tiny heading */
}

/* buttons */

.btn {
  cursor: pointer;
  color: var(--white);
  background: var(--primary-500);
  border: transparent;
  border-radius: var(--border-radius);
  letter-spacing: var(--letter-spacing);
  padding: 0.375rem 0.75rem;
  box-shadow: var(--shadow-1);
  transition: var(--transition);
  text-transform: capitalize;
  display: inline-block;
}
.btn:hover {
  background: var(--primary-700);
  box-shadow: var(--shadow-3);
}
.btn-hipster {
  color: var(--primary-500);
  background: var(--primary-200);
}
.btn-hipster:hover {
  color: var(--primary-200);
  background: var(--primary-700);
}
.btn-block {
  width: 100%;
}
button:disabled {
  cursor: wait;
}
.danger-btn {
  color: var(--red-dark);
  background: var(--red-light);
}
.danger-btn:hover {
  color: var(--white);
  background: var(--red-dark);
}

.upload-download {
  width: 20rem;
  display: inline-block;
  font-size: 0.9rem;
}

img {
  width: 15rem;
  border-radius: 10px;
}

/**************************
  Option 1 for input CSS 
***************************/

.input-file {
  width: 10rem;
  color: transparent;
}

/* this css controlos the interanl css of input file tag */
.input-file::before {
  margin-left: 1rem;
  display: inline-block;
  content: 'my choose file';
  cursor: pointer;
  color: #fff;
  font-size: 0.9rem;
}

/* this line disables the 'choose file' button */
.input-file::file-selector-button {
  display: none;
}

/**************************
  Option 2 for input CSS 
***************************/
h3 {
  margin: 2rem;
}

.input-file-option-2 {
  width: 10rem;
  border-radius: 7px;
}

.input-file-option-2::file-selector-button {
  background-color: blue;
  border-radius: 7px;
  color: #fff;
  width: 10rem;
  height: 2rem;
  cursor: pointer;
  border: none;
  font-size: 0.9rem;
}

/**************************
  Option 3 for input CSS 
***************************/

.file-input-3 {
  display: none;
}

.file-input-label-3 {
  cursor: pointer;
  padding: 0.5rem 1.8rem;
  display: inline-block;
  border-radius: 7px;
  font-size: 1.3em;
  background-color: brown;
  color: #fff;
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 

<img src="https://img.shields.io/badge/-   %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 

<img src="https://img.shields.io/badge/-   %20-blue" height=40px>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---




---


###### 

<img src="https://img.shields.io/badge/- X  %20-yellow" height=40px>

<img src="https://img.shields.io/badge/- X %20- green" height=32px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/-X.   %20-yellow" height=35px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


