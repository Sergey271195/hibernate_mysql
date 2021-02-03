import React, {useState} from 'react'
import CountersFetcher from './CountersFetcher';
import DateSelector from './DateSelector';
import DimensionSelector from './DimensionSelector';
import FetchModeSelector from './FetchModeSelector';
import TableDataRepresentation from './TableDataRepresentation';

const TABLE_TITLE_MAP = {
    "goals": "Цели",
    "views": "Визиты",
    "prices": "Доход",
    "purchases": "Покупки" 
}

const GoalReachesForm = () => {

    const [fetchedAggData, setFetchedAggData] = useState([])
    const [datePeriod, setDatePeriod] = useState({startDate: "", endDate: ""})
    const [selectedCounter, setSelectedCounter] = useState()
    const [dimension, setDimension] = useState()
    const [fetchMode, setFetchMode] = useState()
    const [tableTitle, setTableTitle] = useState()
    

    const fetchAggregatedData = (e) => {
        e.preventDefault();
        const requestBody = prepareRequestBody();
        console.log(requestBody);
        if (datePeriod.startDate != "" && datePeriod.endDate != "") {
            const apiUrl = fetchMode == null ? "goals" : fetchMode
            fetch(`/api/${apiUrl}/agg/${dimension}`, requestBody)
                .then(response => response.json())
                    .then(data => {
                        setFetchedAggData(data);
                        setTableTitle(selectedCounter.name + " - " + TABLE_TITLE_MAP[apiUrl])
                    })
        }
    };

    const prepareRequestBody = () => {
        return {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify({
                id: selectedCounter.id, 
                startDate: datePeriod.startDate, 
                endDate: datePeriod.endDate
            })
        };
    }

    return (
        <div>
            <form onSubmit = {(e) => fetchAggregatedData(e)}>
                <CountersFetcher setSelectedCounter = {setSelectedCounter}/>
                <FetchModeSelector selectedCounter = {selectedCounter} setFetchMode = {setFetchMode}/>
                <DimensionSelector setDimension = {setDimension}/>
                <DateSelector datePeriod = {datePeriod} setDatePeriod = {setDatePeriod}/>
                <div>
                    <button type = "submit">Submit button</button>  
                </div>
            </form>

            <TableDataRepresentation data = {fetchedAggData} tableTitle = {tableTitle}/>
        </div>
    )
}

export default GoalReachesForm
