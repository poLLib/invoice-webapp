package cz.pollib.service.mapper;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.service.common.PersonEntityProvider;
import cz.pollib.service.model.InvoiceRequest;
import cz.pollib.service.model.InvoiceResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper to:
 * - convert from request to new entity {@link InvoiceRequest} and {@link InvoiceEntity}.
 * - convert merge from request to entity {@link InvoiceRequest} and {@link InvoiceEntity}.
 * - convert from entity to response {@link InvoiceEntity} and {@link InvoiceResponse}.
 */
@Component
public class InvoiceMapper {

    private final PersonEntityProvider personEntityProvider;

    private final PersonMapper personMapper;

    public InvoiceMapper(PersonEntityProvider personEntityProvider, PersonMapper personMapper) {
        this.personEntityProvider = personEntityProvider;
        this.personMapper = personMapper;
    }

    /**
     * Converts an {@link InvoiceRequest} to an {@link InvoiceEntity}.
     *
     * @param model the source {@link InvoiceRequest}
     * @return the converted {@link InvoiceEntity}
     */
    public InvoiceEntity toEntity(InvoiceRequest model) {
        return new InvoiceEntity(
                model.getInvoiceNumber(),
                model.getIssued(),
                model.getDueDate(),
                model.getProduct(),
                model.getPrice(),
                model.getVat(),
                model.getNote(),
                personEntityProvider.getEntity(model.getBuyerId()),
                personEntityProvider.getEntity(model.getSellerId())
        );
    }

    /**
     * Updates an existing {@link InvoiceEntity} with values from {@link InvoiceRequest}.
     *
     * @param model  the source {@link InvoiceRequest}
     * @param entity the target {@link InvoiceEntity} to be updated
     */
    public InvoiceEntity merge(InvoiceEntity entity, InvoiceRequest model) {
        entity.setInvoiceNumber(model.getInvoiceNumber());
        entity.setIssued(model.getIssued());
        entity.setDueDate(model.getDueDate());
        entity.setProduct(model.getProduct());
        entity.setPrice(model.getPrice());
        entity.setVat(model.getVat());
        entity.setNote(model.getNote());
        entity.setBuyer(personEntityProvider.getEntity(model.getBuyerId()));
        entity.setSeller(personEntityProvider.getEntity(model.getSellerId()));

        return entity;
    }

    /**
     * Converts a {@link InvoiceEntity} to a {@link InvoiceResponse}.
     *
     * @param entity the entity {@link InvoiceEntity}
     * @return the converted {@link InvoiceResponse}
     */
    public InvoiceResponse toModel(InvoiceEntity entity) {
        return new InvoiceResponse(
                entity.getId(),
                entity.getInvoiceNumber(),
                entity.getIssued(),
                entity.getDueDate(),
                entity.getProduct(),
                entity.getPrice(),
                entity.getVat(),
                entity.getNote(),
                personMapper.toModel(entity.getBuyer()),
                personMapper.toModel(entity.getSeller())
        );
    }
}
