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
import InvoiceFilter from "./InvoiceFilter";
import { InvoiceTable } from "./InvoiceTable";

export function InvoiceIndex() {

    const initialFilterState = {
        minPrice: undefined,
        maxPrice: undefined,
        limit: undefined,
        sellerId: undefined,
        buyerId: undefined,
    };

    const [invoices, setInvoices] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [persons, setPersons] = useState([]);
    const [filterState, setFilter] = useState(initialFilterState);

    async function deleteInvoice(id) {
        try {
            await apiDelete("/api/invoices/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoices(invoices.filter((item) => item._id !== id));
    };

    useEffect(() => {
        async function fetchInvoices() {
            setInvoices(await apiGet("/api/invoices"));
            setPersons(await apiGet("/api/persons"));
            setIsLoading(false);
        }
        fetchInvoices();
    }, []);

    function handleChange(e) {
        if (e.target.value === "false" || e.target.value === "true" || e.target.value === '') {
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: undefined }
            });
        } else {
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value }
            });
        }
    };


    async function handleSubmit(e) {
        e.preventDefault();
        const params = filterState;
        const data = await apiGet("/api/invoices", params);
        setInvoices(data);
    }

    function handleInput(e) {
        setInputText(e.target.value.toLowerCase());
        const filteredData = invoices.filter((product) => {
            return product.text.toLowerCase().includes(inputText);
        })
    }

    function handleReset(e) {
        console.log(initialFilterState)  
        setFilter(initialFilterState);
        handleSubmit(e);
    
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
                    <InvoiceFilter
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleInput={handleInput}
                        handleReset={handleReset}
                        sellers={persons}
                        buyers={persons}
                        filter={filterState}
                        confirm="Filtrovat faktury"
                    />
                    <hr />
                    <InvoiceTable
                        deleteInvoice={deleteInvoice}
                        items={invoices}
                        label="Počet zobrazených faktur:"
                    />
                </div>
            )}
        </div>
    );
};