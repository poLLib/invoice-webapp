package cz.pollib.service;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.specification.InvoiceSpecification;
import cz.pollib.service.common.InvoiceEntityProvider;
import cz.pollib.service.mapper.InvoiceMapper;
import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceRequest;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServicesImpl implements InvoiceServices {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceEntityProvider invoiceEntityProvider;

    private final InvoiceMapper invoiceMapper;

    public InvoiceServicesImpl(InvoiceRepository invoiceRepository, InvoiceEntityProvider invoiceEntityProvider, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceEntityProvider = invoiceEntityProvider;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceResponse createInvoice(InvoiceRequest request) {
        InvoiceEntity entity = invoiceMapper.toEntity(request);
        invoiceRepository.saveAndFlush(entity);

        return invoiceMapper.toModel(entity);
    }

    @Override
    public InvoicePageResponse searchInvoices(InvoiceFilter invoiceFilter, int page) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        Long totalElements = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .getTotalElements();

        List<InvoiceResponse> invoices = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(page, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toModel)
                .toList();

        return new InvoicePageResponse(invoices, totalElements);
    }

    @Override
    public InvoiceResponse getInvoice(Long id) {
        return invoiceMapper.toModel(invoiceEntityProvider.getEntity(id));
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(invoiceEntityProvider.getEntity(id));
    }

    public InvoiceResponse updateInvoice(Long id, InvoiceRequest request) {
        InvoiceEntity fetchedInvoice = invoiceEntityProvider.getEntity(id);
        InvoiceEntity updatedInvoice = invoiceMapper.merge(fetchedInvoice, request);

        invoiceRepository.saveAndFlush(updatedInvoice);

        return invoiceMapper.toModel(updatedInvoice);
    }

    @Override
    public InvoiceStatisticsResponse getInvoiceStatistics() {
        return invoiceRepository.getStats();
    }

}
