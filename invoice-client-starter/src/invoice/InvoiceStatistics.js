import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

const InvoiceStatistics = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [statistic, setStatistic] = useState({
        currentYearSum: 0,
        allTimeSum: 0,
        invoicesCount: 0,
    });

    useEffect(() => {
        setIsLoading(true);
        apiGet("/api/invoices/statistics").then((data) => setStatistic(data));
        setIsLoading(false);
    }, []);

    return (
        <table className="table">
            <tbody>
                <tr>
                    <th>Celkový příjem za letošní rok:</th>
                    {isLoading ? (
                        <div className="progress">
                            <div className="spinner-border" role="status">
                            </div>
                        </div>) : (
                        <td>{statistic.currentYearSum}</td>
                    )}
                </tr>
                <tr>
                    <th>Celkový příjem:</th>
                    <td>{statistic.allTimeSum}</td>
                </tr>
                <tr>
                    <th>Celkový počet faktur:</th>
                    <td>{statistic.invoicesCount}</td>
                </tr>
            </tbody>
        </table>
    );
};

export default InvoiceStatistics;