import Graphic from "./components/Graphic";
import TextBox from "./components/TextBox";
import './styling/results.css'

const Results = () => {
    return(
        <div className="results-container">
            <Graphic />
            <TextBox />
            <div className="image-modifiers">
                <p>Custom name form and graphic style dropdown go here</p>
            </div>
        </div>
        
    )
}

export default Results;