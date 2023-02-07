import AlbumContainer from "./components/AlbumContainer";

import './styling/game.css'

const Game = () => {
    return(
        <>
        {/* Instructions will be a hide/show element that pop up after you click an icon */}
            <h3>Click on the button below the album you prefer.</h3>
            <p>Progress bar will go here</p>
            <div className="game-container">
                <AlbumContainer/>
                <AlbumContainer/>
            </div>
        </>
    );
};

export default Game;