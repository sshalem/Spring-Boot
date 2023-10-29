###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction - links](#Introduction)                          |
|  1  | [`LOB` , `BLOB` , `CLOB`](#1_lob_blob_clob)                          |
|  2  | [upload/download](#2_upload_download)                          |
|     | [2.0.1. Byte Array conversion](#2_0_1_byte_array_conversion)                          |
|     | [2.1. upload/download using Data Base](#2_1_upload_download_using_data_base)                          |
|     | [2.2. upload/download using File System](#2_2_upload_download_using_file_system)                          |
|  3  | [Test with postman](#3_test_with_postman)                          |
|  4  | [Test with ReactJS code](#4_test_with_reactjs_code)                          |
|  5  | [x](#5_main_jsx)                          |
|     | [6.1. x](#4_1_)                          |



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


###### 2_1_upload_download_using_data_base 

<img src="https://img.shields.io/badge/- 2_1_upload_download_using_data_base  %20-yellow" height=32px>

In this example I use database to store the file (image text, none-text ) in DB. </br>

#### [1. Packge layout](#-)

![image](https://github.com/sshalem/Spring-Boot/assets/36256986/12b3c65f-5d33-4aab-9e09-568ddbd9002f)

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
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
			DataBaseAttachmentEntity dataBaseAttachmentEntity = new DataBaseAttachmentEntity(fileName, multipartFile.getContentType(), multipartFile.getBytes());
			return dataBaseRepository.save(dataBaseAttachmentEntity);
		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName);
		}
	}

	@Override
	public DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception {
		return dataBaseRepository.findById(attachmentId).orElseThrow(() -> new Exception("File not found with Id: " + attachmentId));
	}
}
```

#### [4. Controller](#-)


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

```java
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(path = "/database/download/{attachmentId}")
	public ResponseEntity<?> downloadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {
		
		// T ways to return data from server:
    // 1. convert byte[] Array to String . (see the implementation inside Arrays.toString(x) )
		// 2. (Best Practice) convert Byte[] Arry to Base64 String type	
    // At FrontEnd , convert the received data to an image so I can display it on the page (see FrontEnd Implementation)
		
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);

		// Option 1: 
		// convert Byte[] to String		
    // String byteArrayAsString = Arrays.toString(dataBaseAttachmentEntity.getData());
		
    // return ResponseEntity.ok()
    //    .contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
    //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
    //		.body(byteArrayAsString);
		
		
		// Option 2 (Best Practice): 
		// convert Byte[] to Base64 String type	
		String base64String = Base64.getEncoder().encodeToString(dataBaseAttachmentEntity.getData());
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
				.body(base64String);
	}
}
```






[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



###### 2_2_upload_download_using_file_system

<img src="https://img.shields.io/badge/- 2_2_upload_download_using_file_system  %20-yellow" height=32px>







[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)






----------------


###### 3_test_with_postman

<img src="https://img.shields.io/badge/- 3_test_with_postman  %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


----------------

###### 4_test_with_reactjs_code

<img src="https://img.shields.io/badge/- 4_test_with_reactjs_code  %20-blue" height=40px>

#### [Step 1:  create vite app](#-)

- also Install `axios`

```js
npm create vite@latest frontend-file-upload --template react
npm install
npm install axios
```

#### [step 2: Setup code to upload file (any file)](#-)

Note : I used the css from `Jobify` project `index.css`

```js
import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = () => {
    const formData = new FormData();
    formData.append('attachment', selectedFile);

    // to dislapy what are the key/value in formData
    for (const data of formData.entries()) {
      console.log(data);
    }
    upload(formData);
  };

  const upload = async (formData) => {
    const { data } = await axios.post(`http://localhost:8080/database/upload`, formData);
    console.log(data);
  };

  return (
    <>
      <h3>file upload</h3>
      <br />
      <div>
        <input className="btn" type="file" onChange={handleFileUpload} />
      </div>
      <br />
      <div>
        <button className="btn" onClick={handleUpload}>
          Upload
        </button>
      </div>
    </>
  );
}

export default App;
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


