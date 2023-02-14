import canvasTxt from 'canvas-txt';
import { useEffect, useState, useRef } from 'react';

const GRAPHICS = {
    blobs: "/images/blobs.png",
    bubbles: "/images/bubbles.png",
    bookish: "/images/bookish.png"
}

const Graphic = ({displayName, finalAlbums, graphicStyle, hideName}) => {
    const finalAlbumsString = finalAlbums.join("\n");
    const myCanvas = useRef();
    const [resultsText, setResultsText] = useState("");

    const generateResultsText = (displayName) => {
        let resultsText = ""
        if (!displayName) {
            resultsText = `\n\n\n${finalAlbumsString}`
        } else {
            resultsText = `${displayName}'s\n\n\n${finalAlbumsString}`
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
        if (graphicStyle === "blobs" ){
            canvasTxt.font = "Space Mono";
            context.fillStyle = "#f9f3e5";
            // context.fillStyle = "#9585f8";
            canvasTxt.fontSize = 26;
            canvasTxt.lineHeight = 44;
            canvasTxt.fontWeight = "bold";
            canvasTxt.fontStyle = "italic"
        } else if (graphicStyle === "bubbles") {
            canvasTxt.font = 'Playfair Display';
            context.fillStyle = "white";
            canvasTxt.fontSize = 24;
            canvasTxt.fontStyle = "italic";
        } else {
            canvasTxt.font = 'Special Elite';
            context.fillStyle = "black";
            canvasTxt.fontSize = 22;
            canvasTxt.lineHeight = 35;
            canvasTxt.fontStyle = "";
        }
        const image = new Image();
        image.src = GRAPHICS[graphicStyle];
        image.onload = () => {
            context.drawImage(image, 0, 0);
            // drawText(ctx, text, x, y, width, height)
            if (graphicStyle === "blobs") {
                canvasTxt.drawText(context, resultsText, 20, 10, 700, 710);
            } else if (graphicStyle === "bubbles") {
                canvasTxt.drawText(context, resultsText, 20, 15, 700, 710);
            } else {
                canvasTxt.drawText(context, resultsText, 20, -10, 700, 710);
            }
            
        };
    }, [graphicStyle, displayName, resultsText, finalAlbumsString, hideName] );

    return (
    <div className="graphic-container">
        <canvas ref={myCanvas} id="myCanvas" width="750" height="750"></canvas>
    </div>
)};

export default Graphic;