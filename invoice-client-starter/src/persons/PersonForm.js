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

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { apiGet, apiPost, apiPut } from "../utils/api";
import { BackButton } from "../components/BackButton";
import { InputCheck } from "../components/InputCheck";
import { FlashMessage } from "../components/FlashMessage";
import { InputField } from "../components/InputField";
import { Country } from "./Country";

/**
 * PersonForm component allows users to create or update a person's details.
 * It uses form fields to gather data and handles submission with API calls.
 * 
 * @returns {JSX.Element} The component rendering the form for creating or updating a person.
 */
export function PersonForm() {
    const [person, setPerson] = useState({
        name: "",
        identificationNumber: "",
        taxNumber: "",
        accountNumber: "",
        bankCode: "",
        iban: "",
        telephone: "",
        mail: "",
        street: "",
        zip: "",
        city: "",
        country: Country.CZECHIA,
        note: ""
    });
    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);
    const [fieldErrors, setFieldErrors] = useState({});

    const navigate = useNavigate();
    const { id } = useParams();

    /**
     * Fetches the person's data if an ID is provided (for editing purposes).
     */
    useEffect(() => {
        async function fetchPerson() {
            if (id) {
                try {
                    const personData = await apiGet(`/api/persons/${id}`);
                    setPerson(personData);
                } catch (error) {
                    console.error("Error fetching person data:", error);
                }
            }
        }
        fetchPerson();
    }, [id]);

    /**
     * Handles form submission.
     * Sends data to the API and updates the state based on the response.
     * Pops up a flash message of result on top of the page.
     * 
     * @param {Event} e - The form submission event.
     */
    async function handleSubmit(e) {
        e.preventDefault();

        try {
            const response = id ? await apiPut(`/api/persons/${id}`, person) : await apiPost("/api/persons", person);
            setSent(true);
            setSuccess(true);
            setTimeout(() => {
                setSent(false);
            }, 2500);
            navigate("/persons");
        } catch (error) {
            if (error.data) {
                setFieldErrors(error.data);
            } else {
                console.error("Error submitting form:", error);
            }
            setSent(true);
            setSuccess(false);
            setTimeout(() => {
                setSent(false);
            }, 2500);
        }
    }

    const sent = sentState;
    const success = successState;

    return (
        <div className="ms-5 px-5 mb-5">
            <h1>{id ? "Upravit" : "Vytvořit"} společnost</h1>
            <hr />
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Uložení osobnosti proběhlo úspěšně." : ""}
                />
            )}

            {/* controls if user update or create a new person
                when id=true then hides InputField [identificationNumber, taxNumber] 
            */}

            <form noValidate onSubmit={handleSubmit}>
                {id ? (
                    <div>
                        <InputField
                            required={true}
                            type="text"
                            name="personName"
                            min="3"
                            label="Jméno"
                            prompt="Zadejte celé jméno"
                            value={person.name}
                            error={fieldErrors.name}
                            handleChange={(e) => {
                                setPerson({ ...person, name: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="accountNumber"
                            min="3"
                            label="Číslo bankovního účtu"
                            prompt="Zadejte číslo bankovního účtu"
                            value={person.accountNumber}
                            error={fieldErrors.accountNumber}
                            handleChange={(e) => {
                                setPerson({ ...person, accountNumber: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="bankCode"
                            min="3"
                            label="Kód banky"
                            prompt="Zadejte kód banky"
                            value={person.bankCode}
                            error={fieldErrors.bankCode}
                            handleChange={(e) => {
                                setPerson({ ...person, bankCode: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="IBAN"
                            min="3"
                            label="IBAN"
                            prompt="Zadejte IBAN"
                            value={person.iban}
                            error={fieldErrors.iban}
                            handleChange={(e) => {
                                setPerson({ ...person, iban: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="telephone"
                            min="3"
                            label="Telefon"
                            prompt="Zadejte Telefon"
                            value={person.telephone}
                            error={fieldErrors.telephone}
                            handleChange={(e) => {
                                setPerson({ ...person, telephone: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="mail"
                            min="3"
                            label="Mail"
                            prompt="Zadejte mail"
                            value={person.mail}
                            error={fieldErrors.mail}
                            handleChange={(e) => {
                                setPerson({ ...person, mail: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="street"
                            min="3"
                            label="Ulice"
                            prompt="Zadejte ulici"
                            value={person.street}
                            error={fieldErrors.street}
                            handleChange={(e) => {
                                setPerson({ ...person, street: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="ZIP"
                            min="3"
                            label="PSČ"
                            prompt="Zadejte PSČ"
                            value={person.zip}
                            error={fieldErrors.zip}
                            handleChange={(e) => {
                                setPerson({ ...person, zip: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="city"
                            min="3"
                            label="Město"
                            prompt="Zadejte město"
                            value={person.city}
                            error={fieldErrors.city}
                            handleChange={(e) => {
                                setPerson({ ...person, city: e.target.value });
                            }}
                        />

                        <InputField
                            required={false}
                            type="text"
                            name="note"
                            label="Poznámka"
                            value={person.note}
                            error={fieldErrors.note}
                            handleChange={(e) => {
                                setPerson({ ...person, note: e.target.value });
                            }}
                        />

                        <h6>Země:</h6>

                        <InputCheck
                            type="radio"
                            name="country"
                            label="Česká republika"
                            value={Country.CZECHIA}
                            handleChange={(e) => {
                                setPerson({ ...person, country: e.target.value });
                            }}
                            checked={Country.CZECHIA === person.country}
                        />

                        <InputCheck
                            type="radio"
                            name="country"
                            label="Slovensko"
                            value={Country.SLOVAKIA}
                            handleChange={(e) => {
                                setPerson({ ...person, country: e.target.value });
                            }}
                            checked={Country.SLOVAKIA === person.country}
                        />
                    </div>
                ) : (
                    <div>
                        <InputField
                            required={true}
                            type="text"
                            name="personName"
                            min="3"
                            label="Jméno"
                            prompt="Zadejte celé jméno"
                            value={person.name}
                            error={fieldErrors.name}
                            handleChange={(e) => {
                                setPerson({ ...person, name: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="identificationNumber"
                            min="3"
                            label="IČO"
                            prompt="Zadejte IČO"
                            value={person.identificationNumber}
                            error={fieldErrors.identificationNumber}
                            handleChange={(e) => {
                                setPerson({ ...person, identificationNumber: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="taxNumber"
                            min="3"
                            label="DIČ"
                            prompt="Zadejte DIČ"
                            value={person.taxNumber}
                            error={fieldErrors.taxNumber}
                            handleChange={(e) => {
                                setPerson({ ...person, taxNumber: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="accountNumber"
                            min="3"
                            label="Číslo bankovního účtu"
                            prompt="Zadejte číslo bankovního účtu"
                            value={person.accountNumber}
                            error={fieldErrors.accountNumber}
                            handleChange={(e) => {
                                setPerson({ ...person, accountNumber: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="bankCode"
                            min="3"
                            label="Kód banky"
                            prompt="Zadejte kód banky"
                            value={person.bankCode}
                            error={fieldErrors.bankCode}
                            handleChange={(e) => {
                                setPerson({ ...person, bankCode: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="IBAN"
                            min="3"
                            label="IBAN"
                            prompt="Zadejte IBAN"
                            value={person.iban}
                            error={fieldErrors.iban}
                            handleChange={(e) => {
                                setPerson({ ...person, iban: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="telephone"
                            min="3"
                            label="Telefon"
                            prompt="Zadejte Telefon"
                            value={person.telephone}
                            error={fieldErrors.telephone}
                            handleChange={(e) => {
                                setPerson({ ...person, telephone: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="mail"
                            min="3"
                            label="Mail"
                            prompt="Zadejte mail"
                            value={person.mail}
                            error={fieldErrors.mail}
                            handleChange={(e) => {
                                setPerson({ ...person, mail: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="street"
                            min="3"
                            label="Ulice"
                            prompt="Zadejte ulici"
                            value={person.street}
                            error={fieldErrors.street}
                            handleChange={(e) => {
                                setPerson({ ...person, street: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="ZIP"
                            min="3"
                            label="PSČ"
                            prompt="Zadejte PSČ"
                            value={person.zip}
                            error={fieldErrors.zip}
                            handleChange={(e) => {
                                setPerson({ ...person, zip: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="city"
                            min="3"
                            label="Město"
                            prompt="Zadejte město"
                            value={person.city}
                            error={fieldErrors.city}
                            handleChange={(e) => {
                                setPerson({ ...person, city: e.target.value });
                            }}
                        />

                        <InputField
                            required={true}
                            type="text"
                            name="note"
                            label="Poznámka"
                            value={person.note}
                            error={fieldErrors.note}
                            handleChange={(e) => {
                                setPerson({ ...person, note: e.target.value });
                            }}
                        />

                        <h6>Země:</h6>

                        <InputCheck
                            type="radio"
                            name="country"
                            label="Česká republika"
                            value={Country.CZECHIA}
                            handleChange={(e) => {
                                setPerson({ ...person, country: e.target.value });
                            }}
                            checked={Country.CZECHIA === person.country}
                        />

                        <InputCheck
                            type="radio"
                            name="country"
                            label="Slovensko"
                            value={Country.SLOVAKIA}
                            handleChange={(e) => {
                                setPerson({ ...person, country: e.target.value });
                            }}
                            checked={Country.SLOVAKIA === person.country}
                        />
                    </div>
                )}

                <BackButton style="btn btn-success mt-3 ms-3 px-4" />
                <input type="submit" className="btn btn-primary mt-3 ms-5 px-4" value="Uložit" />            </form>
        </div>
    );
}