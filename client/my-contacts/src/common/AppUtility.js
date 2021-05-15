import {notification} from "antd";

export default class AppUtility {

    static showErrorText = function (message, description) {
        notification.error({
            message: message,
            description: description
        });
    };

    static showSucceedMessage = function (message, description) {
        notification.success({
            message: message,
            description: description
        });
    };
}