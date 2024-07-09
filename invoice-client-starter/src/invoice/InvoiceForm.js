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
import { useNavigate, useParams } from "react-router-dom";

import { apiGet, apiPost, apiPut } from "../utils/api";

import InputField from "../components/InputField";
import FlashMessage from "../components/FlashMessage";
import InputSelect from "../components/InputSelect";
import dateStringFormatter from "../utils/dateStringFormatter";

export function InvoiceForm() {
    const navigate = useNavigate();
    const { id } = useParams();
    const [invoice, setInvoice] = useState({
        invoiceNumber: "",
        seller: { _id: "" },
        buyer: { _id: "" },
        issued: "",
        dueDate: "",
        product: "",
        price: "",
        vat: "",
        note: "",

    });
    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);
    const [persons, setPersons] = useState([]);

    useEffect(() => {
        async function fetchInvoices() {
            if (id) {
                setInvoice(await apiGet("/api/invoices/" + id));
            }
            setPersons(await apiGet("/api/persons"));
        }
        fetchInvoices();
    }, [id]);

    function handleSubmit(e) {
        e.preventDefault();

        (id ? apiPut("/api/invoices/" + id, invoice) : apiPost("/api/invoices", invoice))
            .then((data) => {
                setSent(true);
                setSuccess(true);
                setTimeout(() => {
                    setSent(false);
                    navigate("/invoices");
                }, 5000);
            })
            .catch((error) => {
                console.log(error.message);
                setError(error.message);
                setSent(true);
                setSuccess(false);
                setTimeout(() => {
                    setSent(false);
                }, 5000);
            });
    };



    const sent = sentState;
    const success = successState;

    return (
        <div className="ms-5 px-5 mb-5">
            <h1>{id ? "Upravit" : "Vytvořit"} fakturu</h1>
            <hr />
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Uložení faktury proběhlo úspěšně." : ""}
                />
            )}
            <form onSubmit={handleSubmit}>
                <InputField
                    required={true}
                    type="number"
                    name="invoiceNumber"
                    min="0"
                    label="Číslo faktury"
                    prompt="Zadejte číslo faktury"
                    value={invoice.invoiceNumber}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, invoiceNumber: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="date"
                    name="issued"
                    label="Datum vystavení"
                    min="0"
                    value={dateStringFormatter(invoice.issued)}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, issued: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="date"
                    name="dueDate"
                    label="Datum splatnosti"
                    min="0"
                    value={dateStringFormatter(invoice.dueDate)}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, dueDate: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="text"
                    name="product"
                    minlength="3"
                    label="Položka"
                    prompt="Zadejte jméno položky"
                    value={invoice.product}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, product: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="number"
                    name="price"
                    min="0"
                    label="Cena"
                    prompt="Zadejte cenu"
                    value={invoice.price}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, price: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="number"
                    name="vat"
                    min="0"
                    label="DPH"
                    prompt="Zadejte procentuální DHP"
                    value={invoice.vat}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, vat: e.target.value });
                    }}
                />
                <InputField
                    required={false}
                    type="textarea"
                    name="note"
                    label="Poznámky"
                    prompt="Napište dodatečný text k položce"
                    minlength={null}
                    value={invoice.note}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, note: e.target.value });
                    }}
                />
                <InputSelect
                    required={true}
                    name="seller"
                    label="Dodavatel"
                    items={persons}
                    prompt="Vyberte dodavatele"
                    value={invoice.seller._id}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, seller: { _id: e.target.value } });
                    }}
                />
                <InputSelect
                    required={true}
                    name="buyer"
                    label="Odběratel"
                    items={persons}
                    prompt="Vyberte odběratele"
                    value={invoice.buyer._id}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, buyer: { _id: e.target.value } });
                    }}
                />

                <input type="submit" className="btn btn-primary" value="Uložit" />
            </form>
        </div>
    );
};