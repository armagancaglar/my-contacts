package com.cac.mycontacts.controller;

import com.cac.mycontacts.dto.RestResourceResponse;
import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.service.ContactService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact")
public class ContactController extends AbstractRestService {
    final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all/page/{page}/size/{size}")
    public RestResourceResponse getContactsWithPagination(@PathVariable("page") int page, @PathVariable("size") int size){
        Page<Contact> contacts = contactService.getContactsWithPagination(PageRequest.of(page, size));
        return createSuccessResponse(contacts);
    }

    @GetMapping("/search-by-name/{name}/page/{page}/size/{size}")
    public RestResourceResponse getContactsByContactName(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size){
        Page<Contact> contacts;
        if(StringUtils.isBlank(name)) {
            contacts = contactService.getContactsWithPagination(PageRequest.of(page, size));
        } else {
            contacts = contactService.getContactsByNameContaining(name, PageRequest.of(page, size));
        }
        return createSuccessResponse(contacts);
    }
}
