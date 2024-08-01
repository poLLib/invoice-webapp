package cz.pollib.dto.mapper;

import cz.pollib.dto.PersonDTO;
import cz.pollib.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity toEntity(PersonDTO source);

    PersonDTO toDTO(PersonEntity source);

    void updateDTO(PersonEntity source, @MappingTarget PersonDTO target);
}
