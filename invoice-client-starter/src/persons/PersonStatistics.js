import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

export function PersonStatistics() {
    const [isLoading, setIsLoading] = useState();
    const [statistics, setStatistics] = useState([]);

    useEffect(() => {
        async function fetchStats() {
            setStatistics(await apiGet("/api/persons/statistics"));
            setIsLoading(false);
        }
        fetchStats();
    }, []);
    console.log(statistics.personName)
    return (
        <table>
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-border" role="status">
                    </div>
                </div>) : (
                <tbody>
                    {statistics.map((person) => (
                        <div>
                            <tr>
                                <th>Jméno společnosti:</th>
                                <td>{person.personName}</td>
                            </tr>
                            <tr>
                                <th>Celkový příjem:</th>
                                <td>{person.revenue}</td>
                            </tr>
                            <br />
                        </div>
                    ))}
                </tbody>
            )}

        </table>
    );
};