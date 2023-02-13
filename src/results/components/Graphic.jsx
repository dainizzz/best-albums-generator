import canvasTxt from 'canvas-txt';

const GRAPHICS = {
    cyber: "/images/cyber.png",
    paper: "/images/paper.png",
    botanical: "/images/botanical.png"
}


const Graphic = ({displayName, finalAlbumsCleaned, graphicStyle}) => {
    const finalAlbumsString = finalAlbumsCleaned.join("\n");
    const resultsText = `${displayName}'s\n2022 Top Albums\n\n${finalAlbumsString}`
    
    // TODO: Move myCanvas into useEffect

    const image = new Image();
    console.log(GRAPHICS[graphicStyle]);
    image.src = GRAPHICS[graphicStyle];

    const myCanvas = () => {
        const c = document.getElementById("myCanvas");
        const ctx = c.getContext("2d");
        canvasTxt.font = 'Arial';
        ctx.fillStyle = "white";
        canvasTxt.fontSize = 36;
        canvasTxt.lineHeight = 50;
        
        ctx.drawImage(image,0,0);
        // drawText(ctx, text, x, y, width, height)
        canvasTxt.drawText(ctx, resultsText, 50, -20, 700, 700)

    };


    const downloadImage = () => {
        var canvas = document.getElementById("myCanvas");
        var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
        var link = document.createElement('a');
        link.download = "my-image.png";
        link.href = image;
        link.click();
    }
// TODO: Move these buttons to side bar T___T
    return (
    <div className="graphic-container">
        <canvas id="myCanvas" width="750" height="700"></canvas>
        <button onClick={myCanvas}>Try it!</button>
        <button onClick={downloadImage}>Download!</button>
    </div>
)};

export default Graphic;