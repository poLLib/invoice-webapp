/**
 * Base URL for the API
 * @constant {string}
 */
const API_URL = "http://localhost:8080";

/**
 * Get the auth token from session storage
 */
const getAuthToken = () => {
    const session = sessionStorage.getItem("session");
    if (session) {
        const parsed = JSON.parse(session);
        return parsed?.data?.token || null;
    }
    return null;
};

/**
 * Get authorization headers if token exists
 */
const getAuthHeaders = () => {
    const token = getAuthToken();
    return token ? { "Authorization": `Bearer ${token}` } : {};
};

/**
 * Fetch data from the API
 */
const fetchData = async (url, requestOptions) => {
    const apiUrl = `${API_URL}${url}`;

    const response = await fetch(apiUrl, requestOptions);

    if (!response.ok) {
        // Handle 401 - redirect to login
        if (response.status === 401) {
            sessionStorage.removeItem("session");
            window.location.href = "/login";
            throw new Error("Session expired");
        }

        const error = new Error();
        error.status = response.status;

        const text = await response.text();
        if (text) {
            try {
                error.response = JSON.parse(text);
            } catch {
                error.response = { message: text };
            }
        } else {
            error.response = { message: response.statusText || `Error ${response.status}` };
        }
        throw error;
    }

    if (requestOptions.method !== 'DELETE') {
        return response.json();
    }
};

/**
 * GET request to fetch a page from the API
 */
export const apiGetPage = (url) => {
    const requestOptions = {
        method: "GET",
        headers: { ...getAuthHeaders() },
        credentials: 'include'
    };

    return fetchData(url, requestOptions);
};

/**
 * GET request to fetch data from the API with query parameters
 */
export const apiGet = (url, params) => {
    const filteredParams = Object.fromEntries(
        Object.entries(params || {}).filter(([_, value]) => value != null)
    );

    const apiUrl = `${url}?${new URLSearchParams(filteredParams)}`;
    const requestOptions = {
        method: "GET",
        headers: { ...getAuthHeaders() },
        credentials: 'include'
    };

    return fetchData(apiUrl, requestOptions);
};

/**
 * POST request to send data to the API
 */
export const apiPost = (url, data) => {
    const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json", ...getAuthHeaders() },
        body: JSON.stringify(data),
        credentials: 'include'
    };

    return fetchData(url, requestOptions);
};

/**
 * PUT request to update data on the API
 */
export const apiPut = (url, data) => {
    const requestOptions = {
        method: "PUT",
        headers: { "Content-Type": "application/json", ...getAuthHeaders() },
        body: JSON.stringify(data),
        credentials: 'include'
    };

    return fetchData(url, requestOptions);
};

/**
 * DELETE request to remove data from the API
 */
export const apiDelete = (url) => {
    const requestOptions = {
        method: "DELETE",
        headers: { ...getAuthHeaders() },
        credentials: 'include'
    };

    return fetchData(url, requestOptions);
};
