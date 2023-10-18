###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction - links](#Introduction)                          |
|  1  | [Simple Upload file with Spring boot + HTML](#1_simple_setup_spring_and_html)                          |
|  2  | [`LOB` , `BLOB` , `CLOB`](#2_daily_code_buffer_upload_download_files)                          |
|  3  | [upload/download](#3_java_techie)                          |
|     | [3.1. upload/download using File System](#3_1_upload_download_using_file_system)                          |
|     | [3.2. upload/download using Data Base](#3_2_upload_download_using_data_base)                          |
|  4  | [main.jsx](#4_main_jsx)                          |
|     | [4.1. x](#4_1_)                          |



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

[Definition of `LOB` , `BLOB` , `CLOB` ](https://www.geeksforgeeks.org/hibernate-save-image-and-other-types-of-values-to-database/)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 1_simple_setup_spring_and_html

<img src="https://img.shields.io/badge/- 1. simple upload file setup spring and html  %20-blue" height=40px>

Let's see a very simple example for uploading file to Backend. </br>
I use the example from link of [file-upload-Spring-Boot-Ajax-example](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/file-upload-Spring-Boot-Ajax-example)

#### Backend Code

Let’s fine tune the file upload properties for our application using application.properties file.

```sql
#Whether to enable support of multipart uploads.default is true
#spring.servlet.multipart.enabled =true

#Threshold after which files are written to disk.default is 0B
spring.servlet.multipart.file-size-threshold = 3KB

#Max file size.Default is 1MB
spring.servlet.multipart.max-file-size= 2MB

#Max request size.Default is 10MB
spring.servlet.multipart.max-request-size= 20MB

#Whether to resolve the multipart request lazily at the time of file or parameter access.Default is false
spring.servlet.multipart.resolve-lazily=true
```

#### JAVA code

```java
package com.file.upload.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {
			file.transferTo(new File("C:\\Localdata\\" + fileName));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("File uploaded successfully.");

	}
}
```

#### FrontEnd code

Save the frontend code in `index.html` file at `static` forder

```js
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <input id="testUpload" type="file" name="fileupload" />
    <button id="upload-button" onclick="uploadFile()">Upload</button>

    <!-- Ajax JavaScript File Upload to Spring Boot Logic -->
    <script>
      async function uploadFile() {
        let formData = new FormData();
        console.log(formData);

        formData.append('file', testUpload.files[0]);

        let response = await fetch('/upload', {
          method: 'POST',
          body: formData,
        });

        if (response.status == 200) {
          alert('File successfully uploaded.');
        }
      }
    </script>
  </body>
</html>
```

### Test the code

Run the app , upload a simple txt file and see the the backend code stores the file in `localdata`.



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--- 

###### 2_daily_code_buffer_upload_download_files 

<img src="https://img.shields.io/badge/- 2. LOB, CLOB, BLOB %20-blue" height=40px>

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


###### 3_java_techie

<img src="https://img.shields.io/badge/- 3_java_techie_file_system  %20-blue" height=40px>


###### 3_1_java_techie_file_system

<img src="https://img.shields.io/badge/- 3_1_java_techie_file_system  %20-yellow" height=32px>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



###### 3_2_java_techie_data_base

<img src="https://img.shields.io/badge/- 3_2_java_techie_data_base  %20-yellow" height=32px>





[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)






----------------





###### 

<img src="https://img.shields.io/badge/-   %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 

<img src="https://img.shields.io/badge/-  %20- green" height=32px>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 

<img src="https://img.shields.io/badge/- 5_App_jsx  %20-blue" height=40px>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 6_components_folder

<img src="https://img.shields.io/badge/- 6_components_folder  %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 7_images_assets_folder

<img src="https://img.shields.io/badge/- 7_images_assets_folder  %20-blue" height=40px>




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


