import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";

/**
 * InvoiceTable component renders a table of invoice records.
 * Each record includes options to view, edit, or delete the invoice.
 * Additionally, it provides a link to create a new invoice.
 *
 * @param {Array} props.items - Array of invoice objects to display in the table.
 * @param {Function} props.deleteInvoice - Function to call when deleting an invoice.
 * @returns {JSX.Element} A table displaying the invoice records with action buttons.
 */
export function InvoiceTable({ items, deleteInvoice, isAdmin }) {
    const { t } = useTranslation();

    return (
        <div className="pb-5">
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>{t('invoices.table.invoiceNumber')}</th>
                        <th>{t('invoices.table.item')}</th>
                        <th>{t('invoices.table.supplier')}</th>
                        <th>{t('invoices.table.buyer')}</th>
                        <th>{t('invoices.table.price')}</th>
                        <th colSpan={3}></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item) => (
                        <tr key={item.id}>
                            <td className="fw-bold">{item.invoiceNumber}</td>
                            <td>{item.product}</td>
                            <td>{item.seller.name}</td>
                            <td>{item.buyer.name}</td>
                            <td>{item.price} {t('common.currency')}</td>
                            <td>
                                <div className="btn-group">
                                    <Link
                                        to={"/invoices/show/" + item.id}
                                        className="btn btn-sm btn-info"
                                    >
                                        {t('common.show')}
                                    </Link>
                                    <Link
                                        to={"/invoices/edit/" + item.id}
                                        className="btn btn-sm btn-warning"
                                    >
                                        {t('common.edit')}
                                    </Link>
                                    {isAdmin && (
                                        <button
                                            onClick={() => deleteInvoice(item.id)}
                                            className="btn btn-sm btn-danger"
                                        >
                                            {t('common.delete')}
                                        </button>
                                    )}
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Link to={"/invoices/create"} className="btn btn-success ms-5 px-5">
                {t('invoices.newInvoice')}
            </Link>
        </div>
    );
}
