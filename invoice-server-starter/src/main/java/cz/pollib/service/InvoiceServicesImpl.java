package cz.pollib.service;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.specification.InvoiceSpecification;
import cz.pollib.service.common.InvoiceEntityProvider;
import cz.pollib.service.mapper.InvoiceMapper;
import cz.pollib.service.model.CreateInvoiceRequest;
import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.service.model.UpdateInvoiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServicesImpl implements InvoiceServices {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceEntityProvider invoiceEntityProvider;

    private final InvoiceMapper invoiceMapper;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServicesImpl.class);

    public InvoiceServicesImpl(InvoiceRepository invoiceRepository, InvoiceEntityProvider invoiceEntityProvider, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceEntityProvider = invoiceEntityProvider;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceResponse createInvoice(CreateInvoiceRequest request) {
        InvoiceEntity entity = invoiceMapper.toEntity(request);
        invoiceRepository.saveAndFlush(entity);
        logger.info("Invoice was created id:{}", entity.getId());

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

        logger.info("Search found {} invoices", totalElements);

        return new InvoicePageResponse(invoices, totalElements);
    }

    @Override
    public InvoiceResponse getInvoice(Long id) {
        return invoiceMapper.toModel(invoiceEntityProvider.getEntity(id));
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(invoiceEntityProvider.getEntity(id));
        logger.info("Invoice was deleted id:{}", id);
    }

    public InvoiceResponse updateInvoice(Long id, UpdateInvoiceRequest request) {
        InvoiceEntity fetchedInvoice = invoiceEntityProvider.getEntity(id);
        InvoiceEntity updatedInvoice = invoiceMapper.merge(fetchedInvoice, request);

        invoiceRepository.saveAndFlush(updatedInvoice);
        logger.info("Invoice updated id:{}", id);

        return invoiceMapper.toModel(updatedInvoice);
    }

    @Override
    public InvoiceStatisticsResponse getInvoiceStatistics() {
        return new InvoiceStatisticsResponse(
                invoiceRepository.sumPriceOfCurrentYear(),
                invoiceRepository.sumAllPrice(),
                invoiceRepository.countAll()
        );
    }
}
