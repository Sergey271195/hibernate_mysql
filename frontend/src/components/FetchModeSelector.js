import React, {useEffect} from 'react'

const FetchModeSelector = (props) => {

    useEffect(() => {
        if (props.selectedCounter != undefined) {
            console.log(props.selectedCounter);
            console.log(props.selectedCounter.commercial)
        }
    }, [props.selectedCounter])

    const onSelect = (e) => {
        props.setFetchMode(e.target.value)
    }

    return (
        <div>
            {props.selectedCounter && props.selectedCounter.id && 
                <select onChange = {e => onSelect(e)}>
                    <option value = "goals">Цели</option>
                    <option value = "views">Визиты</option>
                    {props.selectedCounter.commercial && 
                    <>
                        <option value = "prices">Доход</option>
                        <option value = "purchases">Покупки</option>
                    </>}
                </select>
            }
        </div>
    )
}

export default FetchModeSelector
