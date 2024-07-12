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
import { Pagination } from "../components/Pagination";
import { PersonTable } from "./PersonTable";
import { useNavigate, useParams } from "react-router-dom";

export function PersonIndex() {
    const [persons, setPersons] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [totalPages, setTotalPages] = useState(null);
    const [pageSize, setPageSize] = useState(10);

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
    };

    useEffect(() => {
        async function fetchPersons() {
            const data = await apiGet(`/api/persons`, `page=${page}&size=${pageSize}`);
            setPersons(data);
            setTotalPages(Math.ceil(dataSum.length / pageSize));
            setIsLoading(false);
        }
        fetchPersons();
    }, [page]);

    function handlePageChange(newPage) {
        navigate(`/persons/pages/${newPage}`);
    }

    return (
        <div>
            <h1>Seznam společností</h1>
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
        </div>
    );
};
