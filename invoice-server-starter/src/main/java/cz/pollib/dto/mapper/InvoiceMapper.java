package cz.pollib.dto.mapper;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between {@link Invoice} and {@link InvoiceDTO}.
 * Utilizes MapStruct for generating the implementation.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface InvoiceMapper {

    /**
     * Converts an {@link Invoice} to an {@link InvoiceDTO}.
     *
     * @param source the source {@link Invoice}
     * @return the converted {@link InvoiceDTO}
     */
    InvoiceDTO toDTO(Invoice source);

    /**
     * Converts an {@link InvoiceDTO} to an {@link Invoice}.
     *
     * @param source the source {@link InvoiceDTO}
     * @return the converted {@link Invoice}
     */
    Invoice toEntity(InvoiceDTO source);

    /**
     * Updates an existing {@link Invoice} with values from an {@link InvoiceDTO}.
     * The buyer and seller fields are ignored in this update.
     *
     * @param source the source {@link InvoiceDTO}
     * @param target the target {@link Invoice} to be updated
     */
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void updateEntity(InvoiceDTO source, @MappingTarget Invoice target);

}
