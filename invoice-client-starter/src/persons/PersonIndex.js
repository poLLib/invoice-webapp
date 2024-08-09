import { useEffect, useState, useContext } from "react";
import { apiDelete, apiGet, apiGetPage } from "../utils/api";
import { Pagination } from "../components/Pagination";
import { PersonTable } from "./PersonTable";
import { useNavigate, useParams } from "react-router-dom";
import { FlashMessageContext } from "../components/FlashMessageContext";
import { Link } from "react-router-dom";

/**
 * PersonIndex component displays a paginated list of persons and provides options to delete persons and navigate between pages.
 * 
 * @returns {JSX.Element} The component rendering the person index page with pagination and action buttons.
 */
export function PersonIndex() {

    const [persons, setPersons] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    // Pagination states
    const [isLoadingCount, setIsLoadingCount] = useState(true);
    const [totalPages, setTotalPages] = useState(null);
    const [pageSize, setPageSize] = useState(10);
    const [totalPersons, setTotalPersons] = useState(null);

    const { page = 1 } = useParams();
    const navigate = useNavigate();
    const { flashMessage, setFlashMessage } = useContext(FlashMessageContext);

    /**
     * Handles the deletion of a person.
     * Updates the state and redirects to the previous page if needed.
     * 
     * @param {string} id - The ID of the person to delete.
     */
    async function deletePerson(id) {
        try {
            await apiDelete(`/api/persons/${id}`);
            setFlashMessage("Společnost byla úspěšně odebrána.")
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setPersons(persons.filter((item) => item._id !== id));

        if ((totalPersons - 1) % 10 === 0) {
            navigate(`/persons/pages/${page - 1}`)
        }
        setTotalPersons(totalPersons - 1);
    };

    /**
     * Fetches the total number of persons and updates the pagination state.
     */
    useEffect(() => {
        async function fetchSumPersons() {
            setTotalPersons(await apiGet("/api/persons/total"));
            setTotalPages(Math.ceil(totalPersons / pageSize));
            setIsLoadingCount(false);
        }
        fetchSumPersons();
    }, [totalPersons, pageSize]);

    /**
     * Fetches the persons for the current page.
     */
    useEffect(() => {
        async function fetchPersons() {
            const data = await apiGetPage(`/api/persons?page=${page - 1}&size=${pageSize}`);
            setPersons(data);
            setIsLoading(false);
        }
        fetchPersons();
    }, [page]);

    /**
    *  Clears the flash message after change of page if shown 
    */
    useEffect(() => {
        return () => {
            setFlashMessage(null)
        }
    }, [page, setFlashMessage]);

    /**
     * Handles page change event.
     * 
     * @param {number} newPage - The new page number to navigate to.
     */
    function handlePageChange(newPage) {
        navigate(`/persons/pages/${newPage}`);
    }

    return (
        <div className="row">
            <div className="col">
                <h1>Seznam společností</h1>
                <hr />

                {flashMessage ? (<div className="alert alert-success fw-bold h4 py-4 ps-5"> {flashMessage}</div>) : null}

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
                <Pagination currentPage={parseInt(page)} totalPages={totalPages} onPageChange={handlePageChange} />

                <Link to={"/persons/create"} className="btn btn-success ms-5 mb-5 px-5">
                    Nová firma/osoba
                </Link>
            </div>
        </div>
    );
}