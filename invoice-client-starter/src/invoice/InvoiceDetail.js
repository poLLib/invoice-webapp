import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { apiGet } from "../utils/api";
import { BackButton } from "../components/BackButton";

export function InvoiceDetail() {
    const { t } = useTranslation();
    const { id } = useParams();
    const [invoice, setInvoice] = useState({});
    const [seller, setSeller] = useState({});
    const [buyer, setBuyer] = useState({});
    const [isLoading, setIsLoading] = useState(true);



    useEffect(() => {
        async function fetchInvoices() {
            const data = await apiGet(`/api/invoice/${id}`);
            setInvoice(data);
            setSeller(data.seller);
            setBuyer(data.buyer);
            setIsLoading(false);
        }
        fetchInvoices();
    }, [id]);

    return (
        <>
            <div>
                <h1>{t('invoices.detail')}</h1>
                <hr />
                {isLoading ? (
                    <div className="d-flex justify-content-center align-items-center">
                        <div className="text-center">
                            <div className="spinner-border" role="status">
                            </div>
                        </div>
                    </div>
                ) : (<div>

                    <h3 className="ms-5 fs-2">{t('invoices.detail_labels.invoiceNumber')} <strong>{invoice?.invoiceNumber}</strong></h3>
                    <br />
                    <p>
                        <strong className="ms-2">{t('invoices.detail_labels.buyer')}</strong>
                        <br />
                        {buyer.name}
                    </p>
                    <p>
                        <strong className="ms-2">{t('invoices.detail_labels.supplier')}</strong>
                        <br />
                        {seller.name}
                    </p>
                    <p>
                        <strong>{t('invoices.detail_labels.issueDate')} </strong> {invoice.issued}
                    </p>
                    <p>
                        <strong>{t('invoices.detail_labels.dueDate')} </strong> {invoice.dueDate}
                    </p>
                    <p>
                        <strong>{t('invoices.detail_labels.item')} </strong> {invoice.product}
                    </p>
                    <p>
                        <strong>{t('invoices.detail_labels.price')} </strong> {invoice.price} {t('common.currency')}
                    </p>
                    <p>
                        <strong>{t('invoices.detail_labels.vat')} </strong> {invoice.vat} %
                    </p>
                    <p>
                        <strong className="ms-2">{t('invoices.detail_labels.note')} </strong>
                        <br />
                        {invoice.note}
                    </p>
                </div>
                )}

                <BackButton style="btn btn-success ms-3 px-4 mb-4" />

            </div>
        </>
    );
}
