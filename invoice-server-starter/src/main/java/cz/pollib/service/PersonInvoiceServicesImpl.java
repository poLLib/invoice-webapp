package cz.pollib.service;

import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.service.mapper.InvoiceMapper;
import cz.pollib.service.model.InvoiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonInvoiceServicesImpl implements PersonInvoiceServices {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    public PersonInvoiceServicesImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public List<InvoiceResponse> getInvoicesBySeller(String identificationNumber) {
        return invoiceRepository
                .findAll()
                .stream()
                .filter(i -> i.getSeller().getIdentificationNumber().equals(identificationNumber))
                .map(invoiceMapper::toModel)
                .toList();
    }

    @Override
    public List<InvoiceResponse> getInvoicesByBuyer(String identificationNumber) {
        return invoiceRepository.findAll()
                .stream()
                .filter(i -> i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                .map(invoiceMapper::toModel)
                .toList();
    }
}
