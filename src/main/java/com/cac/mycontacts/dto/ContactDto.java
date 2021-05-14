package com.cac.mycontacts.dto;

import com.cac.mycontacts.entity.Contact;

public class ContactDto {
    private Long id;
    private String name;
    private String avatarUrl;

    public ContactDto() {
    }

    public ContactDto(Long id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public ContactDto(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.avatarUrl = contact.getAvatarUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
