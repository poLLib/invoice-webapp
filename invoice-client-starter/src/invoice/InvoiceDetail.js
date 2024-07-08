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

export function InvoiceDetail() {
    const { id } = useParams();
    const [invoice, setInvoice] = useState({});
    const [seller, setSeller] = useState({});
    const [buyer, setBuyer] = useState({});
    const [isLoading, setIsLoading] = useState(true);



    useEffect(() => {
        async function fetchInvoices() {
            const data = await apiGet(`/api/invoices/${id}`);
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
            </div>
        </>
    );
};

