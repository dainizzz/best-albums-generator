import canvasTxt from 'canvas-txt';
import { useEffect, useState, useRef } from 'react';

const GRAPHICS = {
    cyber: "/images/cyber.png",
    paper: "/images/paper.png",
    botanical: "/images/botanical.png"
}

const Graphic = ({displayName, finalAlbums, graphicStyle}) => {
    const finalAlbumsString = finalAlbums.join("\n");
    const myCanvas = useRef();
    const [resultsText, setResultsText] = useState("");

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
    },[displayName, finalAlbumsString]);
    
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
            canvasTxt.drawText(context, resultsText, 20, 0, 700, 710)
        };
    }, [graphicStyle, displayName, resultsText, finalAlbumsString] );

    return (
    <div className="graphic-container">
        <canvas ref={myCanvas} id="myCanvas" width="750" height="750"></canvas>
    </div>
)};

export default Graphic;