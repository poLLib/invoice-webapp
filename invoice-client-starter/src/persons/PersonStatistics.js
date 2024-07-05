import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

const PersonStatistics = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [statistics, setStatistics] = useState([]);

    useEffect(() => {
        setIsLoading(true);
        apiGet("/api/persons/statistics").then((data) => setStatistics(data));
        setIsLoading(false);
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
                            <br/>
                        </div>
                    ))}
                </tbody>
            )}

        </table>
    );
};

export default PersonStatistics;