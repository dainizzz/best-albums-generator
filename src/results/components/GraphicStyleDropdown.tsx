import { useState } from "react";

import '../styling/GraphicStyleDropdown.css'

const GraphicStyleDropdown = () => {
    const [style, setStyle] = useState({
        style: "default",
    })
    const handleStyleChange = (event: any) => {
        setStyle({
            style: event.target.value
        });
    }

    return (
        <div className="style-select-container">
            <label className="style-select" htmlFor="select-style">Select Graphic Style</label>
            <select name="select-style" onChange={(e) => handleStyleChange(e.target.value)}>
                <option value="scrapbook">Scrapbook</option>
                <option value="botanical">Botanical</option>
                <option value="sky">Sky</option>
            </select>
        </div>
    );
};

export default GraphicStyleDropdown;