package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceDTO toDTO(InvoiceEntity source);

    InvoiceEntity toEntity(InvoiceDTO source);

    void updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

    void updateDTO(InvoiceEntity source, @MappingTarget InvoiceDTO target);
}
