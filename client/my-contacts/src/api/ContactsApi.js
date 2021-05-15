import {callBackendApi} from "../middleware/api";


export default class ContactsApi {
    static searchContacts = (name, page, size, successCallback, failCallback) => {
        const req = {
            endpoint: `/contact/search?name=${name}&page=${page}&size=${size}`,
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