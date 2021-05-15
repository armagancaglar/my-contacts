package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.dto.ContactDto;
import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.service.ExcelService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService {
    final ResourceLoader resourceLoader;
    final ContactService contactService;

    @Autowired
    public ExcelServiceImpl(ResourceLoader resourceLoader, ContactService contactService) {
        this.resourceLoader = resourceLoader;
        this.contactService = contactService;
    }

    @Override
    public void importContacts() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:people.csv");
        if(resource.exists()) {
            InputStream inputStream = resource.getInputStream();

            Reader reader = new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withAllowMissingColumnNames().withIgnoreEmptyLines());

            for (final CSVRecord row : parser){
                if(row.getRecordNumber() != 1) {
                    ContactDto contactDto = new ContactDto(row.get(0), row.get(1));
                    if(contactService.isValidContact(contactDto)) {
                        contactService.save(contactDto);
                    }
                }
            }
        }
    }
}
