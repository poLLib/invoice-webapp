package cz.pollib.dto.mapper;

import cz.pollib.dto.PersonDTO;
import cz.pollib.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between {@link Person} and {@link PersonDTO}.
 * Utilizes MapStruct for generating the implementation.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Converts a {@link PersonDTO} to a {@link Person}.
     *
     * @param source the source {@link PersonDTO}
     * @return the converted {@link Person}
     */
    Person toEntity(PersonDTO source);

    /**
     * Converts a {@link Person} to a {@link PersonDTO}.
     *
     * @param source the source {@link Person}
     * @return the converted {@link PersonDTO}
     */
    PersonDTO toDTO(Person source);

    /**
     * Updates an existing {@link PersonDTO} with values from a {@link Person}.
     *
     * @param source the source {@link Person}
     * @param target the target {@link PersonDTO} to be updated
     */
    void updateDTO(Person source, @MappingTarget PersonDTO target);
}
