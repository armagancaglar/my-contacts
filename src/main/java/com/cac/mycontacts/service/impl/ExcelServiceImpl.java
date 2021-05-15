package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.service.ExcelService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService {
    private final ResourceLoader resourceLoader;
    private final ContactService contactService;
    Logger LOGGER = LogManager.getLogger(ExcelServiceImpl.class);

    @Autowired
    public ExcelServiceImpl(ResourceLoader resourceLoader, ContactService contactService) {
        this.resourceLoader = resourceLoader;
        this.contactService = contactService;
    }


    @Override
    public void readCsvFileAndImportContacts() throws IOException {
        Resource resource = resourceLoader.getResource("file:people.csv");
        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();

            Reader reader = new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withAllowMissingColumnNames().withIgnoreEmptyLines());
            parser.iterator().next();
            contactService.importContacts(parser);
        } else {
            LOGGER.error("people.csv file not exist!");
        }
    }
}
