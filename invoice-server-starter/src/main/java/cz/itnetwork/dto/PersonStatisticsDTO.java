package cz.itnetwork.dto;

import lombok.Data;

@Data
public class PersonStatisticsDTO {
    private Long personId;
    private String personName;
    private Long revenue;
}
