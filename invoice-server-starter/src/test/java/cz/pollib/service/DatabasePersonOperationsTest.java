package cz.pollib.service;

import cz.pollib.constant.Countries;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.Person;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabasePersonOperationsTest {

    @Mock
    PersonMapper personMapper;

    @Mock
    PersonRepository personRepository;

    @Mock
    InvoiceMapper invoiceMapper;

    @Mock
    InvoiceRepository invoiceRepository;

    PersonOperations database;

    @BeforeEach
    void beforeEach() {
        database = new DatabasePersonOperations(personMapper, personRepository, invoiceRepository, invoiceMapper);
    }

    @Nested
    class TestingAddPerson {

        @Test
        @DisplayName("Should add person into database")
        void shouldAddPerson() {

            PersonDTO personDTO = new PersonDTO();
            personDTO.setName("John Doe");
            personDTO.setIdentificationNumber("123456789");
            personDTO.setTaxNumber("987654321");
            personDTO.setAccountNumber("111222333");
            personDTO.setBankCode("5555");
            personDTO.setIban("CZ111222333");
            personDTO.setTelephone("444555666");
            personDTO.setMail("john@doe");
            personDTO.setStreet("Street");
            personDTO.setZip("90009");
            personDTO.setCity("Prague");
            personDTO.setCountry(Countries.CZECHIA);

            Person person = new Person();
            person.setHidden(false);
            person.setId(1);
            person.setName("John Doe");
            person.setIdentificationNumber("123456789");
            person.setTaxNumber("987654321");
            person.setAccountNumber("111222333");
            person.setBankCode("5555");
            person.setIban("CZ111222333");
            person.setTelephone("444555666");
            person.setMail("john@doe");
            person.setStreet("Street");
            person.setZip("90009");
            person.setCity("Prague");
            person.setCountry(Countries.CZECHIA);

            when(personMapper.toEntity(any(PersonDTO.class))).thenReturn(person);
            when(personRepository.saveAndFlush(any(Person.class))).thenReturn(person);

            Person resultPerson = database.addPerson(personDTO);

            assertNotNull(resultPerson);
            assertEquals(1, resultPerson.getId());
            assertEquals(personDTO.getName(), resultPerson.getName());
            assertEquals(personDTO.getIdentificationNumber(), resultPerson.getIdentificationNumber());
            assertEquals(personDTO.getTaxNumber(), resultPerson.getTaxNumber());
            assertEquals(personDTO.getAccountNumber(), resultPerson.getAccountNumber());
            assertEquals(personDTO.getBankCode(), resultPerson.getBankCode());
            assertEquals(personDTO.getIban(), resultPerson.getIban());
            assertEquals(personDTO.getTelephone(), resultPerson.getTelephone());
            assertEquals(personDTO.getMail(), resultPerson.getMail());
            assertEquals(personDTO.getStreet(), resultPerson.getStreet());
            assertEquals(personDTO.getZip(), resultPerson.getZip());
            assertEquals(personDTO.getCity(), resultPerson.getCity());
            assertEquals(personDTO.getCountry(), resultPerson.getCountry());
            assertFalse(resultPerson.isHidden());

            verify(personMapper).toEntity(personDTO);
            verify(personRepository).saveAndFlush(person);
        }
    }
}