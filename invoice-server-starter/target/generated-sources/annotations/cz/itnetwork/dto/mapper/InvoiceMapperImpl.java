package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDTO toDTO(InvoiceEntity source) {
        if ( source == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setId( source.getId() );
        invoiceDTO.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceDTO.setIssued( source.getIssued() );
        invoiceDTO.setDueDate( source.getDueDate() );
        invoiceDTO.setProduct( source.getProduct() );
        invoiceDTO.setPrice( source.getPrice() );
        invoiceDTO.setVat( source.getVat() );
        invoiceDTO.setNote( source.getNote() );
        invoiceDTO.setBuyer( personEntityToPersonDTO( source.getBuyer() ) );
        invoiceDTO.setSeller( personEntityToPersonDTO( source.getSeller() ) );

        return invoiceDTO;
    }

    @Override
    public InvoiceEntity toEntity(InvoiceDTO source) {
        if ( source == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId( source.getId() );
        invoiceEntity.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceEntity.setIssued( source.getIssued() );
        invoiceEntity.setDueDate( source.getDueDate() );
        invoiceEntity.setProduct( source.getProduct() );
        invoiceEntity.setPrice( source.getPrice() );
        invoiceEntity.setVat( source.getVat() );
        invoiceEntity.setNote( source.getNote() );
        invoiceEntity.setBuyer( personDTOToPersonEntity( source.getBuyer() ) );
        invoiceEntity.setSeller( personDTOToPersonEntity( source.getSeller() ) );

        return invoiceEntity;
    }

    @Override
    public void updateEntity(InvoiceDTO source, InvoiceEntity target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setInvoiceNumber( source.getInvoiceNumber() );
        target.setIssued( source.getIssued() );
        target.setDueDate( source.getDueDate() );
        target.setProduct( source.getProduct() );
        target.setPrice( source.getPrice() );
        target.setVat( source.getVat() );
        target.setNote( source.getNote() );
        if ( source.getBuyer() != null ) {
            if ( target.getBuyer() == null ) {
                target.setBuyer( new PersonEntity() );
            }
            personDTOToPersonEntity1( source.getBuyer(), target.getBuyer() );
        }
        else {
            target.setBuyer( null );
        }
        if ( source.getSeller() != null ) {
            if ( target.getSeller() == null ) {
                target.setSeller( new PersonEntity() );
            }
            personDTOToPersonEntity1( source.getSeller(), target.getSeller() );
        }
        else {
            target.setSeller( null );
        }
    }

    @Override
    public void updateDTO(InvoiceEntity source, InvoiceDTO target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setInvoiceNumber( source.getInvoiceNumber() );
        target.setIssued( source.getIssued() );
        target.setDueDate( source.getDueDate() );
        target.setProduct( source.getProduct() );
        target.setPrice( source.getPrice() );
        target.setVat( source.getVat() );
        target.setNote( source.getNote() );
        if ( source.getBuyer() != null ) {
            if ( target.getBuyer() == null ) {
                target.setBuyer( new PersonDTO() );
            }
            personEntityToPersonDTO1( source.getBuyer(), target.getBuyer() );
        }
        else {
            target.setBuyer( null );
        }
        if ( source.getSeller() != null ) {
            if ( target.getSeller() == null ) {
                target.setSeller( new PersonDTO() );
            }
            personEntityToPersonDTO1( source.getSeller(), target.getSeller() );
        }
        else {
            target.setSeller( null );
        }
    }

    protected PersonDTO personEntityToPersonDTO(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( personEntity.getId() );
        personDTO.setName( personEntity.getName() );
        personDTO.setIdentificationNumber( personEntity.getIdentificationNumber() );
        personDTO.setTaxNumber( personEntity.getTaxNumber() );
        personDTO.setAccountNumber( personEntity.getAccountNumber() );
        personDTO.setBankCode( personEntity.getBankCode() );
        personDTO.setIban( personEntity.getIban() );
        personDTO.setTelephone( personEntity.getTelephone() );
        personDTO.setMail( personEntity.getMail() );
        personDTO.setStreet( personEntity.getStreet() );
        personDTO.setZip( personEntity.getZip() );
        personDTO.setCity( personEntity.getCity() );
        personDTO.setCountry( personEntity.getCountry() );
        personDTO.setNote( personEntity.getNote() );

        return personDTO;
    }

    protected PersonEntity personDTOToPersonEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        if ( personDTO.getId() != null ) {
            personEntity.setId( personDTO.getId() );
        }
        personEntity.setName( personDTO.getName() );
        personEntity.setIdentificationNumber( personDTO.getIdentificationNumber() );
        personEntity.setTaxNumber( personDTO.getTaxNumber() );
        personEntity.setAccountNumber( personDTO.getAccountNumber() );
        personEntity.setBankCode( personDTO.getBankCode() );
        personEntity.setIban( personDTO.getIban() );
        personEntity.setTelephone( personDTO.getTelephone() );
        personEntity.setMail( personDTO.getMail() );
        personEntity.setStreet( personDTO.getStreet() );
        personEntity.setZip( personDTO.getZip() );
        personEntity.setCity( personDTO.getCity() );
        personEntity.setCountry( personDTO.getCountry() );
        personEntity.setNote( personDTO.getNote() );

        return personEntity;
    }

    protected void personDTOToPersonEntity1(PersonDTO personDTO, PersonEntity mappingTarget) {
        if ( personDTO == null ) {
            return;
        }

        if ( personDTO.getId() != null ) {
            mappingTarget.setId( personDTO.getId() );
        }
        mappingTarget.setName( personDTO.getName() );
        mappingTarget.setIdentificationNumber( personDTO.getIdentificationNumber() );
        mappingTarget.setTaxNumber( personDTO.getTaxNumber() );
        mappingTarget.setAccountNumber( personDTO.getAccountNumber() );
        mappingTarget.setBankCode( personDTO.getBankCode() );
        mappingTarget.setIban( personDTO.getIban() );
        mappingTarget.setTelephone( personDTO.getTelephone() );
        mappingTarget.setMail( personDTO.getMail() );
        mappingTarget.setStreet( personDTO.getStreet() );
        mappingTarget.setZip( personDTO.getZip() );
        mappingTarget.setCity( personDTO.getCity() );
        mappingTarget.setCountry( personDTO.getCountry() );
        mappingTarget.setNote( personDTO.getNote() );
    }

    protected void personEntityToPersonDTO1(PersonEntity personEntity, PersonDTO mappingTarget) {
        if ( personEntity == null ) {
            return;
        }

        mappingTarget.setId( personEntity.getId() );
        mappingTarget.setName( personEntity.getName() );
        mappingTarget.setIdentificationNumber( personEntity.getIdentificationNumber() );
        mappingTarget.setTaxNumber( personEntity.getTaxNumber() );
        mappingTarget.setAccountNumber( personEntity.getAccountNumber() );
        mappingTarget.setBankCode( personEntity.getBankCode() );
        mappingTarget.setIban( personEntity.getIban() );
        mappingTarget.setTelephone( personEntity.getTelephone() );
        mappingTarget.setMail( personEntity.getMail() );
        mappingTarget.setStreet( personEntity.getStreet() );
        mappingTarget.setZip( personEntity.getZip() );
        mappingTarget.setCity( personEntity.getCity() );
        mappingTarget.setCountry( personEntity.getCountry() );
        mappingTarget.setNote( personEntity.getNote() );
    }
}
