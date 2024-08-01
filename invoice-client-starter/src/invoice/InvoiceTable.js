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
                    {items.map((item) => (
                        <tr key={item._id}>
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