/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */

import { Link } from "react-router-dom";

/**
 * InvoiceTable component renders a table of invoice records.
 * Each record includes options to view, edit, or delete the invoice.
 * Additionally, it provides a link to create a new invoice.
 * 
 * @param {Array} props.items - Array of invoice objects to display in the table.
 * @param {Function} props.deleteInvoice - Function to call when deleting an invoice.
 * @returns {JSX.Element} A table displaying the invoice records with action buttons.
 */
export function InvoiceTable({ items, deleteInvoice }) {

    return (
        <div className="pb-5">
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Číslo faktury</th>
                        <th>Položka</th>
                        <th>Dodavatel</th>
                        <th>Odběratel</th>
                        <th>Cena</th>
                        <th colSpan={3}></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item, index) => (
                        <tr key={index + 1}>
                            <td className="fw-bold">{item.invoiceNumber}</td>
                            <td>{item.product}</td>
                            <td>{item.seller.name}</td>
                            <td>{item.buyer.name}</td>
                            <td>{item.price} Kč</td>
                            <td>
                                <div className="btn-group">
                                    <Link
                                        to={"/invoices/show/" + item._id}
                                        className="btn btn-sm btn-info"
                                    >
                                        Zobrazit
                                    </Link>
                                    <Link
                                        to={"/invoices/edit/" + item._id}
                                        className="btn btn-sm btn-warning"
                                    >
                                        Upravit
                                    </Link>
                                    <button
                                        onClick={() => deleteInvoice(item._id)}
                                        className="btn btn-sm btn-danger"
                                    >
                                        Odstranit
                                    </button>

                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Link to={"/invoices/create"} className="btn btn-success ms-5 px-5">
                Nová faktura
            </Link>
        </div>
    );
}