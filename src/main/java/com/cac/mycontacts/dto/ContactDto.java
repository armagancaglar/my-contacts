package com.cac.mycontacts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;
    private String name;
    private String avatarUrl;

    public ContactDto(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }
}
