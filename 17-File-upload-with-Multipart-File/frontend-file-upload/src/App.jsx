import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileName, setFileName] = useState(null);
  const [attachmentId, setAttachmentId] = useState(null);
  const [response, setResponse] = useState(null);

  const handleFileUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    const formData = new FormData();
    // this is the @RequestParam with Spring Controller
    formData.append('attachment', selectedFile);

    // to dislapy what are the key/value in formData
    // for (const data of formData.entries()) {
    //   console.log(data);
    // }

    upload(formData);
  };

  const upload = async (formData) => {
    const { data } = await axios.post(`http://localhost:8080/database/upload`, formData);
    // const { data } = await axios.post(`http://localhost:8080/fileSystem/upload`, formData);
    setFileName(data.fileName);
    setAttachmentId(data.id);
    console.log(data);
  };

  const handleDownload = () => {
    download();
  };

  const download = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/download/${attachmentId}`);
    // const { data } = await axios.get(`http://localhost:8080/fileSystem/download/${fileName}`);

    // The Uint8Array typed array represents an array of 8-bit unsigned integers

    // Option 1: getting Array as string from server
    // ---------------------------------------------

    // let binary = '';
    // let uint8Array = new Uint8Array(data);
    // Doesn't work with Int8Array , throws error of
    // DOMException: Failed to execute 'btoa' on 'Window': The string to be encoded contains characters outside of the Latin1 range.
    // let int8Array = new Int8Array(data);
    // console.log(uint8Array);
    // let len = uint8Array.byteLength;
    // for (var i = 0; i < len; i++) {
    //   binary += String.fromCharCode(uint8Array[i]);
    // }
    // const base64String = btoa(binary);

    // Option 2: getting getting Base64 as String from server (Postman shows , this is faster to download + file size 3x smaller)
    // ---------------------------------------------
    const base64String = data;
    setResponse(base64String);
  };

  return (
    <>
      <div style={{ padding: '2rem' }}>
        <h3>file upload</h3>
        <br />
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn" onChange={handleFileUpload} />
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleUpload}>
            Upload
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleDownload}>
            Download
          </button>
        </div>
        <br />
        <div>
          <img src={`data:image/png;base64, ${response}`} />
        </div>
        <h3>------------------------------------------</h3>
        <h5>More styling options for input-file </h5>
        <h5>The upload button ,is not configured for these options</h5>
        {/* option 1 for styling */}
        <div>
          <input type="file" className="btn input-file" onChange={handleFileUpload} />
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
