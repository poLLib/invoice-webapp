package cz.pollib.dto.mapper;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between {@link InvoiceEntity} and {@link InvoiceDTO}.
 * Utilizes MapStruct for generating the implementation.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface InvoiceMapper {

    /**
     * Converts an {@link InvoiceEntity} to an {@link InvoiceDTO}.
     *
     * @param source the source {@link InvoiceEntity}
     * @return the converted {@link InvoiceDTO}
     */
    InvoiceDTO toDTO(InvoiceEntity source);

    /**
     * Converts an {@link InvoiceDTO} to an {@link InvoiceEntity}.
     *
     * @param source the source {@link InvoiceDTO}
     * @return the converted {@link InvoiceEntity}
     */
    InvoiceEntity toEntity(InvoiceDTO source);

    /**
     * Updates an existing {@link InvoiceEntity} with values from an {@link InvoiceDTO}.
     * The buyer and seller fields are ignored in this update.
     *
     * @param source the source {@link InvoiceDTO}
     * @param target the target {@link InvoiceEntity} to be updated
     */
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

}
