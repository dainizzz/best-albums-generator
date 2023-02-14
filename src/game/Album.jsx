import Lottie from "react-lottie";
import animationData from "../lotties/bloom.json"
import "./styling/album.css";
import { useFetchAlbumData } from "./hooks/useFetchAlbumData";

const Album = ({ albumId, userId, selectAlbum, currentMatch }) => {
  const {albumData: {imgLink, title, artist} = {}} = useFetchAlbumData(currentMatch, userId, albumId);

    // Animation Config
    const defaultOptions = {
      loop: true,
      autoplay: true,
      animationData: animationData,
      rendererSettings: {
        preserveAspectRatio: "xMidYMid slice",
      },
    };

  return (
    <>
      {!imgLink ? (
      <Lottie options={defaultOptions} height={400} width={400} />
    ) : null}
      {imgLink ? (
      <div className="album-container">
        <img src={imgLink} alt="album cover" />
        <h2>{title}</h2>
        <h3>{artist}</h3>
        <button className="album-button" onClick={() => selectAlbum(albumId)}>Select</button>
      </div>) : null}
    </>

  );
};

export default Album;
