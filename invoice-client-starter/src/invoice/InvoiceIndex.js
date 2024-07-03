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

import { apiDelete, apiGet } from "../utils/api";

import InvoiceTable from "./InvoiceTable";
import InvoiceFilter from "./InvoiceFilter";


const InvoiceIndex = () => {
    const [invoices, setInvoices] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [filter, setFilter] = useState({
        minPrice: undefined,
        maxPrice: undefined,
    });

    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoices/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoices(invoices.filter((item) => item._id !== id));
    };

    useEffect(() => {
        setIsLoading(true);
        apiGet("/api/invoices").then((data) => setInvoices(data));
        setIsLoading(false);
    }, []);

    function handleChange(e) {

            setFilter((prevState) => {
                return { ...prevState, [e.target.name]: e.target.value };
            });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const params = filter;

        const data = apiGet("/api/invoices", params);
        setInvoices(data)
    }


    return (
        <div>
            <h1>Seznam faktur</h1>
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-grow my-3" role="status"></div>
                </div>
            ) : (
                <div>
                    {/* <InvoiceFilter
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        minPrice={minPrice}
                        maxPrice={maxPrice}
                        confirm="Filtrovat faktury"
                    /> */}

                    <hr />
                    <InvoiceTable
                        deleteInvoice={deleteInvoice}
                        items={invoices}
                        label="Počet faktur:"
                    />
                </div>
            )}
        </div>
    );
};
export default InvoiceIndex;
