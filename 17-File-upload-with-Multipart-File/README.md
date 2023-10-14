###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction](#Introduction)                          |
|  1  | [Simple setup with Spring boot + HTML](#1_simple_setup_spring_and_html)                          |
|  2  | [index.html](#2_index_html)                          |
|  3  | [index.css](#3_index_css)                          |
|  4  | [main.jsx](#4_main_jsx)                          |
|     | [4.1. x](#4_1_)                          |



--- 

###### Introduction

<img src="https://img.shields.io/badge/- Introduction  %20-blue" height=40px>

Links for Spring Boot File upload example with Multipart File

1. [Bezkoder file-upload Using @Controller](https://www.bezkoder.com/spring-boot-file-upload/)
2. [Multipart File Uploads using REST API](https://medium.com/@patelsajal2/how-to-create-a-spring-boot-rest-api-for-multipart-file-uploads-a-comprehensive-guide-b4d95ce3022b)
3. 

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 1_simple_setup_spring_and_html

<img src="https://img.shields.io/badge/- 1. vite_setup  %20-blue" height=40px>

Let's see a very simple example for uploading file to Backend. </br>
I use the example from link of [file-upload-Spring-Boot-Ajax-example](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/file-upload-Spring-Boot-Ajax-example)

#### Backend Code

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

###### 2_index_html

<img src="https://img.shields.io/badge/- x  %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 3_index_css

<img src="https://img.shields.io/badge/- x  %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 4_main_jsx

<img src="https://img.shields.io/badge/- 4_index_js  %20-blue" height=40px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_1_react_strict_mode_gotch

<img src="https://img.shields.io/badge/- index js ,React StrictMode gotcha%20- green" height=32px>




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


