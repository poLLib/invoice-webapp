package cz.pollib.service.mapper;

import cz.pollib.entity.PersonEntity;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import org.springframework.stereotype.Component;

/**
 * Mapper to:
 * - convert from request to new entity {@link CreatePersonRequest} and {@link PersonEntity}.
 * - convert merge from request to entity {@link CreatePersonRequest} and {@link PersonEntity}.
 * - convert from entity to response {@link PersonEntity} and {@link PersonResponse}.
 */
@Component
public class PersonMapper {

    /**
     * Converts a {@link CreatePersonRequest} to a {@link PersonEntity}.
     *
     * @param source the source {@link CreatePersonRequest}
     * @return the converted {@link PersonEntity}
     */
    public PersonEntity toEntity(CreatePersonRequest source) {
        return new PersonEntity(
                source.getName(),
                source.getIdentificationNumber(),
                source.getTaxNumber(),
                source.getAccountNumber(),
                source.getBankCode(),
                source.getIban(),
                source.getTelephone(),
                source.getMail(),
                source.getStreet(),
                source.getZip(),
                source.getCity(),
                source.getCountry(),
                source.getNote()
        );
    }

    /**
     * Updates an existing {@link PersonEntity} with values from {@link CreatePersonRequest}.
     *
     * @param model  the source {@link CreatePersonRequest}
     * @param entity the target {@link PersonEntity} to be updated
     */
    public PersonEntity merge(PersonEntity entity, UpdatePersonRequest model) {
        entity.setName(model.getName());
        entity.setTaxNumber(model.getTaxNumber());
        entity.setAccountNumber(model.getAccountNumber());
        entity.setBankCode(model.getBankCode());
        entity.setIban(model.getIban());
        entity.setTelephone(model.getTelephone());
        entity.setMail(model.getMail());
        entity.setStreet(model.getStreet());
        entity.setZip(model.getZip());
        entity.setCity(model.getCity());
        entity.setCountry(model.getCountry());
        entity.setNote(model.getNote());

        return entity;
    }

    /**
     * Converts a {@link PersonEntity} to a {@link PersonResponse}.
     *
     * @param entity the entity {@link PersonEntity}
     * @return the converted {@link PersonResponse}
     */
    public PersonResponse toModel(PersonEntity entity) {
        return new PersonResponse(
                entity.getId(),
                entity.getName(),
                entity.getIdentificationNumber(),
                entity.getTaxNumber(),
                entity.getAccountNumber(),
                entity.getBankCode(),
                entity.getIban(),
                entity.getTelephone(),
                entity.getMail(),
                entity.getStreet(),
                entity.getZip(),
                entity.getCity(),
                entity.getCountry(),
                entity.getNote()
        );
    }
}
