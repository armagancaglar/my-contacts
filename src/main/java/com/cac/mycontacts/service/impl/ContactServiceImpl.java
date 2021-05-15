package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.dto.ContactDto;
import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.repository.ContactRepository;
import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.util.UtilityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("ContactService")
public class ContactServiceImpl implements ContactService {
    final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact save(ContactDto contactDto) {
        if(null != contactDto) {
            Contact contact = new Contact();
            contact.setName(contactDto.getName());
            contact.setAvatarUrl(contactDto.getAvatarUrl());
            return saveContact(contact);
        }
        return null;
    }

    @Override
    public boolean isValidContact(ContactDto contactDto) {
        return !StringUtils.isBlank(contactDto.getName()) && UtilityHelper.isValidUrl(contactDto.getAvatarUrl());
    }

    @Override
    public boolean isContactsLoadedBefore() {
        return getContactsCount() > 0;
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
        return contactRepository.findByNameIgnoreCaseContainingOrderByIdAsc(name, pageable);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
