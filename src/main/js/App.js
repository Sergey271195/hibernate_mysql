import React from "react";
import ReactDOM from "react-dom";
import ReachesForm from "../../../frontend/src/components/ReachesForm";


const App = () => {
    return(
        <div>
            <ReachesForm />
        </div>
    );
}

export default App;

const app = document.getElementById("react")
ReactDOM.render(<App />, app);