import {callBackendApi} from "../middleware/api";


export default class ContactsApi {
    static loadContacts = (page, size, successCallback, failCallback) => {
        const req = {
            endpoint: `/contact/all/page/${page}/size/${size}`,
            method: 'get'
        };

        callBackendApi(req.endpoint, req.method, null)
            .then(
                (response) => {
                    successCallback(response)
                },
                error => {
                    failCallback(error);
                }
            )
    }

    static searchContacts = (name, page, size, successCallback, failCallback) => {
        const req = {
            endpoint: `/contact/search-by-name/${name}/page/${page}/size/${size}`,
            method: 'get'
        };

        callBackendApi(req.endpoint, req.method, null)
            .then(
                (response) => {
                    successCallback(response)
                },
                error => {
                    failCallback(error);
                }
            )
    }
}