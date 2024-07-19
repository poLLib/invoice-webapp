package cz.pollib.entity.filter;

import lombok.Data;

@Data
public class InvoiceFilter {
    private Integer buyerId;
    private Integer sellerId;
    private String product;
    private Long minPrice;
    private Long maxPrice;
    private int limit = 10;
}
