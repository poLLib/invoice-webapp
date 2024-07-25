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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO data) {
        InvoiceEntity entity = invoiceMapper.toEntity(data);
        entity.setBuyer(personRepository.getReferenceById(data.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(data.getSeller().getId()));

        invoiceRepository.saveAndFlush(entity);
        return invoiceMapper.toDTO(entity);
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
    public InvoiceDTO detailInvoice(Long id) {
        return invoiceMapper.toDTO(invoiceRepository.getReferenceById(id));
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
    }

    @Override
    public InvoiceDTO editInvoice(Long id, InvoiceDTO data) {
        InvoiceEntity invoice = fetchInvoiceById(id);
        invoiceMapper.updateEntity(data, invoice);

        PersonEntity seller = personRepository.getReferenceById(data.getSeller().getId());
        PersonEntity buyer = personRepository.getReferenceById(data.getBuyer().getId());

        invoice.setSeller(seller);
        invoice.setBuyer(buyer);
        invoice.setId(id);

        invoiceRepository.saveAndFlush(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        InvoiceStatisticsDTO invoiceStatisticsDTO = new InvoiceStatisticsDTO();
        Object values = invoiceRepository.getStats();
        Object[] stat = (Object[]) values;

        if (stat[0] == null) {
            invoiceStatisticsDTO.setAllTimeSum(BigDecimal.ZERO);
        } else {
            invoiceStatisticsDTO.setAllTimeSum((BigDecimal) stat[0]);
        }

        if (stat[1] == null) {
            invoiceStatisticsDTO.setInvoicesCount(0L);
        } else {
            invoiceStatisticsDTO.setInvoicesCount((Long) stat[1]);
        }

        if (stat[2] == null) {
            invoiceStatisticsDTO.setCurrentYearSum(BigDecimal.ZERO);
        } else {
            invoiceStatisticsDTO.setCurrentYearSum((BigDecimal) stat[2]);
        }

        return invoiceStatisticsDTO;
    }

    // region: Private methods

    /**
     * <p>Attempts to fetch an invoice.</p>
     * <p>In case a invoice with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * @param id Invoice to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case the invoice with the passed [id] isn't found
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
