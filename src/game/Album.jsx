import "./styling/album.css";
import { useFetchAlbumData } from "./hooks/useFetchAlbumData";

const Album = ({ albumId, userId, selectAlbum, currentMatch }) => {
  const {albumData: {imgLink, title, artist} = {}} = useFetchAlbumData(currentMatch, userId, albumId);

  return (
    <div className="album-container">
      <img src={imgLink} alt="album cover" />
      <h2>{title}</h2>
      <h3>{artist}</h3>
      <button onClick={() => selectAlbum(albumId)}>Select</button>
    </div>
  );
};

export default Album;
