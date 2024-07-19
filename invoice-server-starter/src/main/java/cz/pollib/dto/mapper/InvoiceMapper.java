package cz.pollib.dto.mapper;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface InvoiceMapper {

    InvoiceDTO toDTO(InvoiceEntity source);

    InvoiceEntity toEntity(InvoiceDTO source);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

}
