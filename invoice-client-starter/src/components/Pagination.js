import React from "react";

export function Pagination({ currentPage, totalPages, onPageChange }) {

    function handlePrevClick() {
        if (currentPage > 1) { onPageChange(currentPage - 1) };
    };

    function handleNextClick() {
        if (currentPage < totalPages) { onPageChange(currentPage + 1) };
    };

   
    return (
        <nav>
            <ul className="pagination justify-content-center">
                <li className={`page-item ${currentPage == 1 ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={handlePrevClick}>
                        &laquo;
                    </button>
                </li>

                {Array.from({ length: totalPages }, (_, index) => (
                    <li
                        key={index}
                        className={`page-item ${currentPage === (index + 1) ? `active` : ``}`}>
                        <button className="page-link" id="highlight" onClick={() => onPageChange(index + 1)}>
                            {index + 1}
                        </button>
                    </li>
                ))}

                <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
                    <button className="page-link" onClick={handleNextClick}>
                        &raquo;
                    </button>
                </li>
            </ul>
        </nav>
    );
};