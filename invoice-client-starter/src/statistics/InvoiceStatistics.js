import { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

/**
 * InvoiceStatistics component fetches and displays invoice statistics,
 * including current year's revenue, all-time revenue, and the total number of invoices.
 * 
 * @returns {JSX.Element} A table displaying invoice statistics or a loading spinner.
 */
export function InvoiceStatistics() {
    const [isLoading, setIsLoading] = useState(true);
    const [statistics, setStatistics] = useState({
        currentYearSum: "",
        allTimeSum: "",
        invoicesCount: "",
    });

    useEffect(() => {
        async function fetchStats() {
            setStatistics(await apiGet("/api/invoices/statistics"));
            setIsLoading(false);
        }
        fetchStats();
    }, []);

    return (
        <table className="table table-success table-striped">
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-border" role="status">
                    </div>
                </div>) : (
                <tbody>
                    <tr>
                        <th >Celkový příjem za letošní rok:</th>

                        <td className="fw-bold">{statistics.currentYearSum ? statistics.currentYearSum : 0} Kč</td>
                    </tr>
                    <tr>
                        <th>Celkový příjem:</th>
                        <td className="fw-bold">{statistics.allTimeSum ? statistics.allTimeSum : 0} Kč</td>
                    </tr>
                    <tr>
                        <th >Celkový počet faktur:</th>
                        <td className="fw-bold">{statistics.invoicesCount ? statistics.invoicesCount : 0}</td>
                    </tr>
                </tbody>
            )}

        </table>
    );
}