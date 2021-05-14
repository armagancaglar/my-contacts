package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.repository.ContactRepository;
import com.cac.mycontacts.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DatabaseService")
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
