import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";
import '../styles.css'

export function PersonStatistics() {
    const [isLoading, setIsLoading] = useState();
    const [statistics, setStatistics] = useState([]);
    const [persons, setPersons] = useState([]);

    useEffect(() => {
        async function fetchStats() {
            setStatistics(await apiGet("/api/persons/statistics"));
            setPersons(await apiGet("/api/persons"));
            setIsLoading(false);
        }
        fetchStats();
    }, []);


    const midIndex = Math.ceil(statistics.length / 2);
    const firstHalf = statistics.slice(0, midIndex);
    const secondHalf = statistics.slice(midIndex);
    console.log(secondHalf)
    console.log(persons)
    const renderStatistics = (stats) => (
        <tbody>
            {stats.map((stat) => {
                const person = persons.find(p => p._id === stat.personId);
                return person ? (

                    <React.Fragment key={stat.personId}>
                        <tr>
                            <th>Jméno společnosti:</th>
                            <td className="ps-3 fs-5">{stat.personName}</td>
                        </tr>
                        <tr>
                            <th>IČO:</th>
                            <td className="ps-3">{person.identificationNumber}</td>
                        </tr>
                        <tr>
                            <th>Celkový příjem:</th>
                            <td className="ps-3  fs-5 text-primary">{stat.revenue} Kč</td>
                        </tr>
                        <br />
                        <br />
                    </React.Fragment>

                ) : null;
            })}
        </tbody >
    );

    return (
        <div className="row">
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-border" role="status"></div>
                </div>
            ) : (
                <>
                    <div className="col-md-6">
                        <table className="table table-bordered">
                            {renderStatistics(firstHalf)}
                        </table>
                    </div>
                    <div className="col-md-6">
                        <table className="table table-bordered">
                            {renderStatistics(secondHalf)}
                        </table>
                    </div>
                </>
            )}
        </div>
    );
};
