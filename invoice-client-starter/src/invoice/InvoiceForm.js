import { useEffect, useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { InputSelect } from "../components/InputSelect";
import { apiGet, apiPost, apiPut } from "../utils/api";
import { InputField } from "../components/InputField";
import { dateStringFormatter } from "../utils/dateStringFormatter";
import { BackButton } from "../components/BackButton";
import { FlashMessageContext } from "../components/FlashMessageContext";

/**
 * InvoiceForm component handles the creation and editing of invoices.
 * It fetches necessary data for the form, handles form submission, and displays success or error messages.
 * 
 * @returns {JSX.Element} A component that renders a form for creating or editing invoices.
 */
export function InvoiceForm() {

    const [errorState, setError] = useState(null);
    const [fieldErrors, setFieldErrors] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [persons, setPersons] = useState([]);
    const [invoice, setInvoice] = useState({
        invoiceNumber: "",
        seller: { _id: null },
        buyer: { _id: null },
        issued: "",
        dueDate: "",
        product: "",
        price: "",
        vat: "",
        note: "",
    });

    const navigate = useNavigate();
    const { id } = useParams();
    const { setFlashMessage } = useContext(FlashMessageContext);

    /**
    * Fetches invoice details (if editing) and the list of persons for select options.
    */
    useEffect(() => {
        async function fetchInvoices() {
            if (id) {
                setInvoice(await apiGet("/api/invoice/" + id));
            }
            setPersons(await apiGet("/api/persons"));
        }
        fetchInvoices();
    }, [id]);

    /**
    * Handles form submission.
    * Sends data to the API and updates the state based on the response.
    * Pops up a flash message of result on top of the page.
    * If invalid submit then validation error message is handled.
    * 
    * @param {Event} e - The form submit event.
    */
    async function handleSubmit(e) {
        e.preventDefault();
        setIsSubmitted(true);

        try {
            const response = id ? await apiPut(`/api/invoice/${id}`, invoice) : await apiPost("/api/invoice", invoice);
            setError(false);
            setFlashMessage("Uložení faktury proběhlo úspěšně.")
            navigate("/invoices")
        } catch (error) {
            if (error.data) {
                setFieldErrors(error.data);
                setError("Chyba při odesílání formuláře, zkontrolujte zda-li jsou správně vyplněná pole.");
            } else {
                console.error("Vyskytla se chyba při odesílání formuláře:", error);
            }
        }
    }

    function mergeErrors(primaryError, secondaryError) {
        if (primaryError && secondaryError) {
            return `${primaryError}. ${secondaryError}`;
        }
        return primaryError || secondaryError || null;
    };

    return (
        <div className="ms-5 px-5 mb-5">
            <h1>{id ? "Upravit" : "Vytvořit"} fakturu</h1>
            <hr />
            
            {errorState ? (
                <div className="alert alert-danger fw-bold">{errorState}</div>
            ) : null}

            <form noValidate onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col">
                        <InputSelect
                            name="seller"
                            label="Dodavatel"
                            items={persons}
                            prompt="Vyberte dodavatele"
                            value={invoice.seller._id}
                            isSubmitted={isSubmitted}
                            error={mergeErrors(fieldErrors["seller.id"], fieldErrors.seller)}
                            handleChange={(e) => {
                                const selectedValue = e.target.value === "false" ? null : e.target.value;
                                setInvoice({ ...invoice, seller: { _id: selectedValue } });
                            }}
                        />
                        <InputSelect
                            name="buyer"
                            label="Odběratel"
                            items={persons}
                            prompt="Vyberte odběratele"
                            value={invoice.buyer._id}
                            isSubmitted={isSubmitted}
                            error={mergeErrors(fieldErrors["buyer.id"], fieldErrors.buyer)}
                            handleChange={(e) => {
                                const selectedValue = e.target.value === "false" ? null : e.target.value;
                                setInvoice({ ...invoice, buyer: { _id: selectedValue } });
                            }}
                        />
                        <InputField
                            required={true}
                            type="number"
                            name="invoiceNumber"
                            min="0"
                            label="Číslo faktury"
                            prompt="Zadejte číslo faktury"
                            value={invoice.invoiceNumber}
                            isSubmitted={isSubmitted}
                            error={fieldErrors.invoiceNumber}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, invoiceNumber: e.target.value });
                            }}
                        />
                        <InputField
                            required={true}
                            type="number"
                            name="price"
                            min="0"
                            label="Cena"
                            prompt="Zadejte cenu"
                            value={invoice.price}
                            isSubmitted={isSubmitted}
                            error={fieldErrors.price}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, price: e.target.value });
                            }}
                        />
                    </div>
                    <div className="col">
                        <InputField
                            required={true}
                            type="date"
                            name="issued"
                            label="Datum vystavení"
                            min="0"
                            value={dateStringFormatter(invoice.issued)}
                            isSubmitted={isSubmitted}
                            error={fieldErrors.issued}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, issued: e.target.value });
                            }}
                        />
                        <InputField
                            required={true}
                            type="date"
                            name="dueDate"
                            label="Datum splatnosti"
                            min="0"
                            value={dateStringFormatter(invoice.dueDate)}
                            isSubmitted={isSubmitted}
                            error={fieldErrors.dueDate}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, dueDate: e.target.value });
                            }}
                        />
                        <InputField
                            required={true}
                            type="text"
                            name="product"
                            minlength="3"
                            label="Položka"
                            prompt="Zadejte jméno položky"
                            value={invoice.product}
                            error={fieldErrors.product}
                            isSubmitted={isSubmitted}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, product: e.target.value });
                            }}
                        />
                        <InputField
                            required={true}
                            type="number"
                            name="vat"
                            min="0"
                            label="DPH"
                            prompt="Zadejte procentuální DHP"
                            value={invoice.vat}
                            isSubmitted={isSubmitted}
                            error={fieldErrors.vat}
                            handleChange={(e) => {
                                setInvoice({ ...invoice, vat: e.target.value });
                            }}
                        />
                    </div>
                </div>
                <br />
                <InputField
                    required={false}
                    type="textarea"
                    name="note"
                    label="Poznámky"
                    prompt="Napište dodatečný text k položce"
                    minlength={null}
                    value={invoice.note}
                    isSubmitted={isSubmitted}
                    error={fieldErrors.note}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, note: e.target.value });
                    }}
                />
                <BackButton style="btn btn-success mt-3 ms-3 px-4" />
                <input type="submit" className="btn btn-primary mt-3 ms-5 px-4" value="Uložit" />
            </form>
        </div>
    );
};