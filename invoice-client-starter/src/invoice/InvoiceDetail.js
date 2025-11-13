import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { apiGet } from "../utils/api";
import { BackButton } from "../components/BackButton";

export function InvoiceDetail() {
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
                <h1>Detail faktury</h1>
                <hr />
                {isLoading ? (
                    <div className="d-flex justify-content-center align-items-center">
                        <div className="text-center">
                            <div className="spinner-border" role="status">
                            </div>
                        </div>
                    </div>
                ) : (<div>

                    <h3 className="ms-5 fs-2">Faktura číslo: <strong>{invoice?.invoiceNumber}</strong></h3>
                    <br />
                    <p>
                        <strong className="ms-2">Odběratel:</strong>
                        <br />
                        {buyer.name}
                    </p>
                    <p>
                        <strong className="ms-2">Dodavatel:</strong>
                        <br />
                        {seller.name}
                    </p>
                    <p>
                        <strong>Datum vystavení: </strong> {invoice.issued}
                    </p>
                    <p>
                        <strong>Datum splatnosti: </strong> {invoice.dueDate}
                    </p>
                    <p>
                        <strong>Položka: </strong> {invoice.product}
                    </p>
                    <p>
                        <strong>Cena: </strong> {invoice.price} Kč
                    </p>
                    <p>
                        <strong>DPH: </strong> {invoice.vat} %
                    </p>
                    <p>
                        <strong className="ms-2">Poznámky: </strong>
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

