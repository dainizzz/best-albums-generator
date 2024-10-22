import Album from "./Album";
import { useCurrentMatchQuery } from "./hooks/useCurrentMatchQuery";
import "./styling/game.css";

const constructAlbumProps = (currentMatch, userId, selectAlbum) => {
  return [currentMatch.album1, currentMatch.album2].map((albumId) => ({
    albumId,
    userId,
    selectAlbum,
    currentMatch
  }))
}  
const Game = ({
  albumsList,
  currentUser,
  currentRound,
  currentMatches,
  setAlbumsList,
  setCurrentRound,
  setCurrentMatches,
}) => {
  const {id} = currentUser;
  const {currentMatch, selectAlbum} = useCurrentMatchQuery(
    id,
    currentRound,
    currentMatches,
    setCurrentRound,
    setCurrentMatches,
    albumsList,
    setAlbumsList);

  const albumData = constructAlbumProps(currentMatch, id, selectAlbum)

  return (
    <>
      <h3 className="instructions">
        Click on the button below the album you prefer
      </h3>
      <div className="game-container">
        {
          albumData.map((props) => <Album {...props}/> )
        }
      </div>
    </>
  );
};

export default Game;
