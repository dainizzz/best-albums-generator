import Album from "./Album";
import "../styling/AlbumContainer.css";

const AlbumContainer = ({ albumId, userId, selectAlbum, currentMatch }) => {
  return (
    <div className="album-container">
      <Album
        albumId={albumId}
        userId={userId}
        selectAlbum={selectAlbum}
        currentMatch={currentMatch}
      />
    </div>
  );
};
export default AlbumContainer;
