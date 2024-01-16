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

    /**
     * Option 1
     */
    let reader = new FileReader();
    const imageFileToBase64 = (reader.onloadend = function (readerEvent) {
      return readerEvent.target.result;
    });
    reader.readAsDataURL(event.target.files[0]);
    console.log(imageFileToBase64);

    /**
     * Option 2
     * With JavaScript Promise
     */
    // const getBase64 = (file) =>
    //   new Promise(function (resolve, reject) {
    //     let reader = new FileReader();
    //     reader.readAsDataURL(file);
    //     reader.onload = () => resolve(reader.result);
    //     reader.onerror = (error) => reject('Error: ', error);
    //   });

    // const convertToBase64 = async (file) => {
    //   try {
    //     const response = await getBase64(file);
    //     console.log(response);
    //   } catch (error) {
    //     console.log(error);
    //   }
    // };

    // convertToBase64(event.target.files[0]);
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
