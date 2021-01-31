import React, {useState} from 'react'
import CountersFetcher from './CountersFetcher';
import DimensionSelector from './DimensionSelector';
import TableDataRepresentation from './TableDataRepresentation';

const GoalReachesForm = () => {

    const [fetchedAggData, setFetchedAggData] = useState([])
    const [datePeriod, setDatePeriod] = useState({startDate: "", endDate: ""})
    const [selectedCounter, setSelectedCounter] = useState()
    const [dimension, setDimension] = useState()
    

    const fetchAggregatedData = (e) => {
        e.preventDefault();
        const requestBody = prepareRequestBody();
        if (datePeriod.startDate != "" && datePeriod.endDate != "") {
            fetch(`/api/agg/${dimension}`, requestBody)
                .then(response => response.json())
                    .then(data => setFetchedAggData(data))
        }
    };

    const prepareRequestBody = () => {
        return {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify({
                id: selectedCounter, 
                startDate: datePeriod.startDate, 
                endDate: datePeriod.endDate
            })
        };
    }

    const onDateChange = (e) => {
        setDatePeriod({
            ...datePeriod,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div>
            <form onSubmit = {(e) => fetchAggregatedData(e)}>
                <div>
                    <CountersFetcher setSelectedCounter = {setSelectedCounter}/>
                    <DimensionSelector setDimension = {setDimension}/>
                </div>
                <div>
                    <input type = "date" name = "startDate" onChange = {e => onDateChange(e)}></input>
                </div>
                <div>
                    <input type = "date" name = "endDate" onChange = {e => onDateChange(e)}></input>
                </div>
                <div>
                    <button type = "submit">Submit button</button>
                </div>     
            </form>

            <TableDataRepresentation data = {fetchedAggData} />
        </div>
    )
}

export default GoalReachesForm
