import GraphicText from "./GraphicText";

const Graphic = ({displayName, finalAlbums}) => (
    <div className="graphic-container">
        <h4>{displayName}'s</h4>
        <h3> 2022 Top Albums</h3>
        <ol>
            {finalAlbums.map((album) => (<GraphicText album={album}/>))}
        </ol>
    </div>
);

export default Graphic;