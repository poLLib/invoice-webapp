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

import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { apiGet } from "../utils/api";

const InvoiceDetail = () => {
    const { id } = useParams();
    const [invoice, setInvoice] = useState({});
    const [seller, setSeller] = useState({});
    const [buyer, setBuyer] = useState({});
    const [isLoading, setIsLoading] = useState(true);



    useEffect(() => {

        setIsLoading(true);
        apiGet(`/api/invoices/${id}`).then((data) => setInvoice(data));
        apiGet(`/api/invoices/${id}`).then((data) => setSeller(data.seller));
        apiGet(`/api/invoices/${id}`).then((data) => setBuyer(data.buyer));
        setIsLoading(false);


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

                    <h3>{invoice?.invoiceNumber}</h3>
                    <p>
                        <strong>Odběratel:</strong>
                        <br />
                        {buyer.name}
                    </p>
                    <p>
                        <strong>Dodavatel:</strong>
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
                        <strong>Cena: </strong> {invoice.price}
                    </p>
                    <p>
                        <strong>DPH: </strong> {invoice.vat}
                    </p>
                    <p>
                        <strong>Poznámky: </strong>
                        <br />
                        {invoice.note}
                    </p>
                </div>
                )}
            </div>
        </>
    );
};

export default InvoiceDetail;
