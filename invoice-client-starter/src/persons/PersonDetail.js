import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { apiGet } from "../utils/api";
import { Country } from "./Country";
import { BackButton } from "../components/BackButton";
import '../styles.css'

/**
 * PersonDetail component fetches and displays detailed information about a person,
 * including their personal details and associated invoices (both sold and received).
 *
 * @returns {JSX.Element} A detailed view of the person, including personal details
 * and tables of sold and received invoices.
 */
export function PersonDetail() {
    const { t } = useTranslation();
    const { id } = useParams();
    const [person, setPerson] = useState({});
    const [soldInvoices, setSoldInvoices] = useState([]);
    const [receivedInvoices, setReceivedInvoices] = useState([]);
    const [isLoadingPersons, setIsLoadingPersons] = useState();
    const [isLoadingInvoices, setIsLoadingInvoices] = useState();
    const identificationNumber = person.identificationNumber;

    useEffect(() => {
        async function fetchPerson() {
            setPerson(await apiGet(`/api/person/${id}`))
            setIsLoadingPersons(false);
        }
        fetchPerson();
    }, [id]);

    useEffect(() => {
        async function fetchInvoices() {
            setSoldInvoices(await apiGet(`/api/identification/${identificationNumber}/sales`));
            setReceivedInvoices(await apiGet(`/api/identification/${identificationNumber}/purchases`));
            setIsLoadingInvoices(false);
        }
        fetchInvoices();
    }, [identificationNumber]);

    const country = Country.CZECHIA === person.country ? t('common.czechRepublic') : t('common.slovakia');

    return (
        <>
            <div className="container">
                <h1>{t('persons.detail')}</h1>
                <hr />
                <div className="row mb-5">
                    <div className="col-md-6">

                        {isLoadingPersons ? (
                            <div className="text-center">
                                <div className="spinner-border" role="status">
                                </div>
                            </div>
                        ) : (
                            <div>

                                <h3 className="ms-3 fs-2">{person.name}</h3>
                                <p className="ms-5">{t('persons.detail_labels.ico')}{person.identificationNumber}</p>
                                <p>
                                    <strong className="ms-2">{t('persons.detail_labels.bankAccount')}</strong>
                                    <br />
                                    {person.accountNumber}/{person.bankCode} ({t('persons.detail_labels.iban')} {person.iban})
                                </p>
                                <p>
                                    <strong className="ms-2">{t('persons.detail_labels.phone')}</strong>
                                    <br />
                                    {person.telephone}
                                </p>
                                <p>
                                    <strong className="ms-2">{t('persons.detail_labels.email')}</strong>
                                    <br />
                                    {person.mail}
                                </p>
                                <p>
                                    <strong className="ms-2">{t('persons.detail_labels.address')}</strong>
                                    <br />
                                    {person.street}, {person.city},
                                    {person.zip}, {country}
                                </p>
                                <p>
                                    <strong className="ms-2">{t('persons.detail_labels.note')}</strong>
                                    <br />
                                    {person.note}
                                </p>
                            </div>
                        )}
                    </div>


                    <div className="col-md-6 d-flex flex-column">
                        {isLoadingInvoices ? (
                            <div className="text-center">
                                <div className="spinner-border" role="status"></div>
                            </div>
                        ) : (
                            <div>
                                <div>
                                    <strong className="fs-4">{t('persons.issuedInvoices')}</strong>
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th>{t('invoices.table.invoiceNumber')}</th>
                                                <th>{t('invoices.table.item')}</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {soldInvoices.map((i, index) => (
                                                <tr key={index + 1}>
                                                    <td className="invoice-td"><Link to={`/invoices/show/${i.id}`} className="invoice-link">{i.invoiceNumber}</Link></td>
                                                    <td className="invoice-td">{i.product}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                                <br />
                                <br />
                                <div>
                                    <strong className="fs-4">{t('persons.paidInvoices')}</strong>
                                    <table className="table table-person-detail">
                                        <thead>
                                            <tr>
                                                <th>{t('invoices.table.invoiceNumber')}</th>
                                                <th>{t('invoices.table.item')}</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {receivedInvoices.map((i, index) => (
                                                <tr key={index + 1}>

                                                    <td className="invoice-td"><Link to={`/invoices/show/${i.id}`} className="invoice-link">{i.invoiceNumber}</Link></td>
                                                    <td className="invoice-td">{i.product}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        )}
                    </div>
                </div>

                <BackButton style="btn btn-success ms-3 px-4 mb-4" />

            </div>
        </>
    );
}
