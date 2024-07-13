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
import { apiDelete, apiGet, apiGetPage } from "../utils/api";
import { Pagination } from "../components/Pagination";
import { PersonTable } from "./PersonTable";
import { useNavigate, useParams } from "react-router-dom";
import { Link } from "react-router-dom";

export function PersonIndex() {
    const [persons, setPersons] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isLoadingCount, setIsLoadingCount] = useState(true);
    const [totalPages, setTotalPages] = useState(null);
    const [pageSize, setPageSize] = useState(10);
    const [totalPersons, setTotalPersons] = useState(null);

    const { page = 1 } = useParams();
    const navigate = useNavigate();

    async function deletePerson(id) {
        try {
            await apiDelete(`/api/persons/${id}`);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setPersons(persons.filter((item) => item._id !== id));
        setTotalPersons(totalPersons - 1);
    };

    useEffect(() => {
        async function fetchSumPersons() {
            setTotalPersons(await apiGet("/api/persons/total"));
            setTotalPages(Math.ceil(totalPersons / pageSize));
            setIsLoadingCount(false);
        }
        fetchSumPersons();
    }, [totalPersons, pageSize])

    useEffect(() => {
        async function fetchPersons() {
            const data = await apiGetPage(`/api/persons?page=${page - 1}&size=${pageSize}`);
            setPersons(data);
            setIsLoading(false);
        }
        fetchPersons();
    }, [page]);


    function handlePageChange(newPage) {
        navigate(`/persons/pages/${newPage}`);
    }
    console.log(page);


    return (
        <div>
            <h1>Seznam společností</h1>
            <p>
                Celkový počet: &nbsp;&nbsp;&nbsp; {isLoadingCount ? (<div className="spinner-grow ms-3" role="status"></div>) : (<strong>{totalPersons}</strong>)}
            </p>
            {isLoading ? (
                <div className="text-center">
                    <div className="spinner-grow my-3" role="status"></div>
                </div>
            ) : (
                <PersonTable
                    deletePerson={deletePerson}
                    itemsPerPage={persons}
                    label="Celkový počet:"
                    page={page}
                />
            )}
            <Pagination currentPage={page} totalPages={totalPages} onPageChange={handlePageChange} />

            <Link to={"/persons/create"} className="btn btn-success ms-5 mb-5 px-5">
                Nová firma/osoba
            </Link>
        </div>
    );
};
