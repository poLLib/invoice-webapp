import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

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

                        <td className="fw-bold">{statistics.currentYearSum}</td>
                    </tr>
                    <tr>
                        <th>Celkový příjem:</th>
                        <td className="fw-bold">{statistics.allTimeSum}</td>
                    </tr>
                    <tr>
                        <th >Celkový počet faktur:</th>
                        <td className="fw-bold">{statistics.invoicesCount}</td>
                    </tr>
                </tbody>
            )}

        </table>
    );
};