import {useState, useEffect} from 'react';
import '../styling/customNameForm.css';

const CustomNameForm = ({setDisplayName, username, hideName, setHideName}) => {
  const [customNameBool, setCustomNameBool] = useState(false)

  const handleCustomNameBool = () => {
    setCustomNameBool(customNameBool => !customNameBool);
  }

  const handleHideName = () => {;
    setHideName(!hideName);
  } 

  const handleNameChange = (event) => {
    console.log(event.target.value);
    setDisplayName(event.target.value);
  };

  useEffect(() => {
    if (!customNameBool){
      setDisplayName(username);
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[customNameBool, hideName])

  useEffect(() => {
    if (hideName){
      setDisplayName("");
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[hideName, customNameBool, ])

  return (
  <form>
    <label htmlFor="show-name" className="custom-name-container">Hide Username
    <input type="checkbox" id="show-name" name="show-name" checked={hideName} onChange={handleHideName} />
    <span className="checkmark"></span>
    </label>
    <input type="checkbox" id="custom-name-bool" name="custom-name-bool" checked={customNameBool} onChange={handleCustomNameBool} />
    <label htmlFor="custom-name-bool" className="custom-name-container">Use Custom Name
    </label>
    {customNameBool? (<input type="text" id="custom-name-text" name="custom-name-text" className="custom-name-text" onChange={handleNameChange}/> ) : null}
  </form>
)};
export default CustomNameForm;
