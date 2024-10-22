const TextBox = ({ finalAlbums }) => {
  const finalAlbumsString = finalAlbums.join("\n");
  const copyText = () => {
    navigator.clipboard.writeText(finalAlbumsString);
  };
  return (
    <div className="results-text">
      <textarea id="results-text" name="results-text" value={finalAlbumsString} readOnly cols="50" rows="10">
      </textarea>
      <button className="copy-button" onClick={copyText}>Copy Text</button>
    </div>
  );
};

export default TextBox;