import React from 'react'

const TableDataRepresentation = (props) => {
    return (
        <div>
            {props.data.length != 0 && 
                <table border = "1">
                    <thead>
                        <tr>
                            <th>Достижение цели</th>
                            <th>Источник</th>
                            <th>Название цели</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.data.map(({count, sourceName, goalName}) => {
                            return (
                            <tr key = {`${sourceName}-${goalName}`}>
                                <td>{count}</td>
                                <td>{sourceName}</td>
                                <td>{goalName}</td>
                            </tr>)
                        })}
                    </tbody>
                </table>
            }
        </div>
    )
}

export default TableDataRepresentation
