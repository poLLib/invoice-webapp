import React from "react";

/**
 * Pagination component for navigating through pages.
 *
 * Renders pagination controls with previous and next buttons, and dynamically
 * generates page numbers with ellipses for larger page ranges.
 *
 * @param {number} props.currentPage - The currently active page number.
 * @param {number} props.totalPages - The total number of pages.
 * @param {Function} props.onPageChange - Callback function to handle page changes.
 * @returns {JSX.Element} Pagination controls.
 *
 * @example
 * // Renders a pagination component for 10 pages with the current page set to 5
 * <Pagination
 *   currentPage={5}
 *   totalPages={10}
 *   onPageChange={(page) => console.log('Page changed to:', page)}
 * />
 */
export function Pagination({ currentPage, totalPages, onPageChange }) {

    /**
     * Handles the click event for the "previous page" button.
     * Decrements the current page if it is greater than 1.
     */
    function handlePrevPageClick() {
        if (currentPage > 1) { onPageChange(currentPage - 1) };
    };

    /**
     * Handles the click event for the "next page" button.
     * Increments the current page if it is less than the total number of pages.
     */
    function handleNextPageClick() {
        if (currentPage < totalPages) { onPageChange(currentPage + 1) };
    };


    /**
     * Generates an array of page numbers with optional ellipses.
     * Adjusts the range of visible page numbers based on the current page.
     *
     * @returns {Array<number|string>} Array of page numbers or ellipses.
     */
    function getPageNumbers() {
        const pageNumbers = [];
        const numberDistance = 3;

        const startPage = Math.max(1, currentPage - numberDistance);
        const endPage = Math.min(totalPages - 1, currentPage + numberDistance);

        // Add ellipsis and start page
        if (startPage >= 3) {
            pageNumbers.push(1);
            pageNumbers.push("...");
        } else if (startPage === 2) {
            pageNumbers.push(1);

        }

        // Add page numbers in the range
        for (let i = startPage; i <= endPage; i++) {
            pageNumbers.push(i);
        }

        // Add ellipsis and end page
        if (endPage < totalPages - 1) {
            pageNumbers.push("...");
            pageNumbers.push(totalPages);
        } else if (endPage === totalPages - 1) {
            pageNumbers.push(totalPages);
        }

        return pageNumbers;
    }

    const pageNumbers = getPageNumbers();

    return (
        <nav>
            <ul className="pagination justify-content-center">
                <li className={`page-item ${currentPage == 1 ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={handlePrevPageClick}>
                        &laquo;
                    </button>
                </li>

                {pageNumbers.map((pageNumber, index) => (
                    <li
                        key={index}
                        className={`page-item ${currentPage === pageNumber ? 'active' : ''} ${pageNumber === "..." ? 'disabled' : ''}`}>
                        <button
                            className="page-link"
                            onClick={() => typeof pageNumber === 'number' && onPageChange(pageNumber)}
                            disabled={pageNumber === "..."}>
                            {pageNumber}
                        </button>
                    </li>
                ))}

                <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={handleNextPageClick}>
                        &raquo;
                    </button>
                </li>
            </ul>
        </nav>
    );
}