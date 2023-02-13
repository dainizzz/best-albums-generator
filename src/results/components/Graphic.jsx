import canvasTxt from 'canvas-txt';
import { useEffect, useState, useRef } from 'react';


const GRAPHICS = {
    cyber: "/images/cyber.png",
    paper: "/images/paper.png",
    botanical: "/images/botanical.png"
}


const Graphic = ({displayName, finalAlbumsCleaned, graphicStyle}) => {
    const myCanvas = useRef();
    const [resultsText, setResultsText] = useState("");
    const finalAlbumsString = finalAlbumsCleaned.join("\n");

    const generateResultsText = (displayName) => {
        let resultsText = ""
        if (!displayName) {
            resultsText = `2022 Top Albums\n\n${finalAlbumsString}`
        } else {
            resultsText = `${displayName}'s\n2022 Top Albums\n\n${finalAlbumsString}`
        }
        return resultsText
    };

    useEffect(() =>{
        const newResultsText = generateResultsText(displayName);
        setResultsText(newResultsText);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    },[displayName]);
    
    useEffect(() => {
        const context = myCanvas.current.getContext("2d");
        canvasTxt.font = 'Arial';
        context.fillStyle = "white";
        canvasTxt.fontSize = 36;
        canvasTxt.lineHeight = 50;
        const image = new Image();
        image.src = GRAPHICS[graphicStyle];
        image.onload = () => {
            context.drawImage(image, 0, 0);
            // drawText(ctx, text, x, y, width, height)
            canvasTxt.drawText(context, resultsText, 50, -20, 700, 700)
        };
    }, [graphicStyle, displayName, resultsText] );

    const downloadImage = () => {
        var canvas = document.getElementById("myCanvas");
        var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
        var link = document.createElement('a');
        link.download = "2022-best-albums.png";
        link.href = image;
        link.click();
    }
// TODO: Move these buttons to side bar T___T
    return (
    <div className="graphic-container">
        <canvas ref={myCanvas} id="myCanvas" width="750" height="700"></canvas>
        {/* <button onClick={myCanvas}>Try it!</button> */}
        <button onClick={downloadImage}>Download!</button>
    </div>
)};

export default Graphic;