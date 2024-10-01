package cz.pollib.dto.mapper;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.entity.Invoice;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23 (Eclipse Adoptium)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO toDTO(Invoice source) {
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
        invoiceDTO.setBuyer( personMapper.toDTO( source.getBuyer() ) );
        invoiceDTO.setSeller( personMapper.toDTO( source.getSeller() ) );

        return invoiceDTO;
    }

    @Override
    public Invoice toEntity(InvoiceDTO source) {
        if ( source == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( source.getId() );
        invoice.setInvoiceNumber( source.getInvoiceNumber() );
        invoice.setIssued( source.getIssued() );
        invoice.setDueDate( source.getDueDate() );
        invoice.setProduct( source.getProduct() );
        invoice.setPrice( source.getPrice() );
        invoice.setVat( source.getVat() );
        invoice.setNote( source.getNote() );
        invoice.setBuyer( personMapper.toEntity( source.getBuyer() ) );
        invoice.setSeller( personMapper.toEntity( source.getSeller() ) );

        return invoice;
    }

    @Override
    public void updateEntity(InvoiceDTO source, Invoice target) {
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
    }
}
