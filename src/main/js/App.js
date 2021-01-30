import React from "react";
import ReactDOM from "react-dom";

const App = () => {
    return(
        <div>
            <h1>React Inside Spring</h1>
        </div>
    );
}

export default App;

const app = document.getElementById("react")
ReactDOM.render(<App />, app);