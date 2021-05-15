package com.cac.mycontacts.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Contacts", indexes = @Index(columnList = "name"))
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="avatarUrl")
    private String avatarUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(name, contact.name) &&
                Objects.equals(avatarUrl, contact.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatarUrl);
    }
}
