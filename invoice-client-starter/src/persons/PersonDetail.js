import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
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

    const country = Country.CZECHIA === person.country ? "Česká republika" : "Slovensko";

    return (
        <>
            <div className="container">
                <h1>Detail společnosti:</h1>
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
                                <p className="ms-5">IČO:{person.identificationNumber}</p>
                                <p>
                                    <strong className="ms-2">Bankovní účet:</strong>
                                    <br />
                                    {person.accountNumber}/{person.bankCode} (IBAN: {person.iban})
                                </p>
                                <p>
                                    <strong className="ms-2">Tel.:</strong>
                                    <br />
                                    {person.telephone}
                                </p>
                                <p>
                                    <strong className="ms-2">Mail:</strong>
                                    <br />
                                    {person.mail}
                                </p>
                                <p>
                                    <strong className="ms-2">Sídlo:</strong>
                                    <br />
                                    {person.street}, {person.city},
                                    {person.zip}, {country}
                                </p>
                                <p>
                                    <strong className="ms-2">Poznámka:</strong>
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
                                    <strong className="fs-4">Vystavené faktury:</strong>
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th>Číslo faktury</th>
                                                <th>Položka</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {soldInvoices.map((i, index) => (
                                                <tr key={index + 1}>
                                                    <td className="invoice-td"><Link to={`/invoices/show/${i._id}`} className="invoice-link">{i.invoiceNumber}</Link></td>
                                                    <td className="invoice-td">{i.product}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                                <br />
                                <br />
                                <div>
                                    <strong className="fs-4">Proplacené faktury:</strong>
                                    <table className="table table-person-detail">
                                        <thead>
                                            <tr>
                                                <th>Číslo faktury</th>
                                                <th>Položka</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {receivedInvoices.map((i, index) => (
                                                <tr key={index + 1}>

                                                    <td className="invoice-td"><Link to={`/invoices/show/${i._id}`} className="invoice-link">{i.invoiceNumber}</Link></td>
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