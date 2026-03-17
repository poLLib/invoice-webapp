import { useTranslation } from "react-i18next";
import { PersonStatistics } from "./PersonStatistics";
import { InvoiceStatistics } from "./InvoiceStatistics";

/**
 * StatisticsTable component that renders two sections of statistics:
 * - Invoice Statistics
 * - Person Statistics
 *
 * @returns {JSX.Element} A div containing two sections with statistics.
 *
 * @example
 * // Renders a statistics table with invoice and person statistics
 * <StatisticsTable />
 */
export function StatisticsTable() {
    const { t } = useTranslation();

    return (
        <div className="pb-5">
            <div className="row table-statistics">
                <div className="col">
                    <h2 className="text-center">{t('statistics.title')}</h2>
                    <InvoiceStatistics />
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <h2 className="p-5 text-center">{t('statistics.companiesTitle')}</h2>
                    <PersonStatistics />
                </div>
            </div>
        </div>
    );
}
