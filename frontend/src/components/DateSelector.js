import React from 'react'

const DateSelector = (props) => {

    const onDateChange = (e) => {
        props.setDatePeriod({
            ...props.datePeriod,
            [e.target.name]: e.target.value
        })
    }

    return (
        <>
            <div>
                <input type = "date" name = "startDate" onChange = {e => onDateChange(e)}></input>
            </div>
            <div>
                <input type = "date" name = "endDate" onChange = {e => onDateChange(e)}></input>
            </div>
        </>
    )
}

export default DateSelector
