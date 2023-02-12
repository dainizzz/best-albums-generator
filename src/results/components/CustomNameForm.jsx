const CustomNameForm = ({setDisplayName}) => {
  // pass down show name function (state) and update when this is checked
  // pass down custom name state thingy
  <form>
    <input type="checkbox" id="show-name">Hide Username</input>
    <input type="checkbox">Use Custom Name</input>
    {/* Textbox for custom name, render if custom name is checked */}
  </form>
};
export default CustomNameForm;
