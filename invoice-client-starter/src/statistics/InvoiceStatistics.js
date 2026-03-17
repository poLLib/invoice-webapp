import { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { apiGet } from "../utils/api";

/**
 * InvoiceStatistics component fetches and displays invoice statistics,
 * including current year's revenue, all-time revenue, and the total number of invoices.
 *
 * @returns {JSX.Element} A table displaying invoice statistics or a loading spinner.
 */
export function InvoiceStatistics() {
    const { t } = useTranslation();
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
                        <th >{t('statistics.yearlyRevenue')}</th>

                        <td className="fw-bold">{statistics.currentYearSum ? statistics.currentYearSum : 0} {t('common.currency')}</td>
                    </tr>
                    <tr>
                        <th>{t('statistics.totalRevenue')}</th>
                        <td className="fw-bold">{statistics.allTimeSum ? statistics.allTimeSum : 0} {t('common.currency')}</td>
                    </tr>
                    <tr>
                        <th >{t('statistics.totalInvoices')}</th>
                        <td className="fw-bold">{statistics.invoicesCount ? statistics.invoicesCount : 0}</td>
                    </tr>
                </tbody>
            )}

        </table>
    );
}
