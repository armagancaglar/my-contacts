package com.cac.mycontacts.controller;

import com.cac.mycontacts.dto.ContactRestResourceResponse;
import com.cac.mycontacts.entity.Contact;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;

public class AbstractContactRestService {
    protected ContactRestResourceResponse createSuccessResponse(Page<Contact> data) {
        return new ContactRestResourceResponse(null, null, data);
    }

    protected ContactRestResourceResponse createErrorResponse(String errorCode, String errorMessage, Page<Contact> data) {
        return new ContactRestResourceResponse(errorCode, errorMessage, data);
    }

    protected ContactRestResourceResponse createErrorResponse(String loggerMessage, Logger logger, Exception e) {
        logger.error(String.format("%s : %s", loggerMessage, ExceptionUtils.getRootCauseMessage(e)), e);
        return createErrorResponse(e.getClass().getSimpleName(), e.getMessage() != null ? e.getMessage() : ExceptionUtils.getRootCauseMessage(e), null);
    }

    protected ContactRestResourceResponse createErrorResponse(String loggerMessage, Logger logger) {
        logger.error(loggerMessage);
        return createErrorResponse("500", loggerMessage, null);
    }
}
