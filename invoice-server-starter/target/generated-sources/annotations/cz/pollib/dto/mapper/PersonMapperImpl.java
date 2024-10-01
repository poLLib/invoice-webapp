package cz.pollib.dto.mapper;

import cz.pollib.dto.PersonDTO;
import cz.pollib.entity.Person;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23 (Eclipse Adoptium)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toEntity(PersonDTO source) {
        if ( source == null ) {
            return null;
        }

        Person person = new Person();

        if ( source.getId() != null ) {
            person.setId( source.getId() );
        }
        person.setName( source.getName() );
        person.setIdentificationNumber( source.getIdentificationNumber() );
        person.setTaxNumber( source.getTaxNumber() );
        person.setAccountNumber( source.getAccountNumber() );
        person.setBankCode( source.getBankCode() );
        person.setIban( source.getIban() );
        person.setTelephone( source.getTelephone() );
        person.setMail( source.getMail() );
        person.setStreet( source.getStreet() );
        person.setZip( source.getZip() );
        person.setCity( source.getCity() );
        person.setCountry( source.getCountry() );
        person.setNote( source.getNote() );

        return person;
    }

    @Override
    public PersonDTO toDTO(Person source) {
        if ( source == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( source.getId() );
        personDTO.setName( source.getName() );
        personDTO.setIdentificationNumber( source.getIdentificationNumber() );
        personDTO.setTaxNumber( source.getTaxNumber() );
        personDTO.setAccountNumber( source.getAccountNumber() );
        personDTO.setBankCode( source.getBankCode() );
        personDTO.setIban( source.getIban() );
        personDTO.setTelephone( source.getTelephone() );
        personDTO.setMail( source.getMail() );
        personDTO.setStreet( source.getStreet() );
        personDTO.setZip( source.getZip() );
        personDTO.setCity( source.getCity() );
        personDTO.setCountry( source.getCountry() );
        personDTO.setNote( source.getNote() );

        return personDTO;
    }

    @Override
    public void updateDTO(Person source, PersonDTO target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setName( source.getName() );
        target.setIdentificationNumber( source.getIdentificationNumber() );
        target.setTaxNumber( source.getTaxNumber() );
        target.setAccountNumber( source.getAccountNumber() );
        target.setBankCode( source.getBankCode() );
        target.setIban( source.getIban() );
        target.setTelephone( source.getTelephone() );
        target.setMail( source.getMail() );
        target.setStreet( source.getStreet() );
        target.setZip( source.getZip() );
        target.setCity( source.getCity() );
        target.setCountry( source.getCountry() );
        target.setNote( source.getNote() );
    }
}
