import React, {useEffect} from 'react'

const DIMENSION_MAP = {
    traff: "Источники трафика (сводка)",
    phrase: "Поисковая фраза",
    adv: "Рекламная система",
    soc: "Социальные сети",
    search: "Поисковая система",
    ref: "Внешний реферер",

}

const DimensionSelector = (props) => {

    useEffect(() => {
        props.setDimension("traff")
    }, [])

    const onSelect = (e) => {
        console.log(e.target.value)
        props.setDimension(e.target.value);
    }

    return (
        <div>
            <select onChange = {e => onSelect(e)}>
            {Object.keys(DIMENSION_MAP).map(key => {
                return (<option key = {key} value = {key}>{DIMENSION_MAP[key]}</option>)
            })}
            </select>
        </div>
    )
}

export default DimensionSelector
