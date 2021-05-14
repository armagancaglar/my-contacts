package com.cac.mycontacts.repository;

import com.cac.mycontacts.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("ContactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAll(Pageable pageable);
    Page<Contact> findByNameContainingOrderByIdAsc(String name, Pageable pageable);
}
