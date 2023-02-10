import NameForm from "../ui/components/NameForm";
import Graphic from "./components/Graphic";
import GraphicStyleDropdown from "./components/GraphicStyleDropdown";
import TextBox from "./components/TextBox";
import './styling/results.css'


const displayContent = {
    label: 'Use custom name',
}

const Results = () => {
    return(
        <div className="results-container">
            <Graphic />
            <TextBox />
            <div className="image-modifiers">
                <GraphicStyleDropdown />
                <NameForm label={displayContent.label}/>
            </div>
        </div>
        
    )
}

export default Results;