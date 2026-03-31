package cz.pollib.service;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.specification.InvoiceSpecification;
import cz.pollib.service.common.InvoiceEntityProvider;
import cz.pollib.service.common.PersonInvoiceCacheEvictor;
import cz.pollib.service.mapper.InvoiceMapper;
import cz.pollib.service.model.CreateInvoiceRequest;
import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.service.model.UpdateInvoiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServicesImpl implements InvoiceServices {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServicesImpl.class);
    private final InvoiceRepository invoiceRepository;
    private final InvoiceEntityProvider invoiceEntityProvider;
    private final InvoiceMapper invoiceMapper;
    private final PersonInvoiceCacheEvictor personInvoiceCacheEvictor;

    public InvoiceServicesImpl(
            InvoiceRepository invoiceRepository,
            InvoiceEntityProvider invoiceEntityProvider,
            InvoiceMapper invoiceMapper,
            PersonInvoiceCacheEvictor personInvoiceCacheEvictor
                              ) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceEntityProvider = invoiceEntityProvider;
        this.invoiceMapper = invoiceMapper;
        this.personInvoiceCacheEvictor = personInvoiceCacheEvictor;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(
                            value = {"search-invoices", "get-person-statistics", "get-invoice-statistics"},
                            allEntries = true
                    ),
                    @CacheEvict(
                            value = "get-seller-invoices",
                            key = "'seller=' + #result.seller().identificationNumber()"
                    ),
                    @CacheEvict(
                            value = "get-buyer-invoices",
                            key = "'buyer=' + #result.buyer().identificationNumber()"
                    )
            }
    )
    @Transactional
    public InvoiceResponse createInvoice(CreateInvoiceRequest request) {
        InvoiceEntity entity = invoiceMapper.toEntity(request);
        invoiceRepository.saveAndFlush(entity);
        logger.info(
                "Invoice was created id:{}",
                entity.getId()
                   );

        return invoiceMapper.toModel(entity);
    }

    @Override
    @Cacheable(
            value = "search-invoices",
            key = "#invoiceFilter.cacheKey() + '-' + #page"
    )
    @Transactional(readOnly = true)
    public InvoicePageResponse searchInvoices(
            InvoiceFilter invoiceFilter,
            int page
                                             ) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        Page<InvoiceEntity> result = invoiceRepository.findAll(
                invoiceSpecification,
                PageRequest.of(
                        page,
                        invoiceFilter.limit()
                              )
                                                              );
        List<InvoiceResponse> invoices = result.getContent()
                                               .stream()
                                               .map(invoiceMapper::toModel)
                                               .toList();

        logger.info(
                "Search found {} invoices",
                result.getTotalElements()
                   );

        return new InvoicePageResponse(
                invoices,
                result.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse getInvoice(Long id) {
        return invoiceMapper.toModel(invoiceEntityProvider.getEntity(id));
    }

    @Override
    @CacheEvict(
            value = {"search-invoices", "get-person-statistics", "get-invoice-statistics"},
            allEntries = true
    )
    @Transactional
    public void deleteInvoice(Long id) {
        InvoiceEntity invoiceEntity = invoiceEntityProvider.getEntity(id);
        invoiceRepository.delete(invoiceEntityProvider.getEntity(id));

        logger.info(
                "Invoice was deleted id:{}",
                id
                   );

        personInvoiceCacheEvictor.evictPersonInvoiceCache(invoiceEntity.getSeller()
                                                                       .getIdentificationNumber());
        personInvoiceCacheEvictor.evictPersonInvoiceCache(invoiceEntity.getBuyer()
                                                                       .getIdentificationNumber());
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(
                            value = {"search-invoices", "get-person-statistics", "get-invoice-statistics"},
                            allEntries = true
                    ),
                    @CacheEvict(
                            value = "get-seller-invoices",
                            key = "'seller=' + #result.seller().identificationNumber()"
                    ),
                    @CacheEvict(
                            value = "get-buyer-invoices",
                            key = "'buyer=' + #result.buyer().identificationNumber()"
                    )
            }
    )
    @Transactional
    public InvoiceResponse updateInvoice(
            Long id,
            UpdateInvoiceRequest request
                                        ) {
        InvoiceEntity fetchedInvoice = invoiceEntityProvider.getEntity(id);
        InvoiceEntity updatedInvoice = invoiceMapper.merge(
                fetchedInvoice,
                request
                                                          );

        invoiceRepository.saveAndFlush(updatedInvoice);
        logger.info(
                "Invoice updated id:{}",
                id
                   );

        return invoiceMapper.toModel(updatedInvoice);
    }

    @Override
    @Cacheable(value = "get-invoice-statistics")
    @Transactional(readOnly = true)
    public InvoiceStatisticsResponse getInvoiceStatistics() {
        return invoiceRepository.getStatistics();
    }
}
