import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    const formData = new FormData();
    formData.append('attachment', selectedFile);

    // to dislapy what are the key/value in formData
    for (const data of formData.entries()) {
      console.log(data);
    }
    upload(formData);
  };

  const upload = async (formData) => {
    // const response = await axios.post(`http://localhost:8080/database/upload`, formData);
    const response = await axios.post(`http://localhost:8080/fileSystem/upload`, formData);
    console.log(response);
  };

  const handleDownload = () => {
    download();
  };

  const download = async () => {
    // const response = await axios.get(`http://localhost:8080/database/upload`);
    const response = await axios.get(`http://localhost:8080/fileSystem/download/{fileName}`);
    console.log(response);
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
