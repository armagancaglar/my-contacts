package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.dto.ContactDto;
import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.repository.ContactRepository;
import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.service.DatabaseService;
import com.cac.mycontacts.util.UtilityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("ContactService")
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    DatabaseService databaseService;

    @Override
    public Contact save(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setAvatarUrl(contactDto.getAvatarUrl());
        return databaseService.saveContact(contact);
    }

    @Override
    public boolean isValidContact(ContactDto contactDto) {
        if(StringUtils.isBlank(contactDto.getName())) {
            return false;
        }
        if (!UtilityHelper.isValidUrl(contactDto.getAvatarUrl())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isContactsLoadedBefore() {
        if(getContactsCount() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Long getContactsCount() {
        return contactRepository.count();
    }


    @Override
    public Page<Contact> getContactsWithPagination(Pageable pageable){
        return contactRepository.findAll(pageable);
    }

    @Override
    public Page<Contact> getContactsByNameContaining(String name, Pageable pageable) {
        return contactRepository.findByNameContainingOrderByIdAsc(name, pageable);
    }

}
