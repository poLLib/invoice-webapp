package cz.pollib.service;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.entity.Invoice;
import cz.pollib.entity.Person;
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

    public DatabaseInvoiceOperations(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, PersonRepository personRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.personRepository = personRepository;
    }

    @Override
    public Invoice createInvoice(InvoiceDTO request) {
        Invoice entity = invoiceMapper.toEntity(request);
        entity.setBuyer(personRepository.getReferenceById(request.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(request.getSeller().getId()));
        invoiceRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public InvoicePageDTO searchInvoices(InvoiceFilter invoiceFilter, int page) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        Long totalElements = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .getTotalElements();

        List<InvoiceDTO> invoices = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .toList();

        return new InvoicePageDTO(invoices, totalElements);
    }

    @Override
    public Invoice detailInvoice(Long id) {
        return invoiceRepository.getReferenceById(id);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
    }

    @Override
    public Invoice editInvoice(Long id, InvoiceDTO request) {
        Invoice existingInvoice = fetchInvoiceById(id);
        invoiceMapper.updateEntity(request, existingInvoice);

        Person seller = personRepository.getReferenceById(request.getSeller().getId());
        Person buyer = personRepository.getReferenceById(request.getBuyer().getId());

        existingInvoice.setSeller(seller);
        existingInvoice.setBuyer(buyer);
        existingInvoice.setId(id);

        invoiceRepository.saveAndFlush(existingInvoice);
        return existingInvoice;
    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceRepository.getStats();
    }

    // region: Private methods

    /**
     * Attempts to fetch an invoice.
     * In case an invoice with the passed [id] doesn't exist a [{@link EntityNotFoundException}] is thrown.
     *
     * @param id Invoice to fetch
     * @return Fetched entity
     * @throws EntityNotFoundException In case the invoice with the passed [id] isn't found
     */
    private Invoice fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
