import DownloadButton from "./DownloadButton";

const TextBox = ({ finalAlbums }) => {
  const finalAlbumsString = finalAlbums.join("\n");
  // TODO: Implement copy logic
  return (
    <div className="results-text">
      <DownloadButton/>
      <textarea id="results" name="results" value={finalAlbumsString} readOnly cols="50" rows="10">
      </textarea>
      <button>Copy</button>
    </div>
  );
};

export default TextBox;