import { useEffect, useState, useContext } from "react";
import { apiDelete, apiGet } from "../utils/api";
import { useNavigate, useParams } from "react-router-dom";
import { FlashMessageContext } from "../contexts/FlashMessageContext";
import { InvoiceFilter } from "./InvoiceFilter";
import { InvoiceTable } from "./InvoiceTable";
import { Pagination } from "../components/Pagination";

/**
 * InvoiceIndex component displays a list of invoices with options for filtering, pagination, and deletion.
 * It handles fetching invoices from the API, applying filters, and managing pagination state.
 * 
 * @returns {JSX.Element} A component with an invoice list, filter options, and pagination controls.
 */
export function InvoiceIndex() {

    // Pagination states
    const [isLoadingCount, setIsLoadingCount] = useState(true);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [totalInvoices, setTotalInvoices] = useState(0);

    const [invoices, setInvoices] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [persons, setPersons] = useState([]);
    const [filterState, setFilter] = useState({
        minPrice: undefined,
        maxPrice: undefined,
        limit: pageSize,
        sellerId: undefined,
        buyerId: undefined,
    });

    const { page = 1 } = useParams();
    const navigate = useNavigate();
    const { flashMessage, setFlashMessage } = useContext(FlashMessageContext);

    /**
    * Deletes an invoice by ID and updates the invoice list and pagination.
    * 
    * @param {string} id - The ID of the invoice to delete.
    */
    async function deleteInvoice(id) {
        try {
            await apiDelete("/api/invoice/" + id);
            setFlashMessage("Faktura byla úspěšně odebrána.")
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
        async function fetchInvoices() {
            const params = { ...filterState, page: page - 1 };
            const data = await apiGet(`/api/invoices`, params);
            const personsData = await apiGet("/api/persons");

            setInvoices(data.invoices);
            setTotalInvoices(data.totalElements);
            setPageSize(params.limit);
            setPersons(personsData);
            setTotalPages(Math.ceil(data.totalElements / params.limit));
            setIsLoading(false);
            setIsLoadingCount(false);
        }
        fetchInvoices();
    }, [page, filterState.product]);

    /**
     *  Clears the flash message after change of page if shown 
     */
    useEffect(() => {
        return () => {
            setFlashMessage(null)
        }
    }, [page, setFlashMessage]);

    /**
     * Handles form submission to apply filters and fetch filtered invoices.
     * 
     * @param {Object} e - The form submit event.
     */
    async function handleSubmit(e) {
        e.preventDefault();
        const params = { ...filterState, page: 0 };
        const data = await apiGet("/api/invoices", params);
        setInvoices(data.invoices);
        setTotalInvoices(data.totalElements);
        setPageSize(params.limit);
        setTotalPages(Math.ceil(data.totalElements / params.limit));

        navigate("/invoices/pages/1");
    };

    /**
    * Handles page change for pagination.
    * 
    * @param {number} newPage - The new page number to navigate to.
    */
    function handlePageChange(newPage) {
        navigate(`/invoices/pages/${newPage}`);
    }

    /**
    * Updates the filter state based on user input.
    * 
    * @param {Object} e - The input change event.
    */
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

    /**
     * Handles input changes for filtering invoices.
     * 
     * @param {Object} e - The input change event.
     */
    function handleInput(e) {
        setInputText(e.target.value.toLowerCase());
        const filteredData = invoices.filter((product) => {
            return product.text.toLowerCase().includes(inputText);
        });
    };

    /**
     * Resets filters and navigates to the invoices page.
     * @param {Event} e - The form submit event.
     */
    async function handleReset(e) {
        e.preventDefault();
        setFilter({
            minPrice: undefined,
            maxPrice: undefined,
            limit: 10,
            sellerId: undefined,
            buyerId: undefined,
        });
        navigate("/invoices");

    };

    return (
        <div>
            <h1>Seznam faktur</h1>
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

                    {flashMessage ? (<div className="alert alert-success fw-bold h4 py-4 ps-5"> {flashMessage}</div>) : null}

                    <p>
                        Nalezené faktury: &nbsp;&nbsp;&nbsp; {isLoadingCount ? (<div className="spinner-grow ms-3" role="status"></div>) : (<strong>{totalInvoices}</strong>)}
                    </p>
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
}