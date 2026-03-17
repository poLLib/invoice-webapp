import { useEffect, useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { apiGet, apiPost, apiPut } from "../utils/api";
import { BackButton } from "../components/BackButton";
import { InputCheck } from "../components/InputCheck";
import { FlashMessageContext } from "../contexts/FlashMessageContext";
import { InputField } from "../components/InputField";
import { Country } from "./Country";

/**
 * PersonForm component allows users to create or update a person's details.
 * It uses form fields to gather data and handles submission with API calls.
 *
 * @returns {JSX.Element} The component rendering the form for creating or updating a person.
 */
export function PersonForm() {
    const { t } = useTranslation();
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
    const [errorState, setError] = useState(null);
    const [fieldErrors, setFieldErrors] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    const navigate = useNavigate();
    const { id } = useParams();
    const { setFlashMessage } = useContext(FlashMessageContext);

    /**
     * Fetches the person's data if an ID is provided (for editing purposes).
     */
    useEffect(() => {
        async function fetchPersons() {
            if (id) {
                try {
                    const personData = await apiGet(`/api/person/${id}`);
                    setPerson(personData);
                } catch (error) {
                    console.error("An error occurred while fetching person:", error);
                }
            }
        }
        fetchPersons();
    }, [id]);

    /**
     * Handles form submission.
     * Sends data to the API and updates the state based on the response.
     * Pops up a flash message of result on top of the page.
     * If invalid submit then validation error message is handled.
     *
     * @param {Event} e - The form submission event.
     */
    async function handleSubmit(e) {
        e.preventDefault();
        setIsSubmitted(true);

        try {
            const response = id ? await apiPut(`/api/person/${id}`, person) : await apiPost("/api/person", person);
            setError(false);
            setFlashMessage(t('persons.addSuccess'))
            navigate("/persons");
        } catch (error) {
            if (error.data) {
                setFieldErrors(error.data);
                setError(t('persons.formError'));
            } else {
                console.error("An error occurred while saving form:", error);
            }
        }
    }

    return (
        <div className="ms-5 px-5 mb-5">
            <h1>{id ? t('persons.editTitle') : t('persons.createTitle')}</h1>
            <hr />
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}

            {/* controls if user update or create a new person
                when id=true then hides InputField [identificationNumber, taxNumber]
            */}

            <form noValidate onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col">
                            <InputField
                                required={true}
                                type="text"
                                name="personName"
                                min="3"
                                label={t('persons.form.name')}
                                prompt={t('persons.form.namePlaceholder')}
                                value={person.name}
                                error={fieldErrors.name}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, name: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="mail"
                                min="3"
                                label={t('persons.form.email')}
                                prompt={t('persons.form.emailPlaceholder')}
                                value={person.mail}
                                error={fieldErrors.mail}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, mail: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="telephone"
                                min="3"
                                label={t('persons.form.phone')}
                                prompt={t('persons.form.phonePlaceholder')}
                                value={person.telephone}
                                error={fieldErrors.telephone}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, telephone: e.target.value });
                                }}
                            />
                            {id ? null : (
                            <>
                                <InputField
                                    required={true}
                                    type="text"
                                    name="identificationNumber"
                                    min="3"
                                    label={t('persons.form.ico')}
                                    prompt={t('persons.form.icoPlaceholder')}
                                    value={person.identificationNumber}
                                    error={fieldErrors.identificationNumber}
                                    isSubmitted={isSubmitted}
                                    handleChange={(e) => {
                                        setPerson({ ...person, identificationNumber: e.target.value });
                                    }}
                                />
                                <InputField
                                    required={true}
                                    type="text"
                                    name="taxNumber"
                                    min="3"
                                    label={t('persons.form.dic')}
                                    prompt={t('persons.form.dicPlaceholder')}
                                    value={person.taxNumber}
                                    error={fieldErrors.taxNumber}
                                    isSubmitted={isSubmitted}
                                    handleChange={(e) => {
                                        setPerson({ ...person, taxNumber: e.target.value });
                                    }}
                                />
                            </>
                            )}
                            <InputField
                                required={true}
                                type="text"
                                name="accountNumber"
                                min="3"
                                label={t('persons.form.bankAccount')}
                                prompt={t('persons.form.bankAccountPlaceholder')}
                                value={person.accountNumber}
                                error={fieldErrors.accountNumber}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, accountNumber: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="bankCode"
                                min="3"
                                label={t('persons.form.bankCode')}
                                prompt={t('persons.form.bankCodePlaceholder')}
                                value={person.bankCode}
                                error={fieldErrors.bankCode}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, bankCode: e.target.value });
                                }}
                            />
                        </div>
                        <div className="col">
                            <InputField
                                required={true}
                                type="text"
                                name="IBAN"
                                min="3"
                                label={t('persons.form.iban')}
                                prompt={t('persons.form.ibanPlaceholder')}
                                value={person.iban}
                                error={fieldErrors.iban}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, iban: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="street"
                                min="3"
                                label={t('persons.form.street')}
                                prompt={t('persons.form.streetPlaceholder')}
                                value={person.street}
                                error={fieldErrors.street}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, street: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="ZIP"
                                min="3"
                                label={t('persons.form.zip')}
                                prompt={t('persons.form.zipPlaceholder')}
                                value={person.zip}
                                error={fieldErrors.zip}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, zip: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="city"
                                min="3"
                                label={t('persons.form.city')}
                                prompt={t('persons.form.cityPlaceholder')}
                                value={person.city}
                                error={fieldErrors.city}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, city: e.target.value });
                                }}
                            />
                            <InputField
                                required={true}
                                type="text"
                                name="note"
                                label={t('persons.form.note')}
                                value={person.note}
                                error={fieldErrors.note}
                                isSubmitted={isSubmitted}
                                handleChange={(e) => {
                                    setPerson({ ...person, note: e.target.value });
                                }}
                            />
                            <h6>{t('common.country')}</h6>
                            <InputCheck
                                type="radio"
                                name="country"
                                label={t('common.czechRepublic')}
                                value={Country.CZECHIA}
                                handleChange={(e) => {
                                    setPerson({ ...person, country: e.target.value });
                                }}
                                checked={Country.CZECHIA === person.country}
                            />
                            <InputCheck
                                type="radio"
                                name="country"
                                label={t('common.slovakia')}
                                value={Country.SLOVAKIA}
                                handleChange={(e) => {
                                    setPerson({ ...person, country: e.target.value });
                                }}
                                checked={Country.SLOVAKIA === person.country}
                            />
                        </div>
                    </div>
                <BackButton style="btn btn-success mt-3 ms-3 px-4" />
                <input type="submit" className="btn btn-primary mt-3 ms-5 px-4" value={t('common.save')} />            </form>
        </div>
    );
}
