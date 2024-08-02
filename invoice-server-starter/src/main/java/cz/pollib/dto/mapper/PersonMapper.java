package cz.pollib.dto.mapper;

import cz.pollib.dto.PersonDTO;
import cz.pollib.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between {@link PersonEntity} and {@link PersonDTO}.
 * Utilizes MapStruct for generating the implementation.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Converts a {@link PersonDTO} to a {@link PersonEntity}.
     *
     * @param source the source {@link PersonDTO}
     * @return the converted {@link PersonEntity}
     */
    PersonEntity toEntity(PersonDTO source);

    /**
     * Converts a {@link PersonEntity} to a {@link PersonDTO}.
     *
     * @param source the source {@link PersonEntity}
     * @return the converted {@link PersonDTO}
     */
    PersonDTO toDTO(PersonEntity source);

    /**
     * Updates an existing {@link PersonDTO} with values from a {@link PersonEntity}.
     *
     * @param source the source {@link PersonEntity}
     * @param target the target {@link PersonDTO} to be updated
     */
    void updateDTO(PersonEntity source, @MappingTarget PersonDTO target);
}
