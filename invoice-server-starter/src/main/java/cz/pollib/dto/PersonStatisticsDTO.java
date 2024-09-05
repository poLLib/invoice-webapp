package cz.pollib.dto;

/**
 * Contains statistics for a person.
 * <p>
 * Attributes:
 * - personId: The unique identifier of the person.
 * - personName: The name of the person.
 * - revenue: The total revenue associated with the person.
 */
public class PersonStatisticsDTO {

    private Long personId;
    private String personName;
    private Long revenue;

    public PersonStatisticsDTO() {
    }

    public PersonStatisticsDTO(Long personId, String personName, Long revenue) {
        this.personId = personId;
        this.personName = personName;
        this.revenue = revenue;
    }

    // GETTERs and SETTERs block


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
}
