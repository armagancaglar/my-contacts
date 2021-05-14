package com.cac.mycontacts.configuration;

import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.service.ExcelService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class LoadContacts {
    private static final Logger log = LoggerFactory.getLogger(LoadContacts.class);

    private final ExcelService excelService;
    private final ContactService contactService;

    @Autowired
    public LoadContacts(ExcelService excelService, ContactService contactService) {
        this.excelService = excelService;
        this.contactService = contactService;
    }

    @Bean
    void initializeContacts() {
        if(!contactService.isContactsLoadedBefore()) {
            try {
                excelService.importContacts();
            } catch (IOException e) {
                log.error(ExceptionUtils.getRootCauseMessage(e));
            }
        }
    }
}
