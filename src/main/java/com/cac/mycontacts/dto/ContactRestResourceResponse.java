package com.cac.mycontacts.dto;

import com.cac.mycontacts.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRestResourceResponse {
    private String errorCode;
    private String errorMessage;
    private Page<Contact> data;
}
