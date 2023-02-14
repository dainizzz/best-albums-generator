
import "../styling/graphicStyleDropdown.css";

const GraphicStyleDropdown = ({setGraphicStyle}) => {
  const handleStyleChange = (event) => {
    console.log(event);
    setGraphicStyle(event);
  };

  return (
    <div className="style-select-container">
      <label className="style-select" htmlFor="select-style">
        Select Graphic Style
      </label>
      <select
        name="select-style"
        onChange={(e) => handleStyleChange(e.target.value)}
      >
        <option value="blobs">Blobs</option>
        <option value="bubbles">Bubbles</option>
        <option value="bookish">Bookish</option>
      </select>
    </div>
  );
};

export default GraphicStyleDropdown;
