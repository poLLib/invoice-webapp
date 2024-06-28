import React, { useState, useEffect } from "react";
import { apiGet } from "../utils/api";

const InvoiceStatistics = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [statistic, setStatistic] = useState({
        currentYearSum: "",
        allTimeSum: "",
        invoicesCount: "",
    });

    useEffect(() => {
        setIsLoading(true);
        apiGet("/api/invoices/statistics").then((data) => setStatistic(data));
        setIsLoading(false);
    }, []);

    return (
        <table className="table">
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-border" role="status">
                    </div>
                </div>) : (
                <tbody>
                    <tr>
                        <th>Celkový příjem za letošní rok:</th>

                        <td>{statistic.currentYearSum}</td>
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
            )}

        </table>
    );
};

export default InvoiceStatistics;