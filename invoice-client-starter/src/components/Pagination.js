import React from "react";

export function Pagination({ currentPage, totalPages, onPageChange }) {

    function handlePrevPageClick() {
        if (currentPage > 1) { onPageChange(currentPage - 1) };
    };

    function handleNextPageClick() {
        if (currentPage < totalPages) { onPageChange(currentPage + 1) };
    };

    function getPageNumbers() {
        const pageNumbers = [];
        const numberDistance = 3;

        const startPage = Math.max(1, currentPage - numberDistance);
        const endPage = Math.min(totalPages - 1, currentPage + numberDistance);

        if (startPage >= 3) {
            pageNumbers.push(1);
            pageNumbers.push("...");
        } else if (startPage === 2) {
            pageNumbers.push(1);

        }

        for (let i = startPage; i <= endPage; i++) {
            pageNumbers.push(i);
        }

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