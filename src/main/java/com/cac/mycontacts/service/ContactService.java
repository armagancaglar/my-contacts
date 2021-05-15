package com.cac.mycontacts.service;

import com.cac.mycontacts.dto.ContactDto;
import com.cac.mycontacts.entity.Contact;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    Contact save(ContactDto contactDto);

    boolean isValidContact(ContactDto contactDto);

    boolean isContactsLoadedBefore();

    Long getContactsCount();

    Page<Contact> getContactsWithPagination(Pageable pageable);

    Page<Contact> getContactsByNameContaining(String name, Pageable pageable);

    Contact saveContact(Contact contact);

    void importContacts(Iterable<CSVRecord> records);
}
