import AlbumContainer from "./components/AlbumContainer";

import './styling/game.css'

const Game = () => {
    return(
        <>
        {/* Instructions will be a hide/show element that pop up after you click an icon */}
            <h3 className="instructions">Click on the button below the album you prefer.</h3>
            <div className="game-container">
                <AlbumContainer/>
                <AlbumContainer/>
            </div>
        </>
    );
};

export default Game;