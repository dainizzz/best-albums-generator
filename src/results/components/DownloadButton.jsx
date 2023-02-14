const DownloadButton = () => {
    const downloadImage = () => {
        var canvas = document.getElementById("myCanvas");
        var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
        var link = document.createElement('a');
        link.download = "2022-best-albums.png";
        link.href = image;
        link.click();
    }
    return (<button className="download-button" onClick={downloadImage}>Download Graphic</button>)
};
export default DownloadButton;