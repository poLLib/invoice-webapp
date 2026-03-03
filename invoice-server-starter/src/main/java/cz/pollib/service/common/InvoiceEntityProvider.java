package cz.pollib.service.common;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEntityProvider {

    private final InvoiceRepository invoiceRepository;

    public InvoiceEntityProvider(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Attempts to fetch an invoice.
     * In case an invoice with the passed [id] doesn't exist a [{@link EntityNotFoundException}] is thrown.
     *
     * @param id Invoice to fetch
     * @return Fetched entity
     * @throws EntityNotFoundException In case the invoice with the passed [id] isn't found
     */
    public InvoiceEntity getEntity(long id) {
        return invoiceRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new);
    }
}
