import { useState } from 'react';
import axios from 'axios';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileUpload = (event) => {
    // since the input type is `file`
    // thus, the event.traget.files[] is : array
    console.log(event);
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
    const response = await axios.post(`http://localhost:8080/database/upload`, formData);
    console.log(response);
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
