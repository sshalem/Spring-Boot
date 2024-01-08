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
    // const { data } = await axios.post(`http://localhost:8080/fileSystem/upload`, formData);

    setFileName(data.fileName);
    setAttachmentId(data.id);
    // This url, when I click it , It triggers `download` from server
    setDownloadUrl(data.downloadURL);
    setFileType(data.fileType);
    console.log(data);
  };

  /**
   * [2] - get image (as Base64) from server (load image) and display it on html page
   * (Postman shows , this is faster to download + file size 3x smaller)
   */
  const handleLoadImageFromServer = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/loadAttachment/${attachmentId}`);
    // const { data } = await axios.get(`http://localhost:8080/fileSystem/download/${fileName}`);
    setImage(data);
  };

  /**
   * [3] - downloads an image from server, and save it on local comuter
   */
  const handleDownload = async () => {
    const { data } = await axios.get(`http://localhost:8080/database/download/${attachmentId}`);
    // const { data } = await axios.get(`http://localhost:8080/fileSystem/download/${attachmentId}`);
    /**
     * the data I get from server (of the file) is a String as a Base64 .
     * So need to conver the Base64 String back to an image
     */
    console.log(typeof data);
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
            load (get) image from Server
          </button>
        </div>
        <br />
        <div>
          <button className="btn upload-download" onClick={handleDownload}>
            Download image link
          </button>
          <br />
          <br />
          <p>
            <span>Image link , when clicked ,it will download image from server: </span>
            <a href={downloadUrl}>{downloadUrl}</a>
          </p>
        </div>
        <br />
        <div>{image ? <img src={`data:${fileType};base64, ${image}`} /> : null}</div>

        {/* ********************************************************** */}
        {/* ********************************************************** */}
        {/* ********************************************************** */}

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
