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
import { useNavigate, useParams } from "react-router-dom";
import { Pagination } from "../components/Pagination";

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

    // Pagination states
    const [isLoadingCount, setIsLoadingCount] = useState(true);
    const [totalPages, setTotalPages] = useState(null);
    const [pageSize, setPageSize] = useState(10);
    const [totalInvoices, setTotalInvoices] = useState(null);

    const { page = 1 } = useParams();
    const navigate = useNavigate();


    async function deleteInvoice(id) {
        try {
            await apiDelete("/api/invoices/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoices(invoices.filter((item) => item._id !== id));

        if ((totalInvoices - 1) % 10 === 0) {
            navigate(`/invoices/pages/${page - 1}`)
        }
        setTotalInvoices(totalInvoices - 1);
    };

    useEffect(() => {
        async function fetchSumInvoices() {
            setTotalInvoices(await apiGet("/api/invoices/total"));
            setTotalPages(Math.ceil(totalInvoices / pageSize));
            setIsLoadingCount(false);
        }
        fetchSumInvoices();
    }, [totalInvoices, pageSize]);

    useEffect(() => {
        async function fetchInvoices() {
            setInvoices(await apiGet(`/api/invoices?page=${page - 1}&${pageSize}`));
            setPersons(await apiGet("/api/persons"));
            setIsLoading(false);
        }
        fetchInvoices();
    }, [page]);

    function handlePageChange(newPage) {
        navigate(`/invoices/pages/${newPage}`);
    }

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
            <p>
                Celkový počet: &nbsp;&nbsp;&nbsp; {isLoadingCount ? (<div className="spinner-grow ms-3" role="status"></div>) : (<strong>{totalInvoices}</strong>)}
            </p>
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
                    <Pagination currentPage={parseInt(page)} totalPages={totalPages} onPageChange={handlePageChange} />
                </div>
            )}
        </div>
    );
};