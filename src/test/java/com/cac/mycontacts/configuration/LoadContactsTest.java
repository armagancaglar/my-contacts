package com.cac.mycontacts.configuration;

import com.cac.mycontacts.service.ContactService;
import com.cac.mycontacts.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoadContactsTest {

    @Mock
    ContactService contactService;
    @Mock
    ExcelService excelService;
    @InjectMocks
    LoadContacts loadContacts;

    @Test
    public void should_import_contacts_when_first_time_initialization() throws IOException {
        // ARRANGE
        when(contactService.isContactsLoadedBefore()).thenReturn(false);

        // ACT
        loadContacts.initializeContacts();

        // ASSERT
        verify(excelService).readCsvFileAndImportContacts();
    }

    @Test
    public void should_not_import_contacts_when_initialized_before(){
        // ARRANGE
        when(contactService.isContactsLoadedBefore()).thenReturn(true);

        // ACT
        loadContacts.initializeContacts();

        // ASSERT
        verifyNoInteractions(excelService);

    }

}