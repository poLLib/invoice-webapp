import { useState, useEffect } from "react";
import { apiGet } from "../utils/api";
import { Link } from "react-router-dom";
import '../styles.css'

/**
 * PersonStatistics component fetches and displays statistical data 
 * for persons, including their total revenue and names.
 * 
 * @returns {JSX.Element} A div containing a loading spinner or a list of person statistics.
 */
export function PersonStatistics() {
    const [isLoading, setIsLoading] = useState(true);
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

    return (
        <div className="row">
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-border" role="status"></div>
                </div>
            ) : (
                <div className="col-12">
                    <div className="grid-container">
                        {statistics.map((stat) => (
                            <div key={stat.personId} className="person-stats">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th>Jméno společnosti:</th>
                                            <td className="ps-3 fs-5 align-right">
                                                <Link to={`/persons/show/${stat.personId}`}>{stat.personName}</Link>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Celkový příjem:</th>
                                            <td className="ps-3 fs-5 text-success align-right">{stat.revenue ? stat.revenue : 0} Kč</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}