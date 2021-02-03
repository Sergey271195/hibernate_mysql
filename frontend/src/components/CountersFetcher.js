import React, {useEffect, useState} from "react";

const CountersFetcher = (props) => {

    const [counters, setCounters] = useState([]);

    useEffect(() => {
        fetch("api/counters")
            .then(response => response.json())
                .then(data => {
                    setCounters(data);
                    props.setSelectedCounter(data[0]);
                })
    }, [])

    const onSelect = (e) => {
        props.setSelectedCounter(data[e.target.value]);
    }

    return (
        <select onChange = {e => onSelect(e)}>
            {counters.map(({id, name}, index) => {
                return(
                    <option key = {id} value = {index}>{name}</option>
                )
            })}
        </select>
    
    )
}


export default CountersFetcher