package cz.itnetwork.entity.filter;

import cz.itnetwork.dto.PersonDTO;
import lombok.Data;

@Data
public class InvoiceFilter {
    private Integer buyerID;
    private Integer sellerID;
    private String product;
    private Long minPrice;
    private Long maxPrice;
    private int limit = 3;
}
