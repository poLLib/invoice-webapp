package cz.pollib.service.common;

import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityProvider {

    private final PersonRepository personRepository;

    public PersonEntityProvider(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Attempts to fetch a person.
     * In case a person with the passed [id] doesn't exist a [{@link EntityNotFoundException}] is thrown.
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws EntityNotFoundException In case a person with the passed [id] isn't found
     */
    public PersonEntity getEntity(Long id) {
        return personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
