import { useEffect, useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { InputSelect } from "../components/InputSelect";
import { apiGet, apiPost, apiPut } from "../utils/api";
import { InputField } from "../components/InputField";
import { dateStringFormatter } from "../utils/dateStringFormatter";
import { BackButton } from "../components/BackButton";
import { FlashMessageContext } from "../contexts/FlashMessageContext";

/**
 * InvoiceForm component handles the creation and editing of invoices.
 * It fetches necessary data for the form, handles form submission, and displays success or error messages.
 *
 * @returns {JSX.Element} A component that renders a form for creating or editing invoices.
 */
export function InvoiceForm() {
    const { t } = useTranslation();

    const [errorState, setError] = useState(null);
    const [fieldErrors, setFieldErrors] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [persons, setPersons] = useState([]);
    const [invoice, setInvoice] = useState({
        sellerId: null,
        buyerId: null,
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
                setInvoice(await apiGet(`/api/invoice/${id}`));
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
            setFlashMessage(t('invoices.saveSuccess'))
            navigate("/invoices")
        } catch (error) {
            if (error.data) {
                setFieldErrors(error.data);
                setError("Form submission error, please check if all fields are filled correctly.");
            } else {
                console.error("An error occurred while submitting the form:", error);
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
            <h1>{id ? t('invoices.editTitle') : t('invoices.createTitle')}</h1>
            <hr />

            {errorState ? (
                <div className="alert alert-danger fw-bold">{errorState}</div>
            ) : null}

            <form noValidate onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col">
                        <InputSelect
                            name="seller"
                            label={t('invoices.form.supplier')}
                            items={persons}
                            prompt={t('invoices.form.supplierPlaceholder')}
                            value={invoice.sellerId}
                            isSubmitted={isSubmitted}
                            error={mergeErrors(fieldErrors["seller.id"], fieldErrors.seller)}
                            handleChange={(e) => {
                                const selectedValue = e.target.value === "false" ? null : e.target.value;
                                setInvoice({ ...invoice, sellerId: selectedValue });
                            }}
                        />
                        <InputSelect
                            name="buyer"
                            label={t('invoices.form.buyer')}
                            items={persons}
                            prompt={t('invoices.form.buyerPlaceholder')}
                            value={invoice.buyerId}
                            isSubmitted={isSubmitted}
                            error={mergeErrors(fieldErrors["buyer.id"], fieldErrors.buyer)}
                            handleChange={(e) => {
                                const selectedValue = e.target.value === "false" ? null : e.target.value;
                                setInvoice({ ...invoice, buyerId: selectedValue });
                            }}
                        />
                        {id ? null : (
                            <InputField
                                required={true}
                                type="number"
                                name="invoiceNumber"
                                min="0"
                                label={t('invoices.form.invoiceNumber')}
                                prompt={t('invoices.form.invoiceNumberPlaceholder')}
                                value={invoice.invoiceNumber}
                                isSubmitted={isSubmitted}
                                error={fieldErrors.invoiceNumber}
                                handleChange={(e) => {
                                    setInvoice({ ...invoice, invoiceNumber: e.target.value });
                                }}
                            />
                        )}
                        <InputField
                            required={true}
                            type="number"
                            name="price"
                            min="0"
                            label={t('invoices.form.price')}
                            prompt={t('invoices.form.pricePlaceholder')}
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
                            label={t('invoices.form.issueDate')}
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
                            label={t('invoices.form.dueDate')}
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
                            label={t('invoices.form.item')}
                            prompt={t('invoices.form.itemPlaceholder')}
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
                            label={t('invoices.form.vat')}
                            prompt={t('invoices.form.vatPlaceholder')}
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
                    label={t('invoices.form.note')}
                    prompt={t('invoices.form.notePlaceholder')}
                    minlength={null}
                    value={invoice.note}
                    isSubmitted={isSubmitted}
                    error={fieldErrors.note}
                    handleChange={(e) => {
                        setInvoice({ ...invoice, note: e.target.value });
                    }}
                />
                <BackButton style="btn btn-success mt-3 ms-3 px-4" />
                <input type="submit" className="btn btn-primary mt-3 ms-5 px-4" value={t('common.save')} />
            </form>
        </div>
    );
};
