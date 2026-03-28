package cz.pollib.service;

import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.service.mapper.InvoiceMapper;
import cz.pollib.service.model.InvoiceResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonInvoiceServicesImpl implements PersonInvoiceServices {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    public PersonInvoiceServicesImpl(
            InvoiceRepository invoiceRepository,
            InvoiceMapper invoiceMapper
                                    ) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    @Cacheable(
            value = "get-seller-invoices",
            key = "'seller=' + #identificationNumber"
    )
    public List<InvoiceResponse> getInvoicesBySeller(String identificationNumber) {
        return invoiceRepository
                .findAll()
                .stream()
                .filter(i -> i.getSeller()
                              .getIdentificationNumber()
                              .equals(identificationNumber))
                .map(invoiceMapper::toModel)
                .toList();
    }

    @Override
    @Cacheable(
            value = "get-buyer-invoices",
            key = "'buyer=' + #identificationNumber"
    )
    public List<InvoiceResponse> getInvoicesByBuyer(String identificationNumber) {
        return invoiceRepository.findAll()
                                .stream()
                                .filter(i -> i.getBuyer()
                                              .getIdentificationNumber()
                                              .equals(identificationNumber))
                                .map(invoiceMapper::toModel)
                                .toList();
    }
}
