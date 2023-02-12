const TextBox = ({ finalAlbumsCleaned }) => {
  const finalAlbumsString = finalAlbumsCleaned.join(" / ");

  // TODO: Implement copy logic
  return (
    <div className="results-text">
      <textarea id="results" name="results" readOnly>
        {finalAlbumsString}
      </textarea>
      <button>Copy</button>
    </div>
  );
};

export default TextBox;
