import DownloadButton from "./DownloadButton";

const TextBox = ({ finalAlbums }) => {
  const finalAlbumsString = finalAlbums.join("\n");
  const copyText = () => {
    navigator.clipboard.writeText(finalAlbumsString);
    // document.execCommand('copy' true, text);
  };
  return (
    <div className="results-text">
      <DownloadButton/>
      <textarea id="results-text" name="results-text" value={finalAlbumsString} readOnly cols="50" rows="10">
      </textarea>
      <button className="copy-button" onClick={copyText}>Copy Text</button>
    </div>
  );
};

export default TextBox;