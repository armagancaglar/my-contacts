package com.cac.mycontacts.controller;

import com.cac.mycontacts.dto.ContactRestResourceResponse;
import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.service.ContactService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact")
@PropertySource("file:mycontacts.properties")
@CrossOrigin("${client.baseurl}")
public class ContactController extends AbstractContactRestService {
    final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/search")
    public ContactRestResourceResponse searchContacts(@RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size){
        Page<Contact> contacts;
        if(StringUtils.isBlank(name)) {
            contacts = contactService.getContactsWithPagination(PageRequest.of(page, size));
        } else {
            contacts = contactService.getContactsByNameContaining(name, PageRequest.of(page, size));
        }
        return createSuccessResponse(contacts);
    }
}
