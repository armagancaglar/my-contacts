import {indexOf, lowerCase} from "lodash";

// Fetches an API response and normalizes the result JSON according to schema.
// This makes every API response have the same shape, regardless of how nested it was.
export const callBackendApi = (endpoint, method, postParam) => {
    let fullUrl = process.env.REACT_APP_BASE_URL + endpoint;

    if (lowerCase(method) === 'get') {
        const paramOfTime = indexOf(fullUrl, '?') >= 0 ? '&' : '?'
        fullUrl = fullUrl + paramOfTime + 'cachedTime=' + new Date().getTime()
    }

    let fetchParam = {
        method: method
    };

    let headers = new Headers({
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Cache-Control': 'no-cache, no-store, no-transform',
        'Pragma': 'no-cache',
        'Access-Control-Allow-Origin': '*'
    });

    fetchParam = Object.assign(fetchParam, {headers: headers})

    if (postParam) {
        fetchParam = Object.assign(fetchParam, {body: JSON.stringify(postParam)})
    }

    return fetch(fullUrl, fetchParam).then(response => resolveResult(response))
}


const resolveResult = (response) => {
    if (!response.ok) {
        try {
            if (response.status === 401) {
                return Promise.reject("Client must be authenticated to access this resource.");
            } else if (response.status === 404) {
                return Promise.reject("Service is not available!");
            } else if (response.status === 405) {
                return Promise.reject("Service method is not allowed!");
            }
            return response.json().then(json => Promise.reject(json))
        } catch (e) {
            return Promise.reject("An error occurred!")
        }
    }

    return response.json().then(json => {
        return json;
    })
}