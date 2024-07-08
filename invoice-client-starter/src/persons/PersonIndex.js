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

import { PersonTable } from "./PersonTable";

export function PersonIndex() {
    const [persons, setPersons] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

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
            setPersons(await apiGet("/api/persons"));
            setIsLoading(false);
        }
        fetchPersons();
    }, []);

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
                    items={persons}
                    label="Celkový počet:"
                />
            )}
        </div>
    );
};
