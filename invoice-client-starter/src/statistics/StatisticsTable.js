import { PersonStatistics } from "./PersonStatistics";
import { InvoiceStatistics } from "./InvoiceStatistics";

export function StatisticsTable() {
    
    return (
        <div className="pb-5">
            <div className="row table-statistics">
                <div className="col">
                    <h2 className="text-center">Statistiky faktur</h2>
                    <InvoiceStatistics />
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <h2 className="p-5 text-center">Výpis společností a jejich zisků</h2>
                    <PersonStatistics/>
                </div>
            </div>
        </div>
    );
};