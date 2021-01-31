import React, {useEffect, useState} from "react";

const CountersFetcher = (props) => {

    const [counters, setCounters] = useState([]);

    useEffect(() => {
        fetch("api/counters")
            .then(response => response.json())
                .then(data => {
                    setCounters(data);
                    props.setSelectedCounter(data[0].id);
                })
    }, [])

    const onSelect = (e) => {
        props.setSelectedCounter(e.target.value);
    }

    return (
        <select onChange = {e => onSelect(e)}>
            {counters.map(({id, name}) => {
                return(
                    <option key = {name} value = {id}>{name}</option>
                )
            })}
        </select>
    
    )
}


export default CountersFetcher