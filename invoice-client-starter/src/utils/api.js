/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */


/**
 * Base URL for the API
 * @constant {string}
 */
const API_URL = "http://localhost:8080";

/**
 * Fetch data from the API
 * @param {string} url - The endpoint URL
 * @param {Object} requestOptions - Fetch request options
 * @returns {Promise<Object>} - The response data as a JSON object
 * @throws {Error} - Throws an error if the network response is not ok
 */
const fetchData = (url, requestOptions) => {
    const apiUrl = `${API_URL}${url}`;

    return fetch(apiUrl, requestOptions)
        .then((response) => {
            if (!response.ok) {
                return response.json().then(data => {
                    const error = new Error();
                    error.data = data;
                    throw error;
                });
            }

            if (requestOptions.method !== 'DELETE') {
                return response.json();
            }
        })
        .catch((error) => {
            throw error;
        });
};

/**
 * GET request to fetch a page from the API
 * @param {string} url - The endpoint URL
 * @returns {Promise<Object>} - The response data as a JSON object
 */
export const apiGetPage = (url) => {
    const apiUrl = `${url}`;
    const requestOptions = {
        method: "GET",
    };

    return fetchData(apiUrl, requestOptions);
};

/**
 * GET request to fetch data from the API with query parameters
 * @param {string} url - The endpoint URL
 * @param {Object} params - The query parameters
 * @returns {Promise<Object>} - The response data as a JSON object
 */
export const apiGet = (url, params) => {
    const filteredParams = Object.fromEntries(
        Object.entries(params || {}).filter(([_, value]) => value != null)
    );

    const apiUrl = `${url}?${new URLSearchParams(filteredParams)}`;
    const requestOptions = {
        method: "GET",
    };

    return fetchData(apiUrl, requestOptions);
};

/**
 * POST request to send data to the API
 * @param {string} url - The endpoint URL
 * @param {Object} data - The data to be sent
 * @returns {Promise<Object>} - The response data as a JSON object
 */
export const apiPost = (url, data) => {
    const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
    };

    return fetchData(url, requestOptions);
};

/**
 * PUT request to update data on the API
 * @param {string} url - The endpoint URL
 * @param {Object} data - The data to be updated
 * @returns {Promise<Object>} - The response data as a JSON object
 */
export const apiPut = (url, data) => {
    const requestOptions = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
    };

    return fetchData(url, requestOptions);
};

/**
 * DELETE request to remove data from the API
 * @param {string} url - The endpoint URL
 * @returns {Promise<void>} - No return value
 */
export const apiDelete = (url) => {
    const requestOptions = {
        method: "DELETE",
    };

    return fetchData(url, requestOptions);
};
