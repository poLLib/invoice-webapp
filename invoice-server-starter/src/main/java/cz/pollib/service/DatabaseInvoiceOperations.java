package cz.pollib.service;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import cz.pollib.entity.repository.specification.InvoiceSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseInvoiceOperations implements InvoiceOperations {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public DatabaseInvoiceOperations(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, PersonRepository personRepository, PersonMapper personMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public InvoiceEntity createInvoice(InvoiceDTO data) {
        InvoiceEntity entity = invoiceMapper.toEntity(data);
        entity.setBuyer(personRepository.getReferenceById(data.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(data.getSeller().getId()));
        invoiceRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public InvoicePageDTO getAllInvoicesPageable(InvoiceFilter invoiceFilter, int page) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        Long count = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .getTotalElements();

        List<InvoiceDTO> invoices = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .toList();

        return new InvoicePageDTO(invoices, count);
    }

    @Override
    public InvoiceEntity detailInvoice(Long id) {
        return invoiceRepository.getReferenceById(id);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
    }

    @Override
    public InvoiceEntity editInvoice(Long id, InvoiceDTO data) {
        InvoiceEntity invoice = fetchInvoiceById(id);
        invoiceMapper.updateEntity(data, invoice);

        PersonEntity seller = personRepository.getReferenceById(data.getSeller().getId());
        PersonEntity buyer = personRepository.getReferenceById(data.getBuyer().getId());

        invoice.setSeller(seller);
        invoice.setBuyer(buyer);
        invoice.setId(id);

        invoiceRepository.saveAndFlush(invoice);
        return invoice;
    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceRepository.getStats();
    }

    // region: Private methods

    /**
     * Attempts to fetch an invoice.
     * In case a invoice with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.
     *
     * @param id Invoice to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case the invoice with the passed [id] isn't found
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
