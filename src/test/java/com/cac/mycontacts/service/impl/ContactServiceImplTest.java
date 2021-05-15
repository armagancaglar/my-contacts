package com.cac.mycontacts.service.impl;

import com.cac.mycontacts.dto.ContactDto;
import com.cac.mycontacts.entity.Contact;
import com.cac.mycontacts.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {
    @Mock
    ContactRepository contactRepository;
    @InjectMocks
    ContactServiceImpl contactServiceImpl;

    /*
    @Captor
    ArgumentCaptor<Contact> contactCaptor;
     */
    @Test
    public void should_find_contacts_with_pagination(){
        // ARRANGE
        Contact contact = new Contact();
        contact.setId(10L);
        contact.setName("John");
        when(contactRepository.findAll(Pageable.unpaged()))
                .thenReturn(new PageImpl(Collections.singletonList(contact)));
        // ACT
        Page<Contact> results = contactServiceImpl.getContactsWithPagination(Pageable.unpaged());

        // ASSERT
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
        assertThat(results.getContent().get(0).getName()).isEqualTo("John");
        assertThat(results.getContent().get(0).getId()).isEqualTo(10L);
    }

    @Test
    public void should_find_contacts_with_name(){
        // ARRANGE
        Contact contact = new Contact();
        contact.setId(10L);
        contact.setName("John");
        when(contactRepository.findByNameIgnoreCaseContainingOrderByIdAsc("John", Pageable.unpaged()))
                .thenReturn(new PageImpl(Collections.singletonList(contact)));
        // ACT
        Page<Contact> results = contactServiceImpl.getContactsByNameContaining("John", Pageable.unpaged());

        // ASSERT
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
        assertThat(results.getContent().get(0).getName()).isEqualTo("John");
        assertThat(results.getContent().get(0).getId()).isEqualTo(10L);
    }

    @Test
    public void should_save_successfully() {
        //ARRANGE
        ContactDto contactDto = new ContactDto();
        contactDto.setName("John");

        Contact contact = new Contact();
        contact.setName("John");

        when(contactRepository.save(contact)).thenReturn(contact);

        //ACT
        Contact savedContact = contactServiceImpl.save(contactDto);

        //ASSERT
        verify(contactRepository).save(contact);
        assertThat(savedContact.getName()).isEqualTo("John");
    }

    @Test
    public void should_validate_valid_contact() {
        // ARRANGE
        ContactDto validDto = new ContactDto();
        validDto.setName("John");
        validDto.setAvatarUrl("https://google.com");

        // ACT/ASSERT
        assertThat(contactServiceImpl.isValidContact(validDto)).isTrue();
    }

    @Test
    public void should_not_validate_invalid_contact() {
        // ARRANGE
        ContactDto invalidDto = new ContactDto();
        invalidDto.setName("John");
        invalidDto.setAvatarUrl("www.google.com");

        // ACT/ASSERT
        assertThat(contactServiceImpl.isValidContact(invalidDto)).isFalse();
    }

    @Test
    public void should_check_contacts_are_loaded(){
        // ARRANGE
        when(contactRepository.count()).thenReturn(10L);

        // ACT/ASSERT
        assertThat(contactServiceImpl.isContactsLoadedBefore()).isTrue();
        verify(contactRepository).count();
    }

    @Test
    public void should_check_contacts_are_not_loaded(){
        // ARRANGE
        when(contactRepository.count()).thenReturn(0L);

        // ACT/ASSERT
        assertThat(contactServiceImpl.isContactsLoadedBefore()).isFalse();
        verify(contactRepository).count();
    }

    /*
    CSVRecord is a final class. Mockito can not mock CSVRecord
    @Test
    public void should_import_contacts_from_csv_rows(){
        // ARRANGE
        CSVRecord row1 = mock(CSVRecord.class);
        when(row1.get(0)).thenReturn("Armagan");
        when(row1.get(1)).thenReturn("https://google.com");

        CSVRecord row2 = mock(CSVRecord.class);
        when(row2.get(0)).thenReturn("Cetin");
        when(row2.get(1)).thenReturn("www.google.com");

        // ACT
        contactServiceImpl.importContacts(Arrays.asList(row1, row2));

        // ASSERT
        verify(contactRepository).save(contactCaptor.capture());
        List<Contact> allValues = contactCaptor.getAllValues();
        assertThat(allValues).hasSize(1);
        assertThat(allValues.get(0).getName()).isEqualTo("Armagan");
    }
     */
}
