package com.cac.mycontacts.controller;

import com.cac.mycontacts.dto.RestResourceResponse;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class AbstractRestService {
    protected RestResourceResponse createSuccessResponse(Object data) {
        RestResourceResponse restResourceResponse = new RestResourceResponse(null, null, data);
        return restResourceResponse;
    }

    protected RestResourceResponse createErrorResponse(String errorCode, String errorMessage, Object data) {
        RestResourceResponse restResourceResponse = new RestResourceResponse(errorCode, errorMessage, data);
        return restResourceResponse;
    }

    protected RestResourceResponse createErrorResponse(String loggerMessage, Logger logger, Exception e) {
        logger.error(loggerMessage + ": " + ExceptionUtils.getRootCauseMessage(e), e);
        return createErrorResponse(e.getClass().getSimpleName(), e.getMessage() != null ? e.getMessage() : ExceptionUtils.getRootCauseMessage(e), null);
    }

    protected RestResourceResponse createErrorResponse(String loggerMessage, Logger logger) {
        logger.error(loggerMessage);
        return createErrorResponse("500", loggerMessage, null);
    }
}
