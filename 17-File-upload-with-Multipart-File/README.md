###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction - links](#Introduction)                          |
|  1  | [`LOB` , `BLOB` , `CLOB`](#1_lob_blob_clob)                          |
|  2  | [upload/download](#2_upload_download)                          |
|     | [2.1. upload/download using File System](#2_1_upload_download_using_file_system)                          |
|     | [2.2. upload/download using Data Base](#2_2_upload_download_using_data_base)                          |
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


###### 2_1_upload_download_using_file_system

<img src="https://img.shields.io/badge/- 2_1_upload_download_using_file_system  %20-yellow" height=32px>

In this Example I will show how to store the file in the file System




#### [How to convert byte[] array to String in Java ](#-)

In Java, we can use `new String(bytes, StandardCharsets.UTF_8)` to convert a `byte[]` to a `String`.

```java
  // string to byte[]
  byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);

  // byte[] to string
  String s = new String(bytes, StandardCharsets.UTF_8);
```

#### [1. byte[] in text and binary data](#-)

For text or character data, we use `new String(bytes, StandardCharsets.UTF_8)` to convert the `byte[]` to a `String` directly. </br>
However, for cases that `byte[]` is holding the binary data like the `image` or other `non-text` data, the best practice is to convert the `byte[]` into a `Base64` encoded string. </br>
This Base64 encode decode string is still widely use in
1. email attachment
2. embed image files inside HTML or CSS

Note:
- For text data byte[], try `new String(bytes, StandardCharsets.UTF_8)`.
- For binary data byte[] (images), try `Base64` encoding.

```java
  // convert file to byte[]
  byte[] bytes = Files.readAllBytes(Paths.get("/path/image.png"));

  // Java 8 - Base64 class, finally.

  // encode, convert byte[] to base64 encoded string
  String s = Base64.getEncoder().encodeToString(bytes);

  System.out.println(s);

  // decode, convert base64 encoded string back to byte[]
  byte[] decode = Base64.getDecoder().decode(s);
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



###### 2_2_upload_download_using_data_base

<img src="https://img.shields.io/badge/- 2_2_upload_download_using_data_base  %20-yellow" height=32px>





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


