import React from "react";
import ReactDOM from "react-dom";
import GoalReachesForm from "../../../frontend/src/components/GoalReachesForm";


const App = () => {
    return(
        <div>
            <GoalReachesForm />
        </div>
    );
}

export default App;

const app = document.getElementById("react")
ReactDOM.render(<App />, app);